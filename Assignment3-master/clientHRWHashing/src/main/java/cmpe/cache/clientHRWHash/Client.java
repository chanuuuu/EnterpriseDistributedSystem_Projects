package cmpe.cache.clientHRWHash;

import java.util.ArrayList;

/******************************
 * Implementation of Rendezvous or Highest Random Weight (HRW) hashing 
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
        
        System.out.println("**********************************************************************");
        System.out.println(" ---- Starting Consistent Cache Client --- ");
        System.out.println("**********************************************************************");
        printStatus("**************Starting Rendezvous or Highest Random Weight (HRW) cache client**************");
        HRWHash<String> hrwHash = new HRWHash(cacheServer);
        printStatus("**************adding to cache servers**************");

        for(int i = 1; i<=10; i++){
            addToHRWCache(i, String.valueOf((char) (i + 96)), hrwHash);
        }

        printStatus("**************Cache Retrieved from servers**************");

        for(int i =1; i<=10; i++){
            String value = (String)getFromHRWCache(i, hrwHash);
            System.out.println("Cache Collected : " + value);
        }

    }
    
    public static void addToHRWCache(int toAddKey, String toAddValue, HRWHash hrwHashClient)
    {
        String cacheUrl = (String) hrwHashClient.getCache(toAddKey);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        cache.put(toAddKey,toAddValue);
        System.out.println("put( " + toAddKey + " ---> " + toAddValue + ")");

    }
    
    public static Object getFromHRWCache(int key, HRWHash hrwHashClient)
    {
        String cacheUrl = (String) hrwHashClient.getCache(key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        String value = cache.get(key);
        System.out.println("get( "+ key+ " ) ----> "+ value);
        return value;
    }
    public static void printStatus(String status)
    {
        System.out.println("********************************************************************");
        System.out.println(status);
        System.out.println("********************************************************************");
    }

}
