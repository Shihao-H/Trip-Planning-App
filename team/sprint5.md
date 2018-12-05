# Sprint 5 - *t03* - *WOPR*

## Goal

### Wrap It Up!
### Sprint Leader: *Lacey Willmann*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint5.md).
* Version in pom.xml should be `<version>5.0.0</version>`.
* Increment deployed for demo and testing as server-5.0.jar on the production server.
* Increment release `v5.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 70%.
#### Clean Code
* Code Climate maintainability of A or B.
* Code adheres to Google style guides for Java and JavaScript.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* All tests pass.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests on all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

*Sprint 5. This is where it's all been heading towards. Each of the four WOPR members is primed and ready to go for the
final leg of their trip website. Forming is taking place, and the team is ready to push towards the final goal and put
out their completed project. There are a total of 9 epics we will complete for this sprint. This includes the prior epics
that were considered long standing goals to always pay attention to. Since this is the last sprint, we want to complete all
of them now, so for epics 112, 113, 114, and 234, we plan to have no problems with other team servers, have an A or B on 
code climate with at least 50% coverage, and have our website work well on small devices like tablets and phones. We will
tweak our user interface so that the user experience is that much better when using our site. If the client wants to travel
the world on their private jet and try a trip of 700+ places, our server will be ready for it without lag. For this epic,
we plan on using concurrency to help with making threads to work on their trip in parallel. With our brand new 3-opt
algorithm, we can also help the user plan trips that are optimized, so not to waste time and money. We already have a KML
map working as is, but our goal is to build on that even more, letting anyone pan around, zoom, save the trip on the map,
and even drop onto street mode to get an idea of what they can expect when visting a specific place on their excursion.
Last but certainly not least, we will humbly let the user know who brought this website to life, and dedicated many,
**many** hours into bringing their vision into fruition. For this, we plan to use either a tab on the site, or a splash page
to let the client know who the people are behind the scenes.*

*Include any design diagrams prepared during sprint planning (user interface, component diagram, component/state/hierarchy, etc.) with a short paragraph for each.

| Page Diagram | Description |
|:------------:|-------------|
| ![Rearend hierarchy](image/hierRearend.jpeg) | This shows the formation of our rear end. It starts in Place and fans out from there. |
| ![Client side hierarchy](image/diagram.jpeg) | This shows the relationships between the javascript files, the arrows show the movement of state between the classes. |
| ![User Interface](image/Userinterface.jpeg) | This is what our goal for the end user interface is. |

Epics planned for this sprint.

* *112 TripCo: All clients and servers must interoperate!*
* *113 TripCo: The solution must be responsive for mobile devices.*
* *114 TripCo: All code shall be clean!*
* *234 TripCo: All code must be tested.*
* *536 User: Make the system easier to use.*
* *537 User: I want trip planning to be fast.*
* *538 User: I want the shortest trips possible.*
* *539 User: I want to know who to thank for this application*
* *542 User: I want an interactive map.*


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *10* | *total* |
| Tasks |  *43*   | *total* | 
| Story Points |  *49*  | *total* | 

*Although in the previous sprint, we ended up with 104 issues and 151 story points, we plan to have 43 issues and 49 story points for this sprint, because we have already completed most of the important epics and we don't have much to do for this sprint.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *11/12/2018* | *535, 534, 541* | *N/A* | *None yet!* | 
| *11/14/2018* | *479,560,561,562,563,564,578* | *577, 580, 586* | *None yet!* | 
| *11/16/2018* | *577, 553, 555, 580, 583, 585, 586, 588* | *598* | *None yet!* | 
| *11/19/2018* | *598, 599* | *582, 600, 602, 603, 612* | *None yet!* | 
| *11/21/2018* | *550, 582, 584, 590, 600, 601, 602, 603, 604, 605, 606, 607, 579, 596, 608, 609, 612, 617* | *618, 620* | *None yet!* | 
| *11/23/2018* | *618, 619, 620, 616* | *570, 624, 625* | *None yet!* | 
| *11/26/2018* | *570, 621, 622, 623, 624, 625* | *587, 625, 613* | *None yet!* | 
| *11/28/2018* | *547, 566, 567, 568, 573, 574,591, 630, 631, 632, 633, 636, 638,640, 543, 549, 571, 642, 647, 650* | *613, 643, 544, 546, 548, 648* | *None yet!* | 
| *11/30/2018* | *544, 546, 548, 682, 683, 684, 687* | *643, 653, 692, 693, 694* | *Final projects for other classes* |
| *12/1/2018* | *643, 653, 692, 693, 694* | *696* | *Final projects for other classes* |
| *12/2/2018* | *696* | *688, 689, 690* | *Final projects for other classes* |
| *12/3/2018* | *688, 689, 690* | *699* | *Final projects for other classes* |

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
