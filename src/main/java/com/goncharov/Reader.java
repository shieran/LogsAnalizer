package com.goncharov;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Reader {

    public List<Row> read(String path) throws IOException {
        Parser parser = new Parser();
        List<String> rows = new LinkedList<>();
        List<String> lines = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .map(Objects::toString)
                .collect(Collectors.toList());
        for (String s : lines) {
            Files.lines(Paths.get(s), StandardCharsets.UTF_8).forEach(rows::add);
        }
        List<Row> rowObjects = new LinkedList<>();
        for (String string : rows) {
            rowObjects.add(parser.parse(string));
        }
        return rowObjects;
    }

}
