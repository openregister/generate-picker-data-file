package uk.gov;

import java.io.IOException;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;

public class GeneratorTest {
	@Test
	public void noCountry() throws Exception {
		String inputCountryJson = "{}";
		String expectedPickerJson = "{}";

		String output = Generator.run(inputCountryJson, "", "country");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountry() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGb();
		String expectedPickerJson = Fixtures.graphOnlyGb();

		String output = Generator.run(inputCountryJson, "", "country");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneOtherName() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGb();
		String expectedPickerJson = Fixtures.getGraphJsonOtherName();

		String output = Generator.run(inputCountryJson, "", "otherName");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void twoCountries() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGbDe();
		String expectedPickerJson = Fixtures.graphOnlyGbDe();

		String output = Generator.run(inputCountryJson, "", "country");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountryWithCsv() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGb();
		String inputCsv = Fixtures.csvOnlyGb();
		String expectedPickerJson = Fixtures.graphWithSynonymsOnlyGb();

		String output = Generator.run(inputCountryJson, inputCsv, "country");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void twoCountriesWithCsv() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGbDe();
		String inputCsv = Fixtures.csvOnlyGbDe();
		String expectedPickerJson = Fixtures.graphWithSynonymsOnlyGbDe();

		String output = Generator.run(inputCountryJson, inputCsv, "country");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void threeRegistersWithCsv() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGbDe();
		String inputTerritoryJson = Fixtures.territoryRegisterOnlyPr();
		String inputUkJson = Fixtures.ukRegisterOnlyWls();
		String inputCsv = Fixtures.csvOnlyGbDe();
		String expectedPickerJson = Fixtures.graphThreeRegistersWithCsv();

		String output = Generator.runMultiple(
			inputCountryJson, inputTerritoryJson, inputUkJson,
			inputCsv
		);

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}
}
