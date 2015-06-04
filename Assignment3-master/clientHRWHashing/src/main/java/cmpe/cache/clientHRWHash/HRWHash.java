package cmpe.cache.clientHRWHash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**********************
 * 
 * @author Chanikya Mandapathi
 *
 * @param <T>
 */

public class HRWHash<T> {

    private final HashFunction hashFunction;
    private final HashMap<Integer,T> nodeList = new HashMap<Integer,T>();

    public HRWHash(Collection<T> nodes){

        this.hashFunction = Hashing.md5();
        for(T node: nodes){
            add(node);
        }
        System.out.println("Cache-Node list as of now  : " + nodeList);
        System.out.println("New List: "+ nodeList);
    }
    public void add(T node) {

        int hash = hashFunction.newHasher().putString(node.toString()).hash().asInt();
        System.out.println("hash when adding : " + hash);
        nodeList.put(hash, node);

    }

    public T getCache(Object key){
        if(nodeList.isEmpty()){
            return null;
        }
        Integer maxHash = Integer.MIN_VALUE;
        T maxNode = null;

        for (Map.Entry<Integer, T> node : nodeList.entrySet()) {
            int temp = hashFunction.newHasher()
                    .putString(key.toString())
                    .putString(node.getValue().toString()).hash().asInt();

            if (temp > maxHash) {
                maxHash = temp;
                maxNode = node.getValue();

            }
        }

        return maxNode;
    }





}
