package com.serverless;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

		String key = "location-picker-data.json";

		AmazonS3 s3 = new AmazonS3Client();
		Region usEast1 = Region.getRegion(Regions.US_EAST_1);
		s3.setRegion(usEast1);

		try {
			String dataFileContent = Generator.runMultiple(
				countryRegisterJson, territoryRegisterJson, ukRegisterJson,
				synonymCsv
			);

			s3.putObject(new PutObjectRequest("write-to-s3-test", key, createSampleFile(dataFileContent)));
		} catch (IOException err) {
			LOG.error("Generator.runMultiple IOException: " + err);
		}

		Map<String, String> headers = new HashMap<>();
		headers.put("X-Powered-By", "AWS Lambda & Serverless");
		headers.put("Content-Type", "application/json");

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setRawBody("ok")
				.setHeaders(headers)
				.build();
	}

	private static File createSampleFile(String content) throws IOException {
		File file = File.createTempFile("aws-java-sdk-", ".txt");
		file.deleteOnExit();

		Writer writer = new OutputStreamWriter(new FileOutputStream(file));
		writer.write(content);
		writer.close();

		return file;
	}
}
