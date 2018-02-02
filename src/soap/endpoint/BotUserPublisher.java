package soap.endpoint;

import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

import soap.ws.BotUserServiceImpl;

public class BotUserPublisher {

    public static void main(String[] args) throws UnknownHostException {
//        Endpoint.publish("https://assignment3-chernukha.herokuapp.com/person", new PeopleImpl());
        Endpoint.publish("http://localhost:9090/botuser", new BotUserServiceImpl());
    }
}