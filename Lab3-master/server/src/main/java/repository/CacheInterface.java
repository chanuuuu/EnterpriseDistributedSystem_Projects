import java.util.List;

import domain.Entry;

public interface CacheInterface {

    Entry save(Entry newEntry);
    Entry get(Long key);
    List<Entry> getAll();

}
