# Release 3

## Introduction

This release implements REST-api, and has therefore a proper backend that communicates to the frontend. It also comes with some new features, specifically that you can now edit a workout, as well as added styling for the entire app. This means moving the order of the exercises, adding and deleting an exercise. It is also possible to view and delete a workout. The workouts gets saved in the server, and displayed in workoutview.
This release includes more functionality to the app, and makes it better to use.

## Key Features/Changes

- Moving an exercise
  - The user can now move an exercise forward or backward depending on the desired order of the workout. F.ex. if you want to do Deadlift first, you can press the left arrow for Deadlift.
- Deleting an exercise
  - Added a button for deleting an exercise from the current workout.

- Styling
  - All pages has been styled to a similar theme, specifically the buttons and the header have been changed for each view. The total layout have also been shifted a bit, where there have been added VBox and HBox to align the content as well as possible.
- Restructure of app
  - We have restructured the app to have one controller, `MainController`, as a centralized hub for managing communication between views. A `MainController` will allow us to organize and modularize the code more effectively, making it a lot easier to locate, understand, and maintain specific functionalities.
- Added backend (REST-server)
  - This made us able to store data persistently and centralized easily, making the user experience reliable. We have now separated the frontend from the backend, which have a lot of benefits such as easier testing and a possibility for maintaining and developing the frontend and backend independently.
  - We have added this with editing the POM as well as created a rest-folder that includes mainly the class UserController. This is where the put-requests are received and data is sendt to the userclass. We also have added `RemoteUserAccess` in fxui, where the required data gets sendt to the backend.
- Added JLink/JPackage
  - We have added JLink/JPackage that makes it possible for the user to download the app to their own system and run it from there. It is also possible to run the app locally by changing the boolean useRemote from true to false. Otherwise it is possible to run the REST server and then the app.

## Bug Fixes

- Opening my workouts didnt show anything. This now takes you to workoutview with correct workouts.
- The tests for fxui and fxutil didn't work, this is now added.
- Most of what we fixed in release 3 we added as new features, and there weren't many bugs.

## Dependencies/plugins

- These are some of the most important plugins/dependencies we use for the project.
  - JaCoCo - for test coverage
  - Checkstyles - for Java-doc and writing the code with proper styling conventions
  - Spotbugs - for identifying potential bugs.
  - JLink/JPackage - for downloading the app locally
  - JUnit - testing framework
  - TestFX - for testing
  - Jackson - for proper persistence

## Testing

- We have expanded the tests we have to include all modules as well as persistence. We don't have test coverage for fxutil specifically, because the methods are tested when we run the tests for fxui. We have added tests for everything in both frontend and backend, using JaCoCo for showing test coverage.

## Documentation Updates

- We have changed the way to run the program, much because we added REST-api, in the [root-README](../../README.md)
- We added a lot of user stories covering most of the app.
- We added required documentation for assignment 3, meaning diagrams and documentation about REST.

We had a challenge with eclipse: [Challenges](../../docs/CHALLENGES.md)
