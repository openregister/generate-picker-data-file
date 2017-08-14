package uk.gov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class Fixtures {
	public static final ObjectMapper objectMapper = new ObjectMapper();

	public static final String countryRegisterEntryGb = getFile("countryRegisterEntryGb.json");
	public static final String countryRegisterEntryDe = getFile("countryRegisterEntryDe.json");
	public static final String territoryRegisterEntryPr = getFile("territoryRegisterEntryPr.json");
	public static final String ukRegisterEntryWls = getFile("ukRegisterEntryWls.json");
	public static final String csvHeader = getFile("csvHeader.csv");
	public static final String csvEntryGb = getFile("csvEntryGb.csv");
	public static final String csvEntryDe = getFile("csvEntryDe.csv");
	public static final String graphJsonGb = getFile("graphJsonGb.json");
	public static final String graphJsonNymUKGBNI = getFile("graphJsonNymUKGBNI.json");
	public static final String graphJsonNymBrtain = getFile("graphJsonNymBrtain.json");
	public static final String graphJsonNymGB = getFile("graphJsonNymGB.json");
	public static final String graphJsonNymUK = getFile("graphJsonNymUK.json");
	public static final String graphJsonDe = getFile("graphJsonDe.json");
	public static final String graphJsonNymFRG = getFile("graphJsonNymFRG.json");
	public static final String graphJsonNymDeutschland = getFile("graphJsonNymDeutschland.json");
	public static final String graphJsonNymDE = getFile("graphJsonNymDE.json");
	public static final String graphJsonNymBundesrepublik = getFile("graphJsonNymBundesrepublik.json");
	public static final String graphJsonPr = getFile("graphJsonPr.json");
	public static final String graphJsonWls = getFile("graphJsonWls.json");

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

	public static String territoryRegisterOnlyPr() {
		String[] registerEntries = {territoryRegisterEntryPr};
		return joinJsonEntries(registerEntries);
	}

	public static String ukRegisterOnlyWls() {
		String[] registerEntries = {ukRegisterEntryWls};
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
		String[] graphEntries = {graphJsonGb, graphJsonNymUKGBNI, graphJsonNymBrtain, graphJsonNymGB, graphJsonNymUK};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	public static String graphWithSynonymsOnlyGbDe() throws Exception {
		String[] graphEntries = {graphJsonGb, graphJsonNymUKGBNI, graphJsonNymBrtain, graphJsonNymGB, graphJsonNymUK, graphJsonDe, graphJsonNymFRG, graphJsonNymDeutschland, graphJsonNymDE, graphJsonNymBundesrepublik};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	public static String graphThreeRegistersWithCsv() throws Exception {
		String[] graphEntries = {graphJsonGb, graphJsonNymUKGBNI, graphJsonNymBrtain, graphJsonNymGB, graphJsonNymUK, graphJsonDe, graphJsonNymFRG, graphJsonNymDeutschland, graphJsonNymDE, graphJsonNymBundesrepublik, graphJsonPr, graphJsonWls};
		return prettyJson(joinJsonEntries(graphEntries));
	}

	private static String getFile(String fileName){

		String result = "";

		ClassLoader classLoader = Fixtures.class.getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

//  TODO: will be required again when cy is fixed, leaving here as an annoyance
//	public static final String graphJsonGbWithCy = ""
//		+ "\"country:GB\": {"
//			+ "\"names\": {"
//				+ "\"en-GB\": \"United Kingdom\","
//				+ "\"cy\": \"Y Deyrnas Unedig\""
//			+ "},"
//			+ "\"meta\": {"
//				+ "\"canonical\": true,"
//				+ "\"canonical-mask\": 1,"
//				+ "\"display-name\": true,"
//				+ "\"stable-name\": true"
//			+ "},"
//			+ "\"edges\": {"
//				+ "\"from\": []"
//			+ "}"
//		+ "}";

//	public static final String graphJsonDeWithCy = ""
//		+ "\"country:DE\": {"
//		+ "\"names\": {"
//		+ "\"en-GB\": \"Germany\","
//		+ "\"cy\": \"Yr Almaen\""
//		+ "},"
//		+ "\"meta\": {"
//		+ "\"canonical\": true,"
//		+ "\"canonical-mask\": 1,"
//		+ "\"display-name\": true,"
//		+ "\"stable-name\": true"
//		+ "},"
//		+ "\"edges\": {"
//		+ "\"from\": []"
//		+ "}"
//		+ "}";

}
