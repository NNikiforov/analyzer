package by.epam.analyzer.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import by.epam.analyzer.entity.TextFile;

public class Mapper implements Callable<TextFile> {

	private TextFile file;

	public Mapper(TextFile file) {
		this.file = file;
	}

	@Override
	public TextFile call() throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(
				convertStreamToString(file.getInputStream()));
		HashMap<String, Integer> map = new HashMap<>(tokenizer.countTokens());
		while (tokenizer.hasMoreTokens()) {
			String lexem = tokenizer.nextToken();
			if (map.containsKey(lexem)) {
				map.put(lexem, map.get(lexem) + 1);
			} else {
				map.put(lexem, 1);
			}
		}
		file.setMapping(map.entrySet());
		return file;
	}

	private static String convertStreamToString(InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
