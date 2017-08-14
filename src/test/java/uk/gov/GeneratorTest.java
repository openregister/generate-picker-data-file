package uk.gov;

import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;

public class GeneratorTest {

	@Test
	public void noCountry() throws Exception {
		String inputCountryJson = "{}";
		String expectedPickerJson = "{}";

		String output = Generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountry() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGb();
		String expectedPickerJson = Fixtures.graphOnlyGb();

		String output = Generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void twoCountries() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGbDe();
		String expectedPickerJson = Fixtures.graphOnlyGbDe();

		String output = Generator.run(inputCountryJson, "");

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void oneCountryWithCsv() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGb();
		String inputCsv = Fixtures.csvOnlyGb();
		String expectedPickerJson = Fixtures.graphWithSynonymsOnlyGb();

		String output = Generator.run(inputCountryJson, inputCsv);

		JSONAssert.assertEquals(expectedPickerJson, output, true);
	}

	@Test
	public void twoCountriesWithCsv() throws Exception {
		String inputCountryJson = Fixtures.countryRegisterOnlyGbDe();
		String inputCsv = Fixtures.csvOnlyGbDe();
		String expectedPickerJson = Fixtures.graphWithSynonymsOnlyGbDe();

		String output = Generator.run(inputCountryJson, inputCsv);

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
