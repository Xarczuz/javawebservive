package javaweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Del_B_G2 {

	public static void main(String[] args) {
		String adress = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		URL url;
		try {
			url = new URL(adress);// Creates the URL object
			HttpURLConnection con = (HttpURLConnection) url.openConnection();// Create the instance does not establish
																				// // connection with the network.
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter writer = null;
			try {
				writer = new OutputStreamWriter(con.getOutputStream());
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
						+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n"
						+ "  <SOAP-ENV:Body>\r\n" + "    <ns1:FullCountryInfo>\r\n"
						+ "      <ns1:sCountryISOCode>US</ns1:sCountryISOCode>\r\n" + "    </ns1:FullCountryInfo>\r\n"
						+ "  </SOAP-ENV:Body>\r\n" + "</SOAP-ENV:Envelope>\r\n" + "");
			} finally {
				if (writer != null)
					try {
						writer.close();
					} catch (IOException logOrIgnore) {
					}
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String res = null;
			while ((res = reader.readLine()) != null) {

				System.out.println(res);

			}
		} catch (MalformedURLException e) {// Catch errors
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
