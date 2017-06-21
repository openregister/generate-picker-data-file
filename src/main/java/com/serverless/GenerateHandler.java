package com.serverless;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uk.gov.Generator;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GenerateHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(GenerateHandler.class);

	private static final Generator generator = new Generator();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);

		String postJson = input.get("body").toString();

		String responseBody = "{}";
		try {
			responseBody = generator.run(postJson, "");
		} catch (IOException err) {
			LOG.error("generator.run IOException: " + err);
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
