module restapi {
  requires spring.web;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires java.logging;
  requires com.fasterxml.jackson.databind;
  requires core;

  opens workoutplanner.restapi to com.fasterxml.jackson.databind;
}
