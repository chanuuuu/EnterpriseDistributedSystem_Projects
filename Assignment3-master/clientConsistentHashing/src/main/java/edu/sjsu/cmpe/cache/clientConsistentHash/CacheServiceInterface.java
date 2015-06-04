package edu.sjsu.cmpe.cache.clientConsistentHash;


public interface CacheServiceInterface {
    public String get(long key);

    public void put(long key, String value);
}
