package hello;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountController {

    @RequestMapping("/createTestAccount")
    public String index() throws Exception {
    	
    	String uri = "http://localhost:8080/accounts";
    	//String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
    	
    	HttpPost request = new HttpPost(uri);
    	StringEntity entity = new StringEntity("{\"initialBalance\" : 1000000 }");
    	request.addHeader("content-Type", "application/json");
    	request.setEntity(entity);
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	
    	CloseableHttpResponse response = httpClient.execute(request);
    	
    	String line;
    	StringBuilder sb = new StringBuilder();
    	InputStream in = response.getEntity().getContent();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	while ( (line = reader.readLine()) != null)	{
    		sb.append(line);
    	}
    	
        return "konto " + sb.toString() + " oprettet";
    }

}