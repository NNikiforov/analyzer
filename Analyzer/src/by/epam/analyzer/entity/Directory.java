package by.epam.analyzer.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {

	private String pathname;

	public Directory() {
	}

	public Directory(String pathname) {
		this.pathname = pathname;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public List<TextFile> getFiles() {
		File directotry = new File(pathname);
		File[] files = directotry.listFiles();
		List<TextFile> result = new ArrayList<>(files.length);

		for (File file : files) {
			result.add(new TextFile(file));
		}

		return result;
	}
}
