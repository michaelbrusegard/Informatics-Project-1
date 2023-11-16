# About this module

Core consists of two parts, which are `core` and `json`.

## Links

### Core

- [Exercise](#exercise)
- [User](#user)
- [UserAccess](#useraccess)
- [Workout](#workout)

### Json

- [ExerciseListLoader](#exercise)
- [WorkoutPresistence](#workoutpersistence)

## Core

The `core` contains the inner layer. This is where the essential and fundamental functionality of the app is. It contains two classes and a record, `Workout`, `User` and `Exercise`. It also contains an interface for `User`, and the `RemoteUserAccess` which is in `fxui`.

### Exercise

The Exercise record is a record that contains five properties

#### Exercise properties

- (`String`) name
- (`int`) sets
- (`int`) repMin
- (`int`) repMax
- (`int`) weight

The rep-range is split into two integer fields, `repMin` and `repMax`, rather than making a string containing both.
All fields except from `name` are ints, because it is more intuitive than Strings.
Since `Exercise` is a record, you can get any of the parameters by calling `<nameofexercise>`.`<nameofproperty>`

### Workout

The workout class creates a workout that contains a list of exercises, as well as a name and a date. It also has a `saved` field.

#### Workout-Fields

- (`Date`) date - See when the workout was created
- (`String`) name - Given in `Overview` to classify
- (`boolean`) saved - Tracks if the workout has been saved
- (`List<Exercise>`) exercises - The exercises that make up a workout

#### Workout-Methods

- addExercise(): Adds an `Exercise` to `exercises`
- removeExercise(): Removes an `Exercise` from `exercises`
- moveExercise(): Moves an `Exercise` based on specific position in `exercises` and the direction
- getters and setters

### User

The user class represents a user in the application. It has a list of workouts and an index of the current workout.

#### User-Fields

- (`int`) currentWorkoutIndex - gets the index of the current workout
- (`List<Workout>`) workouts - A list of all workouts for the user
- (`boolean`) persistence - indicates whether workouts should be loaded or not from a file

#### User-Methods

- removeWorkout(): Removes a specific `Workout` from `workouts` based on input
- removeCurrentWorkout(): Removes the current `Workout` from `workouts`
- getCurrentWorkoutSaved(): Returns true if the current `Workout` is saved.
- getCurrentWorkoutExerciseCount(): Returns the number of exercises in the current `Workout`.
- getCurrentWorkoutName(): Returns the `name` fo the current workout.
- getCurrentWorkoutExerciseAttribute(): Returns a `String` based on the exercise index, and which attribute to consider.
- setCurrentWorkout: Sets the current workout index to be the specified value.
- getWorkoutNames(): Returns all the names of the workouts.
- getWorkoutDates(): Returns all the dates of the workouts.
- addExerciseToCurrentWorkout(): Adds an exercise to the current workout.
- removeExerciseFromCurrentWorkout(): Removes an exercise from the current workout.
- moveExerciseInCurrentWorkout(): Calls `moveExercise` on the current workout to specify what and where to move the exercise
- saveCurrentWorkout(): Saves the workout by setting `name`, `date` and `saved` of the workout.
- deleteUnsavedWorkouts(): Removes the workouts that aren't saved from `workouts`.
- getExerciseList(): gets a list of exercise-names based on JSON.

### UserAccess

This is an interface that is implemented by `User` and `RemoteUserAccess`.

## JSON

The `json` folder contains the classes `ExerciseListLoader` and `WorkoutPersistence`. These classes are used for file-handling and are used to read the required data from json files.

### ExerciseListLoader

`ExerciseListLoader` loads exercises from a JSON file as a list of string and has one static method.

#### ExerciseListLoader-Fields

- (`ObjectMapper`) OBJECT_MAPPER - a static objectmapper for reading from a json file.

#### ExerciseListLoader-Methods

- loadExerciseListFromJson(): Loads the names of the exercises to a list, and returns a list of strings.

### WorkoutPersistence

`WorkoutPersistence` saves the workout for the current session.

#### WorkoutPersistence-Fields

- (`ObjectMapper`) OBJECT_MAPPER - a static objectmapper for reading and writing to a json file.
- (`String`) resourcePath - the defined path for where everything should be saved to

#### WorkoutPersistence-Methods

- loadWorkoutFromJson(): Returns a list of workouts from the designated file if possible.
- saveWorkoutsToJson(): Saves a list of workouts to the designated file.

The date is to see when the workout was created, the name is given in `Overview` to classify, and the list of exercises, are the exercises that shows up in the overview of plans.

# Persistance

Our app has implemented persistence via the library Jackson. This library serializes Java objects to a JSON format, and deserializes JSON back to Java objects. Therefore are additional deserializers and serializers unnecessary for the app.

Because we have direct control over when and how the data is saved, the saving is explicit. Take the case where you save a workout. This runs the saveCurrentWorkout() function in User, which activates the persistence. This is the opposite of implicit saving, where data gets saved automatically.
The presistence saves a list of workouts, making the document-metaphor work best. This is because you can look at the list of workouts as a document. This document is what gets serialized and deserialized, and makes the persistence simple by having only a list to save to and from.
>>>>>>> workoutplanner/core/CORE.md
