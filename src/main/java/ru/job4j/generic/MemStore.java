package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        T v = storage.get(model.getId());
        if (v == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        boolean b = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            b = true;
        }
        return b;
    }

    @Override
    public boolean delete(String id) {
        boolean b = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            b = true;
        }
        return b;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}