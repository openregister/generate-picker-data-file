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

	public static final String csvHeader = ""
		+ "Andy's key,Type,Name,Official-name,Code,Excluded,Territory belongs to,Territory belongs to code,Welsh,Passport applicant typos,Endonyms,Combined Synonyms,DWP Synonyms\n";

	public static final String csvEntryGb = ""
		+ "country:GB,country,United Kingdom,The United Kingdom of Great Britain and Northern Ireland,GB,,,,Y Deyrnas Unedig,\"Brtain\",\"UK\",\n";

	public static final String csvEntryDe = ""
		+ "country:DE,country,Germany,The Federal Republic of Germany,DE,,,,Yr Almaen,,Deutschland,\"Bundesrepublik\",\n";

	public static final String graphJsonGb = ""
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

	public static final String graphJsonDe = ""
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
	public static String joinRegisterEntries(String[] registerEntries) {
		return "{" + String.join(",", registerEntries) + "}";
	}

	public static String joinCsvEntries(String [] csvEntries) {
		return String.join("\n", csvEntries);
	}

	// Pipe through Jackson so we get a JSON string with consistent whitespacing and ordering.
	public static String prettyJson(String json) throws Exception {
		return objectMapper.readTree(json).toString();
	}

	public static String countryRegisterOnlyGb () {
		String[] registerEntries = {countryRegisterEntryGb};
		return joinRegisterEntries(registerEntries);
	}

	public static String countryRegisterOnlyGbDe () {
		String[] registerEntries = {countryRegisterEntryGb, countryRegisterEntryDe};
		return joinRegisterEntries(registerEntries);
	}

	public static String csvOnlyGb () {
		String [] csvEntries = {csvHeader, csvEntryGb};
		return joinCsvEntries(csvEntries);
	}

	public static String csvOnlyGbDe () {
		String [] csvEntries = {csvHeader, csvEntryGb, csvEntryDe};
		return joinCsvEntries(csvEntries);
	}

	public static String graphOnlyGb () throws Exception {
		String[] graphEntries = {graphJsonGb};
		return prettyJson(joinRegisterEntries(graphEntries));
	}

	public static String graphOnlyGbDe () throws Exception {
		String[] graphEntries = {graphJsonGb, graphJsonDe};
		return prettyJson(joinRegisterEntries(graphEntries));
	}
}
