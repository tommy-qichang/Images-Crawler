package edu.rutgers.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import edu.rutgers.crawler.impl.BingImgResultRequest;


/**
 * Created by qichang on 3/24/17.
 */
public class TestResultRequest {

    ResultRequest resultRequest;
    @org.junit.Test
    public void TestResultRequest() throws Exception {
        System.out.println("TestResult Console");

        resultRequest = new BingImgResultRequest("Indra swallowtail, Papilio indra");

        JsonNode node = resultRequest.getResult(1);
    }
}
