package edu.rutgers.store;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by changqi on 9/29/15.
 */
public interface Storage {
    boolean StoreResultImages(String query, JsonNode results, int pageNum);

}
