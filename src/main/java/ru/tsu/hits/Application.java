package ru.tsu.hits;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.util.*;

public class Application {
    public static void main(String[] args) {
//        if (args.length < 1){
//            throw new IllegalArgumentException("Вы не ввели поисковую строку");
//        }

        Map<String, String> sortMap = new HashMap<String, String>();
        Map<String, String> filtersMap = new HashMap<String, String>();

        int index = 0;
        while (index < args.length) {
            String[] elem = args[index + 1].split("=");

            switch (args[index++]) {
                case "-f":
                    filtersMap.put(elem[0], elem[1]);
                    break;
                case "-s":
                    sortMap.put(elem[0], elem[1]);
                    break;
            }
            index++;
        }

        var csvStream = Application.class.getResourceAsStream("/task.csv");
        var tasks = new CsvToBeanBuilder<TaskCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(TaskCsv.class)
                .withSkipLines(1)
                .build()
                .parse();

        var filteredList = tasks.stream()
                .filter(taskCsv -> filtersMap.get("type") == null || taskCsv.getType().contains(filtersMap.get("type")))
                .filter(taskCsv -> filtersMap.get("name") == null || taskCsv.getName().contains(filtersMap.get("name")))
                .filter(taskCsv -> filtersMap.get("author") == null || taskCsv.getAuthor().contains(filtersMap.get("author")))
                .filter(taskCsv -> filtersMap.get("performer") == null || taskCsv.getPerformer().contains(filtersMap.get("performer")))
                .filter(taskCsv -> filtersMap.get("date_creation") == null || taskCsv.getDate_creation().contains(filtersMap.get("date_creation")))
                .filter(taskCsv -> filtersMap.get("priority") == null || taskCsv.getPriority().contains(filtersMap.get("priority")));

        var sortedList = filteredList;

        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            switch (entry.getKey()) {
                case "type":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getType));
                    break;
                case "name":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getName));
                    break;
                case "performer":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPerformer));
                    break;
                case "priority":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPriority));
                    break;
                case "date_creation":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getDate_creation));
                    break;
                case "author":
                    sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getAuthor));
                    break;
            }
        }
        
        sortedList.forEach((elem) -> {
            System.out.println(elem);
        });
    }

}

