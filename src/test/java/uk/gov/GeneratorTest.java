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
	public void oneCountry() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGb();
		String expectedPickerJson = fixtures.graphOnlyGb();

		String output = generator.run(inputCountryJson, fixtures.csv);

		assertEquals(expectedPickerJson, output);
		JSONAssert.assertEquals(expectedPickerJson, output, false);
	}

	@Test
	public void multipleCountries() throws Exception {
		String inputCountryJson = fixtures.countryRegisterOnlyGbDe();
		String expectedPickerJson = fixtures.graphOnlyGbDe();

		String output = generator.run(inputCountryJson, fixtures.csv);

		assertEquals(expectedPickerJson, output);
		JSONAssert.assertEquals(expectedPickerJson, output, false);
	}
}
