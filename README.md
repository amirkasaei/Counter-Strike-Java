# Counter-Strike-Java
**This peoject was done or the Advanced Programming course at University of Guilan in May 2020**

## Description:
- In this game, the objective for each player is to eliminate other players in a rectangular playground of adjustable size (n*m). The playground contains randomly placed obstacles that change with each round of the game. In the offline mode, three computer-controlled players act as enemies, spawning in random locations on the ground. </br>
<div align="center"><img src="https://github.com/amirkasaei/Counter-Strike-Java/blob/main/img/game.png?raw=true"width="50%"/></div> </br >

### Players and Weapons:
- Each player (including enemy players) starts the game or respawns with three lives. They have a gun that requires a one-second cooldown after each shot before firing again. The gun has infinite arrows with a range that can hit a player, causing 1 life to be deducted. Arrows move instantaneously and shoot in a straight path in the direction the player is facing. Arrows stop when they hit the first obstacle or player in their path.

### Game Rules:
- Players can fire arrows by pressing the fire button in their current facing direction. There is a one-second delay between shots to prevent rapid firing. Players cannot enter houses occupied by obstacles, and arrows do not pass through obstacles.

### Enemy Players:
- In the single-player mode, enemy players take an action every second. They shoot if another player is in their line of fire. Otherwise, they move one house in a random direction.

### Extra Life:
- Every 20 seconds, an extra life randomly appears on the playing field where no obstacles are present. If a player or robot moves to the square with an extra life, they gain a life. A player can have a maximum of 4 lives. If the player already has 4 lives, the extra life is still taken but doesn't increase their total lives.

### Death and Respawn:
- If a player or robot runs out of lives, they die. Dead players cannot move, shoot, or perform any actions until the start of a new game round. After a 5-second delay, the dead player respawns in a random location on the screen with three lives, and their previous corpse disappears.

### End of the Game:
- In the offline mode, if the player fails to eliminate all the monsters, the game ends, displaying the game duration to the player. After a 5-second delay, the game returns to the main menu. The score of each game round doesn't need to be recorded.

### Online Gameplay:
- not implemented

### GUI:
- the Graphic UI is implemented using ** Java Swing**
