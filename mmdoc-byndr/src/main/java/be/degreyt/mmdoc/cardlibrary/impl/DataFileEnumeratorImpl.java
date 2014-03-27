package be.degreyt.mmdoc.cardlibrary.impl;

import be.degreyt.mmdoc.cardlibrary.DataFileEnumerator;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class DataFileEnumeratorImpl implements DataFileEnumerator {

    private static final Pattern FILE_NAME_PATTERN = Pattern.compile(".*\\.xml");

    @Override
    public Stream<File> dataFiles(String rootPath) {
        File rootDirectory = new File(rootPath);
        if (!rootDirectory.isDirectory()) {
            throw new RuntimeException("Invalid path " + rootDirectory.getAbsolutePath());
        }
        File[] files = rootDirectory.listFiles((dir, name) -> name != null && FILE_NAME_PATTERN.matcher(name).matches());
        return Arrays.stream(files);
    }
}
