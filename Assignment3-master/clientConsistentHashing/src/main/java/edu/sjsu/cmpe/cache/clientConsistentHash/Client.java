package edu.sjsu.cmpe.cache.clientConsistentHash;

import java.util.ArrayList;
/*****************************************
 * Implementation of consistent hashing 
 * @author Chanikya Mandapathi
 *
 */



public class Client {

    public static void main(String[] args) throws Exception 
    {

        

        ArrayList cacheServer = new ArrayList();

        cacheServer.add("http://localhost:3000");
        cacheServer.add("http://localhost:3001");
        cacheServer.add("http://localhost:3002");

        ConsistentHashSimple cHash = new ConsistentHashSimple(cacheServer);
        
        System.out.println("**********************************************************************");
        System.out.println("**************Starting Consistent Cache Client**************");
        System.out.println("**********************************************************************");

        printStatus("**************Adding data to cache servers**************");


        for(int i = 1; i<=10; i++){
            addToCache(i, String.valueOf((char) (i + 96)), cHash);
        }

        printStatus("******************Retrieving Cache from three servers*************************");

        for(int i =1; i<=10; i++){
            String value = (String)getFromCache(i,cHash);
            System.out.println("Cache Collected : "+ value);
        }

        printStatus("**********************After coming to existant state**************************");


       

    }

    public static void addToCache(int toAddKey, String toAddValue, ConsistentHashSimple cHash){
        String cacheUrl = (String) cHash.getCache(toAddKey);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        cache.put(toAddKey,toAddValue);
        System.out.println("put( "+ toAddKey +" ----> " + toAddValue + ")");
    }

    public static Object getFromCache(int key, ConsistentHashSimple cHash){
        String cacheUrl = (String) cHash.getCache(key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        String value = cache.get(key);
        System.out.println("get( "+ key+ " ) ----> "+ value);
        return value;
    }


    public static void printStatus(String status){
    	System.out.println("********************************************************************");
        System.out.println(status);
        System.out.println("********************************************************************");
    }

}
