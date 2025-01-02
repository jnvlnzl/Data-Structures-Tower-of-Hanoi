# Data-Structures-Tower-of-Hanoi

The tower of Hanoi was made by a French mathematician Édouard Lucas in 1883. The puzzle consists of 3 rods and several disks ranging from smallest to largest. The goal is to move an entire stack of disks to another by following this set of rules. The first is that only one disk can be moved at a time. Second, the upper disk of any of the rods can be taken and can only be put on top of the other rods. Third, no disk may be on top of a smaller disk. 
	
The puzzle is perfect for teaching problem-solving in general but also teaches recursive programming techniques because it has an optimal solution that can be solved recursively. The use of data structures is the best practical use for the puzzle. The minimum number of moves is 2^n-1, where n is the number of disks. As the number of disks increases, the number of moves increases. It is a good demonstration of recursive algorithms and algorithms in general. 
	
Objectives: 

1. Develop a Tower of Hanoi simulation that provides an interactive experience.
2. Implement the Linked List and Stack data structure to manage the tower and user moves.
3. Incorporate the Queue data structure to record and display the move history.
4. Use a searching algorithm to validate the correctness of disk order throughout the game.

Data Structures

1. Linked Lists: To store the normal method of transporting and forming the disks at a certain tower.
2. Stack: To manage the insert and delete operations for the disk movements.
3. Queue: To record and display move history.
4. Tree: For random shuffling of disks, trees can be implemented to find the optimal way to get back to the normal method of solving the tower.

Functionalities

1. Allow players to move the disks between pegs.
2. Allow the players to record their moves and view history.
3. Allow the player to backtrack their moves.
4. Verify the order of disks on each peg to ensure they are in the correct sequence.
