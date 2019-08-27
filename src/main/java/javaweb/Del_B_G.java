package javaweb;

import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class Del_B_G {
	public static void main(String[] args) {
		String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		String country = "https://wsdlbrowser.com/soapclient?wsdl_url=http%3A%2F%2Fwebservices.oorsprong.org%2Fwebsamples.countryinfo%2FCountryInfoService.wso%3FWSDL&function_name=CountryName";
		String capital = "https://wsdlbrowser.com/soapclient?wsdl_url=http%3A%2F%2Fwebservices.oorsprong.org%2Fwebsamples.countryinfo%2FCountryInfoService.wso%3FWSDL&function_name=CapitalCity";
		String currency = "https://wsdlbrowser.com/soapclient?wsdl_url=http%3A%2F%2Fwebservices.oorsprong.org%2Fwebsamples.countryinfo%2FCountryInfoService.wso%3FWSDL&function_name=CountryCurrency";
		callSoapWebService(url, country, "SE");

	}

	private static void callSoapWebService(String url, String function, String countryISOCode) {
		try {
			SOAPConnectionFactory soapConFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapCon = soapConFactory.createConnection();
			SOAPMessage response = soapCon.call(createSoapRequest(function, countryISOCode), url);

			response.writeTo(System.out);

		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static SOAPMessage createSoapRequest(String function, String countryISOCode) throws SOAPException {

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage sm = mf.createMessage();
		createSoapMessage(sm, countryISOCode);

//			if (function.endsWith("CountryName")) {
//				System.out.println("kommer jag hit!");
//			} else if (function.endsWith("CapitalCity")) {
//				System.out.println("capital");
//			} else if (function.endsWith("CountryCurrency")) {
//				System.out.println("currency");
//			}

		return sm;
	}

	private static void createSoapMessage(SOAPMessage sm, String countryISOCode) {
		SOAPPart soapPart = sm.getSOAPPart();
		try {
			SOAPEnvelope soapEnevelope = soapPart.getEnvelope();
			soapEnevelope.addNamespaceDeclaration("ns1",
					"http://www.oorsprong.org/websamples.countryinfo");

			// <?xml version="1.0" encoding="UTF-8"?>
//			<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
//			  <SOAP-ENV:Body>
//			    <ns1:CapitalCity>
//			      <ns1:sCountryISOCode>AX</ns1:sCountryISOCode>
//			    </ns1:CapitalCity>
//			  </SOAP-ENV:Body>
//			</SOAP-ENV:Envelope>

//			<?xml version="1.0" encoding="UTF-8"?>
//			<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
//			  <SOAP-ENV:Body>
//			    <ns1:CountryName>
//			      <ns1:sCountryISOCode>?</ns1:sCountryISOCode>
//			    </ns1:CountryName>
//			  </SOAP-ENV:Body>
//			</SOAP-ENV:Envelope>
//			    
//			    <?xml version="1.0" encoding="UTF-8"?>
//			    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns1="http://www.oorsprong.org/websamples.countryinfo">
//			      <SOAP-ENV:Body>
//			        <ns1:CurrencyName>
//			          <ns1:sCurrencyISOCode>AFA</ns1:sCurrencyISOCode>
//			        </ns1:CurrencyName>
//			      </SOAP-ENV:Body>
//			    </SOAP-ENV:Envelope>

			SOAPBody soapBody = soapEnevelope.getBody();
			SOAPElement soapElement = soapBody.addChildElement("CountryName", "ns1");
			SOAPElement soapElement2 = soapElement.addChildElement("sCountryISOCode", "ns1");
			soapElement2.addTextNode(countryISOCode);

		} catch (SOAPException e) {
			e.printStackTrace();
		}

	}
}
