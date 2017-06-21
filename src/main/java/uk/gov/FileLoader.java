package uk.gov;

import java.io.File;
import java.nio.file.Files;

class FileLoader {
	public static String get(String path) {
		String result = "";
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			File file = new File(classLoader.getResource(path).getFile());
			result = new String(Files.readAllBytes(file.toPath()));
		} catch (Exception err) {}
		return result;
	}
}
