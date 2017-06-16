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

	private static final Fetcher fetcher = new Fetcher();

	private static final Generator generator = new Generator();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);

		String countryRegisterJson = "{}";
		String territoryRegisterJson = "{}";
		String ukRegisterJson = "{}";
		String csv = "";
		try {
			countryRegisterJson = fetcher.get("https://country.register.gov.uk/records.json?page-size=500");
			territoryRegisterJson = fetcher.get("https://territory.register.gov.uk/records.json?page-size=500");
			ukRegisterJson = fetcher.get("https://uk.discovery.openregister.org/records.json?page-size=500");
		} catch (IOException err) {
			LOG.error("HTTP request IOException: " + err);
		}

		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			File file = new File(classLoader.getResource("location-picker-data.csv").getFile());
			if (file != null) {
				String csv2 = new String(Files.readAllBytes(file.toPath()));
			}
		} catch (IOException err) {
			LOG.error("CSV read IOException: " + err);
		} catch (Exception err) {
			LOG.error("CSV read Exception: " + err);
		}

		String responseBody = generator.runMultiple(
			countryRegisterJson, territoryRegisterJson, ukRegisterJson,
			csv
		);

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
