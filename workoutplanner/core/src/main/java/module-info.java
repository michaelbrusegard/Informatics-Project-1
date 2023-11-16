module core {
  requires com.fasterxml.jackson.databind;
  requires java.logging;

  exports workoutplanner.core;
  exports workoutplanner.json;
  opens workoutplanner.core to com.fasterxml.jackson.databind;
}
