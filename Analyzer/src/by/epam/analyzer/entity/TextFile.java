package by.epam.analyzer.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.HashSet;
import java.util.Set;

public class TextFile {

	private File file;
	private Set<Entry<String, Integer>> mapping = new HashSet<>();

	public TextFile() {
	}

	public TextFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return file.getName();
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(file);
	}

	public Set<Entry<String, Integer>> getMapping() {
		return mapping;
	}

	public void setMapping(Set<Entry<String, Integer>> mapping) {
		this.mapping = mapping;
	}
}
