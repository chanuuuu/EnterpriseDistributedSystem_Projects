package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

public class CRDTClient {
	private List<DistributedCacheService> cacheServerList;
	
	
	private List<String> urls = new ArrayList<String>();
	public CRDTClient()
	{
		urls.add("http://localhost:3000");
		urls.add("http://localhost:3001");
		urls.add("http://localhost:3002");
		
		
		DistributedCacheService cache1 = new DistributedCacheService(urls.get(0));
		DistributedCacheService cache2 = new DistributedCacheService(urls.get(1));
		DistributedCacheService cache3 = new DistributedCacheService(urls.get(2));

		this.cacheServerList = new ArrayList<DistributedCacheService>();

		cacheServerList.add(cache1);
		cacheServerList.add(cache2);
		cacheServerList.add(cache3);
	}
	
	
	public void put(long key, String value)
	{
		for (final DistributedCacheService cache : cacheServerList) {
					cache.put(key, value);
		}
		
		//countValues(key, value);
		
	}

	public void updateValues(long key, String value)
	{
		String str = "";
		int val = 0;
		List<String> responses = new ArrayList<String>();
		for (final DistributedCacheService cache : cacheServerList) {
			try
			{
				str="";
				str = cache.get(key);
				
			
					if(str.equals(value)){
									val++;
									responses.add("true");
						}
					else
					{
						responses.add("false");
						cache.delete(key);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cache.put(key,value);
					}
			}
			catch(NullPointerException e)
			{
				cache.put(key,value);
				
			}
		}
		
			
	}
	
	
	public void update(long key, String value)
	{
		
		
		
		for (final DistributedCacheService cache : cacheServerList) {
			
				cache.delete(key);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cache.put(key,value);
			
		}
		
			
	}
	
	
	public void get(long key)
	{
		int val = 0;
		String str = "";
		for (final DistributedCacheService cache : cacheServerList) {
			try
			{
				str="";
				str = cache.get(key);
				 
			}
			catch(NullPointerException e)
			{
				
				
			}
			System.out.println("Value from " + urls.get(val) + ": " + str);
			val++;
			}
		
		
	}
	
	public void delete(long key)
	{
		
		int val = 0;
		for (final DistributedCacheService cache : cacheServerList) {
					cache.delete(key);
					System.out.println("The key from " + urls.get(val) + " has been deleted" );
					
					val++;
			}
	}
}
