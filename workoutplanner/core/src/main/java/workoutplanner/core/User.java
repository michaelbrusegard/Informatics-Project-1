package workoutplanner.core;

import java.util.List;

public class User {
    
    private final String name;
    private final String passwd;
    private final List<Workout> workouts;

    public User(String name, String passwd, List<Workout> workouts) {
        //TODO add validation
        this.name = name;
        this.passwd = passwd;
        this.workouts = workouts;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    

}
