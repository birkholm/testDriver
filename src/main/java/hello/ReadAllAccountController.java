package hello;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadAllAccountController {
	
	@Autowired
	private AccountInfoRepository repository;
	
	@RequestMapping("/readAll")
	public String readMongo()	{
		
		//AccountInfo info = repository.findOne("0000015308e58f0e-185e0f71af890000");
		
		//AccountInfo info2 = repository.findOne("1");
		
		//System.out.println("test " + info2.getId());
		//long max = repository.count();
		
		//AccountInfo account = repository.findByAccount("" + ThreadLocalRandom.current().nextLong(1, max + 1));
		
		//return new String(account.getId() + " " + account.getAccount() + " " + account.getBalance());
		
		List<AccountInfo> info  = repository.findAll(); 
		
		StringBuilder sb = new StringBuilder();
		
		for (Iterator iterator = info.iterator(); iterator.hasNext();) {
			AccountInfo accountInfo = (AccountInfo) iterator.next();
			sb.append("<p><b>" + accountInfo.getAccount() + " " + accountInfo.getId() + " " + accountInfo.getBalance() + "</b></p>");
		}
		
		return sb.toString();
	}

}
