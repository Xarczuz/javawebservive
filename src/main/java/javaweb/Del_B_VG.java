package javaweb;

import org.oorsprong.websamples.TCountryInfo;
import org.oorsprong.websamples_countryinfo.CountryInfoService;

public class Del_B_VG {

	public static void main(String[] args) {
		CountryInfoService cs = new CountryInfoService();
		String[] arr = new String[] { "SE", "US", "JP", "HU" };

		for (String string : arr) {
			TCountryInfo fc = cs.getCountryInfoServiceSoap().fullCountryInfo(string);

			System.out.println("Country: " + fc.getSName());
			System.out.println("Capital: " + fc.getSCapitalCity());
			System.out.println("Currency: " + fc.getSCurrencyISOCode());
			fc.getLanguages().getTLanguage().forEach(s -> System.out.println(s.getSName()));
			System.out.println();
		}
	}

}
