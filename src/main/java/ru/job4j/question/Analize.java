package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> prevMap = new HashMap<>();
        for (User u : previous) {
            prevMap.putIfAbsent(u.getId(), u.getName());
        }
        int added = 0;
        int changed = 0;
        int deleted = previous.size();
        for (User u : current) {
            int idTmp = u.getId();
            if (prevMap.containsKey(idTmp)) {
                deleted--;
                if (!prevMap.get(idTmp).equals(u.getName())) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}