package soap.ws;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddUser;
import com.recombee.api_client.api_requests.AddUserProperty;
import com.recombee.api_client.api_requests.Batch;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.api_requests.SetUserValues;
import com.recombee.api_client.exceptions.ApiException;

import soap.model.BotUser;

@WebService(endpointInterface = "soap.ws.IBotUserService", serviceName="BotUserService")
public class BotUserServiceImpl implements IBotUserService{
	
	private static RecombeeClient client;
	
	private static String RECOMBEE_TOKEN;
	
	private static String DB_NAME;
	
	private static final Logger logger = Logger.getLogger(BotUserServiceImpl.class.getName());
	
	
	static {
		Properties properties = new Properties();
        try {
			properties.load(new FileInputStream("local.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // init Recombee client
        RECOMBEE_TOKEN = properties.getProperty("RECOMBEE_TOKEN");
        DB_NAME = properties.getProperty("DB_NAME");
        client = new RecombeeClient(DB_NAME, RECOMBEE_TOKEN);
        
        // setup properties for user
//        try {
////			client.send(new AddUserProperty("chatId", "string"));
//			client.send(new AddUserProperty("name", "string"));
//			client.send(new AddUserProperty("age", "int"));
//			client.send(new AddUserProperty("registerDate", "timestamp"));
//			client.send(new AddUserProperty("occupation", "string"));
//			client.send(new AddUserProperty("preferredSkiType", "string"));
//			client.send(new AddUserProperty("budget", "int"));
//			client.send(new AddUserProperty("nearTrento", "boolean"));
//		} catch (ApiException e) {
//			e.printStackTrace();
//		}
	}
	
	
	@Override
	public boolean addUser(BotUser user) throws ApiException {
		logger.info("Creating new user...");   
    	System.out.println("creating user at post request");
    	
    	// here Request is of package 'com.recombee.api_client.api_requests'
    	ArrayList<Request> userInfo = new ArrayList<>();
    	
    	// add user itself
    	String uId = Integer.toString(user.getChatId());
    	userInfo.add(new AddUser(uId));	
    	
    	// fill user attributes
    	Map<String, Object> values = new HashMap<>();
    	values.put("name", user.getName());
    	values.put("age", user.getAge());
    	values.put("registerDate", user.getRegisterDate());
    	values.put("occupation", user.getOccupation());
    	values.put("preferredSkiType", user.getPreferredSkiType());
    	values.put("budget", user.getBudget());
    	values.put("nearTrento", user.isNearTrento());
    	
    	
    	userInfo.add(new SetUserValues(uId, values).setCascadeCreate(true));
    	
    	client.send(new Batch(userInfo));
        return true;
	}

}
