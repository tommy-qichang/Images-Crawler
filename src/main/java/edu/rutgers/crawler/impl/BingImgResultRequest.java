package edu.rutgers.crawler.impl;

import edu.rutgers.CrawlerBioMed;
import edu.rutgers.crawler.ResultRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class BingImgResultRequest implements ResultRequest {

    ///Image?$format=json&Query=%27Xbox%27
//    https://api.cognitive.microsoft.com/bing/v5.0/images/search
//    final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search";
    final String ROOT_URL = "https://api.cognitive.microsoft.com/bing/v5.0/images/search";
    //    final String SERVICE_OP = "Image?$format=json&Query=";
//    &$skip=100
//    final String SERVICE_OP = "Composite?Sources=%27image%27&ImageFilters=%27Color%3Amonochrome%27&$format=json&Query=";
    final String SERVICE_OP = "?count="+BingSearchAction.DEFAULT_RESULT_NUM+"&q=";

    //%2BRelatedSearch
//    String query = "%27Left%20vertricle%20hypertrophy%20ultrasound%27";
    //https://api.datamarket.azure.com/Bing/Search/v1/Image?Query=%27glioma%27&ImageFilters=%27Color%3Amonochrome%27
    String query;

    //TODO: initial Object RequestInfo which include query, startIndx resultLenth etc.
    public BingImgResultRequest(String curQuery) {
        String origQuery = "'" + curQuery + "'";
        try {
            query = URLEncoder.encode(origQuery, "utf-8");
            CrawlerBioMed.log("encode query:" + query,CrawlerBioMed.DEBUGGER);
        } catch (UnsupportedEncodingException ee) {
            ee.printStackTrace();
        }
//        query = "%27"+curQuery+"%27";
    }


    public JsonNode getResult(double startIdx) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(ROOT_URL  + SERVICE_OP + query + "&offset=" + (int)(startIdx-1));
            httpget.setHeader("Ocp-Apim-Subscription-Key",  BASE64_ACCOUNT_KEY);
            CrawlerBioMed.log("executing request " + httpget.getURI(),CrawlerBioMed.DEBUGGER);
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            CrawlerBioMed.log("----------------------------------------",CrawlerBioMed.DEBUGGER);
            CrawlerBioMed.log(responseBody,CrawlerBioMed.DEBUGGER);
            CrawlerBioMed.log("----------------------------------------",CrawlerBioMed.DEBUGGER);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode jNode = mapper.readTree(responseBody);

            //TODO:Add some important factor
            //jNode.with("");
//            ObjectNode oNode = mapper.valueToTree(responseBody);
//            oNode.with("testNode").put("test1", "value1");
//            String json = mapper.writeValueAsString(oNode);
            return jNode;
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

}