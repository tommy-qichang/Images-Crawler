package edu.rutgers.store.impl;

import com.fasterxml.jackson.databind.JsonNode;
import edu.rutgers.store.Storage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;


/**
 * Created by qichang on 3/24/17.
 */
public class FileStorage implements Storage{
    final String FILE_STORE_ROOT = "/Users/qichang/IdeaProjects/crawler/src/main/resources/results/";
    final int SLEEP_TIME = 300;
    @Override
    public boolean StoreResultImages(String query, JsonNode results, int pageNum) {
        String folderPath = FILE_STORE_ROOT+query;
        createFolder(folderPath);
        Iterator<JsonNode> node = results.elements();
        int idx = 0;
        while(node.hasNext()){
            JsonNode item = node.next();
            String url = item.get("contentUrl").asText();
            String postfix = item.get("encodingFormat").asText();
            String savePath = folderPath+"/"+idx++ d+"."+postfix;
            try {
                saveImage(url, savePath);
            }catch (IOException ioe){
                System.out.println("==Error load image:"+url+"==");
                System.out.println("id is:"+savePath);
//                ioe.printStackTrace();
            }
        }

        return false;
    }

    private void createFolder(String folderPath){
        File theDir = new File(folderPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }catch(SecurityException se){
                se.printStackTrace();
            }
        }
    }
    private void saveImage(String imageUrl, String destinationFile) throws IOException {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ee) {
            ee.printStackTrace();
        }
//        URL url = new URL(imageUrl);
//        InputStream is = url.openStream();

        URLConnection con = new URL(imageUrl).openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        InputStream in = con.getInputStream();
        OutputStream os = new FileOutputStream(destinationFile);


        byte[] b = new byte[2048];
        int length;

        while ((length = in.read(b)) != -1) {
            os.write(b, 0, length);
        }

        in.close();
        os.close();
    }

}
