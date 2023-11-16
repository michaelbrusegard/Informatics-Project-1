# Group gr2307 repository

This is the repository for group gr2307 in the course IT1901 at NTNU. The project is an app to create workout plans made with maven in javafx.

## Versions

maven: 3.8.3 \
java: openjdk-17 \
javafx: 17.0.8

## Running and testing the app

To run the app, first enter the eclipse che workspace with the following link:

[Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2307/gr2307.git?new)

Then navigate to the source code directory of the project, and clean install without tests:

```bash
cd workoutplanner
mvn clean install -DskipTests
```

This installs the necessary dependencies for the app to work.

You can either run the app locally or with the REST-server.

To run it locally, you can change the value of `useRemote` in `MainController`from true to false. This makes it so no progress is saved to backend when you close the app, but it can run on frontend and persistence.

Alternatively you can from the `workoutplanner` folder navigate to `restapi` by running:

```bash
cd restapi
```

followed by:

```bash
mvn spring-boot:run
```

to run the server.

Open a new terminal and navigate to the `workoutplanner` folder, then navigate to `fxui`:

```bash
cd fxui
```

and run the command:

```bash
mvn javafx:run
```

The app should now be up and running.
In Eclipse Che, you can go to the 'Endpoints' window and copy the link from port 6080. This will take you to the virtual machine, where you can view the app.

### Run app with jlink and jpackage

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

## Run JaCoCo

It also creates the site-folder in the target-folders of the different modules, where if you open target/site/jacoco/index.html. You get to see the codecoverage for the module you're in.

## Contents

- ./workoutplanner - [source code](./workoutplanner)
  - ./core - [core logic](./workoutplanner/core)
  - ./fxui - [javafx user interface](./workoutplanner/fxui)
  - ./fxutil - [javafx utility classes](./workoutplanner/fxutil)
  - ./restapi - [rest api server](./workoutplanner/restapi)
- ./docs - [assignment documentation](./docs)
- ./CHANGELOG.md - [changelog](./changelog)

In the root directory, you can find the documentation for the assignments in the `docs` folder and the CHANGELOG.md file. The app is located in the `workoutplanner` folder, where you can find the source code for the app. This src-directory also contains a markdown for user-stories, diagrams, and a readme about the app. The `core` folder contains the core logic for the app, as well as persistence. The `fxui` folder contains the javafx user interface and the `fxutil` folder contains utility classes for the javafx user interface. The `restapi` folder contains the backend of the app, this is where the REST-api is.

### Link to project documentation files

- [README](workoutplanner/README.md) - Contains information about the project and concept photos of the app.
- [USER_STORIES](./docs/USER_STORIES.md) - Contains the user stories for the project
- [DIAGRAMS](./docs/UML) - Contains class-diagram, sequence-diagram and package-diagram
- [work flow, work habits and code quality](./docs/DEVELOPMENT.md) - Contains documentation for the persistence of the program.
- [releases](./docs/releases) - Contains the different releases for each milestone/assignment.

- [core documentation](workoutplanner/core/CORE.md)
- [persistence](workoutplanner/core/CORE.md#persistence)
- [fxui documentation](workoutplanner/fxui/FXUI.md)
- [fxutil documentation](workoutplanner/fxutil/FXUTIL.md)
- [rest documentation](workoutplanner/restapi/RESTAPI.md)
