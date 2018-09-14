# Sprint 2 - *t03* - *WOPR*

## Goal

### A mobile, responsive map and itinerary!
### Sprint Leader: *Shihao Huang*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint2.md).
* Version in pom.xml should be `<version>2.0.0</version>`.
* Increment deployed for demo and testing.
* Increment release `v2.0` created on GitHub with appropriate version number and name.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order.
* Unit tests are fully automated.
#### Configuration Management
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Continuous integration successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

*For Sprint2, team WOPR expects to build a TFFI that will handle a map and itenerary for a trip that the client requests. If
another unit is given that is different than the original three, we will be able to calculate it correctly as well. We
expect the site to work well on mobile devices, as well as computer monitors, with code that is clean and easily readable. 
When giving other teams our TFFI object, their server will respond correctly, and if we are given their TFFI object, ours 
will respond accordingly as well. We have 5 epics that we think will achieve this for us*

* *110 User: I want to supply my own units for the distances : The solution enables the users to define an arbitrary unit of measure to use in the itinerary.*
* *111 User:I want a map and itinerary for my trip : The solution shows a map and itinerary for the round trip in the state of Colorado in the file to the users.*
* *112 TripCo: All clients and servers must interoperate : This requires an adherence to the TFFI specification. Each client must include a configuration option to change to server:port used for RESTful services.*
* *114 TripCo: All code shall be clean : Adhere to the Google style guide for Java and JavaScript. Write maintainable code.*
* *113 TripCo: The solution must be responsive for mobile devices : The solution should be designed/optimized for a mobile environment, but still work well in a desktop environment.*

*During the planning meeting we began by deciding on who would be the team leader for this sprint. Afterwards, we 
discussed the epics and looked at the new version of the TFFI document from the client. From those items, we decided on 
which new components would be a part of which epic and made tasks for creating them. We also discussed alterations that 
may have to be made to our existing application because of the additions and created tasks for those, as well. Once we 
had a basic plan together with the epics and tasks, we discussed how much work and time would be required for each task.
From this discussion, we decided to break up a few of the original tasks into smaller ones. We agreed on time estimates 
and assigned them to each task. We then discussed the priority of each task and all agreed on a final priority order. 
Finally, we set the ground rules on how the team was going to work together on this sprint to accomplish our goal. We 
agreed to treat the backlog as a priority que and pull the next available task when we finished one. We also agreed that 
each team member was going to contribute to the sprint2.md*


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *value* | *value* |
| Tasks |  *value*   | *value* | 
| Story Points |  *value*  | *value* | 

*Enter the `# Planned` at the beginning of the sprint, `# Completed` at the end of the sprint.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *date* | *#task, ...* | *#task, ...* | *none* | 

*Add a new row for each scrum session.*

## Review

*An introductory paragraph describing the overall results of the sprint.*

#### Completed epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* *## epic title: comments*
* 

#### Incomplete epics in Sprint Backlog 

*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## epic title: explanation*
*

#### What went well

*Describe what went well during the sprint in general terms followed by a more detailed list.*

* *something*
*

#### Problems encountered and resolutions

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

*Articulate at the end of the sprint.  Focus on one of things you need to work on.*
