package uk.gov;

import java.io.File;
import java.nio.file.Files;

public class FileLoader {
	public static String get(String path) throws Exception {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		return new String(Files.readAllBytes(file.toPath()));
	}
}
