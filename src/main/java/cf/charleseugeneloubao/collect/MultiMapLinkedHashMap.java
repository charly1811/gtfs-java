package cf.charleseugeneloubao.collect;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * HashMap that support multiple values key-pair and has a predictable order
 */
public class MultiMapLinkedHashMap<K,V> implements ObservableSet.OnObjectChangedListener<ObservableSet<V,K>>{
    private LinkedHashMap<K,ObservableSet<V,K>> map = new LinkedHashMap<>();

    public Set<V> put(K key, V value) {
        initializeKey(key);
        ObservableSet<V,K> set = map.get(key);
        set.add(value);
        return set;
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Set<V> get(K key) {
        return map.get(key);
    }

    public Set<V> remove(K key) {
        return map.remove(key);
    }

    public void putAll(K key, Collection<? extends V> collection) {
        initializeKey(key);
        map.get(key).addAll(collection);
    }

    private void initializeKey(K key) {
        if(!map.containsKey(key)) {
            map.put(key, new ObservableSet<V, K>());
        }
    }

    @Override
    public void onChange(ObservableSet<V,K> object) {
        if(object.isEmpty()) {
            remove(object.getKey());
        }
    }
}
