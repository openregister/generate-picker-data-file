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
		+ "Andy's key,Type,Name,Official-name,Code,Excluded,Territory belongs to,Territory belongs to code,Welsh,Passport applicant typos,Endonyms,Combined Synonyms,DWP Synonyms";

	public static final String csvEntryGb = ""
		+ "country:GB,country,United Kingdom,The United Kingdom of Great Britain and Northern Ireland,GB,,,,Y Deyrnas Unedig,\"Brtain\",\"UK\",";

	public static final String csvEntryDe = ""
		+ "country:DE,country,Germany,The Federal Republic of Germany,DE,,,,Yr Almaen,,Deutschland,\"Bundesrepublik\",";

	public static final String graphJsonGb = ""
		+ "\"country:GB\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"United Kingdom\","
				+ "\"cy\": false"
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

	public static final String graphJsonGbWithCy = ""
		+ "\"country:GB\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"United Kingdom\","
				+ "\"cy\": \"Y Deyrnas Unedig\""
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

	public static final String graphJsonNymUKGBNI = ""
		+ "\"nym:The United Kingdom of Great Britain and Northern Ireland\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"The United Kingdom of Great Britain and Northern Ireland\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": true"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:GB\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymBrtain = ""
		+ "\"nym:Brtain\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Brtain\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:GB\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymGB = ""
		+ "\"nym:GB\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"GB\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:GB\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymUK = ""
		+ "\"nym:UK\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"UK\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:GB\"]"
			+ "}"
		+ "}";

	public static final String graphJsonDe = ""
		+ "\"country:DE\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Germany\","
				+ "\"cy\": false"
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

	public static final String graphJsonDeWithCy = ""
		+ "\"country:DE\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Germany\","
				+ "\"cy\": \"Yr Almaen\""
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

	public static final String graphJsonNymFRG = ""
		+ "\"nym:The Federal Republic of Germany\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"The Federal Republic of Germany\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": true"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:DE\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymDeutschland = ""
		+ "\"nym:Deutschland\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Deutschland\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:DE\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymDE = ""
		+ "\"nym:DE\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"DE\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:DE\"]"
			+ "}"
		+ "}";

	public static final String graphJsonNymBundesrepublik = ""
		+ "\"nym:Bundesrepublik\": {"
			+ "\"names\": {"
				+ "\"en-GB\": \"Bundesrepublik\","
				+ "\"cy\": false"
			+ "},"
			+ "\"meta\": {"
				+ "\"canonical\": false,"
				+ "\"canonical-mask\": 1,"
				+ "\"stable-name\": false"
			+ "},"
			+ "\"edges\": {"
				+ "\"from\": [\"country:DE\"]"
			+ "}"
		+ "}";

	// Turns a list of JSON entries into a big JSON object with the strings as keys.
	public static String joinJsonEntries(String[] registerEntries) {
		return "{" + String.join(",", registerEntries) + "}";
	}

	public static String joinCsvEntries(String [] csvEntries) {
		return String.join("\n", csvEntries);
	}

	// Pipe through Jackson so we get a JSON string with consistent whitespacing and ordering.
	public static String prettyJson(String json) throws Exception {
		return objectMapper.readTree(json).toString();
	}

	public static String countryRegisterOnlyGb() {
		String[] registerEntries = {countryRegisterEntryGb};
		return joinJsonEntries(registerEntries);
	}

	public static String countryRegisterOnlyGbDe() {
		String[] registerEntries = {countryRegisterEntryGb, countryRegisterEntryDe};
		return joinJsonEntries(registerEntries);
	}

	public static String csvOnlyGb() {
		String [] csvEntries = {csvHeader, csvEntryGb};
		return joinCsvEntries(csvEntries);
	}

	public static String csvOnlyGbDe() {
		String [] csvEntries = {csvHeader, csvEntryGb, csvEntryDe};
		return joinCsvEntries(csvEntries);
	}

	public static String graphOnlyGb() throws Exception {
		String[] graphEntries = {graphJsonGb};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	public static String graphOnlyGbDe() throws Exception {
		String[] graphEntries = {graphJsonGb, graphJsonDe};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	public static String graphWithSynonymsOnlyGb() throws Exception {
		String[] graphEntries = {graphJsonGbWithCy, graphJsonNymUKGBNI, graphJsonNymBrtain, graphJsonNymGB, graphJsonNymUK};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	public static String graphWithSynonymsOnlyGbDe() throws Exception {
		String[] graphEntries = {graphJsonGbWithCy, graphJsonNymUKGBNI, graphJsonNymBrtain, graphJsonNymGB, graphJsonNymUK, graphJsonDeWithCy, graphJsonNymFRG, graphJsonNymDeutschland, graphJsonNymDE, graphJsonNymBundesrepublik};
		return prettyJson(joinJsonEntries(graphEntries));
	}
}
