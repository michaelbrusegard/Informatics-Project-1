package fxutil.doc;

import gr2307.workoutplanner.core;

import java.io.PrintStream;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExerciseFileHandler {
    
    private static PrintStream printer;
    private static BufferedReader reader;

    public static void write(Exercise exer) {
        try {
            printer = new PrintStream(new FileOutputStream("Exercises.txt", true));
            printer.println((String)(exer.toString()));
            printer.close();
        } catch (Exception e) {
            //TODO: bør byttes med alert
            e.printStackTrace();
        }
    }
    
    public static List<Exercise> read() throws IOException {
        String lines = ""; 
        try {
            reader = new BufferedReader(new FileReader("Exercises.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines += line;
            }
        } catch (Exception e) {
            //TODO: bør byttes med alert
            e.printStackTrace();
        }
        return Workout.makeList(lines);
        }

}
