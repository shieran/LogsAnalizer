package com.goncharov;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Sorter{

    public void sorterByName(List<Row> rows, String name) {
        if(name.equals("NO")){
            return;
        }
        rows.removeIf(row -> !row.getUsername().equals(name));
    }

    public void sorterByMessage(List<Row> rows, String message) {
        if(message.equals("NO")) {
            return;
        }
        rows.removeIf(row -> !row.getMessage().contains(message));
    }

    public void sorterByDate(List<Row> rows, String dateFrom, String dateTo) {
        if(dateFrom.equals("NO") || dateTo.equals("NO")) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        rows.removeIf(row -> {
            try {return format.parse(row.getDate()).before(format.parse(dateFrom))
                       || format.parse(row.getDate()).after(format.parse(dateTo));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

}
