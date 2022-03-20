package ru.tsu.hits;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.util.Objects;

public class Application {
    public static void main(String[] args) {
//        if (args.length < 1){
//            throw new IllegalArgumentException("Вы не ввели поисковую строку");
//        }

//        var searchString = args[0];
        var csvStream = Application.class.getResourceAsStream("/task.csv");
        var tasks = new CsvToBeanBuilder<TaskCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(TaskCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
        tasks.stream()
//                .filter(taskCsv -> taskCsv.getName().contains(searchString))
                .forEach(System.out::println);
    }
}
