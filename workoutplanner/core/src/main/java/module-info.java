module core {
  requires com.fasterxml.jackson.databind;

  exports workoutplanner.core;

  opens workoutplanner.core to com.fasterxml.jackson.databind;
}
