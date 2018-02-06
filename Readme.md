## Storage SOAP web service for BotUser entities

[https://buss-chernukha.herokuapp.com/botuser?wsdl] (https://buss-chernukha.herokuapp.com/botuser?wsdl)

Please, take a look at Javadoc here.

To run the service you need to create a Recombee database and follow one of the option:  
1. Create a file `local.properties` and put it there, after uncomment code `soap.ws.botuser.BotUserServiceImpl:71`  
2. Implement class `RecombeeUtil` and method `getRecombeeClient` that returns a valid RecombeeClient.   

Then run `ant create.war`.
