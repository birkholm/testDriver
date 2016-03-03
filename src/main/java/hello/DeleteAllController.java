package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAllController {
	
	@Autowired
	AccountInfoRepository accountinfo;
	
	@RequestMapping("/deleteAll")
	public String deleteAll()	{
		
		accountinfo.deleteAll();
		return "everything is .......gone";
		
	}

}
