module workoutplanner.core {
  requires transitive com.fasterxml.jackson.databind;
  exports workoutplanner.core;
//  exports workoutplanner.json;
//  exports workoutplanner.json.internal to com.fasterxml.jackson.databind;

  opens workoutplanner.core to com.fasterxml.jackson.databind;
}

