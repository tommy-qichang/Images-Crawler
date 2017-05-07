package edu.rutgers;

import com.fasterxml.jackson.databind.JsonNode;
import edu.rutgers.crawler.SearchAction;
import edu.rutgers.crawler.impl.BingSearchAction;
import edu.rutgers.query.Queries;
import edu.rutgers.store.Storage;
import edu.rutgers.store.impl.FileStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by changqi on 9/29/15.
 */
public class Crawler {
    final int SLEEP_TIME = 100;
    SearchAction action;
    Storage storageHandler;
    Queries queryHanlder = new Queries();
    double currentIndex = 1;


    public Crawler() {

        storageHandler = new FileStorage();
    }

    public void processCrawling() {
        try {

            List<String> queries = queryHanlder.getQuery();
            List<String> finished = new ArrayList<String>();

            while (queries.size() > 0) {

                for (int i = 0, len = queries.size(); i < len; i++) {
                    boolean hasNext = searchAndStoreResults(queries.get(i), currentIndex);

                    if(!hasNext){
                        String query = queries.get(i);
                        finished.add(query);
                        queries.remove(i);
                        i--;
                        len--;
                        queryHanlder.removeQuery(queries,finished);
                        CrawlerBioMed.log("\n===Not has next pages for query:"+query+",max Index:"+currentIndex
                                +" and move query into finishedQueue===\n",CrawlerBioMed.INFO);
                    }
                }
                CrawlerBioMed.log("\n===Finished travel queries for last index start with:"+currentIndex+"===\n",CrawlerBioMed.INFO);

                currentIndex += BingSearchAction.DEFAULT_RESULT_NUM;

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    public boolean searchAndStoreResults(String query, double currentIndex) {
        action = new BingSearchAction(query);
        JsonNode result;
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ee) {
            ee.printStackTrace();
        }
        result = action.getResults(currentIndex);
        if(result != null){
            storageHandler.StoreResultImages(query, result,(int)Math.ceil(currentIndex/BingSearchAction.DEFAULT_RESULT_NUM));
        }

        if(action.hasNextPage()){
            return true;
        }else{
            return false;
        }


    }
}
