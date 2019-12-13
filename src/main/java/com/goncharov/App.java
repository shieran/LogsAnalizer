package com.goncharov;

import java.io.IOException;
import java.util.LinkedList;

public class App {

    public static void main(String[] args) throws IOException {
        new LogsFilterStarter(new LinkedList<>()).doFilter();
    }
}
