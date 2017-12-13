package com.teksystems.directoryreader;

import java.io.File;

import com.teksystems.exception.BaseException;


public interface DirectoryReader {
	public void listAllFilesRecursively(File dir, int tabs) throws BaseException;
}
