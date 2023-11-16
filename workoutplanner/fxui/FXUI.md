# About this module

In this module, all the ui gets handled. This means most of the frontend, meaning the app and controllers. It has a resources folder containing all the fxml files, where the `Main.fxml` contains the other fxml-views.

## Links

- [BaseController](#basecontroller)
- [ExerciseViewController](#exerciseviewcontroller)
- [FxmlControllerPair](#fxmlcontrollerpair)
- [HomeController](#homecontroller)
- [MainController](#maincontroller)
- [OverviewController](#overviewcontroller)
- [RemoteUserAccess](#remoteuseraccess)
- [WorkoutViewController](#workoutviewcontroller)

## BaseController

The BaseController class is a superclass of the other controller classes
and determines the base functionality of all controller classes.

### BaseController-Fields

- (`MainController`) mainController - Holds the instance of the MainController for program-wide access.

### BaseController-Methods

- getMainController(): returns the MainController
- setMainController(): sets the MainController for a sub-controller
- init(): base initialization method to be overwritten by sub-controllers.

`init()` is a function used in a lot of controllers in this app. The reason we have this method instead of `initialize()` is because the initialize function runs at the moment you start the app, resulting in a grid made before any workout-objects or exercise-objects are added. What we instead want is for the controllers to initialize when we f.ex. Press a save button.

## ExerciseViewController

The ExerciseViewController class operates the ExerciseView page.

### ExerciseViewController-Fields

- (`TextField`) sets, repMin, repMax, weight and (`Text`) name - The different input variables for an `Exercise` object.
- (`ListView<String>`) list - a list of exercises.
- (`Button`) cancelButton - a button to cancel the creation of a workout.

#### ExerciseViewController-Methods

- initialize(): initializes the controller and sets up the user interface
- addExercises(): adds an exercise to the list of exercises
- cancel(): cancels the creation of a workout
- finish(): finishes the creation of a workout
- init(): initializes the controller's state and UI elements
- clearInputFields(): clears the input-fields.

### FxmlControllerPair

The FxmlControllerPair class maintains the association between an FXML file and its Controller. This is a delegate that shows and hides fxml-views based on input.

#### FxmlControllerPair-Fields

- (`VBox`) fxml - the fxml being displayed
- (`BaseController`) baseController - the baseController connected to fxml

#### FxmlControllerPair-Constructor

Creates a new `FxmlControllerPair` object, which associates an FXML view with its respective controller and the MainController. It then sets the proper visibility of the different fxml-views.

#### FxmlControllerPair-Methods

- show(): Shows the new fxml and calls its init function
- hide(): sets visibility false for the fxml.

### HomeController

The HomeController class operates the Home page and delegates all the tasks related to that page. It has two buttons, one for showing all workouts and the other for creating a new workout.

#### HomeController-Methods

- createNewWorkout(): Takes you to the `ExerciseView`
- showAllWorkouts(): Takes you to `WorkoutView`
- init(): initializes the controller by setting the current workout to 0 and deleting unsaved workouts.

### MainController

The MainController class delegates the different pages of the application to the different controllers. This acts as a hub for the controllers, where it has a method for which view to show.
It has a constructor that initializes the remote URL if backend is activated, otherwise it initializes a new `User` object.

#### MainController-Fields

- All the different controllers as separate variables
- All the fxml files connected to each controller as separate variables
- (`UserAccess`) user - the `User` object
- (`Map<String, FxmlControllerPair>`) fxmlControllerMap - maps the specific controller based on the string

#### MainController-Methods

- initialize(): runs as soon as the program is started, it adds all the overviews to the map, and runs showFxml
- showFxml(): takes the given controller-name and shows it. It also hides the previous fxml.

### OverviewController

The OverviewController class operates the overview page and shows a grid of selected exercises for the current workout.

#### OverviewController-Fields

- (`VBox`) saveWorkoutNameBox - Box for when you are creating a workout
- (`HBox`) workoutInfoBox - Box for when you are editing a workout
- (`ScrollPane`) scrollPane - pane where the grid is added
- (`TextField`) inputName - Where you add the name of your workout
- (`Text`) name - The name of your workout

#### OverviewController-Methods

- cancel(): Cancels the creation of a workout and returns you to `Home`
- save(): Saves the workout and shows the `WorkoutView`
- returnWorkoutView(): Action for button that takes you to `WorkoutView`
- addExercise(): Action for button that takes you to `ExerciseView` to add exercises
- init(): Initializes the overview and starts the grid
- buildGrid(): Creates the grid using the `GridBuilder` class in fxutil
- createCell(): Creates a cell based on index consisting of each value of the `Exercise`
- move(): Moves an `Exercise` left or right

### RemoteUserAccess

This is a `UserAccess` object that handles the communication with the backend. It is through this the backend receives requests.
It takes a URI as an input and saves it as the baseUri.

#### RemoteUserAccess-Fields

- (`URI`) baseUri - Local URI variable used for pathing
- (`ObjectMapper`) objectMapper - A class from the jacksonlibrary used for persistence
- (`Logger`) LOGGER - used for logging exceptions

#### RemoteUserAccess-Methods

- httpGetRequest(): sends a get request made with the specified path and `"/user"`.
- httpPutRequest() and httpDeleteRequest() are similar methods as httpGetRequest()
- handleError(): handles the connection and checks if it is valid
- getters and setters for most of the values of a workout using requests.

### WorkoutPlannerApp

The WorkoutPlannerApp class runs the application with the `Application` start method. Here we define the size of the stage, and run the main function to launch the app.

### WorkoutViewController

The WorkoutViewController class operates the WorkoutView page and delegates the tasks related to that page. This shows a grid of all workouts. You have the option of viewing or deleting a `Workout` from the grid.

#### WorkoutViewController-Fields

- (`ScrollPane`) scrollPane - Pane where the grid is added
- (`List<String>`) workoutNames - List of all the workouts' names
- (`List<String>`) workoutDates - List of all the workouts' dates

#### WorkoutViewController-Methods

- returnHome(): Action when clicking button, that moves you to `Home`
- init(): Initializes the `WorkoutView` and starts the grid
- buildGrid(): Creates the grid using the `GridBuilder` class in fxutil
- createCell(): Creates a cell based on index consisting of the `date` and `name` of the `Workout`
- view(): Action when clicking "view" button. Takes you to the specific workout
  