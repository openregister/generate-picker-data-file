package uk.gov;

import java.io.IOException;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;

public class GeneratorTest {
	private static Generator generator = new Generator();
	private static Fixtures fixtures = new Fixtures();

	@Test
	public void noCountry() throws Exception {
		String inputCountryJson = "{}";
		String expectedPickerJson = "{}";

		String output = generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountry() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGb();
		String expectedPickerJson = fixtures.graphOnlyGb();

		String output = generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void multipleCountries() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGbDe();
		String expectedPickerJson = fixtures.graphOnlyGbDe();

		String output = generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountryWithCsv() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGb();
		String inputCsv = fixtures.csvOnlyGb();
		String expectedPickerJson = fixtures.graphWithSynonymsOnlyGb();

		String output = generator.run(inputCountryJson, inputCsv);

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void multipleCountriesWithCsv() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGbDe();
		String inputCsv = fixtures.csvOnlyGbDe();
		String expectedPickerJson = fixtures.graphWithSynonymsOnlyGbDe();

		String output = generator.run(inputCountryJson, inputCsv);

		JSONAssert.assertEquals(expectedPickerJson, output, false);
	}
}
