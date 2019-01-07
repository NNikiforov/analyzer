package by.epam.analyzer.action;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import by.epam.analyzer.entity.TextFile;

import java.util.Set;

public class Matcher implements Callable<String> {

	private TextFile firstFile;
	private TextFile secondFile;

	public Matcher(TextFile firstFile, TextFile secondFile) {
		this.firstFile = firstFile;
		this.secondFile = secondFile;
	}

	public static int match(Set<Entry<String, Integer>> fMapping,
			Set<Entry<String, Integer>> sMapping) {
		Set<Entry<String, Integer>> result = new HashSet<>(fMapping);
		result.retainAll(sMapping);
		return result.size();
	}

	@Override
	public String call() throws Exception {
		int similarity = match(firstFile.getMapping(), secondFile.getMapping());
		return firstFile.getName() + " & " + secondFile.getName() + " = "
				+ similarity;
	}
}
