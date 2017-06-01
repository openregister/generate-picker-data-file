package uk.gov;

import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Generator {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String run(String countryJson, String csv) throws IOException {
		JsonNode countryJsonNode = objectMapper.readTree(countryJson);

		Iterator<JsonNode> countries = countryJsonNode.elements();

		ObjectNode resultNode = objectMapper.createObjectNode();

		while (countries.hasNext()) {
			JsonNode country = countries.next();

			JsonNode item = country.get("item").get(0);

			ObjectNode names = objectMapper.createObjectNode();
			names.put("en-GB", item.get("name").textValue());

			ObjectNode meta = objectMapper.createObjectNode();
			meta.put("canonical", true);
			meta.put("canonical-mask", 1);
			meta.put("stable-name", true);

			ObjectNode edgesNode = objectMapper.createObjectNode();
			edgesNode.set("from", objectMapper.createArrayNode());

			ObjectNode countryNode = objectMapper.createObjectNode();
			countryNode.set("names", names);
			countryNode.set("meta", meta);
			countryNode.set("edges", edgesNode);

			String key = country.get("key").textValue();
			resultNode.set("country:" + key, countryNode);
		}

		return resultNode.toString();
	}
}
