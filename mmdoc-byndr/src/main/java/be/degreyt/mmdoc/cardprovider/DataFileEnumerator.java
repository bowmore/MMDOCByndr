package be.degreyt.mmdoc.cardprovider;

import java.io.File;
import java.util.stream.Stream;

public interface DataFileEnumerator {

    Stream<File> dataFiles(String rootPath);
}
