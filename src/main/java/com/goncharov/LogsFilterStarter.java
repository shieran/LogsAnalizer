package com.goncharov;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LogsFilterStarter {

    private List<Row> rows;

    public LogsFilterStarter(List<Row> rows) {
        this.rows = rows;
    }

    public void doFilter() throws IOException {

        Reader reader = new Reader();
        Sorter sorter = new Sorter();
        Writer writer = new Writer();
        Grouper grouper = new Grouper(new LinkedList<>());

        Scanner scanner = new Scanner(System.in);

        System.out.println("введите полный путь к каталогу, в котором лежат файлы с логами");
        String inputPath = scanner.nextLine();
        rows = reader.read(inputPath);

        System.out.println("если хотите отфильтровать по ключевому слову в логах, введите его (возможно только одно слово). Или введите NO");
        String filterMessage = scanner.nextLine();
        sorter.sorterByMessage(rows, filterMessage);

        System.out.println("если хотите отфильтровать по пользователю, введите его имя. Или введите NO");
        String filterUsername = scanner.nextLine();
        if (!filterUsername.equals("NO")) {
            sorter.sorterByName(rows, filterUsername);
        }

        System.out.println("если хотите отфильтровать по датам, введите дату ОТ в формате yyyy.MM.dd HH.mm.ss.ms + ENTER. Или введите NO");
        String filterDateFrom = scanner.nextLine();
        if (!filterDateFrom.equals("NO")) {
            System.out.println("введите дату ДО в формате yyyy.MM.dd HH.mm.ss.ms + ENTER");
            String filterDateTo = scanner.nextLine();
            sorter.sorterByDate(rows, filterDateFrom, filterDateTo);
        }

        System.out.println("если хотите вывести статистику по конкретному пользователю, введите его имя. Или введите NO");
        String groupUsername = scanner.nextLine();
        if (!groupUsername.equals("NO")) {
            grouper.groupByUsername(rows, groupUsername);
        }
        System.out.println("если хотите вывести статистику по ключевому слову, введите его. Или введите NO");
        String groupMessage= scanner.nextLine();
        if (!groupMessage.equals("NO")) {
            grouper.groupByMessage(rows, groupMessage);
        }
        System.out.println("если хотите вывести статистику по датам, введите YES, или введите NO");
        String sortByDate = scanner.nextLine();
        if (!sortByDate.equals("NO")) {
            grouper.groupByDate(rows);
        }

        System.out.println("введите полный путь к дирректории, в которую хотите сохранить файл");
        String outputPath = scanner.nextLine();
        writer.write(rows, outputPath, grouper);
    }

}
