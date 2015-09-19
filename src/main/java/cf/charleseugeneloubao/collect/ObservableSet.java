package cf.charleseugeneloubao.collect;

import java.util.HashSet;

/**
 * Created by charl on 9/19/2015.
 */
public class ObservableSet<V,K> extends HashSet<V> {

    private K key;
    HashSet<OnObjectChangedListener<? extends ObservableSet<V,K>>> listeners = new HashSet<>();

    public void setKey(K key) {
        this.key = key;
    }

    public void addOnObjectChangedListener(OnObjectChangedListener<? extends ObservableSet<V,K>> listener) {
        listeners.add(listener);
    }

    public K getKey() {
        return key;
    }

    public void removeSetObserver(OnObjectChangedListener<? extends ObservableSet> observer) {
        listeners.remove(observer);
    }
    @Override
    public boolean remove(Object o) {
        boolean removed = super.remove(o);
        for(OnObjectChangedListener listener : listeners) {
            listener.onChange(listener);
        }
        return removed;
    }

    @Override
    public boolean add(V v) {
        boolean added = super.add(v);
        for (OnObjectChangedListener listener : listeners) {
            listener.onChange(this);
        }
        return added;
    }

    public interface OnObjectChangedListener<T> {
        void onChange(T object);
    }

}
