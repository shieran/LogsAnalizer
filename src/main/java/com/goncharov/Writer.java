package com.goncharov;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Writer {

    public void write(List<Row> rows, String outputPath, Grouper grouper) throws IOException {
        rows.stream()
                .sorted(Comparator.comparing(Row::getDate));
        StringBuilder stringBuilder = new StringBuilder();
        List<String> strings = grouper.getGrouperInfo();
        strings.forEach(s ->
            stringBuilder.append(s).append("\n")
        );
        stringBuilder.append("\n");
        rows.forEach(row ->
            stringBuilder.append(row.toString()).append("\n")
        );
        String string = stringBuilder.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss.ms");
        String fileName = "filtered logs " + dateFormat.format(new Date());
        Files.write(Paths.get(outputPath + "\\" + fileName + ".txt"), Collections.singleton(string), StandardCharsets.UTF_8);
    }

}
