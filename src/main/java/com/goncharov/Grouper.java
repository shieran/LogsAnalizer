package com.goncharov;

import java.util.*;

public class Grouper {

    private List<String> grouperInfo;

    public Grouper(List<String> grouperInfo) {
        this.grouperInfo = grouperInfo;
    }

    public List<String> getGrouperInfo() {
        return grouperInfo;
    }

    public void groupByDate(List<Row> rows) {
        Map<String, Integer> mp = new HashMap<>();
        for (Row row : rows) {
            if(mp.keySet().contains(row.getDate().substring(0, 10))) {
                mp.put(row.getDate().substring(0, 10), mp.get(row.getDate().substring(0, 10)) + 1);
            } else {
                mp.put(row.getDate().substring(0, 10), 1);
            }
        }
        mp.forEach((k, v) -> grouperInfo.add("Date: " + k + " Number of logs: " + v.toString()));
        Collections.sort(grouperInfo);
    }

    public void groupByUsername(List<Row> rows, String username){
        long count = rows.stream()
                .filter(row -> row.getUsername().equals(username))
                .count();
        grouperInfo.add("User " + username + " have: " + count + " message(s)");
    }

    public void groupByMessage(List<Row> rows, String message) {
        long count = rows.stream()
                .filter(row -> row.getMessage().contains(message))
                .count();
        grouperInfo.add(message + " count of messages contains text: " + count);
    }
}
