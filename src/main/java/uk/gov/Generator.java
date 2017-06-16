package uk.gov;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Generator {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectNode createEntryNode(
		String gbName, String cyName,
		boolean isCanonical, boolean isStable,
		String[] from
	) {
		ObjectNode names = objectMapper.createObjectNode();
		names.put("en-GB", gbName);
		if (cyName.length() > 0) {
			names.put("cy", cyName);
		} else {
			names.put("cy", false);
		}

		ObjectNode meta = objectMapper.createObjectNode();
		meta.put("canonical", isCanonical);
		meta.put("canonical-mask", 1);
		meta.put("stable-name", isStable);

		ObjectNode edgesNode = objectMapper.createObjectNode();
		ArrayNode fromNode = objectMapper.createArrayNode();
		for (String fromStr : from) { fromNode.add(fromStr); }
		edgesNode.set("from", fromNode);

		ObjectNode entryNode = objectMapper.createObjectNode();
		entryNode.set("names", names);
		entryNode.set("meta", meta);
		entryNode.set("edges", edgesNode);

		return entryNode;
	}

	public static void generateEntries(JsonNode entriesJsonNode, ObjectNode resultNode, String type) {
		Iterator<JsonNode> entries = entriesJsonNode.elements();

		while (entries.hasNext()) {
			JsonNode entry = entries.next();
			JsonNode item = entry.get("item").get(0);
			String key = entry.get("key").textValue();

			ObjectNode entryNode = createEntryNode(
				item.get("name").textValue(), "",
				true, true,
				new String[]{}
			);

			resultNode.set(type + ":" + key, entryNode);
		}
	}

	public static void generateNyms(List<CSVRecord> csvList, ObjectNode resultNode) {
		for (CSVRecord record : csvList) {
			String key = record.get("Andy's key");
			JsonNode canonicalNode = resultNode.get(key);
			if (canonicalNode != null) {
				String officialName = record.get("Official-name");
				if (officialName != null) {
					ObjectNode officialNameNode = createEntryNode(
						officialName, "",
						false, true,
						new String[]{key}
					);

					resultNode.set("nym:" + officialName, officialNameNode);
				}

				String code = record.get("Code");
				if (code != null) {
					ObjectNode codeNode = createEntryNode(
						code, "",
						false, false,
						new String[]{key}
					);

					resultNode.set("nym:" + code, codeNode);
				}

				String welshName = record.get("Welsh");
				if (welshName != null) {
					String englishName = resultNode.get(key).get("names").get("en-GB").textValue();
					ObjectNode replacedCountryNode = createEntryNode(
						englishName, welshName,
						true, true,
						new String[]{}
					);

					resultNode.set(key, replacedCountryNode);
				}

				// Any and all of these field names can be out of bounds.
				String passportNyms = "";
				try { passportNyms = record.get("Passport applicant typos"); } catch (IllegalArgumentException err) {}
				String endonymNyms = "";
				try { endonymNyms = record.get("Endonyms"); } catch (IllegalArgumentException err) {}
				String combinedNyms = "";
				try { combinedNyms = record.get("Combined Synonyms"); } catch (IllegalArgumentException err) {}
				String dwpNyms = "";
				try { dwpNyms = record.get("DWP Synonyms"); } catch (IllegalArgumentException err) {}

				String nyms = ""
					+ passportNyms + ", "
					+ endonymNyms + ", "
					+ combinedNyms + ", "
					+ dwpNyms;

				for (String nym : nyms.replace("\"", "").split(",\\s*")) {
					if (nym.length() > 0) {
						ObjectNode nymNode = createEntryNode(
							nym, "",
							false, false,
							new String[]{key}
						);

						resultNode.set("nym:" + nym, nymNode);
					}
				}
			}
		}
	}

	public static JsonNode parseEntriesJson(String entriesJson) {
		try {
			return objectMapper.readTree(entriesJson);
		} catch(IOException err) {
			return null;
		}
	}

	public static List<CSVRecord> parseCsv(String csv) {
		try {
			CSVFormat format = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord();
			CSVParser parser = CSVParser.parse(csv, format);
			return parser.getRecords();
		} catch (IOException err) {
			return null;
		}
	}

	public static String runMultiple(Map<String, String> typeToEntriesJson, String csv) {
		ObjectNode resultNode = objectMapper.createObjectNode();

		Set<String> types = typeToEntriesJson.keySet();

		for (String type : types) {
			String entriesJson = typeToEntriesJson.get(type);
			JsonNode entriesJsonNode = parseEntriesJson(entriesJson);

			if (entriesJsonNode != null) {
				generateEntries(entriesJsonNode, resultNode, "country");
			}
		}

		List<CSVRecord> csvList = parseCsv(csv);
		if (csvList != null) {
			generateNyms(csvList, resultNode);
		}

		return resultNode.toString();
	}

	public static String run(String countriesJson, String csv) {
		ObjectNode resultNode = objectMapper.createObjectNode();

		JsonNode countriesJsonNode = parseEntriesJson(countriesJson);
		if (countriesJsonNode != null) {
			generateEntries(countriesJsonNode, resultNode, "country");
		}

		List<CSVRecord> csvList = parseCsv(csv);
		if (csvList != null) {
			generateNyms(csvList, resultNode);
		}

		return resultNode.toString();
	}
}
