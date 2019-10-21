package com.ihsinformatics.practice4.entrypoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/*
 * @author qasim.ali@ihsinformatics.com
 * */

public class CodeEstimationProgram {

	public static long lineOfCode;

	public static void main(String[] args) {

		FetchSourceFiles fetchingFiles = new FetchSourceFiles();

		System.out.println("-------------------------LIST OF FILE PATHS-------------------------");
		List<String> filesPaths = fetchingFiles.fetchAllFiles("D:\\Java Projects\\Estimation Project", ".java");

		if (filesPaths.size() == 0) {
			System.out.println("There isn't any file present");
		} else {
			filesPaths.forEach((x) -> {
				startCounting(x);
			});
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println("No Of Lines: " + lineOfCode);
	}

	/*
	 * This method will take file path
	 * 
	 * @param String file path of the source code
	 */
	private static void startCounting(String filePath) {
		// Location of file to read
		File file = new File(filePath);

		try {

			Scanner scanner = new Scanner(file);
			// scanner.useDelimiter("\\s+");
			String line;
			while (scanner.hasNext()) {
				line = scanner.nextLine();

				if (line.contains(";") || line.contains("}")) {
					if (line.startsWith("package ") && line.endsWith(";")) {
						// Package Statement isn't in lineOfCode
					} else if (line.startsWith("import ") && line.endsWith(";")) {
						// Import Statements aren't in lineOfCode
					} else if (line.startsWith("//")) {
						// Single Line Comments aren't in lineOfCode
					} else if (line.contains("for(")) {
						if (line.contains(";") && line.contains(")"))
							lineOfCode++;
					} else {
						lineOfCode++;
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
