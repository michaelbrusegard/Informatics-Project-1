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

This runs all the tests for the different modules and makes sure that the build is successful. It also creates the site-folder in the target-folders of the different modules, where if you open target/site/jacoco/index.html, you get to see the codecoverage for the module you're in.

You can either run the app locally or with the REST-server.

To run it locally, you can change the value of `useRemote` in `MainController`from true to false. This makes it so no progress is saved when you close the app, but it can run without backend.

Alternatively you can from the `workoutplanner` folder navigate to `restapi` by running:

```bash
cd restapi
```

followed by:

```bash
mvn spring-boot:run
```

to run the server.

From the `workoutplanner` folder, navigate to `fxui`:

```bash
cd fxui
```

and run the command:

```bash
mvn javafx:run
```

The app should now be up and running.

### run app with jlink/jpackage

To do this, you also need to navigate to the `fxui` folder in `workoutplanner`. Then you need two commands:
This runs all the tests for the different modules and makes sure that the build is successful. It also creates the site-folder in the target-folders of the different modules, where if you open target/site/jacoco/index.html, you get to see the codecoverage for the module you're in.

You can either run the app locally or with the REST-server.

To run it locally, you can change the value of `useRemote` in `MainController`from true to false. This makes it so the progress is saved through persitence, and you don't communicate with the backend.

Alternatively you can from the `workoutplanner` folder navigate to `restapi` by running:

```bash
cd restapi
```

followed by:

```bash
mvn spring-boot:run
```

to run the server.

### run app from code editor

from the `workoutplanner` folder, navigate to `fxui`:

```bash
cd fxui
```

and run the command:

```bash
mvn javafx:run
```

The app should now be up and running.

### run app with jlink and jpackage

To do this, you also need to navigate to the `fxui` folder in `workoutplanner`. Then you need two commands:

```bash
mvn javafx:jlink 
mvn jpackage:jpackage
```

Make sure you have changed the value of `useRemote`, or have started the server.
If you get an error for not having a toolset to create executables, you need to download this first.
Then you open the `target` folder in `fxui`, where there should be a `dist` folder. If you run the file in the `dist` folder,
you should get the installer for the app.
Then download the app to your desired destination, and run the program.

In Eclipse Che, you can go to the 'Endpoints' window and copy the link from port 6080. This will take you to the virtual machine, where you can view the app.

```bash
mvn javafx:jlink
mvn jpackage:jpackage
```

Make sure you have changed the value of `useRemote`, or have started the server.
If you get an error for not having a toolset to create executables, you need to download this first.
Then you open the `target` folder in `fxui`, where there should be a `dist` folder. If you run the file in the `dist` folder,
you should get the installer for the app.
Then download the app to your desired destination, and run the program.

In Eclipse Che, you can go to the 'Endpoints' window and copy the link from port 6080. This will take you to the virtual machine, where you can view the app.

## Contents

- ./workoutplanner - [source code](./workoutplanner)
  - ./core - [core logic](./workoutplanner/core)
  - ./fxui - [javafx user interface](./workoutplanner/fxui)
  - ./fxutil - [javafx utility classes](./workoutplanner/fxutil)
  - ./restapi - [rest api server](./workoutplanner/restapi)
- ./docs - [assignment documentation](./docs)
- ./CHANGELOG.md - [changelog](./changelog)

In the root directory, you can find the documentation for the assignments in the `docs` folder and the CHANGELOG.md file. The app is located in the `workoutplanner` folder, where you can find the source code for the app. This src-directory also contains a markdown for userstories, diagrams and a readme about the app. The `core` folder contains the core logic for the app, as well as persistence. The `fxui` folder contains the javafx user interface and the `fxutil` folder contains utility classes for the javafx user interface. The `restapi` folder contains the backend of the app, this is where the REST-api is.

### Link to about the project

- [README](workoutplanner/README.md) - Contains information about the project and concept photos of the app.
- [USER_STORIES](./docs/USER_STORIES.md) - Contains the user stories for the project
- [DIAGRAMS](./docs/DIAGRAMS.md) - contains class-diagram, sequence-diagram and package-diagram
- [releases](./docs/releases) - contains the different releases for each milestone/assignment.
