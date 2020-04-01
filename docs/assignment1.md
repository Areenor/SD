# Assignment 1
Group: 6, text adventure

Group members: Richard Eric van Leeuwen, Peter Wassenaar, Leyla Çelik and Zakhar Zhornovyi

## Introduction									
Authors: Richard Eric van Leeuwen, Peter Wassenaar, Leyla Çelik

Our goal is to build a text adventure game that is playable through the terminal. Our game is inspired by the computer game zork which was one of the first interactive fiction games that allowed you to play through the game and story by inputting commands into the terminal. Our game will be playable in a similar way. The main type of user we are targeting is for someone interested in playing the game, with optionally also users who are interested in building their own levels. Our game will not include multiplayer or a save-system due to the complexity of such features and our limited time window to develop the game.
	
We will dive into the details regarding the exact details and decisions we made regarding the game in the text and sections below, but we will give a brief description. Just as in the game zork, the player will be presented with the story and options/commands he can use through text in the terminal. Through typing in these commands the player is given, he or she is able to progress through the story. It will feature different modes such as exploration and combat. If the player gets stuck he is able to request a hint to aid him or her in progressing further.
 What we aim to achieve is a system that is easily modifiable and extended. Our main concern is to create a software that allows the developer to easily change the story elements such as characters, items, locations and enemies. To achieve this goal, we will aim to create a few super classes which can be extended/inherited from such that we have a system in place with a clear structure and hierarchy.
 
The main player is able to interact with other characters and objects, this can be done using a multitude of commands, such as speak to; these commands require the player to specify one or multiple targets. By default most of these interaction commands will print a line of text stating nothing happened. In order for interaction commands to trigger an event a response to the event should be written in the target’s custom class. Events can be implemented using an update service. The update services enable changing an instance of one of the three super classes or their subclasses, making it easy to, for instance, change the description of an instance or remove an object from a location. 
	
The main player can enter into combat with another player, this will happen when the player enters a location containing a hostile player, or by using the Attack command. Combat is turn based and ends once the hit points of one of the characters reaches zero. Each turn a player can perform one of three actions, attack (damage the opponent), block (reduce the damage done by the opponent's attack next turn), or dodge (evade the opponent's attack next turn). These actions are influenced by the main player statistics strength, constitution  and dexterity, respectively. These stats can be increased by equipping a weapon and an armor piece. If the player is defeated in combat, the main player will respawn in the starting location with full health.
	
An optional feature is to change the game difficulty, which will make combat harder and reduce the number of hints available. Another optional feature is a creator mode, which allows a player to easily create a custom story using predefined objects, characters and locations. These features are made optional because not implementing them doesn’t affect the player experience we aim for, however we do think it would improve the game.
The game should run as smoothly as possible, with commands doing exactly what they are described to do in up to 5 seconds. When a command should only work in a certain location, it must be recognized as an illegal command in other locations. We need to specify to the player whether a command is illegal or non-existent. An example of an illegal command is teleportation in a combat area. A non-existent command would be a misspelled word. We check if a command exists by comparing it to our list of commands and if it matches, the game will give a response stating the action they have performed according to the command they typed in. After a command is processed, we check whether it got executed correctly. When using items that increase combat stats such as health or strength, we need to check whether these stats are actually increased by the right amount. We do this by, for example, comparing the strength stat before and after equipping a weapon. 
	
Described here is a concrete example of how the qualities and checks would work in a typical scenario. The player finds armor, looks through the command list and inputs the command ‘take Armor”. First, the command is checked on whether it is legal and checked for errors. A way we would do this is by keeping a list of all currently legal moves and comparing them to command the player chose. If the command is legal, we proceed to execute the command. Once the execution is done, we check if the command succeeded by checking if the armor got put into the players inventory. If this failed, we rerun the command until it is successfully completed and then we output to the player in the terminal the next scenario and options they have in the gamestate. They can equip the armor by using the equip command and if they do so, we check whether it is really equipped by comparing the health stat from before equipping to after equipping. 


### Functional features

Authors: Peter Wassenaar, Zakhar  Zhornovyi

While the most basic game features such as commands, locations and objects were included in regard to the computer game zork, which was taken as the base for our project, we also wanted to enrich our game with more advanced features. It is worth mentioning that some of these features, such as multiple difficulties and creator mode, did not make a cut for our final paper due to the word count limitation. We ended up adding two substantial features on top of the basic ones: statistics of a characters and combat. In the first place, we decided to add combat to increase the variety of gameplay. While we were creating the concept of the combat system, we came to the realization that adding player statistics which would influence the combat opportunities is an elegant way of making the combat feel more realistic and natural.

<p><br /><br /></p>
<table>
<tbody>
<tr>
<td>
<p><strong>ID</strong></p>
</td>
<td>
<p><strong>Short name</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F1</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Character Statistics</span></p>
</td>
<td>
<p><span style="font-weight: 400;">There are character statistics: </span><em><span style="font-weight: 400;">S</span></em><em><span style="font-weight: 400;">trength</span></em><span style="font-weight: 400;">, </span><em><span style="font-weight: 400;">Dexterity </span></em><span style="font-weight: 400;">and </span><em><span style="font-weight: 400;">Constitution</span></em><em><span style="font-weight: 400;">.</span></em></p>
<p><span style="font-weight: 400;">At the very beginning of the game, the player can divide three points over these three different statistics.</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F2</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Commands</span></p>
</td>
<td>
<p><span style="font-weight: 400;">The player can control the main character by issuing command-line commands following the syntaxes: </span><span style="font-weight: 400;">command-name ([target-objects]* on [target-objects]*)</span><span style="font-weight: 400;">. Some of available </span><span style="font-weight: 400;">command-names</span><span style="font-weight: 400;"> are the following:</span></p>
<br />
<p><span style="font-weight: 400;">- </span><strong>Examine </strong><span style="font-weight: 400;">(</span><em><span style="font-weight: 400;">[object/NPC])</span></em><span style="font-weight: 400;"> :&nbsp;</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;Retrieve the description of the current location or&nbsp; a specified item or </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; &nbsp; NPC.&nbsp;</span></p>
<p><span style="font-weight: 400;">-</span><strong> Move </strong><em><span style="font-weight: 400;">[direction]</span></em><span style="font-weight: 400;">:</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;Move to the adjacent location in the given direction .</span></p>
<p><span style="font-weight: 400;">- </span><strong>Take </strong><em><span style="font-weight: 400;">[object] </span></em><span style="font-weight: 400;">: </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; &nbsp; Put a retrievable item into the player&rsquo;s inventory.</span></p>
<p><span style="font-weight: 400;">- </span><strong>Use </strong><em><span style="font-weight: 400;">[object] (</span></em><strong>on </strong><em><span style="font-weight: 400;">[object/character]) </span></em><span style="font-weight: 400;">:</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; &nbsp; Use an item (on something), this may consume it.</span></p>
<p><span style="font-weight: 400;">- </span><strong>Talk to</strong> <em><span style="font-weight: 400;">[NPC] </span></em><span style="font-weight: 400;">:</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;Interact with a specific NPC.</span></p>
<p><span style="font-weight: 400;">- </span><strong>Attack </strong><em><span style="font-weight: 400;">[NPC]:</span></em></p>
<p><em><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;</span></em><span style="font-weight: 400;">Enter combat against an NPC.</span></p>
<p><span style="font-weight: 400;">- </span><strong>Commands</strong><span style="font-weight: 400;"> :</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;&nbsp;&nbsp;Prints all commands usable in the current situation.</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F3</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Locations</span></p>
</td>
<td>
<p><span style="font-weight: 400;">A location consists of a name, description, set of items and NPCs, and a list of connecting locations. Some adjacent locations can not be entered right away, but must first be unlocked.</span></p>
<br />
<p><strong>Locations instances</strong><span style="font-weight: 400;">: </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; locations are story specific and are initiated using a JSON file.&nbsp;</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F4</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Item</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Items can be used, either on their own or on an NPC or other object, the effect of using an item depends on which type of item it is.</span></p>
<br />
<p><strong>Item instances:</strong></p>
<p><strong>&nbsp;&nbsp;</strong><span style="font-weight: 400;">Just like </span><strong>Location</strong><span style="font-weight: 400;">, items are initiated using JSON files, however not all items </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; use the same JSON file, that depends on the item&rsquo;s type.&nbsp;</span></p>
<br />
<p><strong>Item subclasses:</strong></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;There are multiple so called subclasses of </span><strong>Item</strong><span style="font-weight: 400;">, each with their own use. </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; </span><strong>Junk</strong><span style="font-weight: 400;"> items have no use at all. </span><strong>Consumable</strong><span style="font-weight: 400;"> are one time use items </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; increasing the player&rsquo;s statistics or decreasing those of an enemy NPC. </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; </span><strong>KeyItem</strong><span style="font-weight: 400;"> are items used to unlock locations or for trading with NPCs. </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; </span><strong>Equipment </strong><span style="font-weight: 400;">are items which can give the player a lasting bonus to their </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; statistics.&nbsp;</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F5</span></p>
</td>
<td>
<p><span style="font-weight: 400;">NPCs</span></p>
</td>
<td>
<p><span style="font-weight: 400;">NPCs can be interacted with, resulting in a conversation, an event, or combad. NPCs have an HP, attack, block and stamina statistic. Additionally they have an </span><em><span style="font-weight: 400;">isHostile</span></em><span style="font-weight: 400;"> variable, stating if the character will attack the player, and a </span><em><span style="font-weight: 400;">isFightable</span></em><span style="font-weight: 400;"> variable, stating if the player can attack the character.</span></p>
<br />
<p><strong>NPC instances:</strong></p>
<p><strong>&nbsp;&nbsp;&nbsp;</strong><span style="font-weight: 400;">Instances of </span><strong>NPC</strong><span style="font-weight: 400;"> are created exactly the same way as with </span><strong>Location</strong><span style="font-weight: 400;">, using </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; JSON files.</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F6</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Player</span></p>
</td>
<td>
<p><span style="font-weight: 400;">The user controls a player character, which is similar to an NPC as it also has HP, attack, block and stamina statistics. The player character will move between locations and performs the actions corresponding to the commands given by the user. A player has its own inventory in which it can store items; items stored here can be used.&nbsp;</span></p>
<br />
<p><strong>Player instance</strong><span style="font-weight: 400;"> :&nbsp;</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;A </span><strong>Player </strong><span style="font-weight: 400;">instance is created using the character statistics choices and </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; username submitted by the user. This instance will be referred to as the main </span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">&nbsp; character.</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">F7</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Combat</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Combat is turn based. Each turn a character can make a certain number of actions, they can choose to attack or skip their turn. Each action consumes stamina, if an enemy attacks and the player still has stamina, they can choose to block, dodge or do nothing. Stamina regenerates at the start of the turn.</span></p>
<br />
<p><strong>Attack</strong><span style="font-weight: 400;">:&nbsp;</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;Deal damage to an opponent.</span></p>
<br />
<p><strong>Skip:</strong></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;End your turn, saving excess stamina in order to respond to enemy attacks.</span></p>
<br />
<p><strong>Dodge</strong><span style="font-weight: 400;">:</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;Chance to avoid an opponent's attack.</span></p>
<br />
<p><strong>Block</strong><span style="font-weight: 400;">:</span></p>
<p><span style="font-weight: 400;">&nbsp;&nbsp;Decrease the amount of damage done by an opponent's attack.</span></p>
</td>
</tr>
</tbody>
</table>
<p><br /><br /><br /><br /></p>
  

### Quality requirements
Authors: Richard Eric van Leeuwen, Leyla Çelik

Our main goal for our text adventure game is to create a framework that allows itself to be easily modified, extended and has the proper checks and protection against improper use. Our main plan to achieve this goal is to view the game in its different game states. Each game state needs to be correctly represented at all times to the user so that the user knows the commands/options he has. To achieve this goal, we have checks to make sure that actions/commands properly affect the game state and that in a maximum 5 second time window the game state gets updated and displays the new current version of the game state in the terminal.

| ID  | Short name  | Quality attribute | Description  |
|---|---|---|---|
| QR1  | Mutable game world | Maintainability  | The game world (The locations, characters, enemies and items) can be extended and changed with ease. |
| QR2  | Accessible world | Usability/Availability | All available world features such as different locations, characters, items and items will be accessible to the player if they are legally accessible and not blocked intentionally in that current game state. |
| QR3  | Command checks | Reliability | All commands are checked for errors in the syntax and/or correct use. In case of errors and/or misuse of a command, the command will be prevented from occurring and a suitable error message will be returned. |
| QR4  | Command reliability | Reliability | All commands will return the correct reply and, if applicable, affect the game state correctly when requested to be used by the user. Commands are prevented from being used outside of their allowed game state. This means that, for example, a command only available in location A, will only be functional in location A. |
| QR5  |World state presentation | Usability | The presented game state, which is on the terminal through text (e.g. current location, equipments, etc.) will always be up-to-date with the current game state, which is kept in a separate GameData file. |
| QR6  | World state updates | Responsiveness | When an action affects the game state, either through a command or due to a prior game state, the game state updates accordingly within a 5 second time window and returns to the user the proper reply and/or information. |


### Java libraries
Author: Richard Eric van Leeuwen

| Name (with link) | Description  |
|---|---|
| [Text-IO](https://github.com/beryx/text-io)  |  Seen our text adventure games will be played through the console, we were looking for a library that allows us to easily display information from the console, simple text but also lists, and the ability to read user input. We decided to go for Text-IO because it satisfies those requirements mentioned. We also were on the fence with a pure command line parsers args4j or a more graphical alternative such as Lanterna, but we feel that Text-IO came out on top due to its ability to also display information while also keeping the complexity low. | 
| [Fastjson](https://github.com/alibaba/fastjson) | We would like to be able to store certain data in json files to make it easier to  use, manage and group our data while lowering the overall complexity. For this we need a library that is able to convert to and from json objects in an easy and clear way. We have considered various options such as moshi, but we decided to go for fastjson because it is fast, requires no additional dependencies and is easy to use. |
