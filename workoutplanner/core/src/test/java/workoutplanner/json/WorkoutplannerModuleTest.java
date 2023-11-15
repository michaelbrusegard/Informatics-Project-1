//package workoutplanner.json;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.time.LocalDateTime;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import workoutplanner.core.Exercise;
//import workoutplanner.core.User;
//import workoutplanner.core.Workout;
//
//
//public class WorkoutplannerModuleTest {
//
//    private static ObjectMapper mapper;
//
//    @BeforeAll
//    public static void setUp(){
//        mapper = WorkoutplannerPersistence.createObjectMapper();
//    }
//
//    final static String userWithOneWorkout = """
//            [
//              {
//                "name": "Bench",
//                "date": "November 12, 2023 15:55",
//                "saved": true,
//                "exercises": [
//                  {
//                    "name": "Barbell Bench Press",
//                    "sets": 1,
//                    "repMin": 1,
//                    "repMax": 1,
//                    "weight": 1
//                  }
//                ]
//              },
//              {
//                "name": "Biceps",
//                "date": "November 12, 2023 16:08",
//                "saved": true,
//                "exercises": [
//                  {
//                    "name": "Bicep Curls",
//                    "sets": 1,
//                    "repMin": 1,
//                    "repMax": 1,
//                    "weight": 1
//                  }
//                ]
//              }
//            ]
//            """;
//
//    static User createUserWithOneWorkout(){
//        User user = new User();
//        user.addWorkoutFromFile(new Workout());
//        user.setCurrentWorkout(0);
//        user.getCurrentWorkout().addExercise(new Exercise("Barbell Bench Press",1,1,1,1));
//        user.getCurrentWorkout().setName("Bench");
//        user.getCurrentWorkout().setDate("November 12, 2023 15:55");
//        user.getCurrentWorkout().setSaved(true);
//        user.addWorkoutFromFile(new Workout());
//        user.setCurrentWorkout(1);
//        user.getCurrentWorkout().addExercise(new Exercise("Bicep Curls",1,1,1,1));
//        user.getCurrentWorkout().setName("Biceps");
//        user.getCurrentWorkout().setDate("November 12, 2023 16:08");
//        user.getCurrentWorkout().setSaved(true);
//        return user;
//    }
//
//    @Test
//    public void testSerializers(){
//        User user = createUserWithOneWorkout();
//        List<Workout> workoutList = user.getWorkouts();
//        try {
//            StringWriter writer = new StringWriter();
//            assertEquals(userWithOneWorkout.replaceAll("\\s+", ""), mapper.writeValueAsString(workoutList));
//        } catch (JsonProcessingException e) {
//            fail(e.getMessage());
//        }
//    }
//
//}
