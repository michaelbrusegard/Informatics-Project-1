package workoutplanner.core;


public class Exercise implements Comparable<Exercise>{
    
    private String name;
    private String muscleGroup;
    private String sets;
    private String reps;
    private String weight;

    public Exercise(String exerciseName, String muscleGroup, String exerciseSet,String exerciseRep, String exerciseWeight) {
        this.name = exerciseName;
        this.muscleGroup = muscleGroup;
        this.reps = exerciseRep;
        this.sets = exerciseSet;
        this.weight = exerciseWeight;
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

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name+"," + muscleGroup+",";
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

}
