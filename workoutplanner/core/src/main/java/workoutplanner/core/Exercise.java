package workoutplanner.core;


public class Exercise implements Comparable<Exercise>{
    
    private String name;
    private String muscleGroup;
    private int sets;
    private int reps;
    private int weight;

    public Exercise(String exerciseName, String muscleGroup, String exerciseSet,String exerciseRep, String exerciseWeight) {
        this.name = exerciseName;
        this.muscleGroup = muscleGroup;
        this.reps = Integer.parseInt(exerciseRep);
        this.sets = Integer.parseInt(exerciseSet);
        this.weight = Integer.parseInt(exerciseWeight);
    }

    public Exercise(String string, String string2) {
        this.name = string;
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
