package uk.gov;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Fixtures {
	public static final ObjectMapper objectMapper = new ObjectMapper();

	public static final String countryRegisterEntryGb = ""
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

	public static final String countryRegisterEntryDe = ""
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

	public static final String csv = "";

	public static final String gbGraphJson = ""
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

	public static final String deGraphJson = ""
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

	// Turns a list of JSON entries into a big JSON object with the strings as keys.
	public static String joinEntries(String[] registerEntries) {
		return "{" + String.join(",", registerEntries) + "}";
	}

	// Pipe through Jackson so we get a JSON string with consistent whitespacing and ordering.
	public static String prettyJson(String json) throws Exception {
		return objectMapper.readTree(json).toString();
	}

	public static String countryRegisterOnlyGb () {
		String[] registerEntries = {countryRegisterEntryGb};
		return joinEntries(registerEntries);
	}

	public static String countryRegisterOnlyGbDe () {
		String[] registerEntries = {countryRegisterEntryGb, countryRegisterEntryDe};
		return joinEntries(registerEntries);
	}

	public static String graphOnlyGb () throws Exception {
		String[] graphEntries = {gbGraphJson};
		return prettyJson(joinEntries(graphEntries));
	}

	public static String graphOnlyGbDe () throws Exception {
		String[] graphEntries = {gbGraphJson, deGraphJson};
		return prettyJson(joinEntries(graphEntries));
	}
}
