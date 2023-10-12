# About this module

In this module all the fx-objects gets handled. This means the app, controllers, cells and handlers.

## links

[WorkoutPlannerApp](#workoutplannerapp)
[HomeController](#homecontroller)
[ExerciseCell](#exercisecell)
[ExerciseViewController](#exerciseviewcontroller)
[HomeController](#homecontroller)
[OverviewGridHandler](#overviewgridhandler)
[PlanController](#plancontroller)
[PlanGridHandler](#plangridhandler)
[WorkoutCell](#workoutcell)
[WorkoutPlannerApp](#workoutplannerapp)
[UIUtils](#uiutils)

## WorkoutPlannerApp

This is the App that runs the code. It has the start and main method, and runs with maven.

## HomeController

This is the code for the controller to the App.

### HomeController-Fields

- (`Button`) newWorkout
- (`Button`) allWorkouts
  
The newWorkout-button sends you to exerciseview, while the allWorkouts-button sends you to a grid of all the workouts.

### HomeController-Methods

- handleNewWorkout()
- handleAllWorkout()

`handleNewWorkout()` does the code for the noewWorkout-button (sends you to exerciseview), while `handleAllWorkout()` sends you to the grid of workouts.

## ExerciseViewController

The ExerciseViewController class is a controller for the `Exerciseview.fxml`, where you can select exercise and different parameters to then add them to your workout.

### ExerciseViewController-Fields

- (`TextField`) sets
- (`TextField`) repMin
- (`TextField`) repMax
- (`TextField`) weight
- (`Text`) name
- (`ListView<String>`) list
- (`Button`) finishButton
- (`Workout`) workout

The TextFields are fields that gets added to a new exercise when you finish. The name field gets added from the predefined exercise-names, that we get from the ListView. The finishButton sends you to Overview. The workout object is the workout created and passed on.

### ExerciseViewController-Methods

- initialize()
- addExercise()
- finishAddingExercise()

The initialize function adds the names to the list, and changes the name field to the appropriate name.
The `addExercise()` adds an exercise to the workout and checks for different errors.
The `finishAddingExercise()` method sends the workout to `OverviewController`.

## OverviewController

This class is the controller for `Overview.fxml`, where its displayed the different exercises added.

### OverviewController-Fields

- (`Workout`) workout
- (`ScrollPane`) scrollPane
- (`Button`) cancelButton
- (`Button`) saveButton
- (`TextField`) inpName

The workout field is the workout that has the exercises required to create the grid. ScrollPane is the pane where the grid is added.
The buttons save and cancel the overview respectfully.
The field inpName is a value to save Name of the workout.

### OverviewController-Methods

- cancel()
- save()
- validateOverview()
- init()
- loadOverviewFromPlan()

The cancel method cancels the workout, while the save method saves the method. `validateOverview()` does the validating for save and cancel. The `init()` function gets called in exerciseViewController to run `OverviewController()`. `loadOverviewFromPlan()` loads an overview from `PlanGridHandler`, where the name and date is set at the top.

## ExerciseCell

The ExerciseCell object is an object that contains the Text-Nodes that shows up in the overviewGridHandler class. It contains four Text-Nodes, one for each field in Exercise, except reps, that is combines max and min reps.

### ExerciseCell-Fields

- (`Excercise`) exercise
- (`Group`) group

The exercise field is the exercise that is going provide the inputs for the Text-Nodes. The group is what is returned after the cell is created.

### ExerciseCell-Methods

- createFXCell()
- getGroup()

The `createFXCell()` method creates the group containing the information from the exercise. `getGroup()` returns the group.

## OverviewGridHandler

This class creates the grid based on exerciseCells.

### OverviewGridHandler-Fields

- (`ScrollPane`) scrollPane
- (`Workout`) workout
- (`List<Exercises>`) exercises

The scrollPane and workout is the scrollPane-Node and workout-Node from `OverviewController`, while the list of exercises are the exercises that get created as exerciseCells and added to the grid.

### OverviewGridHandler-Methods

- createGrid()

this method creates the grid with the given fields.

## PlanController

This class is the controller for the `PlanView.fxml` where you get a view over the different workouts

### PlanController-Fields

- (`ScrollPane`) scrollPane
- (`PlanGridHandler`) planGrid

The scrollPane is where the grid is going to be, while the planGrid is the class for making the grid.

### PlanController-Methods

- init()
- clickNode()

Where the init function is run in `OverviewController` to initialize the grid, while `clickNode()` runs the `clickNode()` function for planGridHandler.

## WorkoutCell

The `WorkoutCell` object is an object that contains the Text-Nodes that shows up in the `PlanGridHandler` class. It contains four a workout.

### WorkoutCell-Fields

- (`Workout`) workout

This field is where the values for the cell comes from.

### WorkoutCell-Methods

- getText()
- getWorkout()

the `getText()` method is creates and returns the values for the cell. `getWorkout()` returns the workout.

## PlanGridHandler

The class that creates a grid of workoutcells.

### PlanGridHandler-Fields

- (`List<WorkoutCell> workouts`) workoutCellList
- (`ScrollPane`) scrollPane

The list is a list of each workoutcell. The scrollpane is the container for the grid.

### PlanGridHandler-Methods

- configureGrid()
- clickNode()
- addRow()
- addColumn()
- addWorkoutCell()
- createGrid()

`configureGrid()` sets up the base grid for the planview. `clickNode` makes it so you can return to overview from planview. `addRow` and `addColumn` adds rows and columns.
`addWorkoutCell` adds a new workoutCell.
`createGrid` sets up the grid properly, and adds the cells to the grid.

## UIUtils

Has one method where it creates an alert. We use this to create alerts in different classes.
