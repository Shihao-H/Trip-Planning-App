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
| Epics | *5* | *5* |
| Tasks |  *12*   | *52* | 
| Story Points |  *25*  | *62* | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *09/14/2018* | *131, 132, 133, 134, 135, 136, 137, 138* | *117, 123, 129, 130* | *Dave forgetting to publish canvas assignments; Learning curve* | 
| *09/17/2018* | *130, 140* | *117, 123, 129* | *Headaches from trying to learn ReactJS* |
| *09/19/2018* | *118, 144, 146, 149, 150* | *165, 160* | *Headaches from trying to learn Big Data* |
| *09/20/2018* | *116, 123, 165, 160* | *170* | *Headaches from trying to learn ReactJS* |
| *09/21/2018* | *170* | *176, 185* | *Headaches from trying to learn ReactJS* |
| *09/23/2018* | *117, 176, 185* | *174, 179, 180, 186, 189, 190, 193* | *Headaches from trying to learn ReactJS* |
| *09/24/2018* | *129, 174, 179, 180, 186, 189, 190, 193* | *183, 184* | *Headaches from trying to learn ReactJS* |
| *09/26/2018* | *183, 184* | *119, 124, 125, 214* | *Headaches from trying to parse the file* |
| *09/27/2018* | *119, 124, 125, 214* | *182* | *Headaches from trying to display the Itinerary table* |

## Review

Overall the team pulled most everything together and got more done than we thought we would!

#### Completed epics in Sprint Backlog 


* *110 User: I want to supply my own units for the distances : All related issues were completed; our solution enables users to use their user-defined units.*
* *111 User:I want a map and itinerary for my trip : The map and the table of itinerary are displayed.*
* *112 TripCo: All clients and servers must interoperate : We strictly adhered to the TFFI specification.*
* *114 TripCo: All code shall be clean : We wrote maintainable code; All our code is clean.*

#### Incomplete epics in Sprint Backlog 

* *113 TripCo: The solution must be responsive for mobile devices : We don't have enough time to deal with that.*

#### What went well

* *Our itinerary works and is dynamic*
* *The option tab successfully takes a file, loads it, and renders the trip on the website*

#### Problems encountered and resolutions

* *Learning React was still the most difficult and time consuming for the team. Spending time and sharing knowledge about it
was the key for success. After syntax was discovered, it was told to the other teammates, which helped understanding
and sped up development time*

## Retrospective

#### What we changed this sprint

*We spent far more time on this sprint than the previous sprint and went to Dave's office hour.*

#### What we did well

*We worked well as a team, making sure that if a WOPR member needed help with their task, help was given. Overall the
team did a good job of meeting up at school to figure out where each person was on their task, and to brainstorm about
ways to conquer the next task*

#### What we need to work on

*The team overestimated the learning curve for React, and spent many hours in the last week fighting with the front-end
related tasks. This was due to not devoting more time earlier on in the sprint for figuring out what exactly was happening
in the .js files. More time needs to be devoted earlier on in Sprint3 for it to be even better than Sprint2.*

#### What we will change next sprint 

*For Sprint3, the whole team will try to spread out their time on the project more evenly, so as to better deal with any 
problems that creep up later on in the sprint. The better time management will help the team finish tasks quicker, and will
ease pressure during the last week before deployment*
