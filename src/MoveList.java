import java.util.LinkedList;

public class MoveList {
    public static void pm(LinkedList<Integer[]> moveList,int start, int end){
        Integer[] moveSet = new Integer[2];
        moveSet[0] = start;
        moveSet[1] = end;
        moveList.add(moveSet); //stores in order traversal of the binary tree for moving discs
    }
    public static void generateMoveList(int n, LinkedList<Integer[]> moveList){
        recGenerateMoveList(n,1,3,moveList); //calls recursive method
    }

    public static void recGenerateMoveList(int n, int start, int end, LinkedList<Integer[]> moveList){
        //For every size n of the tower, there will always be a middle move where disc x = n is moved from tower 1 to 3.
        //To accomplish this move, all x<n discs must move from tower 1 to 2, after which those discs move tower 2 to 3.
        //The before and after moves operate the same way where x < n-1 discs move from starting tower to other tower...
        //... then from other tower to end tower.
        //As such, this method calls upon a binary tree whose inorder traversal will provide the correct move list...
        //... to solve the Hanoi Tower.
        //VISUALIZATION for a sample 3-disc tower:
        //                                    makeMove(disc 3, 1->3)
        //                                      /                 \
        //                   makeMove(disc 2, 1->2)              makeMove(disc 2, 2->3)
        //                    /               \                    /                 \
        // makeMove(disc 1, 1->3)  makeMove(disc 1, 3->2)  makeMove(disc 1, 2->1)   makeMove(disc 1, 1->3)

        //this method makes use of this tree to create a recursive functionality.

        if(n ==1){
            pm(moveList,start,end);
        }
        else{
            int other = 6 - (start + end);
            recGenerateMoveList(n-1, start, other, moveList);
            pm(moveList, start, end);
            recGenerateMoveList(n-1, other, end, moveList);
        }
    }

    public static void minimumMoves(int n, int currentMove){
        LinkedList<Integer[]> moveList = new LinkedList<>();
        generateMoveList(n, moveList);
        for (int i = currentMove; i < moveList.size();i++){
            TowerOfHanoi.makeMove(moveList.get(i)[0], moveList.get(i)[1]);
        }
    } // call this method to main class for minimum moves made.

    public static void minimumMovesSingle(int n, int move){
        LinkedList<Integer[]> moveList = new LinkedList<>();
        generateMoveList(n, moveList);
        TowerOfHanoi.makeMove(moveList.get(move)[0], moveList.get(move)[1]);
    }//makes a single move from the minimum moves list at the given move number.

    public static void readMoveList(LinkedList<Integer[]> moveList){
        for (int i =0; i < moveList.size(); i++){
            System.out.println("[" + moveList.get(i)[0] + ", " + moveList.get(i)[1] + "]");
        }
    } // for debugging only

    public static void main(String[] args) {
        LinkedList<Integer[]> moveList = new LinkedList<>();
        generateMoveList(3, moveList);
        readMoveList(moveList);
    } // run program to test validity of list generation
}