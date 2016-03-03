package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

interface AccountInfoRepository extends MongoRepository<AccountInfo, String> {
	
	public AccountInfo findByAccount(String account);
}
