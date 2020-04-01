# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): `name of the team member(s) responsible for this section`

Provide a bullet list summarizing all the changes you performed in Assignment 2 for addressing our feedback.

Maximum number of words for this section: 1000

### Application of design patterns
Author(s): Richard Eric van Leeuwen, Peter Wassenaar

`Figure representing the UML class diagram in which all the applied design patterns are highlighted graphically (for example with a red rectangle/circle with a reference to the ID of the applied design pattern`

For each application of any design pattern you have to provide a table conforming to the template below.

| ID  | DP1  |
|---|---|
| **Design pattern**  | Factory method |
| **Problem**  | In our code we use multiple subclasses of the abstract class **item**. Depending on the situation we need different new *objects* of these subclasses, but using the *new* keyword would make our code hard to manage, extend and read. Also, the use of the *new* keyword is troublesome seen the type of *object* is decided during runtime using the provided jsons and thus cannot be hardcoded in. |
| **Solution**  | The application of the factory method allows us to separate the creation of new objects from the place where the objects are needed. The **ItemFactory class** this design pattern provides also makes it easier for use to add in any potential extra **item subclasses** and change how they are made. Most importantly, this method allows us to easily instantiate d*ifferent new objects* during runtime, which was an important issue to solve to make our system change depending on the jsons provided. |
| **Intended use**  | At the start of runtime, the program needs to use the provided json files to build the text adventure game. Most of this initialization happens in the **InitiationService** class. It will ask for new item objects by using the I**temFactory** class *GetItem()* method. The *getItem*() method accepts as parameter the String of the name of the item wanted. This name gets looked for in the json directories to connect it with the associated json file. Once the right json file is found using the item name, the json file gets used to create a *new item object* of the required **item subclass**. This **item subclass** gets returned to the caller from where it can use the item object in however way it deems fit. |
| **Constraints**  | The factory method requires that all **subclasses** have a common type to which it can be referred to. This common type is the **abstract class item**. This means that no matter the **subclass**, the object is treated as an *Item object*. This prevents the **subclasses** to have any additional functionality compared to the **item class**, they can only overwrite existing functionality. |
| **Additional remarks**  | Currently in our code there are two places from where the **itemfactory** gets called, the **InitiationService** and the **location** **class** *constructor*.|

| ID  | DP2  |
|---|---|
| **Design pattern**  | Singleton |
| **Problem**  | For a class in our project to use the IO library Asciitable it needs to have access to an instance of **TextTerminal** to print and **StringInputReader** to read. |
| **Solution**  | Create a globally accessible class named **Terminal** using the singleton design pattern, containing the required instances for reading and printing named before, which are initiated in the class’ constructor, and a set of functions to perform the required operations on these instances. |
| **Intended use**  | The **Terminal** class should be used whenever another class wishes to make use of the Asciitable library. When calling the **Terminal** class it can be regarded as an abstract class. The instance of **Terminal** should only be disposed of once the program exits. |
| **Constraints**  | If a class wants to make use of a specific functionality of the Asciitable library, there has to be a corresponding function in the **Terminal** class for this functionality. |

| ID  | DP3  |
|---|---|
| **Design pattern**  | Overloading |
| **Problem**  | A number of classes in the project contain functionalities which have (slightly) different implementation depending on the arguments being passed. Most notably is the *Use* functionality of the **Item** class, which has a different effect depending on whether an item is used on its own, on another item or on a NPC. |
| **Solution**  | Using overloading it is possible to  implement a single functionality in different ways, while still expressing the logical connection between these implementations. |
| **Intended use**  | Functionalities with multiple implementations should be implemented using overloading, if possible. |
| **Constraints**  | When an implementation of a functionality takes in a set of arguments with the same types as a set of arguments of a different implementation of the same functionality, overloading cannot be used. |

Maximum number of words for this section: 2000

## Class diagram									
Author(s): `name of the team member(s) responsible for this section`

![Class Diagram](https://github.com/Areenor/SD/blob/Assignment-3/docs/Class_Diagram.png)

<p><span style="font-weight: 400;">To improve the readability of the class diagram, all getter and setter methods required and the associations connected to them are not included in the class diagram.</span></p>
<p>&nbsp;</p>
<h3><strong>Location</strong></h3>
<p><span style="font-weight: 400;">The Location class represents a room or area which contains objects and characters. </span><em><span style="font-weight: 400;">Locations</span></em><span style="font-weight: 400;"> are linked together and can be accessed through different directions using the </span><em><span style="font-weight: 400;">AdjacentLocations</span></em><span style="font-weight: 400;"> hashmap.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_name: The location&rsquo;s name.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_baseDescription: Location&rsquo;s description, excluding the descriptions of items and NPCs in the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_items: Items present in the location</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_NPCs: NPCs present in the location</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_adjacentLocations: The locations which are adjacent to the location.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">AddAdjacentLocation : adds a location reference to the adjacent location map.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ContainsNpc : checks if the location contains an NPC.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">AddNpc : adds an NPC to the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">RemoveNPC : removes an NPC from the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ContainsItem : checks if the location contains an item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">AddItem : adds an item to the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">RemoveItem : removes an item from the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">GetNpcDescriptions : gets the descriptions of all NPCs in the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">GetItemDescriptions : gets the descriptions of all items in the location.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Character</strong></h3>
<p><span style="font-weight: 400;">The Character class represents a person or other being which acts as a living entity in the game. Such living entities or beings would be, for example, a merchant, a bandit or a wolf. Note, the role a being plays in a story determines if it should be an instance of the Character or Item class; in one story a salamander may be regarded as a character which can be battled with or spoken to, while another story sees it as (functionally speaking) just an item that. Characters have a certain amount of hit points, representing their health, if these drop to zero the character will die. The amount of actions a character can perform in one turn during combat depends on their stamina, as long as this does not hit zero they can perform another action.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">BASE_HEALTH : the base health of every character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">BASE_ATTACK : the base attack of every character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">BASE_STAMINA : the base stamina of every character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_name : name of the character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_strength : statistic influencing the character&rsquo;s </span><em><span style="font-weight: 400;">_attack</span></em><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_dexterity : statistic influencing the character&rsquo;s </span><em><span style="font-weight: 400;">_maxStamina</span></em><span style="font-weight: 400;"> and chance to dodge chance during combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_constitution : statistic influencing the character&rsquo;s </span><em><span style="font-weight: 400;">_maxHitPoints, _block</span></em><span style="font-weight: 400;"> and standart damage mitigation during combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_maxHitPoints : character&rsquo;s total health.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_currentHitPoints : character&rsquo;s current health.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_attack : damage a character can deal when attacking in combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_block : amount of mitigated damage upon when blocking an attack in combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_maxStamina : character&rsquo;s total stamina.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_currentStamina : character&rsquo;s stamina.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_inventory : the items in possession of the character.</span><span style="font-weight: 400;">&nbsp;</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">ResetHealth : Restores </span><em><span style="font-weight: 400;">_currentHitPoints</span></em><span style="font-weight: 400;"> to the value of </span><em><span style="font-weight: 400;">_maxHitPoints</span></em><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ResetStamina : Restores </span><em><span style="font-weight: 400;">_currentStamina</span></em><span style="font-weight: 400;"> to the value of </span><em><span style="font-weight: 400;">_maxStamina.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">AddToInventory : Adds an item to the character&rsquo;s inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">RemoveFromInventory : Removes an item from the character&rsquo;s inventory</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Attack : Initiates combat with another character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Die : Triggers an event when the character is defeated in combat, this event is different for </span><strong>Player</strong><span style="font-weight: 400;"> and </span><strong>NPC</strong><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">DealDamage : character inflicts damage on another character.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Player</strong></h3>
<p><strong>Player </strong><span style="font-weight: 400;">inherits </span><strong>Character</strong><span style="font-weight: 400;">, and</span> <span style="font-weight: 400;">is controlled by the user. A player can equip an armor piece or weapon, boosting their stats (</span><em><span style="font-weight: 400;">_strength, _dexterity, _constitution</span></em><span style="font-weight: 400;">), however only one piece of equipment of each type may be equipped at once.&nbsp;</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_currentLocation: the location in which the player resides.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_weapon : the equipment piece of type </span><em><span style="font-weight: 400;">Weapon </span></em><span style="font-weight: 400;">currently equipped by the player.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_armor : the equipment piece of type </span><em><span style="font-weight: 400;">Armor </span></em><span style="font-weight: 400;">currently equipped by the player.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isDodge : boolean stating if the player is currently dodging, used during combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isBlock : boolean stating if the player is currently blocking, used during combat.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">EquipWeapon : equips a piece of equipment of type </span><em><span style="font-weight: 400;">Weapon.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">EquipArmor : equips a piece of equipment of type </span><em><span style="font-weight: 400;">Armor.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : use an item in the player&rsquo;s inventory by itself.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">UseOnItem : use an item in the player&rsquo;s inventory on another item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">UseOnNpc : use an item in the player&rsquo;s inventory on an NPC.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">TalkTo : initiate a conversation with an NPC.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Take : remove an item from the current location and put it in the player&rsquo;s inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Move : move to a location adjacent to the current location, requires a direction to move in.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Attak : triggers combat against an NPC and inflicts damage to it.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Die : causes the player to respawn in the designated spawn room with full health and stamina.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ResponseAction : executes a response action during combat if </span><em><span style="font-weight: 400;">_currentStamina</span></em><span style="font-weight: 400;"> is greater than zero.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PrintInventory : prints the names of all the items in the player&rsquo;s inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Respawn : sets the player&rsquo;s current location to the spawn location.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Talk to : is triggered by the </span><em><span style="font-weight: 400;">TalkTo</span></em><span style="font-weight: 400;"> function of </span><strong>Player</strong><span style="font-weight: 400;"> and calls the </span><em><span style="font-weight: 400;">Talk</span></em><span style="font-weight: 400;"> function of </span><strong>NPC</strong><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Move : is triggered by the </span><em><span style="font-weight: 400;">Move</span></em><span style="font-weight: 400;"> function of </span><strong>Player</strong><span style="font-weight: 400;">, gets the location adjacent to the current location in the direction specified, this adjacent location then used to set the </span><em><span style="font-weight: 400;">_currentLocation</span></em><span style="font-weight: 400;"> attribute of the player.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Take : is triggered by the </span><em><span style="font-weight: 400;">Take</span></em><span style="font-weight: 400;"> function of </span><strong>Player</strong><span style="font-weight: 400;">, removes an item from the player&rsquo;s current location and adds it to their inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : is triggered by the </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;">, </span><em><span style="font-weight: 400;">UseOnItem</span></em><span style="font-weight: 400;">, or </span><em><span style="font-weight: 400;">UseOnNpc</span></em><span style="font-weight: 400;"> functions of </span><strong>Player</strong><span style="font-weight: 400;"> and calls the </span><em><span style="font-weight: 400;">Use </span></em><span style="font-weight: 400;">function of </span><strong>NPC</strong><span style="font-weight: 400;">, the effect of </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;"> in </span><strong>NPC</strong><span style="font-weight: 400;"> will vary based on which </span><strong>Player</strong><span style="font-weight: 400;"> function calls it and of which subclass the </span><strong>NPC</strong><span style="font-weight: 400;"> instance is. An instance of </span><strong>Item</strong><span style="font-weight: 400;"> or </span><strong>NPC</strong><span style="font-weight: 400;"> can be passed when the player calls the </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;"> operation of the target item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Equipped : an instance of </span><strong>Equipment</strong><span style="font-weight: 400;"> may be equipped by a player. A player may have at most two pieces of equipment equipped at once, and a piece of equipment may be equipped to at most one player.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Attack : may attack or be attacked by an NPC.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">A player is present in one location at a time, and can move between them.</span></li>
</ul>
<p><br /><br /></p>
<h3><strong>NPC</strong></h3>
<p><span style="font-weight: 400;">A subclass which inherits from the </span><strong>Character </strong><span style="font-weight: 400;">class. A character which is not controlled by the player.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_description : a description of the NPC.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_type : the type of the NPC, such as wolf or vampire.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_dialogue : the text spoken by the NPC when talking to them.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_combatDialogue : the text spoken by the NPC when initiating combat with them.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isHostile : boolean stating if the NPC would attack a player on sight.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isFightable : boolean stating if the NPC can be attacked by the player.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Talk : prints the content of the NPC&rsquo;s </span><em><span style="font-weight: 400;">_dialogue </span></em><span style="font-weight: 400;">attribute.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Attack :&nbsp; triggers combat against a player and inflicts damage to them.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PrintCondition : prints the condition the NPC is in based on </span><em><span style="font-weight: 400;">_currentHitPoints.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Die : removes the NPC from the location it&rsquo;s in and drops its inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">DropInventroy : adds all the NPC&rsquo;s inventory items to the location it is present in.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">NPCs are present in one location at a time, and can move between them.</span></li>
</ul>
<h3><strong>Item</strong></h3>
<p><span style="font-weight: 400;">An abstract class with multiple subclasses. The functionality of an item is mainly determined by the subclass type it&rsquo;s an instance of. The item subclasses are: </span><strong>Junk, KeyItem, Consumable,</strong><span style="font-weight: 400;"> and </span><strong>Equipment</strong><span style="font-weight: 400;">. An Item is present in a location or a character&rsquo;s inventory, and can move between these.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_name : name of the item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_description : description of the item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isRetrievable : boolean stating if an item can be picked up by a character (added to the character&rsquo;s inventory).</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_isWinningItem : boolean stating if picking up the item leads to victory / completing the game.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_endText : text to be printed upon finishing the game; is null unless </span><em><span style="font-weight: 400;">_isWinningItem</span></em><span style="font-weight: 400;"> is set to true.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : triggers an event or action. An item may be used on its own, on an NPc or another item. The effect of </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;"> depends on the </span><strong>Item</strong><span style="font-weight: 400;"> instance&rsquo;s type.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">FinishGame : is called upon picking up an item which has </span><em><span style="font-weight: 400;">_isWinningItem</span></em><span style="font-weight: 400;"> set to true; prints </span><em><span style="font-weight: 400;">_endText</span></em><span style="font-weight: 400;"> , closes </span><strong>Terminal</strong><span style="font-weight: 400;"> and ends the game.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">An item is either present in the inventory of a player or (exclusive) a location, and may be moved between these.</span></li>
</ul>
<h3><strong>Junk</strong></h3>
<p><span style="font-weight: 400;">A subclass of </span><strong>Item</strong><span style="font-weight: 400;"> with no additional effects. Inherits </span><strong>Item.</strong></p>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : has no effect outside of printing some feedback for the player.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>KeyItem</strong></h3>
<p><span style="font-weight: 400;">A subclass of </span><strong>Item</strong><span style="font-weight: 400;"> used to unlock a direction in a location, or to be traded with an NPC for another item. A key item can never be used on its own, but must be used on a specific item or NPC. Inherits </span><strong>Item</strong><span style="font-weight: 400;">.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_newTargetDescriptions : the new descriptions of the specific items the key item unlocks. A new description is set upon using the key item upon the corresponding item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_unlockDirections : directions which are unlocked when using the key item on specific target items.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_unlockLocationNames : the names of the locations in which the key item can unlock a direction.&nbsp;</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_npcItemExchange : contains the NPC which can trade the key item for another item and which items they trade it for.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : if used on a correct target item a direction in a location will be unlocked, allowing a player to move in that direction. If used on&nbsp; a correct target NPC the key item is traded for another item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">IsCorrectTargetItem : checks if the target item passed in </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;"> is a correct target for the key item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">IsCorrectTargetNpc : checks if the target NPC passed in </span><em><span style="font-weight: 400;">Use</span></em><span style="font-weight: 400;"> is a correct target for the key item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">SetTargetItemDescription : replaces the description of a target item with the corresponding description in </span><em><span style="font-weight: 400;">_newTargetDescription</span></em><span style="font-weight: 400;"> if a key item is used on that item.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">GiveAccessNewLocation :&nbsp; adds the location name corresponding to the unlocked direction, found in _</span><em><span style="font-weight: 400;">unlockedLocationNames,</span></em><span style="font-weight: 400;"> to the </span><em><span style="font-weight: 400;">_adjacentLocation</span></em><span style="font-weight: 400;"> attribute of the player&rsquo;s current location.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Consumable</strong></h3>
<p><span style="font-weight: 400;">A subclass of </span><strong>Item</strong><span style="font-weight: 400;"> which can be used once to aid a player or harm an NPC. If the consumable is harmful it may only be used on an NPC and will lower one of their statistics, alternatively if a consumable is not harmful it may only be used on the player and will increase one of their statistics. Consumables can only be used once. Inherits </span><strong>Item.</strong></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_statChange : the amount by which the target statistic is changed upon use.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_dangerous : boolean expressing if the consumable is harmful or not.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_affectedStat : statistic to be effected upon use.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : When used on its own and not harmful, a specified statistic of the player using it will be increased. When used on an NPC and harmful, a specified statistic of the NPC will be reduced. Both actions consume the consumable, removing it from the player&rsquo;s inventory.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Equipment</strong></h3>
<p><span style="font-weight: 400;">A subclass of Item which can be equipped by a character, raising their statistics. Equipment has two distinct types, </span><em><span style="font-weight: 400;">Weapon </span></em><span style="font-weight: 400;">and </span><em><span style="font-weight: 400;">Armor</span></em><span style="font-weight: 400;">. Weapons tend to increase a character&rsquo;s </span><em><span style="font-weight: 400;">_Stength </span></em><span style="font-weight: 400;">and armor tends to increase </span><em><span style="font-weight: 400;">_Constitution</span></em><span style="font-weight: 400;">.&nbsp;&nbsp;</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">_blockBonus : bonus to </span><em><span style="font-weight: 400;">_constitution</span></em><span style="font-weight: 400;"> when equipped.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_attackBonus : bonus to </span><em><span style="font-weight: 400;">_strength</span></em><span style="font-weight: 400;"> when equipped.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_type : type of the equipment, either </span><em><span style="font-weight: 400;">Weapon</span></em><span style="font-weight: 400;"> or </span><em><span style="font-weight: 400;">Armor</span></em><span style="font-weight: 400;">.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : when equipment is used on its own, it will be equipped by the player, if the player already wears a piece of equipment of the same type that piece of equipment will be unequipped in favor of the piece of equipment being used. Use will have no effect when a target is specified.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>GameState</strong></h3>
<p><span style="font-weight: 400;">Contains information about the state of the game, such as a reference to the user&rsquo;s character, whether the game is finished.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Combat : boolean stating if the game is in combat mode.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">IsFinished : boolean stating if the game is finished, becomes true once the player meets the victory condition.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">SpawnRoom&nbsp; : name of the location in which a new instance of </span><strong>Player</strong><span style="font-weight: 400;"> is placed (the player&rsquo;s initial current location).</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">MainCharacter : the user&rsquo;s character, an instance of </span><strong>Player</strong><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Locations : all locations used in the game.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">GetLocation : returns a specific location.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">GameState is a globally accessible class, and is referred to numerous times by different classes in the program, because of this most associations of </span><strong>GameState</strong><span style="font-weight: 400;"> have been omitted from the class diagram, to improve clearness.&nbsp;</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>InitiationService</strong></h3>
<p><span style="font-weight: 400;">A service used to initiate the instances of all non-abstract classes, except for the item subclasses, and reading the story specific json files.</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">storyDirPath : path to the directory of the story files.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">locationJsonDirPath : path to the directory containing the json files of the story&rsquo;s locations.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">npcJsonDirPath : path to the directory containing the json files of the story&rsquo;s NPCs.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateMainCharacter : creates an instance of </span><strong>Player</strong><span style="font-weight: 400;"> which will be the user&rsquo;s character, and&nbsp; sets </span><em><span style="font-weight: 400;">MainCharacter</span></em><span style="font-weight: 400;"> in </span><strong>GameState</strong><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateCharacterInventory : creates an instance of an empty character inventory.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateLocations : creates a map of </span><strong>Location </strong><span style="font-weight: 400;">instances for every json file in the story&rsquo;s locations directory, and sets </span><em><span style="font-weight: 400;">Locations</span></em><span style="font-weight: 400;"> in </span><strong>GameState</strong><span style="font-weight: 400;">.&nbsp;</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateLocation : creates an instance of </span><strong>Location</strong><span style="font-weight: 400;">. An instance of </span><strong>LocationConfig</strong><span style="font-weight: 400;"> is used as an argument when initiating the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateNpc : creates an instance of </span><strong>NPC</strong><span style="font-weight: 400;">. An instance of </span><strong>NpcConfig</strong><span style="font-weight: 400;"> is used as an argument when initiating the location.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ReadLineByLine : reads the content of a json file into a string line by line.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Initiates instances of </span><strong>Location</strong><span style="font-weight: 400;">, </span><strong>Player</strong><span style="font-weight: 400;">, and </span><strong>NPC</strong><span style="font-weight: 400;">. Also initiates the </span><em><span style="font-weight: 400;">MainCharacter</span></em><span style="font-weight: 400;"> and </span><em><span style="font-weight: 400;">Locations</span></em><span style="font-weight: 400;"> attributes of </span><strong>GameState</strong><span style="font-weight: 400;">.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Passes new instances of </span><strong>NPC</strong><span style="font-weight: 400;"> to an instance of </span><strong>Location</strong><span style="font-weight: 400;">.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>ItemFactory</strong></h3>
<p><span style="font-weight: 400;">A service which creates instances of the </span><strong>Item</strong><span style="font-weight: 400;"> subclasses.&nbsp;</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">storyDirPath : path to the directory of the story files.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">junkJsonDirPath : path to the directory containing the json files of the story&rsquo;s junk items.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">consumableJsonDirPath : path to the directory containing the json files of the story&rsquo;s consumable items.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">keyItemJsonDirPath : path to the directory containing the json files of the story&rsquo;s key items.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">equipmentJsonDirPath : path to the directory containing the json files of the story&rsquo;s equipment items.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateItem : creates an instance of an </span><strong>Item</strong><span style="font-weight: 400;"> subclass. The instance of the subclass is created if a JSON file can be found matching the name passed when the function is called. An instance of </span><strong>ItemConfig</strong><span style="font-weight: 400;">, </span><strong>ConsumableConfig</strong><span style="font-weight: 400;">, </span><strong>KeyItemConfig</strong><span style="font-weight: 400;">, or </span><strong>EquipmentConfig </strong><span style="font-weight: 400;">&nbsp;is used for initiation depending on the item&rsquo;s type.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">IsExsitingFile : Checks if the passed path matches an existing JSON file.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Initiates instances of </span><strong>Item</strong><span style="font-weight: 400;"> subclasses.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Passes new instances of </span><strong>Item </strong><span style="font-weight: 400;">to an instance of </span><strong>Location</strong><span style="font-weight: 400;">.</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Controller</strong></h3>
<p><span style="font-weight: 400;">A service which reads user input and executes user commands.&nbsp;</span></p>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">ExecuteCommand : retrieves user input, computes which command the user wishes to execute and executes it.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ExecuteCombatCommand : retrieves user input while their character is&nbsp; in combat, computes which command the user wishes to execute and executes it.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">ExecuteResponseCommand : retrieves user input while they respond to a combat action of an enemy, computes which command the user wishes to execute and executes it.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PrintCommands : prints the usable commands at the current moment.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Examine : prints the description of the target (an </span><strong>Item</strong> <span style="font-weight: 400;">or </span><strong>NPC</strong><span style="font-weight: 400;">) or the description of the player&rsquo;s current location if no target is specified.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Talk : calls the player&rsquo;s </span><em><span style="font-weight: 400;">Talk()</span></em><span style="font-weight: 400;"> operation.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Take : calls the player&rsquo;s </span><em><span style="font-weight: 400;">Take()</span></em><span style="font-weight: 400;"> operation.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Move : calls the player&rsquo;s </span><em><span style="font-weight: 400;">Move()</span></em><span style="font-weight: 400;"> operation.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Use : calls the player&rsquo;s </span><em><span style="font-weight: 400;">Use(), UseOnItem(), </span></em><span style="font-weight: 400;">or </span><em><span style="font-weight: 400;">UseOnNpc()</span></em><span style="font-weight: 400;"> operations, depending on the target specified by the user, if any.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Attack : calls the player&rsquo;s </span><em><span style="font-weight: 400;">Attack()</span></em><span style="font-weight: 400;"> operation.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Perform action : makes an instance of </span><strong>Player</strong><span style="font-weight: 400;"> perform an action such as take, move or use.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Examine : retrieves the description of a location, item or NPC.&nbsp;</span></li>
</ul>
<p>&nbsp;</p>
<h3><strong>Combat</strong></h3>
<p><span style="font-weight: 400;">Service which manages combat. Combat is performed between a player and one or multiple NPCs. NPCs are only able to attack the player, however the player can perform a multitude of actions. A player performs multiple actions each turn, depending on their stamina, they can attack, use a consumable, or dodge or block if their opponent attacks. To be able to dodge or block the player must have at least 1 stamina remaining once the NPC attacks them, meaning the user should properly manage the stamina of their character, this can be done by skipping a part of their turn. The stamina of a character gets regenerated completely at the start of their turn. If an NPC dies in combat, they will be removed from the location they&rsquo;re present in and will drop all the items in their inventory, a player on the other hand will simply respawn at the spawn location.&nbsp;</span></p>
<h4><span style="font-weight: 400;">Attributes:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">combatOrder : keeps track of the turn order of all characters currently in combat.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">skipTurn : boolean stating if the player wants to skip their turn.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Init : initiates combat;&nbsp; switches </span><em><span style="font-weight: 400;">Combat</span></em><span style="font-weight: 400;"> in </span><strong>GameState</strong><span style="font-weight: 400;"> to true, prints the combat dialogue of all enemies, adds all enemies and the player to </span><em><span style="font-weight: 400;">_combatOrder</span></em><span style="font-weight: 400;">, and sorts </span><em><span style="font-weight: 400;">_combatOrder </span></em><span style="font-weight: 400;">based on the characters&rsquo; dexterity and if the player attacks an NPC or the other way around.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Run : runs combat and checks if all enemies are defeated.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">CombatEnd : ends combad; restores the player's health and stamina, clears </span><em><span style="font-weight: 400;">combatOrder</span></em><span style="font-weight: 400;"> . and switches </span><em><span style="font-weight: 400;">Combat</span></em><span style="font-weight: 400;"> in </span><strong>GameState</strong><span style="font-weight: 400;"> to false.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">CombatFlow : loops over </span><em><span style="font-weight: 400;">combatOrder</span></em><span style="font-weight: 400;"> and executes the combat action(s) for each character.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">RunNewOrder : called once an NPC dies, creates a new combat order without the killed NPC(s) and calls </span><em><span style="font-weight: 400;">Run.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PlayerAction : called on the player&rsquo;s turn. Executes the combat action the player makes by calling </span><em><span style="font-weight: 400;">ExecuteCombatCommand</span></em><span style="font-weight: 400;"> in </span><strong>Controller</strong><span style="font-weight: 400;"> as long as the player still has stamina.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">EnemyAction : makes an NPC attack the player.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PrintCombatDialogues : gets and prints the </span><em><span style="font-weight: 400;">_combatDialogue</span></em><span style="font-weight: 400;"> of each NPC in combat.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">InitiateCombat : when an NPC or player attacks the other they initiate combat.</span></li>
</ul>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Perform combat action : </span><strong>Combat</strong><span style="font-weight: 400;"> executes a player&rsquo;s combat action by calling </span><em><span style="font-weight: 400;">ExecuteCombatCommand </span></em><span style="font-weight: 400;">in </span><strong>Controller</strong><span style="font-weight: 400;">.</span></li>
</ul>
<ul>
<li><strong>Combat </strong><span style="font-weight: 400;">may make calls to </span>Location<span style="font-weight: 400;">, </span>Player, NPC, <span style="font-weight: 400;">or </span>Character<span style="font-weight: 400;"> in order to update them, for example deleting an NPC from a location, or resetting a player&rsquo;s health and stamina.</span></li>
</ul>
<p><br /><br /></p>
<h3><strong>Terminal</strong></h3>
<p><span style="font-weight: 400;">A wrapper class for usage of the</span><em><span style="font-weight: 400;"> org.beryx.textio </span></em><span style="font-weight: 400;">library; used for classes to print to and read from the terminal.&nbsp;</span></p>
<p>&nbsp;</p>
<h4><span style="font-weight: 400;">Attributes :</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">INSTANCE : instance of the </span><strong>Terminal</strong><span style="font-weight: 400;"> class.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_textIO : instance of </span><em><span style="font-weight: 400;">org.beryx.textio.TextIO</span></em><span style="font-weight: 400;">, used to initiate </span><em><span style="font-weight: 400;">_inputReader</span></em><span style="font-weight: 400;"> and </span><em><span style="font-weight: 400;">_terminal.</span></em></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_inputReader : instance of </span><em><span style="font-weight: 400;">org.beryx.textio.StringInputReader</span></em><span style="font-weight: 400;">, used reading user input.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">_terminal : instance of </span><em><span style="font-weight: 400;">org.beryx.textio.TextTerminal</span></em><span style="font-weight: 400;">, used for printing to the terminal.</span></li>
</ul>
<h4><span style="font-weight: 400;">Operations:</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Read : reads user input from the terminal.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Print : prints a string to the terminal.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">PrintLine : prints a line to the terminal.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">CloseTerminal : desposes the </span><em><span style="font-weight: 400;">_textIO</span></em><span style="font-weight: 400;"> instance.</span></li>
</ul>
<h4><span style="font-weight: 400;">Associations</span></h4>
<ul>
<li style="font-weight: 400;"><span style="font-weight: 400;">Get user input : </span><strong>Terminal</strong><span style="font-weight: 400;"> reads user input from the terminal and sends this in a string to the associated class.</span></li>
<li style="font-weight: 400;"><span style="font-weight: 400;">Print : </span><strong>Terminal</strong><span style="font-weight: 400;"> prints the string passed by the associated class to the terminal, either as just a string or a line.&nbsp; This association is omitted from the class diagram to improve clearness, as the majority of the classes have this association.</span></li>
</ul>
<h3><strong>Configuration classes</strong></h3>
<p><span style="font-weight: 400;">A total of six configuration classes are added to the project, and are used for the initiation of instances of </span><strong>Location</strong><span style="font-weight: 400;">, </span><strong>NPC, </strong><span style="font-weight: 400;">and all the </span><strong>Item</strong><span style="font-weight: 400;"> subclasses. Using a JSON reader and deserializer, instances of these configuration classes are created from the story JSON files, such an instance can then be passed as an argument when calling the constructor of its corresponding class. Which configuration class is just to initiate which class is quite straight forward based on the name, except for the </span><strong>ItemConfig</strong><span style="font-weight: 400;"> class which is used to initiate items of type </span><strong>Junk</strong><span style="font-weight: 400;">. Additionally the classes </span><strong>ConsumableCpnfig</strong><span style="font-weight: 400;">, </span><strong>KeyItemConfig </strong><span style="font-weight: 400;">and </span><strong>EquipmentConfig </strong><span style="font-weight: 400;">inherit the </span><strong>ItemConfig </strong><span style="font-weight: 400;">class. Taking the configuration classes contain the exact same attributes as the classes they correspond to, a detailed summary of their attributes will be left out.</span></p>

## Object diagrams								
Author(s): Leyla Celik

![Object Diagram](https://github.com/Areenor/SD/blob/Assignment-3/docs/Object_Diagram_2.0.png)

This diagram shows a snapshot of a player who had their **MainCharacter** enter a **Location** that contains **Equipment** and is adjacent to another **Location** with **Equipment** and a **Character**. The **MainCharacter** is an instance of the class Character with all inherited variables. The **MainCharacter** is named player1 and their stats are 5 *HitPoints*, 1 *Strength*, 1 *Dexterity* and 1 *Constitution* and 1 *Stamina*. The *MaxHitPoints* and *MaxStamin* are 10. The player can use items to increase *HitPoints* and *Stamina* for the **MainCharacter** with a cap of *MaxHitPoints* and *MaxStamina*. The **MainCharacter**’s inventory can consist of items such as **Equipment**, **Consumable**s, **KeyItem**s, etc. and, at this point in the snapshot, consists of a *torch*. The **MainCharacter** can use items such as **Equipment** with the command *‘Use’*. The **MainCharacter** is at a **Location** named *“a beach”*. The player gets a description of this **Location** upon entering it: “You are on a beach, there are objects and items. There is a room east.”. This **Location** contains two objects of subclass Equipment: one of type *'weapon'* that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) named ‘Sword’ that gives an attack bonus of 1 (int *AttackBonus* = 1;) and one of type *'armor'* named ‘Chestplate’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that gives a block bonus of 1 (int BlockBonus = 1;); The **Location** *‘a beach’* has an adjacent location to the east (*AdjacentLocations* = {"east":"room"}) called *‘room’*. 
The adjacent location *‘room’* contains one item of subclass **Consumable** and an **NPC** of class **NPC**. The **Consumable** is a ‘Strength potion’ that the **MainCharacter** can pick up (bool *IsRetrievable* = true;) that is not dangerous (bool *IsDangerous* = false;), which means that the **MainCharacter** can use it on themselves. The affected stat is the *Strength* stat (*AffectedStat* = *Strength*;) and the added bonus is 1 (int *StatChange* = 1;). The potion has the description: “This potion will make you stronger on consumption.”. The **NPC** is named ‘Guard’, is of type *‘guard’*, has the description "This is a Guard that attacks on sight.", is hostile (bool *IsHostile* = true;) and is fightable (bool *IsFightable* = true;). Possible types of interactions with the **NPC** are: *‘Talk’*, which gives the player dialogue between the **MainCharacter** and the **NPC** and *’DropInventory’*, which adds all of the **NPC**’s items to the **Location** it is in. An **NPC** that *IsHostile* will attack the **MainCharacter** on sight, meaning the player will not have time to interact with the **NPC**.
<p><br /><br /><br /></p>
<p><span style="font-weight: 400;">Our class diagram has been greatly improved upon compared to the one submitted in assignment 2. This is mainly due to the inclusion of functional classes, such as </span><strong>GameState</strong><span style="font-weight: 400;"> or </span><strong>InitiationService</strong><span style="font-weight: 400;">. The core of the class diagram, the </span><strong>Location</strong><span style="font-weight: 400;">, </span><strong>Character</strong><span style="font-weight: 400;">, </span><strong>Npc</strong><span style="font-weight: 400;">, </span><strong>Player</strong><span style="font-weight: 400;">, and </span><strong>Item </strong><span style="font-weight: 400;">classes, remains relatively the same as it was in assignment 2, except for the addition of the </span><strong>Player </strong><span style="font-weight: 400;">class and the </span><strong>Item </strong><span style="font-weight: 400;">subclasses. The rest of the diagram is mainly the initiation process for creating all necessary instances of classes, the combat mechanic, and handling using commands; these parts are all newly added in this version of the diagram.</span></p>

## State machine diagrams									
Author: Richard van Leeuwen

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

**The Player State Machine**

The Player State Machine is similar to our previous main character state machine seen the player class we created has as role to take over (part) of the responsibilities that were previously assigned to the main character. Into building this state machine, we took the feedback into account and made sure the arrow pointing to the final state points in the right direction and we made sure to include multiple sub states to further expand on all the states the **Player class** can possibly be in. These sub states are connected to the various items *the player object* can have or not have in its *inventory*. We considered focusing on using a general item sub state, but in the end we decided to split them into the different item subclasses we have seen certain functions and/or transitions only occur with certain specific subclasses. For example, once the *Consumable object* gets used, it gets removed from *the player's inventory* which would cause it to change state. On the other hand, if the *Junk item* object gets used, it does not get removed from *the player's inventory* and can be used again and does not change state. One final consideration we have decided to take with this state machine, but also in general with the others, is to not include the Setter/Getter methods available in the object classes. Including the Setter/Getter functions would be unnecessary as they are always available to be used, no matter what state the object is in, and they would distract from the behavior of the object with its more detailed nature. From that angle the state machine is more descriptive than prescriptive, but the reader can assume these methods are always available to be used. With these general details and considerations discussed, we will now move step by step through the Player state machine and discuss the different states and transitions.

First, after the *player object* goes from its initial state, it moves into the initiation state. In this state the *player object* gets created with the correct stats and details. We have decided to keep this state simple seen the character itself is not directly involved with it, apart from providing some getter/setter methods, but it is a state that one should be made aware of.

Second, it moves into the out of combat state. This state machine diagram is orthogonal seen it contains various sub states, of which multiple can be active such as the key item in inventory state and the equipment not in inventory state. These sub states share multiple functions, and for this reason they are included in the diagram multiple times. The events available in every state are the following:

Controller invokes inventory command/*PrintInventory()*

Controller invokes move direction command/*Move(Direction)*

Controller invokes talk to NPC command/*TalkTo("NPC")*

The main differences between the different sub  states is the Use and Take commands that get invoked by the Controller class. The take command cause different transitions for the substates, and also move the player object from the out of combat state to the final state if the Item to be taken has the *IsWinningItem* condition set to True. The Use Command may cause the player object to transition into a different sub state and it function depends on its parameters and the sub state it is being used in. The other state the out of combat state can transition to is the in combat state. It can transition to this state either by moving to a new location and the *location* contains *NPC(s*) of which the *IsHostile* attribute is set to true or by attacking a *NPC* that has the *IsFightable* attribute set to true.

Once the *player object* is in the in combat state, it shares the same sub states as the out of combat state with a couple of key differences. First, even though the transitions from an Item in Inventory  to an item not being in inventory still exists through the *Use()* function, it does not have access to the take function and cannot transition from an item not in inventory to an item in inventory. The second key difference is that the player object has lost access to functions such as M*ove()* and T*alkTo()* and has access to new functions and events that trigger them. Even though previously *the attack function* and its event caused the state to transition, that is not the case in the in combat state. Also, the *player object* needs to respond to the *NPC* attacking them with its own *ResponseAction()* function. From here the combat class can transition back to the out of combat state if the combat class signals combat is over and the *currentHitPoints of the player* is above 0, or it can transition into the Defeated state if the *currentHitPoints* reaches 0 or lower. In the defeated state, *the player object* gets its *currentHitPoints, currentStamina and currentLocation* reset so that *the player object* can safely move back into the out of combat state.

![Player State Machine Diagram](https://github.com/Areenor/SD/blob/Assignment-3/docs/Player_state_machineDiagr.png)


**The Player State Machine**

![NPC State Machine Diagram](https://github.com/Areenor/SD/blob/Assignment-3/docs/NPC_state_machine_updated.png)

## Sequence diagrams									
Author(s): Leyla Celik, Richard Eric van Leeuwen

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.


Now, we would like to discuss the movement scenario. 

![Sequence Diagram Movement](https://github.com/Areenor/SD/blob/Assignment-2/docs/Sequence_Diagram_Movement.png)

This movement sequence diagram shows the way the program handles a movement command when the direction the player wants to go to does not exist and when it does exist. The interaction partners in this diagram are the Player, **MainCharacter** and **Location**. We will discuss each of the interactions partners’ involvement and what their tasks are during this scenario. Player depicts an external influence and is the stakeholder of this diagram. The player is the person playing the game who inputs commands and controls the **MainCharacter**. The situation in this sequence diagram is as follows: the player wants to move the **MainCharacter** to a new **Location**. The player inputs a command to move the **MainCharacter** north of its current **Location**: “move north”. 
The **MainCharacter** processes the commands they receive from the player and will teleport if the direction the player wants to go in has a **Location**. The **MainCharacter** receives the “move north” command and will invoke the *move(“north”)* method to **Location**. 
**Location** is mostly a container for data, but also sends back a message depending on whether a **Location** exists in the direction chosen by the Player. If the direction chosen by the player has a **Location**, the message is a description of the **Location**. If there is no **Location** in that direction the message will say there was nothing in that direction. The player chose north as their direction to move in, but there is no *AdjacentLocation* north of the **MainCharacter**’s current **Location**, so Location sends the player the response “You found nothing traveling in this direction and returned to your original **Location**.” as output. The player tries to change locations again, this time wanting to move the **MainCharacter** east of its current **Location** with the command: “move east”. The **MainCharacter** receives the “move east” command and will invoke the *move(“east”)* method to **Location**. There is an adjacent **Location** east of the **MainCharacter**’s current **Location** and the player gets the response: "You are in a room, there are objects and items. There is a beach west." as output.

![Sequence Diagram Take](https://github.com/Areenor/SD/blob/Assignment-3/docs/Sequence_diagram_TakeCom.png)

In this sequence diagram we show the execution of the take command. The take command allows the user of the game, which we will refer to as the player actor, to remove an *item* from the *currentLocation* stored in the *player object* and put the *item* in the inventory of the *player object*. We decided to showcase this sequence seen it shows the execution of a typical command the player actor will regularly use and how the *player object* interacts with other *objects* to complete such a command.

First of all, the *Controller* requests the *Read()* function from the *Terminal*. The player actor inputs the command it wants, which in this case is "take potion", which gets forwarded as a reply back to the *Controller*. The *Controller* reads this command and calls his own *Take()* function where it checks for any command specific errors in the input it received from the *Terminal*. Upon not finding any, it calls the *Take()* function of the *player object* which has as parameter the name of the *Item* it wants taken. *The player object* receives this call and proceeds to process the execution of exchange of *Items*. First, it communicates with the *currentLocation object* it has stored inside himself and asks for the *item* wanted with as parameter the item name. In this case, the name is "potion" and it receives back the *potion Item object* which it stores in the variable targetItem. Now, it has the Item it wants stored in a variable, it proceeds to send another request to the *currentLocation object* telling it to remove the *potion item object* from the *currentLocation* with the function *RemoveItem()* which takes as parameter the name of the *item*. With the *item* removed from the *currentLocation*, the *player object* only needs to add it to his own *inventory* which it does with a call to himself called *AddToInventory()* with as parameter targetItem. The command is now completed, all that is left to do is tell the player actor it was successful. The *player object* sends to the *Terminal* using the *Terminal's PrintLine()* function the message containing the success of the action, in this case "You took the potion", which the *Terminal* then forwards to the player actor as output. The player actor now knows the command went successful and can now continue inputting a new command of his choosing.

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
