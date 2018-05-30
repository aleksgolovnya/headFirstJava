package collectionsFramework.map;

import java.util.HashMap;

/* Реализация колекции HashMap */

public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("Кэти", 42);
        scores.put("Берт", 111);
        scores.put("Алекс", 666);

        System.out.println(scores);
        System.out.println(scores.get("Берт"));
    }
}
