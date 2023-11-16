# About this module

Fxutil consists only of one part which is `fxutil`. The modules main task is to help the `fxui` module to load everything properly.

## Links

- [ExerciseView](#exerciseview)
- [GridBuilder](#gridbuilder)
- [Overview](#overview)
- [UiUtils](#uiutils)

## Fxutil

The `fxutil` package contains four classes, `ExerciseView`, `GridBuilder`, `Overview` and `UiUtils`.

### ExerciseView

The `ExerciseView` class is a helper class for validating and displaying prompts for adding exercises.

#### ExerciseView-Fields

- (`int`) LIMIT - A static int variable used for defining a numerical limit for the input-fields of an exercise.

#### ExerciseView-Methods

- validateExerciseInput(): Validates the user input for an exercise.
- displayExerciseAddedPrompt(): Displays a prompt confirming the addition of an exercise.

### GridBuilder

The `GridBuilder` class is a helper class for creating a grid of cells for workouts and exercises.

#### GridBuilder-Fields

- (`ScrollPane`) scrollPane - used for containing the grid.
- (`int`) itemCount - used for knowing how many items there are in the grid.
- (`Function<Integer, VBox>`) createCell - used for containing the items in the grid.
- (`int`) PREFSIZE - used for defining the preferred size of the grid.
- (`int`) ROWHEIGHT - used for defining the height of each row in the grid
- (`int`) COLUMNWIDTH - used for defining the width of each column in the grid.
- (`int`) COLUMNS - used to define the number of columns in the grid.

#### GridBuilder-Methods

- createGrid(): creates a grid.
- initializeGrid(): constructs the grid with the provided elements.

### Overview

The `Overview` class handles error validation and user interaction for the workout overview page.

#### Overview-Fields

- (`int`) CHARLIMIT - used to limit the number of characters in the input field

#### Overview-Methods

- validateOverview(): validates a workout during saving and closing actions
- checkIfCancel(): checks if the user wants to cancel a workout

### UiUtils

The `UiUtils` class provides utility methods for managing the user interface across all pages.

#### UiUtils-Methods

- showAlert(): a static method that shows an alert dialog with the specified title, message and alert type.
- showConfirmation(): a static method that shows a confirmation dialog with the specified title and message.
