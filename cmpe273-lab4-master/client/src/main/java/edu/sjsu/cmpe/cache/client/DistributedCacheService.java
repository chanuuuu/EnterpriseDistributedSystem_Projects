package edu.sjsu.cmpe.cache.client;


import java.io.IOException;
import java.util.concurrent.Future;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;


public class DistributedCacheService implements CacheServiceInterface {
    private final String cacheServerUrl;

    public DistributedCacheService(String serverUrl) {
        this.cacheServerUrl = serverUrl;
    }

    @Override
    public String get(long key) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(this.cacheServerUrl + "/cache/{key}")
                    .header("accept", "application/json")
                    .routeParam("key", Long.toString(key)).asJson();
        } catch (UnirestException e) {
            System.err.println(e);
        }
        String value = response.getBody().getObject().getString("value");

        return value;
    }


    @Override
    public void put(long key, String value) {
    	Future<HttpResponse<JsonNode>> future = null;
    	
        future = Unirest
		        .put(this.cacheServerUrl + "/cache/{key}/{value}")
		        .header("accept", "application/json")
		        .routeParam("key", Long.toString(key))
		        .routeParam("value", value)
		        .asJsonAsync(new Callback<JsonNode>() {

		            public void failed(UnirestException e) {
		                System.out.println("PUT request Failed");
		                	
		            }

		            public void completed(HttpResponse<JsonNode> response) {
		            	System.out.println("PUT request successfully ran");
		            
		            }

		            public void cancelled() {
		                System.out.println("Put request has been cancelled");
		            }

		        });
      
        if (future.isDone()) {
            System.out.println("Failed to add to the cache.");
        }
    }
    
    

    @Override
    public void delete(long key)  {
        Future<HttpResponse<JsonNode>> response = null;
        		
        			response = Unirest
				     .delete(this.cacheServerUrl + "/cache/{key}")
				     .header("accept", "application/json")
				     .routeParam("key", Long.toString(key))
					.asJsonAsync(new Callback<JsonNode>() {

			            public void failed(UnirestException e) {
			                System.out.println("Delete request Failed");
			                	
			            }

			            public void completed(HttpResponse<JsonNode> response) {
			            	System.out.println("Delete request successfully ran");
			            	
			            }

			            public void cancelled() {
			                System.out.println("Delete request has been cancelled");
			            }

			        });
        			
	        if (response.isDone()) {
	            System.out.println("Failed to delete to the cache.");
	        }

    }
    
}
