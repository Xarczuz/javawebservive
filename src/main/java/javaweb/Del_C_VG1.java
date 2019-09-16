package javaweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Uses the http protocol to send the xml message to the server and prints the
 * response
 * 
 * @author chjun
 *
 */
public class Del_C_VG1 {

	public static void main(String[] args) throws SOAPException {

		String ISOCODE = "US";

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
				// Sends the xml soap message
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
						+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n"
						+ "  <SOAP-ENV:Body>\r\n" + "    <ns1:FullCountryInfo>\r\n" + "      <ns1:sCountryISOCode>"
						+ ISOCODE + "</ns1:sCountryISOCode>\r\n" + "    </ns1:FullCountryInfo>\r\n"
						+ "  </SOAP-ENV:Body>\r\n" + "</SOAP-ENV:Envelope>\r\n" + "");
			} finally {
				if (writer != null)
					try {
						writer.close();
					} catch (IOException logOrIgnore) {
					}
			}
			// Prints the response directly to console
//			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String res = null;
//			while ((res = reader.readLine()) != null) {
//				System.out.println(res);
//			}

			// Parse it to a SoapMEssage so that you can easier get you data from the tag
			// names.
			SOAPMessage sm = MessageFactory.newInstance().createMessage(new MimeHeaders(), con.getInputStream());
			// Takes all the text content and prints it.

			System.out.println(
					"Capital: " + sm.getSOAPBody().getElementsByTagName("m:sCapitalCity").item(0).getTextContent());
			System.out.println("Country: " + sm.getSOAPBody().getElementsByTagName("m:sName").item(0).getTextContent());
			System.out.println("Currency: "
					+ sm.getSOAPBody().getElementsByTagName("m:sCurrencyISOCode").item(0).getTextContent());

		} catch (MalformedURLException e) {// Catch errors
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
