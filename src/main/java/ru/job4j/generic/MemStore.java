package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    /*весь метод ниже можно заменить на putIfAbsent*/
    @Override
    public void add(T model) {
        T v = storage.get(model.getId());
        if (v == null) {
            storage.put(model.getId(), model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}