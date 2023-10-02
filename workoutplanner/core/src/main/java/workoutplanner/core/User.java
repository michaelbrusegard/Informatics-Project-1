package workoutplanner.core;

import fxutil.doc.UserFileHandler;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String name;
    private String passwd;
    private List<Workout> workouts = new ArrayList<>();

    public User(String name, String passwd, List<Workout> workouts) {
        //TODO add validation
        this.name = name;
        this.passwd = passwd;
        this.workouts = workouts;
    }
    

    public void write() {
        UserFileHandler.write(this.toString(),this);
    }

    @Override
    public String toString() {
        return workouts.toString().replaceAll("[]","");
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
