package com.goncharov;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Row parse(String text) {
        Row row = new Row();
        Matcher matcherUsername = Pattern
                .compile("User: .+Date")
                .matcher(text);
        Matcher matcherDate = Pattern
                .compile("Date: .+Message")
                .matcher(text);
        Matcher matcherMessage = Pattern
                .compile("Message: .+")
                .matcher(text);
        while (matcherUsername.find()) {
            row.setUsername(text.substring(matcherUsername.start() + 6, matcherUsername.end() - 5));
        }
        while (matcherDate.find()) {
            row.setDate(text.substring(matcherDate.start() + 6, matcherDate.end() - 8));
        }
        while (matcherMessage.find()) {
            row.setMessage(text.substring(matcherMessage.start() + 9, matcherMessage.end()));
        }
        return row;
    }

}
