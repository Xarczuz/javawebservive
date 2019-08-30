package javaweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

/**
 * Deluppgift A Du skall skriva ett program som konsumerar / använder sig av den
 * REST WS som finns på http://date.jsontest.com/ och som returnerar datum och
 * klockslag. Tjänsten finns beskriven på https://www.jsontest.com/. Krav för
 * godkänt: Skriv ett Java-program som anropar denna WS och skriva ut datum och
 * tid som det får i svaret. Ta med förklarande kodkommentarer som visar vad
 * programmet gör. (Du får använda vilken teknik du vill för implementationen.)
 * 
 * @author chjun
 *
 */
public class Del_A_G {
	public static void main(String[] args) {
		connectToAdress();
	}

	private static void connectToAdress() {
		String adress = "http://date.jsontest.com/";
		URL url;
		try {
			url = new URL(adress);// Creates the URL object
			HttpURLConnection con = (HttpURLConnection) url.openConnection();// Create the instance does not establish																				// connection with the network.
			con.setRequestMethod("GET");
			print(con);

		} catch (MalformedURLException e) {// Catch errors
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void print(HttpURLConnection con) {
		if (con != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String input;
				StringBuilder sb = new StringBuilder();
				while ((input = br.readLine()) != null) {
//					System.out.println(input); Write out line directly from inputstream.
					sb.append(input); // Builds a string from the inputstream
				}
				br.close();
//				Parse the string to a jsonobject
				JSONObject js = new JSONObject(sb.toString());
				
				System.out.println("Date: " + js.get("date"));
				System.out.println(js.get("milliseconds_since_epoch") + "ms since epoch");
				System.out.println("Time: " + js.get("time"));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}