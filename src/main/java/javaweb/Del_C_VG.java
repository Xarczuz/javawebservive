package javaweb;

import autogen.CountryInfoService;
import autogen.CountryInfoServiceSoapType;

public class Del_C_VG {
	public static void main(String[] args) {
		 
		CountryInfoService service = new CountryInfoService();
 
		CountryInfoServiceSoapType server = service.getCountryInfoServiceSoap();
 
		System.out.println(server.listOfContinentsByCode().getTContinent().iterator().next().getSName());
 
	}
}
