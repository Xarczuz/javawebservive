package javaweb;

import autogen.CountryInfoService;
import autogen.CountryInfoServiceSoapType;

public class Del_C_VG {
	/**
	 * Auto generated classes using wsimport to get the data
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		CountryInfoService service = new CountryInfoService();
		CountryInfoServiceSoapType server = service.getCountryInfoServiceSoap();
		String[] arr = new String[] { "SE", "US", "JP", "HU", "AM", "IS", "CH", "CA" };
		for (String string : arr) {
			autogen.TCountryInfo fc = server.fullCountryInfo(string);

			System.out.println("Country: " + fc.getSName());
			System.out.println("Capital: " + fc.getSCapitalCity());
			System.out.println("Currency: " + fc.getSCurrencyISOCode());
			fc.getLanguages().getTLanguage().forEach(s -> System.out.println("Langues: " + s.getSName()));
			System.out.println();
		}
	}
}
