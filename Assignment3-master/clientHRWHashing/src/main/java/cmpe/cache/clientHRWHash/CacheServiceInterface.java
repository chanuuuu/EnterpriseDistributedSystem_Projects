package cmpe.cache.clientHRWHash;

/**********************
 * Cache Service Interface
 * @author Chanikya Mandapathi
 *
 */
public interface CacheServiceInterface {
    public String get(long key);

    public void put(long key, String value);
}
