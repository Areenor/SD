# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): `name of the team member(s) responsible for this section`

Provide a bullet list summarizing all the changes you performed in Assignment 2 for addressing our feedback.

Maximum number of words for this section: 1000

### Application of design patterns
Author(s): `name of the team member(s) responsible for this section`

`Figure representing the UML class diagram in which all the applied design patterns are highlighted graphically (for example with a red rectangle/circle with a reference to the ID of the applied design pattern`

For each application of any design pattern you have to provide a table conforming to the template below.

| ID  | DP1  |
|---|---|
| **Design pattern**  | Name of the applied pattern |
| **Problem**  | A paragraph describing the problem you want to solve |
| **Solution**  | A paragraph describing why with the application of the design pattern you solve the identified problem |
| **Intended use**  | A paragraph describing how you intend to use at run-time the objects involved in the applied design patterns (you can refer to small sequence diagrams here if you want to detail how the involved parties interact at run-time |
| **Constraints**  | Any additional constraints that the application of the design pattern is imposing, if any |
| **Additional remarks**  | Optional, only if needed |

Maximum number of words for this section: 2000

## Class diagram									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of the UML class diagram of your system, together with a textual description of all its elements.

`Figure representing the UML class diagram`

For each class (and data type) in the class diagram you have to provide a paragraph providing the following information:
- Brief description about what it represents
- Brief description of the meaning of each attribute
- Brief description of the meaning of each operation
- Brief description of the meaning of each association involving it (each association can be described only once in this deliverable)

Also, you can briefly discuss fragments of previous versions of the class diagram (with figures) in order to show how you evolved from initial versions of the class diagram to the final one.

In this document you have to adhere to the following formatting conventions:
- the name of each **class** is in bold
- the *attributes*, *operations*, *associations*, and *objects* are in italic.

Maximum number of words for this section: 4000

## Object diagrams								
Author(s): Leyla Celik

![Object Diagram](https://github.com/Areenor/SD/blob/Assignment-3/docs/Object_Diagram_2.0.png)

This diagram shows a snapshot of a player who had their **MainCharacter** enter a **Location** that contains **Equipment** and is adjacent to another **Location** with **Equipment** and a **Character**. The **MainCharacter** is an instance of the class Character with all inherited variables. The **MainCharacter** is named player1 and their stats are 5 *HitPoints*, 1 *Strength*, 1 *Dexterity* and 1 *Constitution* and 1 *Stamina*. The *MaxHitPoints* and *MaxStamin* are 10. The player can use items to increase *HitPoints* and *Stamina* for the **MainCharacter** with a cap of *MaxHitPoints* and *MaxStamina*. The **MainCharacter**’s inventory can consist of items such as **Equipment**, **Consumable**s, **KeyItem**s, etc. and, at this point in the snapshot, consists of a *torch*. The **MainCharacter** can use items such as **Equipment** with the command *‘Use’*. The **MainCharacter** is at a **Location** named *“a beach”*. The player gets a description of this **Location** upon entering it: “You are on a beach, there are objects and items. There is a room east.”. This **Location** contains two objects of subclass Equipment: one of type *'weapon'* that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) named ‘Sword’ that gives an attack bonus of 1 (int *AttackBonus* = 1;) and one of type *'armor'* named ‘Chestplate’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that gives a block bonus of 1 (int BlockBonus = 1;); The **Location** *‘a beach’* has an adjacent location to the east (*AdjacentLocations* = {"east":"room"}) called *‘room’*. 
The adjacent location *‘room’* contains one item of subclass **Consumable** and an **NPC** of class **NPC**. The **Consumable** is a ‘Strength potion’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that is not dangerous (bool *IsDangerous* = false;), which means that the **MainCharacter** can use it on themselves. The affected stat is the *Strength* stat (*AffectedStat* = *Strength*;) and the added bonus is 1 (int *StatChange* = 1;). The potion has the description: “This potion will make you stronger on consumption.”. The **NPC** is named ‘Guard’, is of type *‘guard’*, has the description "This is a Guard that attacks on sight.", is hostile (bool *IsHostile* = true;) and is fightable (bool *IsFightable* = true;). Possible types of interactions with the **NPC** are: *‘Talk’*, which gives the player dialogue between the **MainCharacter** and the **NPC** and *’DropInventory’*, which adds all of the **NPC**’s items to the **Location** it is in. An **NPC** that *IsHostile* will attack the **MainCharacter** on sight, meaning the player will not have time to interact with the **NPC**.


## State machine diagrams									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

## Sequence diagrams									
Author(s): Leyla Celik, Richard Eric van Leeuwen

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000


Now, we would like to discuss the movement scenario. 

![Sequence Diagram Movement](https://github.com/Areenor/SD/blob/Assignment-2/docs/Sequence_Diagram_Movement.png)

This movement sequence diagram shows the way the program handles a movement command when the direction the player wants to go to does not exist and when it does exist. The interaction partners in this diagram are the Player, **MainCharacter** and **Location**. We will discuss each of the interactions partners’ involvement and what their tasks are during this scenario. Player depicts an external influence and is the stakeholder of this diagram. The player is the person playing the game who inputs commands and controls the **MainCharacter**. The situation in this sequence diagram is as follows: the player wants to move the **MainCharacter** to a new **Location**. The player inputs a command to move the **MainCharacter** north of its current **Location**: “move north”. 
The **MainCharacter** processes the commands they receive from the player and will teleport if the direction the player wants to go in has a **Location**. The **MainCharacter** receives the “move north” command and will invoke the *move(“north”)* method to **Location**. 
**Location** is mostly a container for data, but also sends back a message depending on whether a **Location** exists in the direction chosen by the Player. If the direction chosen by the player has a **Location**, the message is a description of the **Location**. If there is no **Location** in that direction the message will say there was nothing in that direction. The player chose north as their direction to move in, but there is no *AdjacentLocation* north of the **MainCharacter**’s current **Location**, so Location sends the player the response “You found nothing traveling in this direction and returned to your original **Location**.” as output. The player tries to change locations again, this time wanting to move the **MainCharacter** east of its current **Location** with the command: “move east”. The **MainCharacter** receives the “move east” command and will invoke the *move(“east”)* method to **Location**. There is an adjacent **Location** east of the **MainCharacter**’s current **Location** and the player gets the response: "You are in a room, there are objects and items. There is a beach west." as output.


## Implementation									
Author(s): `name of the team member(s) responsible for this section`

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 2000

## References

References, if needed.
