package com.teksystems.util;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommonUtil {
	private final int lenghtOfDirectory;

	public CommonUtil(int lenghtOfDirectory) {
		this.lenghtOfDirectory = lenghtOfDirectory;
	}

	/**
	 * This methods list and print all files recursively
	 *
	 * @param dir
	 * @param tabs
	 */
	public void listAllFilesRecursively(File dir, int tabs) {

		if (dir != null) {
			if (dir.isDirectory()) {
				for (int i = 0; i < tabs; i++) {
					System.out.print("  ");
				}
				System.out.println("- Project: " + dir.getName() + " - URL: "
						+ dir.getAbsolutePath().substring(lenghtOfDirectory));
				String listOfFileNames[] = dir.list();
				// sortedList = sort(s);
				List<String> sortedListOfFiles = sortFilesByExtensions(listOfFileNames);
				iterateListOfFiles(sortedListOfFiles, tabs, dir);
			} else {
				System.out.println("Please provide a path to root directory");
			}
		} else {
			System.out.println(dir + " is not a directory");
		}

	}

	/**
	 * Iterate directory and sub directory
	 *
	 * @param sortedListOfFiles
	 * @param tabs
	 * @param dir
	 */
	public void iterateListOfFiles(List<String> sortedListOfFiles, int tabs, File dir) {
		System.out.println("sortedListOfFiles >> 1234"+sortedListOfFiles +"dir >> "+dir);
		for (String item : sortedListOfFiles) {
			tabs++;
			File file = new File(dir + "/" + item);
			if (file.isDirectory()) {
				listAllFilesRecursively(file, tabs);
				tabs--;
			} else {
				printFileOnconsole(file, tabs);
				tabs--;
			}
		}
	}

	/**
	 * This methods sort the list of files by extension
	 *
	 * @param listOfFileNames
	 * @return
	 */
	public List<String> sortFilesByExtensions(String[] listOfFileNames) {
		List<String> orginalList = new CopyOnWriteArrayList<>(Arrays.asList(listOfFileNames));
		Set<String> setOfuniqueExtension = new TreeSet<>();

		for (String item : listOfFileNames) {
			if (item.contains(".")) {
				String[] split = item.split(ReaderUtilConstants.DELIMETER);
				String temp = "." + split[split.length - 1];
				setOfuniqueExtension.add(temp);
			}
		}

		List<String> finalListOfAllFiles = new LinkedList<>();
		setOfuniqueExtension.stream().forEach((s1) -> {
			for (int i = 0; i < orginalList.size(); i++) {
				if (orginalList.get(i).contains(s1)) {
					finalListOfAllFiles.add(orginalList.get(i));
					orginalList.remove(orginalList.get(i));
					i--;
				}
			}
		});

		orginalList.stream().filter((s1) -> (!finalListOfAllFiles.contains(s1))).forEach((s1) -> {
			finalListOfAllFiles.add(s1);
		});
		return finalListOfAllFiles;
	}

	/**
	 * This method prints directory and files on console
	 *
	 * @param file
	 * @param tabs
	 */
	private void printFileOnconsole(File file, int tabs) {
		String fileName = file.getName();
		String[] split = fileName.split(ReaderUtilConstants.DELIMETER);
		for (int i = 0; i < tabs; i++) {
			System.out.print("  ");
		}
		System.out.println("- Document: " + file.getName() + " - Extension: ." + split[split.length - 1] + " - URL: "
				+ file.getAbsolutePath().substring(lenghtOfDirectory));
	}

}
