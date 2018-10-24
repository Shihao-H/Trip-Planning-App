# Sprint 4 - *t03* - *WOPR*

## Goal

### Interactive Maps and Shorter Trips!
### Sprint Leader: Lacey Willmann

## Definition of Done

* Sprint Review and Restrospectives completed (sprint4.md).
* Version in pom.xml should be `<version>4.0.0</version>`.
* Increment deployed for demo and testing as server-4.0.jar on the production server.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
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
#### Continuous Integration / Delivery
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

*WOPR expects to have a very successful release for our 4.0 release. We were pretty happy with our 3.0 product, but
we really feel that this will be a great sprint for us. Not including our 'static' epics that we always focus on, we have
four epics that we think will help us reach our goal of creating a product that users will really benefit from. 
Our shorter trips epic will have our 2-opt algorithm integrated in our system to help the client plan a more optimal trip 
than our nearest neighbor. The epic focused on viewing the trip in other tools will provide a user friendly experience of
displaying a world map for whatever trips the client decides to make, along with many options for adding new places with a
variety of specific choices for each one (id, name, elevation, etc.). For planning trips worldwide, we have tasks that deal
with creating back end systems that communicate with the more robust database, and making trips that can span the globe. 
Itinerary and map display is another epic in our repertoire that will enhance our product. Being able to decide different
attributes to display in the itinerary and/or the map can help clients modify their trip to their liking. With these epics
and handful of many tasks that have been outlined above, WOPR is confident that the release of 4.0 will be their best yet!*

| Page Diagram | Description |
|:------------:|-------------|
| ![Our Page Layout Diagram](../images/pageLayout.jpeg) | The proposed layout and flow chart for our page for sprint 4. We want to try to hide some of the elements until they are needed to reduce clutter. |
| ![Frontend Search](../images/serverDiagram.jpeg) | Hierarchy diagram for our backend.  |
| ![Backend Search](../images/stateDiagram.jpeg) | The state diagram for our client side. Blue lines represent state being passed; the arrows show the direction the state flows.|


Epics planned for this sprint.

* *112 TripCo: All clients and servers must interoperate!*
* *113 TripCo: The solution must be responsive for mobile devices.*
* *114 TripCo: All code shall be clean!*
* *234 TripCo: All code must be tested.*
* *393 User: I'd like even shorter trips.*
* *394 User: I want to plan trips worldwide.*
* *403 User: I want to choose what information is displayed in the itinerary and map.*
* *395 User: I want to view my trip in other tools.*
* *396 User: I want trip planning to be fast.*
* *397 User: I want the shortest trips possible.*


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *7* | *total* |
| Tasks |  *21*   | *total* | 
| Story Points |  *35*  | *total* | 

*Although in the previous sprint, we ended up with 76 issues and 130 story points, we plan to have 28 issues and 35 story points for this sprint, because we are familiar with all the stuff and we are confident when we plan.*

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
