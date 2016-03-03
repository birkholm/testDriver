package hello;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTransferController {

	@Autowired
	private AccountInfoRepository accountInfo;
	
	@RequestMapping("/createTestTranfer")
	public String createTestTransfer() throws Exception	{
	
		//Get max accounts
		long max = accountInfo.count();
		
		String uri = "http://localhost:8080/transfers";
    	//String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
    	
		//'{"amount" : 150, "fromAccountId" : "0000014ae4caf314-ae7453bbb71e0000", "toAccountId" : "0000014ae4cc8415-ae7453bbb71e0000"}'
    	HttpPost request = new HttpPost(uri);
    	String from = "" + ThreadLocalRandom.current().nextLong(1, max + 1);
    	String to   = "" + ThreadLocalRandom.current().nextLong(1, max + 1);
    	//String fra = "{\"account\" : \"" + from + "\"}";
    	//System.out.println(fra);
    	AccountInfo accountFrom = accountInfo.findByAccount(from);
    	AccountInfo accountTo = accountInfo.findByAccount(to);
    	
    	System.out.println("fra " + accountFrom.getId() + " til " + accountTo.getId());
    	String jsonRequest = "{\"amount\" : 1, \"fromAccountId\" : \"" + accountFrom.getId() + "\", \"toAccountId\" : \"" + accountTo.getId() + "\" }";
    	System.out.println(jsonRequest);
    	StringEntity entity = new StringEntity(jsonRequest);
    	//StringEntity entity = new StringEntity("{\"amount\" : 5000, \"fromAccountId\" : \"9\", \"toAccountId\" : \"1\" }");
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
    	
        return "transfer " + sb.toString() + " gennemført";
	}
}
