package edu.sjsu.cmpe.cache.client;

import java.io.InputStream;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.Headers;


public class Client {

	
    public static void main(String[] args) throws Exception {
    	 
    	 String valueFirst = "a", valueSecond = "b";
    	 System.out.println("Starting Cache Client...");
         
    	 
    	 System.out.println("Starting 1..!!!");
         
         CRDTClient crdt = new CRDTClient();
         
         
         crdt.put(1, valueFirst);
        
        
         Thread.sleep(30000);
         
         //crdt.get(1);
         
         //Thread.sleep(10000);
         
         
          
    	 System.out.println("Step 1 Ended");
         
         
         System.out.println("Starting 2..!!!");
          
         crdt.update(1, valueSecond);
         Thread.sleep(30000);
         
         System.out.println("Step 2 Ended");
         
         System.out.println("Starting 3..!!!");
         
         crdt.get(1);
         crdt.updateValues(1, valueSecond);
         Thread.sleep(30000);
         System.out.println("Post updation!!");
         crdt.get(1);
         System.out.println("Step 3 Ended");
         

        System.out.println("Cache Client already exist!!!");
        
    }
    
    
    

}
