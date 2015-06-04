package edu.sjsu.cmpe.cache.clientConsistentHash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.ArrayList;



public class ConsistentHashSimple<T> {

    private final HashFunction hashFunction;
    private ArrayList<T> nodeList;



    public ConsistentHashSimple(ArrayList<T> nodes){
        this.hashFunction = Hashing.md5();
        nodeList  = new ArrayList<T>();
        nodeList.addAll(nodes);
        System.out.println("*************************************************");
        System.out.println("Cache-Node list as of now  : " + nodeList);
        System.out.println("*************************************************");
    }

    public void add(T node){
        nodeList.add(node);
    }

    public void remove(T node){
        nodeList.remove(node);
    }

    public T getCache(Object key){
        int bucket = Hashing.consistentHash(hashFunction.newHasher().putString(key.toString()).hash(), nodeList.size());
        System.out.println("Nodes present at Node: localhost:300"+bucket);
        return nodeList.get(bucket);
    }
}
