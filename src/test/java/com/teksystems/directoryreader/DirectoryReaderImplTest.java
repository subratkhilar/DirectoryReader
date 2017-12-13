package com.teksystems.directoryreader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.teksystems.exception.BaseException;

public class DirectoryReaderImplTest {

	private DirectoryReader directoryReader;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void beforeMethod() throws IOException {
		directoryReader = new DirectoryReaderImpl(0);

	}

	@Test
	public void testListAllFilesRecursively() throws Exception {
		File file = folder.newFolder("testfolder");
		directoryReader.listAllFilesRecursively(file, 0);
		assertTrue(file.exists());

	}

	@Test(expected = BaseException.class)
	public void testException() throws Exception {
		File rootDirectory = new File("");
		directoryReader.listAllFilesRecursively(rootDirectory, 0);
	}

}
