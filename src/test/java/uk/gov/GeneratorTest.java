package uk.gov;

import java.io.IOException;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skyscreamer.jsonassert.JSONAssert;

public class GeneratorTest {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static final String gbRegisterJson = ""
		+ "\"GB\": {"
			+ "\"index-entry-number\": \"6\","
			+ "\"entry-number\": \"6\","
			+ "\"entry-timestamp\": \"2016-04-05T13:23:05Z\","
			+ "\"key\": \"GB\","
			+ "\"item\": ["
				+ "{"
					+ "\"country\": \"GB\","
					+ "\"official-name\": \"The United Kingdom of Great Britain and Northern Ireland\","
					+ "\"name\": \"United Kingdom\","
					+ "\"citizen-names\": \"Briton;British citizen\""
				+ "}"
			+ "]"
		+ "}";

	private static final String deRegisterJson = ""
		+ "\"DE\": {"
			+ "\"index-entry-number\": \"71\","
			+ "\"entry-number\": \"71\","
			+ "\"entry-timestamp\": \"2016-04-05T13:23:05Z\","
			+ "\"key\": \"DE\","
			+ "\"item\": ["
				+ "{"
					+ "\"country\": \"DE\","
					+ "\"official-name\": \"The Federal Republic of Germany\","
					+ "\"name\": \"Germany\","
					+ "\"start-date\": \"1990-10-03\","
					+ "\"citizen-names\": \"German\""
				+ "}"
			+ "]"
		+ "}";

	private static final String csv = "";

	private static final String gbGraphJson = ""
		+ "\"country:GB\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"United Kingdom\""
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": true,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": true"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": []"
			+ "}"
		+ "}";

	private static final String deGraphJson = ""
		+ "\"country:DE\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Germany\""
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": true,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": true"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": []"
			+ "}"
		+ "}";

	private static Generator generator = new Generator();

	// Turns a list of register JSON strings into a big JSON object with the strings as keys.
	private static String inputJson(String[] registerEntries) {
		return "{" + String.join(",", registerEntries) + "}";
	}

	// Turns a list of graph file JSON strings into a big JSON object with the strings as keys.
	private static String expectedJson(String[] graphEntries) throws Exception {
		String joinedJsons = String.join(",", graphEntries);
		// Pipe through Jackson so we get a JSON string with consistent whitespacing and ordering.
		return objectMapper.readTree("{" + joinedJsons + "}").toString();
	}

	@Test
	public void oneCountry() throws Exception {
		String[] registerEntries = {gbRegisterJson};
		String input = inputJson(registerEntries);

		String[] graphEntries = {gbGraphJson};
		String expectedPickerJson = expectedJson(graphEntries);

		String output = generator.run(input, csv);

		assertEquals(expectedPickerJson, output);
		JSONAssert.assertEquals(expectedPickerJson, output, false);
	}

	@Test
	public void multipleCountries() throws Exception {
		String[] registerEntries = {gbRegisterJson, deRegisterJson};
		String input = inputJson(registerEntries);

		String[] graphEntries = {gbGraphJson, deGraphJson};
		String expectedPickerJson = expectedJson(graphEntries);

		String output = generator.run(input, csv);

		assertEquals(expectedPickerJson, output);
		JSONAssert.assertEquals(expectedPickerJson, output, false);
	}
}
