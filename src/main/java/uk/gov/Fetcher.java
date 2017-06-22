package uk.gov;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class Fetcher {
	public static String get(String urlToRead) throws IOException {
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		return IOUtils.toString(conn.getInputStream(), "UTF-8");
	}
}
