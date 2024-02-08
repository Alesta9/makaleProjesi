package org.example;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Yazarın Makalesini Girin: ");
        String makale1 = scanner.nextLine();

        System.out.println("2. Yazarın Makalesini Girin: ");
        String makale2 = scanner.nextLine();

        System.out.println("3. Makaleyi Girin: ");
        String makale3 = scanner.nextLine();

        int benzerlik13 = benzerlikHesapla(top10Word(makale1),top10Word(makale3));

        int benzerlik23 = benzerlikHesapla(top10Word(makale2),top10Word(makale3));

        if(benzerlik13 > benzerlik23) {
            System.out.println("3. Makale 1. Yazara aittir.");
        } else if (benzerlik23 > benzerlik13){
            System.out.println("3. Makale 2. Yazara aittir.");
        } else {
            System.out.println("3. Makale 2 yazara da eşit oranda benzemektedir.");
        }




    }
    public static int benzerlikHesapla(String[] makale1, String[] makale2) {
        int benzerlikCount = 0;
        for(String i : makale1) {
            for(String j : makale2) {
                if(i.equals(j)) {
                    benzerlikCount++;
                }
            }
        }
        return benzerlikCount;
    }
    public static String[] top10Word(String makale) {
        String noktalamasizMetin = makale.replaceAll("[^a-zA-Z0-9\\sçğşıüöıÇĞİÜÖ]", "");

        String baglacsizMetin = noktalamasizMetin.toLowerCase().replaceAll("\\s*\\b(ve|veya|ama|fakat|ile|ancak|yahut|hem|de|ise|amaç|çünkü|neden|mademki|gibi|örneğin|mesela)\\b\\s*", " ").trim();

        String[] kelimeler = baglacsizMetin.split(" ");

        Map<String,Integer> kelimeCount = new HashMap<>();

        for(String i : kelimeler) {
            if(kelimeCount.containsKey(i)) {
                kelimeCount.put(i,kelimeCount.get(i)+1);
            } else {
                kelimeCount.put(i,1);
            }
        }
        Map<String,Integer> sortedMap = getFirst10Element(sortByValue(kelimeCount));
        String[] keysArray = sortedMap.keySet().toArray(new String[0]);

        return keysArray;

    }
    public static Map<String, Integer> getFirst10Element(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .limit(10)
                .collect(LinkedHashMap::new,
                        (oldValue, newValue) -> oldValue.put(newValue.getKey(), newValue.getValue()),
                        LinkedHashMap::putAll);
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        // EntrySet'ten Stream oluştur, value'ya göre sırala (büyükten küçüğe) ve sıralı bir Map döndür
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(LinkedHashMap::new,
                        (oldValue, newValue) -> oldValue.put(newValue.getKey(), newValue.getValue()),
                        LinkedHashMap::putAll);
    }
}