Yiyang Xu
Nikki Tyagi

(a) This game is a game involving two players who take turns placing their markers on the game board in order to achieve 5 in a row first.

(b)
* As a user, I can place markers when it is my turn so that I can get 5 in a row.
* As a user, I can play against other people so that I can strategize to win.

(c) The software runs. It displays a menu with instructions and a play option and starts and completes a game with two users.

(d)
* As a user, I would want to play against the computer so I could play alone.
* As a user, I would want the display with the game board to be more organized and cleaned so that I would not be distracted.
* As a user, I would want to see the number of games I have won so that I could keep track.
* As a user, I would want an undo button so that I could change my mind.

(e) Currently, the README.md contains a brief introduction to the game and development history of the project. It would be better if it could contain a more detailed description of the game and instructions of how to play the game. Also, it could contain information regarding whether the project is based on Ant, Maven or Gradle so that future programmers would be able to know how to compile or run just by reading the README.md.

(f) It's based on Ant. The build.xml works and all the targets have reasonable descriptions. It contains stuff related to older versions of the program.

(g) The issues are reasonable and clear. There are enough issues that we could potentially earn 1000 points by working on this project.

(h)
* [Infinitely printed message on terminal after someone wins](https://github.com/ucsb-cs56-projects/cs56-games-gomoku/issues/52)
* [Incorrectly highlighted rectangle around text "New Game"](https://github.com/ucsb-cs56-projects/cs56-games-gomoku/issues/53)

(i) The overall organization of the program is clear. It is a lot of basic graphics design code. The names of different classes and methods reflect what they are supposed to do. There are also comments that help us understand the relation between classes and the purposes of different methods. However, the Viewer.java class seems a bit confusing. It looks like an older version of implementing the main menu. Overall the code is easy to read and understand but the indentation of Gomoku.java class needs to be fixed. To explain this code to someone else: there are several classes that help set up the game components, like the instructions and home screen and more. There is one main gomoku file that has how the game actually runs and ties in all the other components.

(j) There are JUnit tests for the code to check for wins. These tests are fairly extensive since most of the other code is simply to set up the game. Another thing to test for could be the error messages and exceptions.

