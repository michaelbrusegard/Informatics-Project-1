package workoutplanner.core;

import javafx.scene.control.TextField;

public class Exercise implements Comparable<Exercise>{
    
    private String name;
    private String muscleGroup;
    private int sets;
    private int reps;
    private int weight;

    public Exercise(TextField exerciseName, String muscleGroup, TextField exerciseSet,TextField exerciseRep, TextField exerciseWeight) {
        this.name = exerciseName.getText();
        this.muscleGroup = muscleGroup;
        this.reps = Integer.parseInt(exerciseRep.getText());
        this.sets = Integer.parseInt(exerciseSet.getText());
        this.weight = Integer.parseInt(exerciseWeight.getText());
    }

    public Exercise(TextField string, String string2) {
        this.name = string.getText();
        this.muscleGroup = string2;
    }

    @Override
    public int compareTo(Exercise arg0) {
        //Use Collections.sort(list name) to sort the list based on muscle group
        return this.muscleGroup.compareTo(arg0.getMuscleGroup());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name+"," + muscleGroup+",";
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

}
