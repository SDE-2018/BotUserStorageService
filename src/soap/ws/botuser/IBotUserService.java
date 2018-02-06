package soap.ws.botuser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.recombee.api_client.exceptions.ApiException;

import soap.model.BotUser;

/**
 * Basic service from data layer give an interface to user creation
 * at Recombee database, get user profile by user id
 * 
 * @author ivan
 *
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface IBotUserService {

	/**
	 * Updates user preferences.
	 * @return true if successfully created, false otherwise
	 * @throws ApiException
	 */
    @WebMethod(operationName="setPreferences") 
    @WebResult(name="updatedBotUser") 
    public boolean updateBotUserPreferences(@WebParam(name="user") BotUser user) 
    															throws ApiException;

    /**
     * Creates a new user.
     * @return true if successfully created, false otherwise
     * @throws ApiException
     */
    @WebMethod(operationName="createBotUser") 
    @WebResult(name="createdBotUser") 
    public boolean addUser(@WebParam(name="user") BotUser user) throws ApiException;
   
    /**
     * Get user profile by user id.
     */
    @WebMethod(operationName="getBotUserById") 
    @WebResult(name="createdBotUserInfo") 
    public BotUser getUser(@WebParam(name="userId") String userId);
    
}




