package soap.ws.botuser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.jws.WebService;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddUser;
import com.recombee.api_client.api_requests.AddUserProperty;
import com.recombee.api_client.api_requests.Batch;
import com.recombee.api_client.api_requests.ListUsers;
import com.recombee.api_client.api_requests.MergeUsers;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.api_requests.SetUserValues;
import com.recombee.api_client.bindings.BatchResponse;
import com.recombee.api_client.bindings.User;
import com.recombee.api_client.exceptions.ApiException;

import soap.model.BotUser;
import util.RecombeeUtil;

/**
 * User Add and Update operations using Recombee API.
 * Consumes BotUser from the client and fills corresponding information 
 * in order to create/update the user at the Recombee database.
 * 
 * Provides access to user profile by user id.
 * 
 * @author ivan
 *
 */
@WebService(endpointInterface = "soap.ws.botuser.IBotUserService", 
											serviceName="BotUserService")
public class BotUserServiceImpl implements IBotUserService{
	
	private static RecombeeClient client;
	
	private static String RECOMBEE_TOKEN;
	
	private static String DB_NAME;
	
	private static final Logger logger = Logger.getLogger(
										BotUserServiceImpl.class.getName());
	
	static {
	    FileHandler fh;  
	    try {  
	        fh = new FileHandler("server-logs.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	static {
		/*
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
		*/
		
		/**
		 * TODO:
		 * Server doesn't know the properties file path outside the .war,
		 * so we have a separate class containing tokens.
		 * This repository doesn't contain this class, so you if you clone the repo,
		 * you should either define here your database name and token, or uncomment comment
		 * above and put it into local.properties.
		 */
        client = RecombeeUtil.getRecombeeClient();
        logger.info("recombee client initialized");
        
        // setup properties for user
        try {
        	client.send(new AddUserProperty("expertLevel", "int"));
//			client.send(new AddUserProperty("chatId", "string"));
			client.send(new AddUserProperty("name", "string"));
			client.send(new AddUserProperty("age", "int"));
			client.send(new AddUserProperty("registerDate", "timestamp"));
			client.send(new AddUserProperty("occupation", "string"));
			client.send(new AddUserProperty("preferredSkiType", "string"));
			client.send(new AddUserProperty("budget", "int"));
			client.send(new AddUserProperty("nearTrento", "boolean"));
		} catch (ApiException e) {
			e.printStackTrace();
			logger.info("properties have been already setup!");
		}
	}
	
	/**
	 * Create a new user at Recombee database.
	 */
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
    	values.put("expertLevel", user.getExpertLevel());
    	
    	logger.info(values.toString());
    	    	
    	userInfo.add(new SetUserValues(uId, values)); //.setCascadeCreate(true));
    	
    	BatchResponse [] result = client.send(new Batch(userInfo));
    	logger.info(result.toString());
        return true;
	}

	/**
	 * Updates the preferences for the user.
	 * It firstly creates a new user with preferences info only,
	 * then merges with the one that's already in the system, but containing all
	 * personal information.
	 */
	@Override
	public boolean updateBotUserPreferences(BotUser user) throws ApiException {
		
		ArrayList<Request> userInfo = new ArrayList<>();
		// add user with partial information.
		// This user will be merged with full information already present in the system
    	String targetUserId = Integer.toString(user.getChatId());
    	String sourceUserId = "100";
    	userInfo.add(new AddUser(sourceUserId));	
    	
    	// fill user attributes with preferences information
    	Map<String, Object> values = new HashMap<>();
    	values.put("preferredSkiType", user.getPreferredSkiType());
    	values.put("budget", user.getBudget());
    	values.put("nearTrento", user.isNearTrento());
    	values.put("expertLevel", user.getExpertLevel());
    	
    	logger.info(values.toString());
    	    	
    	userInfo.add(new SetUserValues(sourceUserId, values)); //.setCascadeCreate(true));
    	
    	BatchResponse [] result = client.send(new Batch(userInfo));
    	try {
    		/*
    		 * Try to merge, if merge is unsuccessful, return false.
    		 * We shouldn't worry about assigning the same sourceUserId,
    		 * according to Recombee documentation that user will be deleted
    		 * immediately after the operation succeeds.
    		 */
    		client.send(new MergeUsers(targetUserId, sourceUserId)
    					.setCascadeCreate(true));
    	} catch (Exception e) {
    		logger.info(e.getMessage());
    		e.printStackTrace();
    		return false;
    	}
    	
    	logger.info(result.toString());
        return true;
	}

	
	/**
	 * Get user profile by user id.
	 */
	@Override
	public BotUser getUser(String userId) {
		BotUser user = null;
		try {
			List<User> result = Arrays.asList(client.send(new ListUsers()
					  .setFilter("\"" + userId + "\" in 'userId'")
					  .setReturnProperties(true)
					));
			for (User u: result) {
				if (u.getUserId().equals(userId)) {
					user = new BotUser();
					Map<String, Object> v = u.getValues();
					if (v.get("name") != null) {
						user.setName(v.get("name").toString());
					}
					if (v.get("age") != null) {
						user.setAge(Integer.parseInt(v.get("age").toString()));
					}
					if (v.get("occupation") != null) {
						user.setOccupation(v.get("occupation").toString());
					}
					if (v.get("preferredSkiType") != null) {
						user.setPreferredSkiType(v.get("preferredSkiType").toString());
					}
					if (v.get("expertLevel") != null) {
						user.setExpertLevel(Integer.parseInt(
												v.get("expertLevel").toString()));
					}
					if (v.get("budget") != null) {
						user.setBudget(Integer.parseInt(v.get("budget").toString()));
					}
					if (v.get("nearTrento") != null) {
						user.setNearTrento(Boolean.parseBoolean(
											v.get("nearTrento").toString()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return user;
	}

}
