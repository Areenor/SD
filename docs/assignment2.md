# Assignment 2

Maximum number of words for this document: 9000

**IMPORTANT**: In this assignment you will model the whole system. Within each of your models, you will have a *prescriptive intent* when representing the elements related to the feature you are implementing in this assignment, whereas the rest of the elements are used with a *descriptive intent*. In all your diagrams it is strongly suggested to used different colors for the prescriptive and descriptive parts of your models (this helps you in better reasoning on the level of detail needed in each part of the models and the instructors in knowing how to assess your models).   

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Implemented feature

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Tags | Code snippets can be tagged via freely-defined labels called tags  |

### Used modeling tool
Add here the name of the modeling tool you are using for your project.

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

Maximum number of words for this section: 2500

## Object diagrams								
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`
  
`Textual description`

Maximum number of words for this section: 500

## State machine diagrams									
Author(s): Richard Eric van Leeuwen

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 2500

We ended up creating two state machine diagrams for our text adventure game: The character state machine diagram and the NPC state machine diagram. We will discuss in detail below each diagram.

First, the character diagram. This diagram will be the one discussed in most detail seen this diagram is responsible for most interactions in our system.

The character diagram is responsible for creating an instance which we call our main character which will be stored in our GameData file. This main character is responsible for the most interactions in our system and its position will be tracked separately under a variable called currentLocation which is also kept in our GameData file. As the player of the game inputs commands, the main character will be responsible for processing them using its methods and making sure these methods get processed correctly. The commands that can be used at any given moment are dependent on the main character's position, which is kept in the currentLocation variable in GameData, and its current state. Regarding the commands it can use depending its position, the main character can only interact with objects and NPCS that are in the same position as he is currently in. It is not allowed to interact with them outside of the location they are residing in. To make sure this is properly presented in our diagrams, we have included location checks as guards. Regarding states the main character has which influence the commands it can perform, at this point in time we envision the main character to have three main states: Not in combat, in combat and defeated. We will briefly describe each of these states. 

First of all, the not in combat state. This will be the state the main character has access to most of the commands. During this time, the main character can talk to NPCS, examine his surroundings, objects or NPCS, move and thus change his current location, request for a hint that is stored in GameData, attack a isFightable NPC, take objects and put them in his Inventory as long as they are Pickupable, equip objects by moving them from his inventory to his Equipment and use objects in his inventory on NPCS or as default on himself. We expect the player to spend most of his time in this state as he interacts and explores the game world. To beat the game the goal of the player is to go from the not in combat state to the endstate of the main character, which is only possible once it has managed to set the GameFinished variable in GameData to true.

The second state is the in combat state. The main character reaches this state by either attacking a isFightable NPC itself or by getting attacked by a isHostile NPC upon entering the isHostile NPC's location. In the combat state, the main character will fight with a NPC in turn-based combat where each participant is allowed to select one combat move during its turn. Combat will continue till one participant's health attribute reaches 0. If it is not the main character's turn, all command inputs will be blocked from executing. During the combat state, the main character only has access to three combat moves: attack, dodge or block. Each move invokes their respective method, each with a different goal. The attack method will attempt to lower the foe's health during the next turn. The block method will lower the health reduction received if the opponent used an attack move during the previous turn. The dodge method will attempt to avoid all health reduction received if the opponent used an attacked move during the previous turn, but has a chance of failing. If the main character wins and its health attribute does not reach 0, it will return its non-combat state. If the main character loses and its health attribute does reach 0, it will go into the defeated state.

The final state is the defeated state. The main character can only reach this state through losing during the in combat state. The state is at this point in time intended as temporary state where the main character gets its health attribute restored to the maximum allowed amount so that it can respawn and return to its not in combat state. With respawn is meant that the main character's currentLocation gets changed to the starting location constant. The game does not restart and progress is not lost so that the player gets another chance to beating the game.

Now, we will focus on the NPC state machine diagram. The NPC class is a subclass of the character class. The NPC does not only get interacted with by the main character instance, but also has roles of its own it needs to fullfill. Seen it is a subclass of the character class, the state machine looks fairly similar so we will mainly focus on the differences it has compared to the character state machine diagram. Just like the character state machine diagram, the NPC's actions are dependent on its location and its current state. A NPC can only interact with the main character if the currentLocation variable in GameData is the same as the location the NPC is presiding in. These are, just like in the character state machine diagram, portrayed as guards. Regarding the states it has, it has not in combat and in-combat. 

The not in combat state is its default state. The not in combat state does not have actions of its own, but it does have two ways to transition into the combat state. The first way would be if the NPC is isFightable and gets attacked by the main character while it shares the same location. The second way is when the NPC is isHostile and the NPC attacks the player the moment the player and the NPC share the same location.

The other main state is the in combat state. Just like the main character, it has the same combat moves attack, block or dodge and the combdat works in a similar turn-based fashion. The key difference is that combat moves randomly get selected during the NPC's turn and not by input. Combat ends when one of the participants health attribute reaches 0. If the NPC wins combat and its health attribute does not reach 0, it transitions back into the not in combat state just like in the character state machine diagram. However, if the NPC loses and its health attribute does reach 0, the defeated NPC's instance gets deleted from the game.

Words: Around 1500 including diagrams
## Sequence diagrams									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 2500

## Implementation									
Author(s): `name of the team member(s) responsible for this section`

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 1000

## References

References, if needed.
