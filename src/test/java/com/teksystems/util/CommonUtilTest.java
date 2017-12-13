package com.teksystems.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CommonUtilTest {
	private CommonUtil commonUtil;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	File tempFile;

	@Before
	public void beforeMethod() throws IOException {
		commonUtil = new CommonUtil(0);
		tempFile = tempFolder.newFile("test.txt");
		tempFolder.newFolder("testfolder");
	}

	@Test
	public void testListAllFilesRecursively() {
		assertNotNull(tempFile);
		commonUtil.listAllFilesRecursively(tempFile, 0);

	}

	@Test
	public void testIterateListOfFiles() throws IOException {

		File file = tempFolder.newFile("test2.txt");
		List<String> sortedListOfFiles = new ArrayList<>();
		sortedListOfFiles.add("test.txt");
		commonUtil.iterateListOfFiles(sortedListOfFiles, 0, file);
	}

	@Test
	public void testSortFilesByExtensions() {
		String[] listOfFileNames = { "Project 1", "Project 2" };
		List<String> results = commonUtil.sortFilesByExtensions(listOfFileNames);
		assertNotNull(results);
		assertEquals(2, results.size());
		assertEquals("Project 1", results.get(0));

	}

}
