package fxutil.doc;

import gr2307.workoutplanner.core;
import gr2307.workoutplanner.core;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserFileHandler {

    static PrintStream printer;
    static BufferedReader reader;

    public static void write(String str, User user) {
        try {
            printer = new PrintStream("UserWorkouts.txt");
            printer.println(user.getName());
            printer.println(user.getPasswd());
            for (Workout workout : user.getWorkouts()) {
                printer.println(workout.getDate().toString());
                printer.println(workout.toString());
            }
            printer.flush();
        } catch (Exception e) {
            // TODO: bør byttes med alert
            e.printStackTrace();
        }
    }

    public static User read() throws IOException {
        reader = new BufferedReader(new FileReader("UserWorkouts.txt"));
        String name = reader.readLine();
        String passwd = reader.readLine();
        List<Workout> workouts = new ArrayList<>();
        String str;
        while((str = reader.readLine())!=null){
            LocalDate date = LocalDate.parse(reader.readLine());
            workouts.add(new Workout(date, Workout.makeList(reader.readLine())));
        }
        User user = new User(name,passwd,workouts);
        return user;
    }
}