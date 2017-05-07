package edu.rutgers.query;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by changqi on 9/29/15.
 */
public class Queries {

    final static String QUERIES_PATH = "/Users/qichang/IdeaProjects/crawler/src/main/resources/list.txt";
    final static String FINISHED_PATH = "/Users/qichang/IdeaProjects/crawler/src/main/resources/finished.txt";
    final static Charset ENCODING = StandardCharsets.UTF_8;



//    public String getQuery() throws IOException{
//
//        List<String> lines = readSmallTextFile(QUERIES_PATH);
//        if(lines.size()<=0)return null;
//        String query = lines.get(0);
//        lines.remove(0);
//        writeSmallTextFile(lines,QUERIES_PATH);
//        List<String> finished = readSmallTextFile(FINISHED_PATH);
//        finished.add(query);
//        writeSmallTextFile(finished,FINISHED_PATH);
//        return query;
//    }

    public List<String> getQuery() throws  IOException{
        List<String> lines = readSmallTextFile(QUERIES_PATH);

        return lines;

    }

    public void removeQuery(List<String> lines,List<String> removeQueries) throws  IOException{
        writeSmallTextFile(lines,QUERIES_PATH);
        writeSmallTextFile(removeQueries,FINISHED_PATH);

    }




    List<String> readSmallTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        return Files.readAllLines(path, ENCODING);
    }

    void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aLines, ENCODING);
    }
}
