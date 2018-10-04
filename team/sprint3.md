# Sprint 3 - *t03* - *WOPR*

## Goal

### Build shorter trips!
### Sprint Leader: *Minjie Shen*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint3.md).
* Version in pom.xml should be `<version>3.0.0</version>`.
* Increment deployed for demo and testing as server-3.0.jar.
* Increment release `v3.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

*An introductory paragraph describing what you expect to accomplish this sprint that will achieve the goal.*

*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph or each.

Epics planned for this sprint.

* *234 TripCo: All code must be tested: minimum 50% coverage, preferred 70% coverage.*
* *236 User: I want to design a trip from scratch so I can stop using the other tool: Create an empty itinerary.*
* *232 User: I want to make and save changes to the trip: Enable the users to add or remove destinations; choose a different starting location; reverse the order of the trip; find possible destinations so that the users can add them; save the changes made to the trip.*
* *233 User: I want my trips to be shorter: Use nearest neighbor to build a shorter trip.*
* *235 User: I want to choose what information is displayed in the itinerary and map: allow additional attribut/evalue pairs to be captured for destinations whether they are entered manually or obtained from a database; allow the user to select attributes to display in the itinerary, including the latitude and longitude.*
* *240 User: I'd like even shorter trips: Use 2-opt to improve the nearest neighbor tours.*

Below is the design diagram for Search class (Server):
![Backend Search](/images/Search Backend.jpg)
Below is the design diagram for Search class (User Interface):
![Frontend Search](/images/Search User Interface.jpg)

## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *10* | *value* |
| Tasks |  *28*   | *value* | 
| Story Points |  *48*  | *value* | 

*At the beginning of the previous sprint, we planned 12 tasks and 25 story points, however we ended up with 52 tasks and 62 story points. So in this sprint, we broke the large tasks down and created more tasks than the previous sprint and we allocated 48 story points for this sprint.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 

*Add a new row for the scrum session after each lecture. *

## Review

*An introductory paragraph describing the overall results of the sprint.*

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *## epic title: comments*
* 

#### Incomplete Epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## epic title: explanation*
*

#### What Went Well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *something*
*

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *something*
*

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

*Articulate specifically what you will do differently based on the retrospective from the previous sprint before the sprint starts.*

#### What we did well

*Articulate what went well at the end of the sprint.*

#### What we need to work on

*Articulate things you could improve at the end of the sprint.*

#### What we will change next sprint 

*Articulate the one thing you will change for the next sprint and how you will accomplish that.*
