package com.serverless;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;

import uk.gov.Generator;
import uk.gov.Fetcher;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FetchHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private static final Logger LOG = Logger.getLogger(FetchHandler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);

		String countryRegisterJson = "{}";
		String territoryRegisterJson = "{}";
		String ukRegisterJson = "{}";
		String synonymCsv = "";
		try {
			countryRegisterJson = Fetcher.get("https://country.register.gov.uk/records.json?page-size=500");
			LOG.info("Country Register string size: " + countryRegisterJson.length());
			territoryRegisterJson = Fetcher.get("https://territory.register.gov.uk/records.json?page-size=500");
			LOG.info("Territory Register string size: " + territoryRegisterJson.length());
			ukRegisterJson = Fetcher.get("https://uk.discovery.openregister.org/records.json?page-size=500");
			LOG.info("UK Register string size: " + ukRegisterJson.length());
			synonymCsv = Fetcher.get("https://raw.githubusercontent.com/openregister/generate-picker-data-file/master/src/main/resources/location-picker-data.csv");
			LOG.info("Synonym CSV string size: " + synonymCsv.length());
		} catch (IOException err) {
			LOG.error("HTTP request IOException: " + err);
		}

		String responseBody = "{}";
		try {
			responseBody = Generator.runMultiple(
				countryRegisterJson, territoryRegisterJson, ukRegisterJson,
				synonymCsv
			);
		} catch (IOException err) {
			LOG.error("Generator.runMultiple IOException: " + err);
		}

		Map<String, String> headers = new HashMap<>();
		headers.put("X-Powered-By", "AWS Lambda & Serverless");
		headers.put("Content-Type", "application/json");

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setRawBody(responseBody)
				.setHeaders(headers)
				.build();
	}
}
