package ru.tsu.hits;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
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
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getType));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getType).reversed());
                    break;
                case "name":
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getName));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getName).reversed());
                    break;
                case "performer":
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPerformer));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPerformer).reversed());
                    break;
                case "priority":
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPriority));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getPriority).reversed());
                    break;
                case "date_creation":
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getDate_creation));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getDate_creation).reversed());
                    break;
                case "author":
                    if (Objects.equals(entry.getValue(), "asc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getAuthor));
                    else if (Objects.equals(entry.getValue(), "desc"))
                        sortedList = sortedList.sorted(Comparator.comparing(TaskCsv::getAuthor).reversed());
                    break;
            }
        }

        sortedList.forEach((elem) -> {
            try (FileWriter writer = new FileWriter("result.txt", true)) {
                writer.write(
                        "Тип задачи: " + elem.getType() + '\n' +
                                "Название: " + elem.getName() + '\n' +
                                "Автор: " + elem.getAuthor() + '\n' +
                                "Исполнитель: " + elem.getPerformer() + '\n' +
                                "Приоритет: " + elem.getPriority() + '\n' +
                                "Дата создания: " + elem.getDate_creation() + '\n'
                );
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

}

