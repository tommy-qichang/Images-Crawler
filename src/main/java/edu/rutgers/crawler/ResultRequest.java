package edu.rutgers.crawler;


import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by changqi on 9/29/15.
 */
public interface ResultRequest {

    //final String ACCOUNT_KEY = "uob2Hskwk5EDB2qX2cFXuhZO9gs3GACSUHBMexmu9Iw";
    //String BASE64_ACCOUNT_KEY = "OnVvYjJIc2t3azVFREIycVgyY0ZYdWhaTzlnczNHQUNTVUhCTWV4bXU5SXc=";

    //for rutgers_biomed@outlook.com
    //final String ACCOUNT_KEY = "j008aaLpt0efoHXsrFa8Dx4XMhm5ipSvNsQkQXfLqCA";
    String BASE64_ACCOUNT_KEY = "315d4c670f114eb5992de5975ad50eba";



    JsonNode getResult(double startIdx);

}
