# Group gr2307 repository

This is the repository for group gr2307 in the course IT1901 at NTNU. The project is an app to create workout plans made with maven in javafx.

## Versions

maven: 3.8.3 \
java: openjdk-17 \
javafx: 17.0.8

## Running and testing the app

To run the app first enter the eclipse che workspace with the following link:

[Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2307/gr2307.git?new)

Then navigate to the source code directory of the project:

```bash
cd workoutplanner
```

In Eclipse Che, you can go to the 'Endpoints' window and copy the link from port 6080. This will take you to the virtual machine, where you then can see the project after the following commands:

To install the different dependencies, run the tests and ultimatly run the program:

```bash
mvn clean install
```

This runs all the tests for the different modules and makes sure that the build is successful. It also creates the site-folder in the target-folders of the different modules, where if you open target/site/jacoco/index.html, you get to see the codecoverage


## Contents

- ./workoutplanner - [source code](./workoutplanner)
  - ./core - [core logic](./workoutplanner/core)
  - ./fxui - [javafx user interface](./workoutplanner/fxui)
  - ./fxutil - [javafx utility classes](./workoutplanner/fxutil)
- ./docs - [assignment documentation](./docs)
- ./CHANGELOG.md - [changelog](./changelog)

In the root directory, you can find the documentation for the assignments in the `docs` folder and the CHANGELOG.md file. The app is located in the `workoutplanner` folder, where you can find the source code for the app. The `core` folder contains the core logic for the app, the `fxui` folder contains the javafx user interface and the `fxutil` folder contains utility classes for the javafx user interface.

### Link to about the project

- [README](workoutplanner/README.md) - Contains information about the project and concept photos of the app.
- [USER STORY](workoutplanner/USER_STORY.md) - Contains the user stories for the project.
