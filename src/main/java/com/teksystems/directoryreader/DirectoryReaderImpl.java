package com.teksystems.directoryreader;

import java.io.File;
import java.util.List;

import com.teksystems.exception.BaseException;
import com.teksystems.util.CommonUtil;

public class DirectoryReaderImpl implements DirectoryReader {

	private final int lenghtOfDirectory;
	private CommonUtil commonUtil;

	public DirectoryReaderImpl(int lenghtOfDirectory) {
		this.lenghtOfDirectory = lenghtOfDirectory;
	}

	/**
	 * This methods list and print all files recursively
	 *
	 * @param dir
	 * @param tabs
	 * @throws DirectoryNotFoundException
	 */
	@Override
	public void listAllFilesRecursively(File dir, int tabs) throws BaseException {
		if (dir != null) {
			commonUtil = new CommonUtil(lenghtOfDirectory);
			if (dir.isDirectory()) {
				for (int i = 0; i < tabs; i++) {
					System.out.print("  ");
				}
				System.out.println("- Project: " + dir.getName() + " - URL: "
						+ dir.getAbsolutePath().substring(lenghtOfDirectory));
				String listOfFileNames[] = dir.list();
				// sortedList = sort(s);
				List<String> sortedListOfFiles = commonUtil.sortFilesByExtensions(listOfFileNames);
				commonUtil.iterateListOfFiles(sortedListOfFiles, tabs, dir);
			} else {
				System.out.println("Please provide a path to root directory");
				throw new BaseException("Directory can not be empty");
			}
		} else {
			System.out.println(dir + " is not a directory");

		}

	}

}
