package javaweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

/**
 *
 * @author chjun
 *
 */
public class Del_A_G {
	public static void main(String[] args) {
		connectToAdress();
	}

	/**
	 * Connects to the adress using http
	 */
	private static void connectToAdress() {
		String adress = "http://date.jsontest.com/";
		URL url;
		try {
			// Creates the URL object
			url = new URL(adress);
			// Create the instance does not establish connection with the network.
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			print(con);

		} catch (MalformedURLException e) {// Catch errors
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * prints the response
	 * 
	 * @param con
	 */
	private static void print(HttpURLConnection con) {
		if (con != null) {
			try {
				// Reads the response from the inputstream
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String input;
				StringBuilder sb = new StringBuilder();
				// Iterates over the response and builds the string with string builder.
				while ((input = br.readLine()) != null) {
					// System.out.println(input); Write out line directly from inputstream.
					// Builds a string from the inputstream
					sb.append(input);
				}
				br.close();
				// Parse the whole string to a jsonobject
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