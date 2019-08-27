package javaweb;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * @author chjun
 *
 */

public class Del_A_VG {
	public static void main(String[] args) throws IOException {
		ClientConfig config = new DefaultClientConfig();//Create default config
		Client client = Client.create(config);//Create client with the config
		WebResource service = client.resource(UriBuilder.fromUri("http://date.jsontest.com/").build());//connect to webadress
		// get JSON data
		JSONObject js = new JSONObject(
				service.path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));//Get the jsonobject from site
		System.out.println("Date: " + js.get("date"));
		System.out.println(js.get("milliseconds_since_epoch") + "ms since epoch");
		System.out.println("Time: " + js.get("time"));

	}
}