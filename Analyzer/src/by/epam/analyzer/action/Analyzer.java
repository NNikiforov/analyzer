package by.epam.analyzer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import by.epam.analyzer.entity.Directory;
import by.epam.analyzer.entity.TextFile;

public class Analyzer {

	private static CopyOnWriteArrayList<TextFile> processedFiles = new CopyOnWriteArrayList<>();

	public static String analyzeDirectory(Directory directory) {
		StringBuilder result = new StringBuilder("");
		int threadCount = Runtime.getRuntime().availableProcessors() - 1;
		ExecutorService es = Executors.newFixedThreadPool(threadCount);

		ArrayList<Future<TextFile>> mapperResults = new ArrayList<Future<TextFile>>();
		ArrayList<Future<String>> matcherResults = new ArrayList<Future<String>>();

		directory.getFiles()
				.forEach(x -> mapperResults.add(es.submit(new Mapper(x))));
		while (!mapperResults.isEmpty()) {
			Iterator<Future<TextFile>> it = mapperResults.iterator();
			while (it.hasNext()) {
				Future<TextFile> future = (Future<TextFile>) it.next();
				if (future.isDone()) {
					try {
						TextFile processedFile = future.get();
						processedFiles.add(processedFile);
						int fileCount = processedFiles.size();
						for (int i = 0; i < fileCount - 1; i++) {
							matcherResults.add(es.submit(new Matcher(
									processedFiles.get(i), processedFile)));
						}
						it.remove();
					} catch (InterruptedException | ExecutionException e) {
						System.err.println("Can not read result of callable thread.");
					}
				}
			}
		}
		matcherResults.forEach(action -> {
			try {
				result.append("\n");
				result.append(action.get());
			} catch (InterruptedException | ExecutionException e) {
				System.err.println("Can not read result of callable thread.");
			}
		});
		es.shutdown();
		return result.toString();
	}
}
