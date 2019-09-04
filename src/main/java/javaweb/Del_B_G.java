package javaweb;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * @author chjun
 *
 */
public class Del_B_G {
	public static void main(String[] args) {
		getInfoFromISOCode("SE");
		getInfoFromISOCode("AF");
		getInfoFromISOCode("US");
		getInfoFromISOCode("CH");
		getInfoFromISOCode("AX");
	}

	/**
	 * @param countryISOCode
	 */
	private static void getInfoFromISOCode(String countryISOCode) {
		String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		String country = "CountryName";
		String capital = "CapitalCity";
		String currency = "CountryCurrency";
		System.out.println("************");
		callSoapWebService(url, country, countryISOCode);
		callSoapWebService(url, capital, countryISOCode);
		callSoapWebService(url, currency, countryISOCode);
		System.out.println("************");
	}

	/**
	 * @param url to API
	 * @param function to call to the API
	 * @param countryISOCode
	 */
	private static void callSoapWebService(String url, String function, String countryISOCode) {
		try {
			SOAPConnectionFactory soapConFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapCon = soapConFactory.createConnection();

			// Get the response after sending the xml soapmessage.
			SOAPMessage response = soapCon.call(createSoapRequest(function, countryISOCode), url);
			// response.writeTo(System.out); Prints the whole XML response
			if (function.contentEquals("CountryCurrency")) {
				System.out.println(response.getSOAPBody().getElementsByTagName("m:sISOCode").item(0).getTextContent());
				System.out.println(response.getSOAPBody().getElementsByTagName("m:sName").item(0).getTextContent());
			} else {
				System.out.println(response.getSOAPBody().getFirstChild().getNextSibling().getTextContent().trim());
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param function to call the API
	 * @param countryISOCode
	 * @return SOAPMessage
	 * @throws SOAPException
	 */
	private static SOAPMessage createSoapRequest(String function, String countryISOCode) throws SOAPException {
		// Creates the empty soap- part, envelope, body, header
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage sm = mf.createMessage();
		createSoapMessage(sm, countryISOCode, function);
		return sm;
	}

	/**
	 * @param sm
	 * @param countryISOCode
	 * @param function to call the API
	 */
	private static void createSoapMessage(SOAPMessage sm, String countryISOCode, String function) {
		SOAPPart soapPart = sm.getSOAPPart();
		try {
			SOAPEnvelope soapEnevelope = soapPart.getEnvelope();
			soapEnevelope.addNamespaceDeclaration("ns1", "http://www.oorsprong.org/websamples.countryinfo");

			// Build the SOAPMESSAGE XML structure for each function.
			SOAPBody soapBody = soapEnevelope.getBody();
			SOAPElement soapElement = null;
			SOAPElement soapElement2 = null;
			if (function.contentEquals("CountryName")) {
				soapElement = soapBody.addChildElement("CountryName", "ns1");
			} else if (function.contentEquals("CapitalCity")) {
				soapElement = soapBody.addChildElement("CapitalCity", "ns1");
			} else if (function.contentEquals("CountryCurrency")) {
				soapElement = soapBody.addChildElement("CountryCurrency", "ns1");
			}
			soapElement2 = soapElement.addChildElement("sCountryISOCode", "ns1");
			// Add the ISOCode to the SOAPMessage
			soapElement2.addTextNode(countryISOCode);
		} catch (SOAPException e) {
			e.printStackTrace();
		}

	}
}
