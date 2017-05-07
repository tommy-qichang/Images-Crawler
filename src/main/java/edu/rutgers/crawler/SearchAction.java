package edu.rutgers.crawler;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by changqi on 9/29/15.
 */
public interface SearchAction {

    public boolean hasNextPage();

    public JsonNode getResults(double currentIndex);


}
