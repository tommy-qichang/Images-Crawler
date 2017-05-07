package edu.rutgers;


import edu.rutgers.query.Queries;

import java.io.IOException;
import java.util.List;

public class CrawlerBioMed {

    public final static  int DEBUGGER = 0;
    public final static int INFO = 1;
    public final static int ERROR = 2;

    private static int LOG_LEVEL = INFO;



    public static void log(String s, int severity) {
        if (severity >= LOG_LEVEL) {
            System.out.println(s);
        }
    }


    public static void main(String args[]) {
        log("Biomedical Images Crawler System. QiChang<qc58@cs.rutgers.edu>",INFO);
        log("Start crawling queries ... ",INFO);

        Crawler crawler = new Crawler();
        crawler.processCrawling();


//        Queries query = new Queries();
//        try{
//            List<String> q = query.getQuery();
////            log(query.getQuery());
////            log(query.getQuery());
////            log(query.getQuery());
//
//        }catch(IOException io){
//            io.printStackTrace();
//        }

    }
}