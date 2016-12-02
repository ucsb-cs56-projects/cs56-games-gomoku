cs56-games-client-server-v2
===========================

A new version of cs56-games-client-server that is identical on the outside, but substantially refactored inside to have better MVC separation.

project history
===============
CS56, F16: Madhu Kannan, Colin Garrett

Two player game where to win you need to place five of your own markers in a row. Players take turns placing markers down one at a time.

Code currently contains two versions, an older one that has all classes and methods in a single file, as well as a second version that splits the game into three classes: Gomoku, Controller, and Viewer. Gomoku contains the bulk of the code that sets up the gameplay including tokens and checks for winners. Controller deals with mouse events. Viewer establishes the user interface by creating a JFrame and other graphics components.

In its current state, the execution of game principles works perfectly, but there are a number of other issues. For example, when somebody wins, although this is detected, a winning message is printed infinitely to the screen. There are no graphical components for a homescreen, endscreen, or instructions. Also, there are no tests at all. We plan to address these issues and add JUnit tests.

F16 final remarks:

Currently, a vast majority of the code is in Gomoku.java. However, the program starts in Viewer.java, where the homescreen is launched. From the home screen, you can check a box that alters the game mode, read instructions, and launch the game. The game is a 19x19 grid of tiles. Clicks alternate between placing green tiles and placing blue tiles. In standard gomoku, there must be exactly 5 tiles (i.e. ==5) of one color in a row for there to be a win. In freestyle gomoku, there must be at least 5 tiles (i.e. >=5) of one color in a row for there to be a win. When a player/color wins, a window with the option to either play again or return to the homescreen appears. This window also announces the winner of the game. Clicking either will close this window and the game window, and additionally open a new game board window if the user decided to play again.

When we received this project, running the program opened the game board directly and there was no way to play again after winning. We added a home screen, a play again screen, instructions, and a way to see whose turn is next. In addition, we added JUnit tests. Because we added all of these from scratch they are all very basic. There is a lot of potential for the screens we added to look nicer and for the JUnit tests to be more rigorous. An example of one possible improvement would be for the graphics elements on the board to be part of some sort of border layout. Currently, the game tiles and the text that tells the user whose turn it is are simply painted onto the panel, which makes modifications more difficult.