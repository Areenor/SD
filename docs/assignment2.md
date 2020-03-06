# Assignment 2

**Text adventure group 6**

### Implemented feature

<table>
<thead>
<tr>
<th>ID</th>
<th>Short name</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>F1</td>
<td>Choosing character`s main starting statistics</td>
<td>There are three character statistics, parameters of which are chosen by the player at the very beginning of the game: Strength, Dexterity and Constitution. The player can choose to be proficient (+2) in one of these statistics, or to be decent (+1) in two of them. The Strength statistic increases the standard damage the player can deal, the Dexterity statistic increases the chance to dodge an attack and the number of actions a player can make in one turn while Constitution increases the standard possible blocked damage and maximum number of Hit Points of a main character. Main character may later increase any of this statistics with, for example, a special item.</td>
</tr>
<tr>
<td>F2</td>
<td>Commands</td>
<td>The player can control the main character by issuing command-line commands following the syntaxes: command-name ([target-objects]* on [target-objects]*). Some of available command-names are the following:<br />- examine (optionally [item/NPC]):<br />&emsp;Retrieve the description of the current location by default or if specified an item or NPC.<br />- move (Up/Down/North/East/South/West):<br />&emsp;Move to the location up, down, north, east, south, or west of the current location.<br />- take [item] :<br />&emsp;Put a retrievable item into the main character&rsquo;s inventory.<br />-  talk to [NPC] :<br />&emsp;Get dialogue line of a specified NPC.</td>
</tr>
<tr>
<td>F3</td>
<td>Locations</td>
<td>A location consists of a name, description, set of objects and characters, and a list of connecting locations. Additionally, certain commands may have different results depending on the location.<br /><br />Story-defined instances:<br />&emsp; Locations are story specific and are initiated with custom values for their variables. The configurations of such story specific locations are saved in a JSON file.</td>
</tr>
<tr>
<td>F4</td>
<td>Items</td>
<td>Items are stored in inventory of characters or are located at locations and can be used by main character on himself, NPCs or other items, which may cause an event or create a new item. Items have name, description and can either be retrievable or not.<br /><br />Equipment:<br />&emsp; Equipment is a subclass of items. Such items can be equipped and thus have an influence on character statistics. If the type of equipment is weapon, it raises a damage the player can deal when equipped. If the type of equipment is armor, it raises a character&rsquo;s maximum number of Hit Points when equipped.<br /><br />Story-defined items:<br />&emsp; Story-defined items work the same way for items as they do for locations&nbsp;</td>
</tr>
<tr>
<td>F5</td>
<td>Characters</td>
<td>Characters are either an NPC located on locations or the main character. All characters have a name and three main statistics: Strength, Dexterity and Constitution. All characters also have special HP and Attack statistics which determine maximum health and standard damage dealt by the character. Stamina statistic determines the number of actions a character can make in one turn during combat. All characters have an internal inventory, which is a list of items they posses.<br /><br />Main character:<br />&emsp; The main character is just an instance of Character class with all inherited variables which is controlled by the player. The main character may pick up items which will be placed in his inventory, allowing the main character to perform actions using these items.<br /><br />Non Player Characters (NPCs):<br />&emsp; NPCs are the of the subclass of Character class. Each NPC has a Type variable which determines a nature of NPC (such as vampire or human), isFightable variable which determines if it the NPC can enter combat and a isHostile variable to determine if a NPC is friendly to the player. Once defeated, NPCs may drop items in their inventory which can be picked up by the main character. NPCs also can have description and dialogue data for when they converse with the main character.<br /><br />Story-defined NPCs: &emsp;<br />&emsp; Story-defined NPCs work the same way for NPC as they do for locations.</td>
</tr>
</tbody>
</table>

### Used modeling tool
For our project we used the modeling tool available on lucidchart.com

## Class diagram									
Author(s): Peter Wassenaar, Zakhar Zhornovyi, Richard Eric van Leeuwen

![Class Diagram](https://github.com/Areenor/SD/blob/Assignment-2/docs/classDiagram.png)

### Location

The **Location** class represents a room or area which contains objects and characters. *Locations* are linked together and can be accessed through different directions using the *AdjacentLocations* hashmap.

| Attributes  | Operations  | Association  |
|---|---|---|
| *Name*: String containing the name of the location.  | *Examine()*: Prints the description of the *CurrentLocation* to the terminal. |   |
| *Description*: String describing the location.  | *Move()*: Sets the *CurrentLocation* to the *location's* adjacent *location* corresponding to a given direction. |   |
| *Items*: Hashmap containing the names of the items in the location paired to instances of those items. |  |   |
| *AdjacentLocations*: Hashmap containing the names of the *locations* adjacent to the *location*, paired to the corresponding direction. |  |   |

### Character

The **Character** class represents a person or other being which acts as living entity in the game. Such living entities or beings would be, for example, a merchant, a bandit or a wolf. Note, the role a being plays in a story determines if it should be an instance of the **Character** or **Item** class; in one story a salamander may be regarded as a *character* where it can be battled with or spoken to, while another story sees it as (functionally speaking) an *item* that, for example, can be put in an *Inventory*.

| Attributes  | Operations  | Association  |
|---|---|---|
| *Name*: String containing the name of the character. | *ExecuteCommand()* : Makes the character perform an action corresponding to a command given by the player. | **Character** is present in a location. Cannot exist outside a location or in multiple ones at the same time.  |
| *HitPoints*: number of hit points a character has, used for combat. | | *Examine* : Calls on the *examine* operation for an instance of **Location**, **Item** or **NPC**.  |
| *Inventory*: Hashmap containing the names of the items in the possession of the *Character* paired to instances of those *Items*. |  | *Move*: Calls on the *move()* operation of an instance of **Location**.  |
| *Strength*: Statistic used for damage calculation during combat. |  | *Talk to* : Calls on the *talk to ()* operation of an instance of **NPC**.  |
| *Dexterity* : Statistic used for calculating the chance to successfully evade the next attack during combat when using *dodge()*. |  | *Attack*: Calls on the *attack()* operation on an instance of **NPC**, fails if the npc's attribute *IsFightabel* is false.  |
| *Constitution* : Statistic used to calculate the damage mitigation during combat when using block(). |  | *Equip* : Calls on the *equip()* operation of an instance of **Equipment**. |
| |  | *Use* : Calls on the *use()* operation of an instance of **Item**.|
| |  | *Take* : Calls on the *take()* operation of an instance of **Item**.|
| |  | *Use on*: Calls on the *use(on )* operation of an instance of **Item**, passing the name of an instance of **Item** or **Character** as a parameter to use the item on. If no target is selected, the *MainCharacter* is the target. |
| | | *Give to*: Calls on the *Give to()* operation of an instance of **Item**, passing the name of an instance of **Character** as a parameter to give the item to. |

### NPC

A subclass which inherits from the **Character** class. A character which is not controlled by the player.

| Attributes  | Operations  | Association  |
|---|---|---|
| *Type* : a string containing the type name to which an NPC belongs. The type attribute may be used in a story to simplify describing the effect of items on a multitude of characters.  | *Examine()*: Prints the description of the NPC to the terminal. | **NPC** can be selected as the target of the *Use on ()* operation of an item, resulting in an item being used on the NPC.  |
| *Description* : String describing the *NPC*  | *Talk to()* : Starts a conversation with the *NPC*. The dialogue lines of the *NPC* to will be printed inthe terminal during the conversation. |   |
| *Dialogue* : String of conversation lines of the *NPC* used during a conversation with the player. | *Attack()* : Initiates combat with the NPC if the property *IsFightable* is true.  |   |
| *IsHostile* : Boolean describing if the *NPC* would attack the player on sight (when the player enters the location the *NPC* is in). Is true when the NPC would attack the player on sight. |  |   |
 | *IsFightable* : Boolean describing if combat can be initiated with the NPC. Is true when combat can be initiated. | | |
 
 ### Item
 
An object which is present in a *location* or the *Inventory* of a *character*. Some *items* can be picked up and removed from a location and placed into the *Inventory* of a *character*. *Items* inside of the inventory of the *MainCharacter* can be used by the player, either on a *character* or another *item*.
 
| Attributes  | Operations  | Association  |
|---|---|---|
|  *Name*: String containing the name of the *item*. | *Examine()*: Prints the description of the *Item* to the terminal. | *Items* are present in a location or a character’s inventory. Cannot exist outside a location or inventory or in multiple ones at the same time.  |
|  *Description* : String describing the *item*. | *Use()*: Perform an action with an *item* specified by the story. If no argument for the target is given, the item is used on its own, else it is used on the target, resulting in a different outcome/event. Can only be used on *items* inside of the *MainCharacter's Inventory*. |  *Items* can be selected as the target of the Use on operation of an item, resulting in an item being used on the target item. |
| *IsRetrievable* : Boolean describing if the *item* can be picked up from a *location* and placed in a *character's Inventory*.| *Give to()*: Give an *item* to a target *character*, resulting in a story specified event. Can only be used on *items* inside of the *MainCharacter's Inventory*. |   |
|  | *Take()* : Removes the *item* from the *CurrentLocation* and places into the *MainCharacter's Inventory*. The command can only be used on *items* present in a *location*. |   |

 ### Equipment
 
Equipment is a subclass which inherits the **Item** class. A special type of item which can be equipped by a character, adding a bonus to either their *Strength* or *Constitution* statistics. The bonus is lost when another piece of equipment is equipped. There are two types of *equipment*, armor and weapons. Only one piece of *equipment* per type can be equipped at the same time. Equipping *equipment* of a type which is already being worn by the character replaces the original piece of equipment. Armor tends to give a greater bonus to the *Constitution* statistic, while weapons tend to give a higher bonus to the *Strength* statistic.

| Attributes  | Operations  | Association  |
|---|---|---|
| *Type* : Specifies what kind of *equipment* the instance is, a weapon or an armor piece. | *Equip()* : Equips the equipment to the *MainCharacter*, adding the *BlockBonus* and *AttackBonus* to the *MainCharacter's Constitution* and *Strength* statistics respectively. Replaces the currently equipped piece of *equipment* of the same *type*. |   |
| *BlockBonus* : The bonus added to the *Constitution* statistics when the *equipment* is equipped. |  |   |
| *AttackBonus* : The bonus added to the *Strength* statistics when the *equipment* is equipped. |  |   |

## Object diagrams								
Author(s): Leyla Celik

![Object Diagram](https://github.com/Areenor/SD/blob/Assignment-2/docs/Object_Diagram_.png)

This diagram shows a snapshot of a player who had their **MainCharacter** enter a **Location** that contains **Equipment** and is adjacent to another **Location** with **Equipment** and a **Character**. The **MainCharacter** is an instance of the class Character with all inherited variables. The **MainCharacter** is named player1 and their stats are 25 *HP*, 1 *Strength*, 1 *Dexterity* and 1 *Constitution*. The **MainCharacter**’s inventory can consist of, among other things, **Equipment** and is, at this point in the snapshot, empty. In addition to being able to pick up **Equipment**, the **MainCharacter** could also use **Equipment** with the command *‘use’*; get a description of the **Equipment** with the command *‘examine’*; add **Equipment** to their inventory using the command *‘take’*. The **MainCharacter** is at a **Location** named *“a beach”*. When using the command *‘examine’*, the player gets a description of this **Location**: “You are on a beach, there are objects and items. There is a room east.”. This **Location** contains two objects of subclass Equipment: one of type *'weapon'* that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) named ‘Sword’ that gives an attack bonus of 1 (int *AttackBonus* = 1;) and one of type *'armor'* named ‘Chestplate’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that gives a block bonus of 1 (int BlockBonus = 1;); The **Location** *‘a beach’* has an adjacent location to the east (*AdjacentLocations* = {"east":"room"}) called *‘room’*. 
The adjacent location *‘room’* contains one item of subclass Equipment and an **NPC** of class **NPC**. The **Equipment** is of type *'armor'* named ‘Headgear’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that gives a block bonus of 1; (int *BlockBonus* = 1;). The **NPC** is named ‘Guard’, is of type *‘guard’*, has the description "This is a Guard that attacks on sight.", is hostile (bool *IsHostile* = true;) and is fightable (bool *IsFightable* = true;). Possible types of interactions with the **NPC** are: *‘talk to’*, which gives the player dialogue between the **MainCharacter** and **NPC**; *‘examine’*, which gives a description of the **NPC**; and *‘attack’*, which will put the **MainCharacter** into combat with the **NPC**. An **NPC** that *IsHostile*, however, will attack the **MainCharacter** on sight, meaning they will not have time to interact with the **NPC**.

## State machine diagrams									
Author(s): Richard Eric van Leeuwen

We ended up creating two state machine diagrams for our text adventure game: The character state machine diagram and the NPC state machine diagram. These diagrams, as the name suggests, focus on the **Character** class and the **NPC** class. We will discuss in detail below each diagram.

First, the character diagram. This diagram will be the one discussed in most detail seen this diagram is responsible for most interactions in our system.

![Character State Machine](https://github.com/Areenor/SD/blob/Assignment-2/docs/characterstatemachine.png)

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

In the combat sequence diagram we show a typical scenario where the player engages in combat with a **NPC**using the **MainCharacter**. The interaction partners in this particular diagram are the following: Player, **MainCharacter** and **NPC**.  We will discuss each of the interaction partners' involvement and what their tasks are during this combat scenario. We will also, while discussing each individual interaction partner, elaborate on the messages they send and in what fragments of the interaction they are involved in.

First, we will discuss the Player. The Player refers to the stakeholder of this diagram and thus depicts an external influence. The player is the one who inputs commands and controls the **MainCharacter**. In this scenario, as depicted in the diagram, the player wishes to engage in combat with a particular **NPC**.  To do so, he inputs the command “attack NPC” which starts the sequence of events. The player receives information as output about the result of his attack and from there is able to direct the **MainCharacter** to either attack the **NPC**or block or dodge the next attack from the **NPC**.  The player is able to input these combat moves till either the **MainCharacter** or the engaged **NPC**is defeated by lowering their *HitPoints* to 0. In this scenario the player put in two attack input commands which ends the battle with the *NPC’s HitPoints* reaching 0 and being defeated.

The **MainCharacter** is the one who processes the commands he receives from the player and is the one who is directly engaged in combat with the **NPC**. As it receives from the player the "attack NPC" command, it will invoke the *attack(NPC)* method to attack the **NPC**and start combat. Now the **MainCharacter** will have to wait for the **NPC**to do his move. Once the **MainCharacter** has received the move from the *NPC*, it will output the results of the battle to the player which includes the damage dealt and the current health from both the **MainCharacter** and the **NPC**. In this scenario, the **MainCharacter** attacks twice. The first time the *NPC’s HitPoints* reach 5 and the second time the **NPC**is defeated as *NPC’s HitPoints* reach 0.

Finally, we will discuss the **NPC**. The **NPC**converses in a similar way as the **MainCharacter**. Upon being attacked by the **MainCharacter** through the *attack(NPC)* method, combat starts and the **NPC**will randomly select his next combat move. Once the combat move is select, it is the *NPC's* to perform a combat method from which he has the same options as the main character: *attack()*, *block()* and *dodge()*. In this diagram, after the first attack from the **MainCharacter**, they perform an attack back using the *attack(MainCharacter)* method. Sadly for the *NPC*, it gets attacked again, its *HitPoints* reach 0, and it gets removed from the game.
 

Now, we would like to discuss the movement scenario.

![Sequence Diagram Movement](https://github.com/Areenor/SD/blob/Assignment-2/docs/Sequence_Diagram_Movement.png)

This movement sequence diagram shows the way the program handles a movement command when the direction the player wants to go to does not exist and when it does exist. The interaction partners in this diagram are the Player, **MainCharacter** and **Location**. We will discuss each of the interactions partners’ involvement and what their tasks are during this scenario. Player depicts an external influence and is the stakeholder of this diagram. The player is the person playing the game who inputs commands and controls the **MainCharacter**. The situation in this sequence diagram is as follows: the player wants to move the **MainCharacter** to a new **Location**. The player inputs a command to move the **MainCharacter** north of its current **Location**: “move north”. 
The **MainCharacter** processes the commands they receive from the player and will teleport if the direction the player wants to go in has a **Location**. The **MainCharacter** receives the “move north” command and will invoke the *move(“north”)* method to **Location**. 
**Location** is mostly a container for data, but also sends back the ‘ok’ for teleportation if the direction chosen by the player has a **Location** with a description of the **Location**, or a message saying there was nothing in that direction if there is no **Location** in that direction. The player chose north as their direction to move in, but there are no *AdjacentLocations* north of the **MainCharacter**’s current **Location**, so Location sends the player the response “You found nothing traveling in this direction and returned to your original **Location**.” as output. The player tries to change locations again, this time wanting to move the **MainCharacter** east of its current **Location** with the command: “move east”. The **MainCharacter** receives the “move east” command and will invoke the *move(“east”)* method to **Location**. There is an adjacent **Location** east of the **MainCharacter**’s current **Location** and the player gets the response: "You are in a room, there are objects and items. There is a beach west." as output.




## Implementation									
Author(s): Peter Wassenaar

<p><span style="font-weight: 400;">Our program utilizes a set of </span><strong>Location</strong><span style="font-weight: 400;"> instances which function as the &ldquo;playing field&rdquo; of the game. The locations contain instances of </span><strong>NPC </strong><span style="font-weight: 400;">and </span><strong>Item</strong><span style="font-weight: 400;">. At all times there is a copy of a </span><strong>Location</strong><span style="font-weight: 400;"> instance assigned to a static variable called </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;"> which is part of the public </span><strong>GameState</strong><span style="font-weight: 400;"> class, this variable reflects the location in which the player&rsquo;s character is currently present. The player&rsquo;s character is an instance of the </span><strong>Character</strong><span style="font-weight: 400;"> class named </span><em><span style="font-weight: 400;">Main</span></em><span style="font-weight: 400;">C</span><em><span style="font-weight: 400;">haracter</span></em><span style="font-weight: 400;">. Whenever the player issues a command in the terminal, </span><em><span style="font-weight: 400;">MainCharacter</span></em><span style="font-weight: 400;"> will call a function of </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;"> or a function of an npc or item which is contained either in </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;">, or alternatively for items, contained in </span><em><span style="font-weight: 400;">MainCharacter</span></em><span style="font-weight: 400;">.</span><em><span style="font-weight: 400;">Inventory</span></em><span style="font-weight: 400;">.&nbsp;</span></p>
<p><span style="font-weight: 400;">The player can orientate themself and figure which commands to use in order to make progress based on </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;">.</span><em><span style="font-weight: 400;">description </span></em><span style="font-weight: 400;">which is printed to the terminal upon entering the location or using the </span><em><span style="font-weight: 400;">Examine</span></em><span style="font-weight: 400;"> command (without specifying a target). Moving between locations is done by reassigning </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;">; when the player uses the go to command </span><em><span style="font-weight: 400;">MainCharacter</span></em><span style="font-weight: 400;"> will call </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;">.</span><em><span style="font-weight: 400;">Move()</span></em><span style="font-weight: 400;"> which parses </span><em><span style="font-weight: 400;">CurrentLocation.AdjacentLocations </span></em><span style="font-weight: 400;">for the name of the location matching the direction specified by the player, this name is then used to find the corresponding </span><strong>Location</strong><span style="font-weight: 400;"> instance in </span><em><span style="font-weight: 400;">GameState.Locations,</span></em><span style="font-weight: 400;"> which is then assigned to </span><em><span style="font-weight: 400;">CurrentLocation.</span></em><span style="font-weight: 400;">&nbsp; When the direction specified by the player does not match an adjacent location, a response will be printed along the lines &ldquo;You cannot go that way&rdquo;, and the function will return without changing </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;">. Implementing movement between locations this way allows for a way of navigating between locations without having to construct some kind of map in the program, something which would be extra complicated because vertical movement between locations is allowed. This also makes it easier to write a story for the game, as the developer can simply assign which locations are connected to each other in specific directions (further explanations follows). Additionally, storing a copy of the </span><strong>Location</strong><span style="font-weight: 400;"> instance in which the main character is present in </span><em><span style="font-weight: 400;">CurrentLocation</span></em><span style="font-weight: 400;"> allows for access to this instance from anywhere in the code, giving more flexibility during the programming process.&nbsp;</span></p>
<p><span style="font-weight: 400;">Most instances of classes used in the program are initiated during startup, this includes all locations, the NPCs and items contained by these locations, and the </span><strong>Character </strong><span style="font-weight: 400;">instance</span> <span style="font-weight: 400;">for the main character. The initiation of instances of the </span><strong>Location</strong><span style="font-weight: 400;">, </span><strong>NPC</strong><span style="font-weight: 400;"> and </span><strong>Item</strong><span style="font-weight: 400;"> classes is done using JSON files. A JSON file is read into a special config class, </span><strong>LocationConfig</strong><span style="font-weight: 400;">, </span><strong>NpcConfig</strong><span style="font-weight: 400;"> and </span><strong>ItemConfig</strong><span style="font-weight: 400;">, </span><strong>&nbsp;</strong><span style="font-weight: 400;">which is then used to create the instance of the corresponding class. On startup, a special </span><strong>InitiationService</strong><span style="font-weight: 400;"> class will iterate over all JSON files containing configurations for the </span><strong>Location</strong><span style="font-weight: 400;"> instances and use them to create the locations. The names of the items and NPCs given in a location JSON file correspond to the JSON files belonging to those items and NPCs, allowing </span><strong>InitiationService</strong><span style="font-weight: 400;"> to create instances of these items and NPCs and place them in the </span><em><span style="font-weight: 400;">Items </span></em><span style="font-weight: 400;">and </span><em><span style="font-weight: 400;">NPCs</span></em><span style="font-weight: 400;"> hashmaps of the </span><strong>Location</strong><span style="font-weight: 400;"> instance.&nbsp;</span></p>
<p><span style="font-weight: 400;">The use of JSON files in this way makes it very easy to create a custom story for the game. Like this, writing a story mostly boils down to creating JSON files with configurations which reflects the story. For example, if a story contains a location called mountains, which has a rock and a guide in it, the JSON file titled mountain.json simply needs to look something like the following:&nbsp;</span></p>
<p>&nbsp;</p>
<p><em><span style="font-weight: 300;">{</span></em></p>
<p><em><span style="font-weight: 300;">&ldquo;Name&rdquo; : &ldquo;Mountain&rdquo;,</span></em></p>
<p><em><span style="font-weight: 300;">&ldquo;Description&rdquo; : &ldquo;A mountain with a rock and a guide&rdquo;,</span></em></p>
<p><em><span style="font-weight: 300;">&ldquo;Items&rdquo; : [ &ldquo;rock&rdquo; ],</span></em></p>
<p><em><span style="font-weight: 300;">&ldquo;Characters&rdquo; : [&ldquo;guide&rdquo;]</span></em></p>
<p><em><span style="font-weight: 300;">}</span></em></p>
<p><br /><br /></p>
<p><span style="font-weight: 400;">We followed a dynamic process to move from our UML diagram to our implementation code, in other words we created part of the diagram, than translated that to code, observed if there were things in the implementation which we were not satisfied with, if so we adjusted our diagram accordingly, and then move on to creating the next part of our diagram. Especially when selecting the types of the attributes of the classes used in our diagram we went back and forth between our code and our diagram, in order to find what would work best. Creating the associations was mostly done in the diagram only, and later copied to the code. Designing the initiation process was mainly done while writing the implementation code, as we did not create a diagram taking it was quite the straightforward tasks which depended mostly on the classes which were already created in our class diagram and the general idea of how we wanted our initiation process to work.</span></p>



<p>Location of the main class :&nbsp;src/main/java/Main.java<br />Location of Jar file :&nbsp;gradle/wrapper/gradle-wrapper.jar</p>

Maximum number of words for this section: 1000

## References

References, if needed.
