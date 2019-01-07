package by.epam.analyzer.runner;

import by.epam.analyzer.action.Analyzer;
import by.epam.analyzer.entity.Directory;

public class Runner {

	public static void main(String[] args) {
		Directory dir = new Directory("H:\\dir");
		System.out.println(Analyzer.analyzeDirectory(dir));
	}
}
