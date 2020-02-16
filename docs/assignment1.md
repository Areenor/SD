# Assignment 1
Maximum number of words for this document: 2500


## Introduction									
Authors: Richard Eric van Leeuwen, Peter Wassenaar, Leyla Çelik

Our goal is to build a text adventure game that is playable through the terminal. Our game is inspired by the computer game zork which was one of the first interactive fiction games that allowed you to play through the game and story by inputting commands into the terminal. Our game will be playable in a similar way. The main type of user we are targeting is for someone interested in playing the game, with optionally also users who are interested in building their own levels. Our game will not include multiplayer or a save-system due to the complexity of such features and our limited time window to develop the game.
We will dive into the details regarding the exact details and decisions we made regarding the game in the text and sections below, but I will give a brief description. Just as in the game zork, the player will be presented with the story and options/commands he can use through text in the terminal. Through typing in these commands the player is given, he or she is able to progress through the story. It will feature different modes such as exploration and combat. If the player gets stuck he is able to request a hint to aid him or her in progressing further.
 What we aim to achieve is a system that is easily modifiable and extended. Our main concern is to create a software that allows the developer to easily change the story elements such as characters, items, locations and enemies. To achieve this goal, we will aim to create a few super classes which can be extended/inherited from such that we have a system in place with a clear structure and hierarchy.
The main character is able to interact with other characters and objects, this can be done using a multitude of commands, such as speak to; these commands require the player to specify one or multiple targets. By default most of these interaction commands will print a line of text stating nothing happened. In order for interaction commands to trigger an event a response to the event should be written in the target’s custom class. Events can be implemented using an update service. The update services enable changing an instance of one of the three super classes or their subclasses, making it easy to, for instance, change the description of an instance or remove an object from a location. 
The main character can enter into combat with another character, this will happen when the player enters a location containing a hostile character, or by using the Attack command. Combat is turn based and ends once the hit points of one of the characters reaches zero. Each turn a character can perform one of three actions, attack (damage the opponent), block (reduce the damage done by the opponent's attack next turn), or dodge (evade the opponent's attack next turn). These actions are influenced by the main character statistics strength, constitution  and dexterity, respectively. These stats can be increased by equipping a weapon and an armor piece. If the player is defeated in combat, the main character will respawn in the starting location with full health. Alternatively, the main character will be restored to full health and a story defined event may trigger.
An optional feature is to change the game difficulty, which will make combat harder and reduce the number of hints available. Another optional feature is a creator mode, which allows a player to easily create a custom story using predefined objects, characters and locations. These features are made optional because not implementing them doesn’t affect the player experience we aim for, however we do think it would improve the game.
The game should run as smoothly as possible, with commands doing exactly what they are described to do in up to 5 seconds. When a command should only work in a certain location, it must be recognized as an illegal command in other locations. We need to specify to the player whether a command is illegal or non-existent. An example of an illegal command is teleportation in a combat area. A non-existent command would be a misspelled word. We check if a command exists by comparing it to our list of commands and if it matches, the game will give a response stating the action they have performed according to the command they typed in. After a command is processed, we check whether it got executed correctly. When using items that increase combat stats such as health or strength, we need to check whether these stats are actually increased by the right amount. We do this by, for example, comparing the strength stat before and after equipping a weapon. 
	Described here is a concrete example of how the qualities and checks would work in a typical scenario. The player finds armor, looks through the command list and inputs the command ‘take Armor”. First, the command is checked on whether it is legal and checked for errors. A way we would do this is by keeping a list of all currently legal moves and comparing them to command the player chose. If the command is legal, we proceed to execute the command. Once the execution is done, we check if the command succeeded by checking if the armor got put into the players inventory. If this failed, we rerun the command until it is successfully completed and then we output to the player in the terminal the next scenario and options they have in the gamestate. They can equip the armor by using the equip command and if they do so, we check whether it is really equipped by comparing the health stat from before equipping to after equipping. 


### Functional features

Authors: Peter Wassenaar, Zakhar  Zhornovyi

As a preamble to the table, you can discuss the main line of reasoning you applied for defining the functional features provided in the table.

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Character Statistics | There are character statistics: Strength, Dexterity and Constitution.
At the very beginning of the game, the player can choose to be proficient in one of these statistics (+2), or to be decent in two of them (+1).  |
| F2  | Commands  | The player can control the main character by issuing command-line commands following the syntaxes: command-name ([target-objects]* on [target-objects]*). Some of available command-names are the following:<br/>- Examine ([object/character]) : 
    <br/>Retrieve the description of the current location or  a specified object or character. 
- North/East/South/West:
    Move to the location north, east, south, or west of the current location.
- Climb up/down [object] :
    Target a climbable object to move to the location above or below the 
    current location.
- Take [object] : 
    Put a retrievable object into the main character’s inventory.
- Use [object] (on [object/character]) :
    Use an object (on something), this may consume it.
- Talk to [character] :
    Interact with a specific character.
- Equip [weapon/armor]:
    Equips a weapon or armor piece.
- Attack [character]:
    Enter combat against a character.
- Hint :
   Request a hint. |
| F3  | Locations  | A location consists of a name, description, set of objects and characters, and a list of connecting locations, if discovered. Additionally, certain commands may have different results depending on the location, such as climb up. 

Locations instances: 
  locations are story specific and are initiated with custom values for their 
  variables and  a location type. The location type is either the standard 
  Location type or a custom type created for the story. The configurations of a 
  custom  location are saved in a JSON file.  |
| F4  | Objects | Objects can be interacted with or used to interact with characters or other objects, which may cause an event or create a new object. 

Custom objects:
  Custom objects work the same way for objects as they do for locations. 

Weapons:
  raises a character’s attack stat when equipped. 
 
Armor:
 raises a character’s Constitution stat when equipped. |
| F5 | Characters | Characters can be interacted with, resulting in a conversation, an event, or combad. Characters have an HP, attack and stamina statistic. Additionally they have an isHostile variable, stating if the character will attack the player, and a isFightable variable, stating if the player can attack the character.

Custom characters:
  Custom characters work the same way for character as they do for locations. 

Main character:
 The main character does not have a stamina statistic, but instead a 
  constitution and dexterity statistic, used to calculate blockable damage and 
  dodge chance. |
 | F6 | Combat | Combad is turn based. Each turn a character can make a certain number of actions, they can choose to attack, block, or dodge.

Dodge:
  Chance to avoid an opponent's attack next turn.

Block:
  Decrease the amount of damage done by an opponent's attack next turn. |
  

### Quality requirements
Author: Richard Eric van Leeuwen, Leyla Çelik

Our main goal for our text adventure game is to create a framework that allows itself to be easily modified, extended and has the proper checks and protection against improper use. Our main plan to achieve this goal is to view the game in its different game states. Each game state needs to be correctly represented at all times to the user so that the user knows the commands/options he has. To achieve this goal, we have checks to make sure that actions/commands properly affect the game state and that in a maximum 5 second time window the game state gets updated and displays the new current version of the game state in the terminal.


| ID  | Short name  | Quality attribute | Description  |
|---|---|---|---|
| QR1  | Commands sanity checks | Reliability  | When the player issues a command, the syntax of the command shall always get validated against the format specified in F2 |
| QR2  | Extensible world | Maintainability  | The video game shall be easilty extendable in terms of levels, worlds, interaction points  |
| QR3  | Instantaneous results | Responsiveness  | Once the scores of all soccer players are provided by the user, the results of the virtual matches shall be available within 1 second |
| QR4  | ... | ... | ... |

Each quality requirement must be tagged with the corresponding quality attribute (see corresponding slides of the first lecture for knowing them).

Maximum number of words for this section: 1000

### Java libraries
Author: Richard Eric van Leeuwen

| Name (with link) | Description  |
|---|---|
| [JFoenix](http://www.jfoenix.com/)  | Used for styling the user interface of the system. We chose it among others because ... | 
| [fastjson](https://github.com/alibaba/fastjson) | We will use it for reading and writing JSON configuration files containing the description of the levels of the videogame. We chose it because it is easy to configure and use from Java code and preliminary experiments makes us confident about its correct functioning... |
| ...  | ... |

Maximum number of words for this section: 500
