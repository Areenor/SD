# Assignment 2

**Text adventure group 6**

### Implemented feature

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Tags | Code snippets can be tagged via freely-defined labels called tags  |

### Used modeling tool
For our project we used the modeling tool available on lucidchart.com

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
Author(s): Leyla Celik

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

![Object Diagram](https://github.com/Areenor/SD/blob/Assignment-2/docs/Object_Diagram.png)

This diagram shows a snapshot of a player who had their main character enter a location that contains items and is adjacent to another location with items and a character. The main character is an instance of the class Character with all inherited variables. The main character is named player1 and their stats are 25 HP, 1 Strength, 1 Dexterity and 1 Constitution. The main character’s inventory can consist of items and is, at this point in the snapshot, empty. In addition to being able to pick up items of class Item, the main character could also use an item with the command ‘use’; get a description of an item with the command ‘examine’; add an item to their inventory using the command ‘take’. The main character is at a location named “a beach”. When using the command ‘examine’, the player gets a description of this location: “You are on a beach, there are objects and items. There is a room east.”. This location contains two objects of subclass Equipment: one of type 'weapon' that the main character can pick up (bool IsRetrievable = true;) named ‘Sword’ that gives an attack bonus of 1 (int AttackBonus = 1;) and one of type 'armor' named ‘Chestplate’ that the main character can pick up (bool IsRetrievable = true;) that gives a block bonus of 1 (int BlockBonus = 1;); The location ‘a beach’ has an adjacent location to the east (AdjacentLocations = {"east":"room"}) called ‘room’. 
The adjacent location ‘room’ contains one item of subclass Equipment and an NPC of class NPC. The item is of type 'armor' named ‘Headgear’ that the main character can pick up (bool IsRetrievable = true;) that gives a block bonus of 1; (int BlockBonus = 1;). The NPC is named ‘Guard’, is of type guard, has the description "This is a Guard that attacks on sight.", is hostile (bool IsHostile = true;) and is fightable (bool IsFightable = true;). Possible types of interactions with the NPC are: ‘talk to’, which gives the player dialogue between the main character and NPC; ‘examine’, which gives a description of the NPC; and ‘attack’, which will put the main character into combat with the NPC. An NPC that IsHostile, however, will attack the main character on sight, meaning they will not have time to interact with the NPC. 

## State machine diagrams									
Author(s): Richard Eric van Leeuwen

We ended up creating two state machine diagrams for our text adventure game: The character state machine diagram and the NPC state machine diagram. These diagrams, as the name suggests, focus on the **Character** class and the **NPC** class. We will discuss in detail below each diagram.

First, the character diagram. This diagram will be the one discussed in most detail seen this diagram is responsible for most interactions in our system.

![Character State Machine](https://github.com/Areenor/SD/blob/Assignment-2/docs/character_state_machine.png)

The character diagram is responsible for creating an instance which we call our *MainCharacter* which will be stored in our **GameState** class file. This *MainCharacter* is responsible for the most interactions in our system and its position will be tracked separately under a variable called *CurrentLocation* which is also kept in our **GameState** class file. As the player of the game inputs commands, the main character will be responsible for processing them using its methods and making sure these methods get processed correctly. The commands that can be used at any given moment are dependent on the *maincharacter's* location, which is kept in the *CurrentLocation* variable in **GameState** class file, and its current state. In this diagram we will focus particularly on the *MainCharacter's* states. Regarding the states the *MainCharacter* has which influence the commands it can perform, at this point in time we envision the *MainCharacter* to have three main states: Not in combat, in combat and defeated. We will briefly describe each of these states. 

First of all, the not in combat state. This will be the state the *MainCharacter* has access to most of the commands. During this time, the *MainCharacter* can: talk to NPCS; examine his *Location, Items or NPCS*; move and thus change his *CurrentLocation*; request for a hint that is stored in **GameState** class file; attack a *IsFightable NPC*; take *Items* and put them in its *Inventory* as long as they are *IsRetrievable*; give *Items* to *NPC*; equip *Equipment pieces* and use *Items* in his *Inventory* on *NPCS* or as default on itself. We expect the player to spend most of his time in this state as he interacts and explores the game world. To beat the game the goal of the player is to go from the not in combat state to the endstate of the *MainCharacter*, which is only possible once it has managed to set the *IsFinished* variable in **GameState** class file to true.

The second state is the in combat state. The *MainCharacter* reaches this state by either attacking a *isFightable NPC* itself or by getting attacked by a *isHostile NPC* upon entering the *isHostile NPC's location*. In the combat state, the main character will fight with a NPC in turn-based combat where each participant is allowed to select one combat move during its turn. Combat will continue till one participant's *HitPoints* statistic reaches 0. During the combat state, the main character only has access to three combat moves: attack, dodge or block. Each move invokes their respective method, each with a different goal. The *attack() method* will attempt to lower the foe's *HitPoints*. The *block() method* will lower the *HitPoints* reduction received if the opponent used a attack move during the next turn. The *dodge() method* will attempt to avoid all *HitPoints* reduction received if the opponent used an attacked move during the next turn, but has a chance of failing. If the *MainCharacter* and its *HitPoints* statistic does not reach 0, it will return its non-combat state. If the *MainCharacter* loses and its *HitPoints* statistic does reach 0, it will go into the defeated state.

The final state is the defeated state. The *MainCharacter* can only reach this state through losing during the in combat state. The state is at this point in time intended as temporary state where the *MainCharacter* gets its *HitPoints* statistic restored to original amount so that it can respawn and return to its not in combat state. With respawn is meant that the *CurrentLocation* gets changed to the one given through the constant String SpawnRoom. The game does not restart and progress is not lost so that the player gets another chance to beating the game.

Now, we will focus on the NPC state machine diagram.

![NPC State Machine](https://github.com/Areenor/SD/blob/Assignment-2/docs/NPC_state_machine.png)

The **NPC** class is a subclass of the **Character** class. The *NPC* does not only get interacted with by the *MainCharacter* instance, but also has roles of its own it needs to fullfill. Seen it is a subclass of the **Character** class, the state machine looks fairly similar so we will mainly focus on the differences it has compared to the character state machine diagram. Just like the character state machine diagram, the *NPC's* actions are dependent on its *Location* and its current state.  

The not in combat state is its default state. The not in combat state does not have actions of its own, but it does have two ways to transition into the combat state. The first way would be if the *NPC* is *IsFightable* and gets attacked by the *MainCharacter*. The second way is when the *NPC* is *isHostile* and the NPC attacks the *MainCharacter* whenever he has access to the *MainCharacter*.

The other main state is the in combat state. Just like the *MainCharacter*, it has the same combat moves attack, block or dodge and the combat works in a similar turn-based fashion. The key difference is that combat moves randomly get selected during the *NPC's* turn and not by input. Combat ends when one of the participants *HitPoints* statistic reaches 0. If the *NPC* wins combat and its *HitPoints* statistic does not reach 0, it transitions back into the not in combat state just like in the character state machine diagram. However, if the *NPC* loses and its *HitPoints& statistic does reach 0, the defeated *NPC's* instance gets deleted from the game.

## Sequence diagrams									
Author(s): Richard Eric van Leeuwen, Leyla Celik

We have two sequence diagrams we would like to discuss. The two sequence diagrams shows two typical scenarios: the player engaging in combat with a *NPC* and the player moving around between locations.

First, we would like to discuss the combat scenario. 

![Sequence Diagram Combat](https://github.com/Areenor/SD/blob/Assignment-2/docs/Sequencediagramcombat.png)

In the combat sequence diagram we show a typical scenario where the player engages in combat with a *NPC* using the *MainCharacter*. The interaction partners in this particular diagram are the following: Player, *MainCharacter* and *NPC*.  We will discuss each of the interaction partners' involvement and what their tasks are during this combat scenario. We will also, while discussing each individual interaction partner, elaborate on the messages they send and in what fragments of the interaction they are involved in.

First, we will discuss the Player. The Player refers to the stakeholder of this diagram and thus depicts an external influence. The player is the one who inputs commands and controls the *MainCharacter*. In this scenario, as depicted in the diagram, the player wishes to engage in combat with a particular *NPC*.  To do so, he inputs the command “attack NPC” which starts the sequence of events. The player receives information as output about the result of his attack and from there is able to direct the *MainCharacter* to either attack the *NPC* or block or dodge the next attack from the *NPC*.  The player is able to input these combat moves till either the *MainCharacter* or the engaged *NPC* is defeated by lowering their *HitPoints* to 0. In this scenario the player put in two attack input commands which ends the battle with the *NPC’s HitPoints* reaching 0 and being defeated.

The *MainCharacter* is the one who processes the commands he receives from the player and is the one who is directly engaged in combat with the *NPC*. As it receives from the player the "attack NPC" command, it will invoke the *attack(NPC)* method to attack the *NPC* and start combat. Now the *MainCharacter* will have to wait for the *NPC* to do his move. Once the *MainCharacter* has received the move from the *NPC*, it will output the results of the battle to the player which includes the damage dealt and the current health from both the *MainCharacter* and the *NPC*. In this scenario, the *MainCharacter* attacks twice. The first time the *NPC’s HitPoints* reach 5 and the second time the *NPC* is defeated as *NPC’s HitPoints* reach 0.

Finally, we will discuss the *NPC*. The *NPC* converses in a similar way as the *MainCharacter*. Upon being attacked by the *MainCharacter* through the *attack(NPC)* method, combat starts and the *NPC* will randomly select his next combat move. Once the combat move is select, it is the *NPC's* to perform a combat method from which he has the same options as the main character: *attack()*, *block()* and *dodge()*. In this diagram, after the first attack from the *MainCharacter*, they perform an attack back using the *attack(MainCharacter)* method. Sadly for the *NPC*, it gets attacked again, its *HitPoints* reach 0, and it gets removed from the game. 

Now, we would like to discuss the movement scenario.

![Sequence Diagram Teleport](https://github.com/Areenor/SD/blob/Assignment-2/docs/Sequence_Diagram_Teleportation.png)

This teleportation sequence diagram shows the way the program handles a teleportation command when the direction the player wants to go to does not exist and when it does exist. The interaction partners in this diagram are the Player, Main Character and Location. We will discuss each of the interactions partners’ involvement and what their tasks are during this scenario. Player depicts an external influence and is the stakeholder of this diagram. The player is the person playing the game who inputs commands and controls the main character. The situation in this sequence diagram is as follows: the player wants to move the main character to a new location. The player inputs a command to move the main character north of its current location: “move north”. 
The main character processes the commands they receive from the player and will teleport if the direction the player wants to go in has a location. The main character receives the “move north” command and will invoke the move(“north”) method to Location. 
Location is mostly a container for data, but also sends back the ‘ok’ for teleportation if the direction chosen by the player has a location with a description of the location, or a message saying there was nothing in that direction if there is no location in that direction. The player chose north as their direction to move in, but there are no adjacent locations north of the main character’s current location, so Location sends the player the response “You found nothing traveling in this direction and returned to your original location.” as output. The player tries to change locations again, this time wanting to move the main character east of its current location with the command: “move east”. The main character receives the “move east” command and will invoke the move(“east”) method to Location. There is an adjacent location east of the main character’s current location and the player gets the response: "You are in a room, there are objects and items. There is a beach west." as output. 



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
