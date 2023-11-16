# About the file

This file goes over how we developed the application, specifically work habits, workflow and code quality.

## Work Habits and Workflow

- Issues/Milestones
  - We created a milestone in gitlab for each assignment. This was to get a high-level view over what needed to be done during that assignment, what was in progress and what needed to be done. In each milestone, we made issues for the things we needed to fix. These issues were mostly created each sprint or gotten from the product backlog, but others were created when we later discovered features or bugs to add/fix.
  We edited the issues so that they would be easy to understand from the 'Issue boards.' We specifically added labels for different issues, for example `documentation` or `bug`. We also added a deadline to a lot of critical issues. This was to ensure that they would be done, and show where the focus should be.
- Branching
  - We created a branch related to each issue. This resulted in better workflow because we could each work on something at the same time without creating merge conflicts. It also meant that features could be isolated to one branch, and not mess up the rest of the code.
- Code Reviews
  - Code reviews were important because it prevented faulty code and made us up to speed on a newer version of the application. The review gave suggestions to how the code could be improved and how to do it, resulting in a more precise code.
- Agile Development
  - We implemented Scrum by having weekly meetings, where each meeting started a new sprint. We would each talk about what we had done up to the meeting, and what each member would focus on for the next sprint. The agenda was something we created in the app `notion`, where we also kept minutes of meeting for each sprint as well as product backlog. We also gave each other positive and negative feedback.
- Communication channels
  - We discovered that using `messenger` would be hard when finding a specific message, and therefore used `discord` as a primary communication channel. Here we had multiple channels similar to `slack`, where we would answer each other in the `#general` chat. This made it easier to find and discuss different topics. We also had `#announcement` to keep everyone up to date and announce meetings.
  
## Code Quality

- We look at code quality as code which is reliable, easier to read and maintain. Achieving this makes it easier to edit and improve further on the project, as well as reduces the chance of bugs.
- SpotBugs is a dependency we implemented from the start to prevent bugs. This has helped us several times, and we made it so the program wouldn't run unless all spotbugs bugs had been solved. This forced us to deal with the bugs then and there and therefore preemptively improve the code quality.
- Checkstyle was another dependency we implemented in our project. This changed how we wrote our code, and gave us code conventions to improve the quality. Each of the classes has a java-doc segment explaining the code because of this. Checkstyle also improves readability because it forces you to have a specific length for each line (80 characters). It also improves stability with final variables both local and as inputs.
- We also wrote a lot of inline comments to make it easier to understand what specific lines of code would do.
  