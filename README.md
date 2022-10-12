# Avaj_-_aircraft_simulation

is an aircraft simulation program based on the UML-diagram (see /docs/UML_avaj_project_structure.png).

Avaj-launcher is the simulation of aircraft flights at different weather conditions.

By the way, the code ought to include 3 software design patterns: Singleton, Factory and Observer pattern.

For more details (plus bonus features: custom exceptions, use of MD5) see /docs/subject.pdf and /docs/checklist.pdf

**This program works with:**
* OS: Linux (Ubuntu 21.04) 64-bits
* Language: Java
* Compiler: javac

~~ ~~ ~~

- There are 4 types of weather (fog, rain, snow, sun).

- There are 3 types of aircrafts (Baloon, Helicopter, JetPlane).

When a weather change occurs, each aircraft type needs to log a message. The message format is:

	TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE.

An aircraft registers (when starts the game) or unregisters (when landed) to/from the weather tower and logges a message. For example:

	Tower says: Baloon#B1(1) registered to weather tower.

The game stops when the number of weather changes is done.

By the way, the weather affects the abilitity of each aircraft where/how to fly. (See the final paragraph on the page)

___

No build tools are allowed, so:

~~ ~~ ~~

**Compiling the program** && **Launching the program**

- Get into the root directory of the program using a terminal.
- Launch ***./bash1.txt*** file (the rights of this file should be -rwxrwxr-x).

- For deleting .class files and some others launch ***./bash2.txt*** (peep inside it, see what is deleted).

'scenario.txt' (is the only argument for launching (see ***./bash1.txt***)) can be changed to another appropriate file. Use 'scenario_1.txt' / scenario_2.txt / scenario_o.txt if you wish. Change the line in bash1.txt for launching the file in this case.

**Scenario file**
- 1-st line is the number of weather launches.
- Each next line describes an aircraft that will be part of the simulation. In this format: TYPE NAME LONGITUDE LATITUDE HEIGHT.

**Result of program work**

Executing the program will generate the file ***'simulation.txt'*** that describes the outcome of the simulation. For example:

	Tower says: Baloon#B2(1) registered to weather tower.
	Tower says: JetPlane#J1(2) registered to weather tower.
	Tower says: Helicopter#H1(3) registered to weather tower.

	::: #1
	Helicopter#H1(3): It's raining.
	JetPlane#J1(2): It's raining.
	Baloon#B2(1): Winter is coming!

	::: #2
	Helicopter#H1(3): It's raining.
	JetPlane#J1(2): It's raining.
	Baloon#B2(1): It's raining.

	::: #3
	Helicopter#H1(3): It's so foggy.
	JetPlane#J1(2): It's raining.
	Baloon#B2(1): It's sunny.

***Aircraft's ability to fly**

JetPlane:

	◦ SUN - Latitude increases with 10, Height increases with 2
	◦ RAIN - Latitude increases with 5
	◦ FOG - Latitude increases with 1
	◦ SNOW - Height decreases with 7
• Helicopter:

	◦ SUN - Longitude increases with 10, Height increases with 2
	◦ RAIN - Longitude increases with 5
	◦ FOG - Longitude increases with 1
	◦ SNOW - Height decreases with 12
• Baloon:

	◦ SUN - Longitude increases with 2, Height increases with 4
	◦ RAIN - Height decreases with 5
	◦ FOG - Height decreases with 3
	◦ SNOW - Height decreases with 15
