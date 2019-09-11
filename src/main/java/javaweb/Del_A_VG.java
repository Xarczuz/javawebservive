package javaweb;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import org.json.JSONObject;

/**
 * REST request with jersey client
 * 
 * @author chjun
 *
 */

public class Del_A_VG {
	/**
	 * Rest with jersey client
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Create default config
		ClientConfig config = new ClientConfig();
		// Create client with the config
		Client client = ClientBuilder.newClient(config);
		// Connect to webadress
		WebTarget service = client.target((UriBuilder.fromUri("http://date.jsontest.com/").build()));
		// Get JSON data
		Response res = service.request().get();
		if (res.getStatus() == 200) {
			// Get the json from site.
			JSONObject js = new JSONObject(service.request(MediaType.APPLICATION_JSON).get().readEntity(String.class));
			System.out.println("Date: " + js.get("date"));
			System.out.println(js.get("milliseconds_since_epoch") + "ms since epoch");
			System.out.println("Time: " + js.get("time"));

		}

	}
}