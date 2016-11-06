cs56-games-client-server-v2
===========================

A new version of cs56-games-client-server that is identical on the outside, but substantially refactored inside to have better MVC separation.

project history
===============
CS56, F16: Madhu Kannan, Colin Garrett

Two player game where to win you need to place five of your own markers in a row. Players take turns placing markers down one at a time.

Code currently contains two versions, an older one that has all classes and methods in a single file, as well as a second version that splits the game into three classes: Gomoku, Controller, and Viewer. Gomoku contains the bulk of the code that sets up the gameplay including tokens and checks for winners. Controller deals with mouse events. Viewer establishes the user interface by creating a JFrame and other graphics components.

In its current state, the execution of game principles works perfectly, but there are a number of other issues. For example, when somebody wins, although this is detected, a winning message is printed infinitely to the screen. There are no graphical components for a homescreen, endscreen, or instructions. Also, there are no tests at all. We plan to address these issues and add JUnit tests.
