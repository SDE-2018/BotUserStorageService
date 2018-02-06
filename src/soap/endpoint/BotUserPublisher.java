package soap.endpoint;

import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

import soap.ws.botuser.BotUserServiceImpl;

public class BotUserPublisher {

    public static void main(String[] args) throws UnknownHostException {
//        Endpoint.publish("https://assignment3-chernukha.herokuapp.com/botuser", BotUserServiceImpl());
        Endpoint.publish("http://localhost:8081/botuser", new BotUserServiceImpl());
    }
}