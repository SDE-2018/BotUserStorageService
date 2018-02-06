package soap.endpoint;

import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

import soap.ws.botuser.BotUserServiceImpl;

/**
 * Endpoint publisher of the service.
 * 
 * @author ivan
 *
 */
public class BotUserPublisher {

    public static void main(String[] args) throws UnknownHostException {
        Endpoint.publish("http://localhost:8081/botuser", new BotUserServiceImpl());
    }
}