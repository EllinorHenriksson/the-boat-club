# Assignment 4 - The Boat Club
In short this assignment is about creating a member registry for a boat club. The application should be runnable as a simple console application with a menu and the information in the registry should be loaded and saved to a file.

## General
Remember that the course is about _object oriented programming_ and you need to show that you know the concepts and how to properly work with classes and objects.

The following is a list of common best practices that you should follow in general.

1. Connect objects using oo-relation (associations/dependencies) and not with keys/ids.
2. Classes have high cohesion and are not too large or have too much responsibility.
3. Classes have low coupling and are not too connected to other entities.
4. Do not use static variables and operations (the main method is of course an exception, and also constants can be an exception).
5. Do not use magic constants - eg. hard coded numbers/strings etc. Constants and enumerations can be a help here.
6. Work with encapsulation - it should be easy to do the correct thing from a programming perspective and not easy to mess things up.
7. Let reality insprie the design, concepts from reality should be used as classes etc.
8. Name classes, methods, attributes, parameters etc as well as possible. Try to use naming that explains what the method/variable is used for and avoid just repeating the type.
9. Add comments for parts that are hard to understand. 
10. The application should be runnable using command `./gradlew run -q --console=plain`
11. The application should be buildable using command `./gradlew build` and there should not be any quality errors.
12. You should regularely commit and push to gitlab.
13. Use the readme to explain any adaptations, problems, strangeness or things that does not work properly, or is not that well made. Show that you know about things that are lacking. 
14. Avoid duplicated code, if you copy paste then you should think twice... refactor common code into methods or base classes if appropriate.

## Application Requirements
These requirements are used to assess the assignment passing grades E-C.
1. You should be able to create a new member. A member has a name, and an optional email adress.
   1. If the member has an email adress it must be unique (two different members cannot have the same email adress)
   1. When a member is created the member is assigned a new unique random 6 character alpha numeric member id. This id must be unique and no other member in the registry should be able to get the same id.
2. You should be able to list all members.
3. You should be able to select a particular member see the detailed information about the member. You should then be able to:
   1. delete the member
   2. add a new boat. A boat has a name, type (sailboat, motorboat, motorsailer, or canoe). Depending on the type there is different information needed:
      1. Sailboat: lenght in meter, depth in meter.
      2. Motorboat: length in meter, engine power in horse powers.
      3. Motorsailer: length in meter, depth in meter, engine power in horse powers.
      4. Canoe: lenght in meter.
   5. Select a boat and see the detailed information about the boat. You should then be able to:
      1. delete the boat
4. You should be able to quit the application
5. The registry information should be loaded from a file `registry.data` when the application starts. See the [example file](registry.data) for the format of the file.
6. The registry information should be saved to a file `registry.data` when the application exits. See the [example file](registry.data) for the format of the file.
7. There should be at least one class diagram showing the application structure with all classes and correct relations between the classes. You do not need to add every operation in the class diagram.
8. Basic error handling, i.e. it should not crash. No need for user friendly error messages.

### File Loading/Saving Format
The requirement is that the data of the application should be loaded from a file when the application starts, and saved to a file when the application ends. The idea is to load the whole file, convert it to nice to work with objects. When the user quits, the whole file is overwritten with new data. Trying to load/save continously or manipulate the file incrementally will only make things more complicated.

The format of the file is as follows:

```
MEMBER:Member Name:optional email adress:member id
optional (BOAT:boat name:(sailboat:length:depth)|(motorboat:length:engine power)|(motorsailer:length:depth:engine power)|(canoe:length))
```

Note that the data depends on the type of boat as per the requirements.

For example:

```
MEMBER:Margaret Hamilton:margaret@nasa.gov:lkd432
BOAT:apollo1:sailboat:20:4
BOAT:appollo3:motorboat:20:50
MEMBER:Allan Turing:allan@bletchleypark.org.uk:kh654s
BOAT:enigma:canoe:4
MEMBER:Barbara Liskov:barbara@mit.edu:bg54w2
BOAT:substitution:motorsailer:17:4:30
MEMBER:Charles Babbage::8ww3ls
```
Note the options for the different boat types. I.e. Barbara Liskov's boat is 17m long, 4m depth with a 30hp engine. Charles Babbage does not have an email adress, and he has no boats.


## Extended Requirements
These requirements are used to assess the assignment higher grades C-A.
1. You should be able to select a particular member and edit the member
   1. name
   2. email adress
2. Select a members boat and edit its:
   1. name
   2. type
   3. type information (length etc.)
1. Use the _strategy design pattern_ to design and implement an extendible and flexible way of searching for members. For example searching for members with sailboats, or searching for members with a particular memberid, etc.
2. Extend the basic way of searching with the _composite design pattern_ to enable boolean expression type searcing. E.g. members with sailboats and boats with a length over 10m or a motorboat `(type=sailboat and length>10) or type=motorboat`. For this requirements it is sufficient that you design and implement, there is no need for adding a user interface for this.
3. Unit testing - add a few automatic unit tests to the codebase. It should be someting more than just simple getters and setters.
4. The application should handle input-errors well with user friendly error messages.

## Approaching
Start small! Begin by trying to get an idea of the structure of the application by beginning to draw a class diagram. Try to find out the classes needed to fulfil the requirements, but also have in mind that you might need to revise them later. Try to find what relations the different classes have, what might be dependent on what and so on.

When coding, do it in small, small steps. Do not add too much functionality at once, but rather try to add one or two methods at a time and see to that it works. If it does not add too much overhead for you, try to add tests as early as possible in the process. 

Reading and parsing (as well as saving) the file is something we have not covered extensively in the course, so put some time to try to figure that out. It is always a good idea to try out parts that you are unfamiliar with in a separate project without the overhead of all the other parts of the program. We will allow you to define the structure of the text file to read and save yourselves, but try to make it as flexible as possible, see more information below.

Be prepared to change the class diagram as you go along with the coding. This happens in real life as well, but perhaps more so when we are this early in the educational programme. That said, see the class diagram as a help to structure your thoughts. Add other diagrams _as you see fit_ if they help you.

## Handing In the Assignment
When done with the tasks you issue a merge request to the release branch.

Follow the instructions for the hand in process and use milestone `2022_A4`
