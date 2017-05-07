package edu.rutgers.crawler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import edu.rutgers.CrawlerBioMed;
import edu.rutgers.crawler.ResultRequest;
import edu.rutgers.crawler.SearchAction;

/**
 * Created by changqi on 9/29/15.
 */
public class BingSearchAction implements SearchAction {

    public final static int DEFAULT_RESULT_NUM = 50;

    public String currentQuery;
    public double currentIndex;
    public double currentPage, totalPage, totalResults;
    public JsonNode mainEntry;
    ResultRequest request;

    public BingSearchAction(String query) {
        currentQuery = query;
        currentPage = 1;
        currentIndex = 0;
        totalPage = -1;
        totalResults = 0;


        request = new BingImgResultRequest(currentQuery);
    }

    public boolean hasNextPage() {
        return false;
        //if not set the total page number, then should get the first records.
//        if (totalPage == -1) {
//            CrawlerBioMed.log("has next page with current Page:" + currentPage + " and total Page:" + totalPage,CrawlerBioMed.DEBUGGER);
//            return true;
//        } else if (currentPage >= totalPage) {
//            CrawlerBioMed.log("not have next page, max page:" + totalPage,CrawlerBioMed.DEBUGGER);
//            return false;
//        } else {
//            CrawlerBioMed.log("has next page with current Page:" + currentPage + " and total Page:" + totalPage,CrawlerBioMed.DEBUGGER);
//            return true;
//        }

    }


    public JsonNode getResults(double currentIndex) {

        try {
            JsonNode node = request.getResult(currentIndex);
            mainEntry = node;
            JsonNode images = node.get("value");

            totalResults = mainEntry.get("totalEstimatedMatches").asDouble();
            totalPage = (int) Math.ceil(totalResults / DEFAULT_RESULT_NUM);
            currentPage = (int) Math.ceil(currentIndex / DEFAULT_RESULT_NUM);

            CrawlerBioMed.log("getResults totalRestuls:"+totalResults+"|totalPage:"+totalPage
                    +"|currentPage:"+currentPage+" currentIndex:" + currentIndex  ,CrawlerBioMed.INFO);

//        currentIndex += DEFAULT_RESULT_NUM;
//        currentPage = (int) Math.ceil(currentIndex / DEFAULT_RESULT_NUM);
//
//        CrawlerBioMed.log("get Results Back.\n The Next page info should be:totalResults:" + totalResults +
//                " totalPage:" + totalPage +
//                " currentIndex:" + currentIndex +
//                " currentPage:" + currentPage +
//                " the size of images is :" + images.size(),CrawlerBioMed.INFO);
            if (images.size() <= 0) {
                totalPage = currentPage;
                return null;
            }

            return images;
        }catch(Exception ee){
            System.out.println("ERROR: Null Pointer");
            return null;
        }

    }


}
