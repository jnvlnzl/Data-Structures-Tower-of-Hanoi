# Data-Structures-Tower-of-Hanoi

The tower of Hanoi was made by a French mathematician Édouard Lucas in 1883. The puzzle consists of 3 rods and several disks ranging from smallest to largest. The goal is to move an entire stack of disks to another by following this set of rules. The first is that only one disk can be moved at a time. Second, the upper disk of any of the rods can be taken and can only be put on top of the other rods. Third, no disk may be on top of a smaller disk. 

The use of data structures is the best practical use for the puzzle. The minimum number of moves is 2^n-1, where n is the number of disks. As the number of disks increases, the number of moves increases. It is a good demonstration of recursive algorithms and algorithms in general. The following data structures were used: 

* Linked Lists: To store the normal method of transporting and forming the disks at a certain tower.
* Stack: To manage the insert and delete operations for the disk movements.
* Queue: To record and display move history.
* Tree: For random shuffling of disks, trees can be implemented to find the optimal way to get back to the normal method of solving the tower.

Additionally, the program has the following functionalities:

* Allow players to move the disks between pegs.
* Allow the players to record their moves and view history.
* Allow the player to backtrack their moves.
* Verify the order of disks on each peg to ensure they are in the correct sequence.
