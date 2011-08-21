package no.karianneberg.util;

import java.util.HashMap;
import java.util.Map;

public class Maps {


    public static <K,V> Map<K,V> asMap(Map.Entry<K,V>... entries) {
        Map<K,V> map = new HashMap<K,V>();
        for(Map.Entry<K,V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static <K,V> Map.Entry<K,V> entry(final K k, final V v) {
        return new Map.Entry<K, V>() {
            public K getKey() {
                return k;
            }
            public V getValue() {
                return v;
            }
            public V setValue(V value) {
                throw new UnsupportedOperationException("Not supported");
            }
        };
    }
}