package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class Workout {
    
    private LocalDate date;
    private List<Exercise> exercises = new ArrayList<>();

    public Workout(LocalDate date, List<Exercise> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return exercises.toString().replaceAll("[]", "");
    }

    public static List<Exercise> makeList(String line) {
        String[] items = line.split(",");
        List<Exercise> list = new ArrayList<>();
        for (int i = 0; i < items.length; i += 2) {
            list.add(new Exercise(new TextField(items[i]), (items[i + 1])));
        }
        return list;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
    
}
