# JCGame-of-Life
A simulation of John Conway's game of life played on a finite board where cells beyond the edge of the board are assumed to always be unoccupied.

The game board is represented by a 2D-array.

GameOfLife asks the user for a file name. The given set of GOLInit.txt files each contain a series of coordinates that are used to initialize different starting generations for the game, which the program will use to generate future generations. The first set of coordinates specifies the game board dimensions. Input files should be given to the main method as a parameter before the program is run.

The program asks the user to input the number of generations they would like to see played out. Then, the program asks if the user would like to see the generations printed continuously. Continuously printed generations are printed one after the other without interuption, while non-continuously printed generations will print one at a time and wait for the user to press the Enter key before printing the next generation.

The original generation and setup of the game board is printed under 'Generation 0' and is not counted towards the number of generations played. For example, if the user asks to play 4 generations, the output shows Generation 0 and four more generations after it.

There are three possible game-ending conditions that will cause the program to terminate. First, all orginisms die; this is represented by a completely empty gameboard. Second, a pattern of organisms repeats itself. Third, the program reaches the user-inputed number of generations.

This project is a past homework assignment from a programming class in college. The goal of the project was to test file usage, to work with 2D arrays, and to become familiar with main method parameters.
