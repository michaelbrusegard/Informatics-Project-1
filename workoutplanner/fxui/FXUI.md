# About this module

In this module all the fx-objects gets handled. This means the app, controllers, cells and handlers.

## links

## fxui

### BaseController
The BaseController class is a superclass of the other controller classes
and determines the base functionality of all controller classes.

#### BaseController-Fields

- mainController - Holds the instance of the MainController for program-wide access.

#### BaseController-Methods

- getMainController(): returns the MainController
- setMainController(): sets the MainController for a sub-controller
- init(): base initialization method to be overwritten by sub-controllers.

### ExerciseViewController
The ExerciseViewController class operates the ExerciseView page and delegates all the tasks related to that page.

#### ExerciseViewController-Fields
- sets, repMin, repMax, weight and name - The different input variables for an Exercise object.
- list - a list of exercises.
- cancelButton - a button to cancel the creation of a workout.

#### ExerciseViewController-Methods
- initialize(): initializes the controller and set up the user interface
- addExercises(): adds an exercise
- cancel(): cancels the creation of a workout
- finish(): finishes the creation of a workout
- init(): initializes the controller's state and UI elements
- clearInputFields(): clears the input-fields.

### FxmlControllerPair
The FxmlControllerPair class maintains the association  

### HomeController
The HomeController class operates the Home page and delegates all the tasks related to that page.

### MainController
The MainController class delegates the different pages of the application to the different controllers.
It contains the controllers in a registry, and switches between them depending on the user input.

### OverviewController
The OverviewController class operates the overview page and delegates all the tasks related to that page.

### RemoteUserAccess
The RemoteUserAccess class handles the communication to the backend
to get the information about the objects needed to run the application.

### WorkoutPlannerApp
The WorkoutPlannerApp class runs the application.

### WorkoutViewController
The WorkoutViewController class operates the WorkoutView page and delegates the tasks related to that page.

## Resources

In resources folder contains all the fxml for all the pages as well as the style sheet for the application.