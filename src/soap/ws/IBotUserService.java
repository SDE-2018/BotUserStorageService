package soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.recombee.api_client.exceptions.ApiException;

import soap.model.BotUser;


@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface IBotUserService {

//    @WebMethod(operationName="readPersonList") // Method 1
//    @WebResult(name="people") 
//    public List<Person> getPeople();

//    @WebMethod(operationName="readPerson") // Method 2
//    @WebResult(name="person") 
//    public Person readPerson(@WebParam(name="idPerson") int id); 

    @WebMethod(operationName="setPreferences") 
    @WebResult(name="updatedBotUser") 
    public boolean updateBotUserPreferences(@WebParam(name="user") BotUser user) throws ApiException;

    @WebMethod(operationName="createBotUser") 
    @WebResult(name="createdBotUser") 
    public boolean addUser(@WebParam(name="user") BotUser user) throws ApiException;
    
 
}




