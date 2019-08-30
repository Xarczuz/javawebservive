package javaweb;

import org.oorsprong.websamples_countryinfo.CountryInfoService;

public class Del_B_VG {

	public static void main(String[] args) {
		CountryInfoService cs = new CountryInfoService();

		System.out.println(cs.getCountryInfoServiceSoap().capitalCity("SE"));
		System.out.println(cs.getCountryInfoServiceSoap().countryName("SE"));
		System.out.println(cs.getCountryInfoServiceSoap().countryCurrency("SE").getSName());
		System.out.println(cs.getCountryInfoServiceSoap().countryCurrency("SE").getSISOCode());
	}

}
