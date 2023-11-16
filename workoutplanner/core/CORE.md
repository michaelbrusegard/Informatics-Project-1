# About this module

In the core module there are two classes: Exercise and Workout.

## Exercise

The Exercise object is the object that contains the core fields of what an exercise should be. These values are the ones displayed in `Overview`.

### Exercise-Fields

- (`String`) name
- (`int`) sets
- (`int`) repMin
- (`int`) repMax
- (`int`) weight

The rep-range is split into two integer fields, `repMin` and `repMax`, rather than making a string containing both.
All fields except from `name` are ints, because it is more intuitive than Strings.

### Exercise-Methods

- getName()
- getSets()
- getRepMin()
- getRepMax()
- getWeight()

The only methods in the Exercise class are getters. Setters are unnecessary because the values of the exercises gets set in the constructor.

## Workout

The workout class creates a workout that contains a list of exercises, as well as a name and a date.

### Workout-Fields

- (`Date`) date
- (`String`) name
- (`List<Exercise>`) exercises

The date is to see when the workout was created, the name is given in `Overview` to classify, and the list of exercises, are the exercises that shows up in the overview of plans.

# Persistance

Our app has implemented persistence via the library Jackson. This library serializes Java objects to a JSON format, and deserializes JSON back to Java objects. Therefore are additional deserializers and serializers unnecessary for the app.

Because we have direct control over when and how the data is saved, the saving is explicit. Take the case where you save a workout. This runs the saveCurrentWorkout() function in User, which activates the persistence. This is the opposite of implicit saving, where data gets saved automatically.
The document-metaphor works better because you can see the list of workouts as a document. This document is what gets serialized and deserialized, and makes the persistence simple by having only a list to save to and from.
