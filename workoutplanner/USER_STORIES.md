# User Stories

This page displays the different cases for which a user can use the app as well as different functionality and situations.

Links:
[User story 1](#making-a-workout)
[User story 2](#overview-of-exercises---1)
[User story 3](#overview-of-exercises---2)
[User story 4](#swap-order-of-exercises)
[User story 5](#open-a-previous-workout-and-track-progress)
[User story 6](#edit-a-workout)

## Making a workout

As an individual, I want to make my own workout, but I can't find any programs or apps that specify the sets and reps that i want for my workouts.

The user needs to make a workout where they can specify what and how much they are training, this includes `sets`, `reps` and `weight`.
Therefore it needs to be an inputfield for the user to write the desired amount for any exercise, for example editing the sets.

### Important visuals

- Which exercises pertain to the workout
- The amount of sets and the rep-range for the exercises
- The amount of weight required for the exercises

### Important traits

- To create a new workout
- To add exercises to a workout
- Write values for weight, rep-range and sets

## Overview of exercises - 1

I am a person that easily forgets what exercises I have planned when I reach the training center.
Therefore I need an overview where I can look up what I decided when I was at home, when I'm at the training center.

The user needs to have an overview of the exercises they have chosen already.
The overview should include the same values that they have entered before, as well as the name of the exercises, in a structured manner.

### Important visuals

- Text-Nodes displaying
  - Name
  - Set
  - Rep-range
  - Weight

### Important traits

- See the exercises
- Save or cancel the workout

## Overview of exercises - 2

As a personal trainer, I want to design a plan for my client containing specific exercises that fits them. I need to design it so that it is easy for them to understand what to do, and how much they need to do when they are training without me.

The user needs an overview of specific exercises that fit the client, and  manually choose the weight, reps and sets of the exercises.
The overview should include the same values that they have entered before.

Visuals and traits are the same as in [previous user story](#overview-of-exercises---1)

## Swap order of exercises

I am someone who thinks the order of the exercises in a workout is important to how the workout is.
Therefore i wish that i can change the order of the exercises in my workout myself.
This makes it so I can compare my strength more easily to previous attempts.

It has to be possible for the user to swap places between the exercises so that the preferred exercise is at the start of the grid.
This means that the user needs an overview where the exercises are presented clearly.

### Important visuals

- VBox displaying exercise values
- Arrow-buttons that swap the order

### Important traits

- Move a workout backward
- Move a workout forward

## Open a previous workout and track progress

As a regular person that goes to the gym, I want to make a workout to later revisit.
I want this to do the same workout again at a different date.
I also want to track the progress i have done on a muscle group,
and it is therefore necessary to watch what I trained, when. F.ex: I want to name a workout "core",
and when I visit the workout, i can see the core-exercises i've added earlier.

For reviewing previous workouts, it has to be possible to get an overview over which workouts have been registered, and when.
It also have to be possible to enter the workouts without adding a new workout, and to visit the specific workout by the press of a button.

### Important visuals

- A Text-field showing the date of the workout
- A Text-field showing the name of the workout
- A button that enters the specific workout

### Important traits

- enter the specific workout
- view the name of the workout
- view the date of the workout

## Edit a workout

As an individual who often changes their mind, I want to change almost everything about any workout I have created.
This includes swapping the order, deleting an exercise, adding exercises and changing the parameters.

To achieve this, there has to be a `view` button when all the workouts are displayed, where you can view the specific workout.
When you are viewing a specific workout, there has to be a possibility to add a workout, and from there add different parameters.

### Important visuals

- A View-button
- A delete-button for exercises
- An Add Exercise-button
- Overview of workouts
- Overview of exercises in a workout
- Interface for adding an exercise
  
### Important traits

- view a specific workout
- add an exercise
- delete an exercise
- swap order of exercises
