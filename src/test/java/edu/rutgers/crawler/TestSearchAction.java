package edu.rutgers.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import edu.rutgers.crawler.impl.BingSearchAction;

/**
 * Created by qichang on 3/24/17.
 */
public class TestSearchAction {
    SearchAction searchAction ;
    @org.junit.Test
    public void testGetResults() throws Exception{
        searchAction = new BingSearchAction("Indra swallowtail, Papilio indra");
        JsonNode node = searchAction.getResults(1);
    }
}
