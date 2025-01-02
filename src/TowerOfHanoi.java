import java.util.*;

class TowerOfHanoi {
    public static int N = 7;
    public static Stack<Integer>[] tower = new Stack[4];
    public static Queue<String> previousMove = new LinkedList<>();
    public static List<String> overallMoveHistory = new ArrayList<>();

    // Data Structure: List, Stack
    public static void main(String[] args) {
        Stack<int[]> previousMoveSet = new Stack<>();
        int[] prevMove = new int[2];
        previousMoveSet.push(prevMove);

        int moveNumber = 0;
        Scanner scan = new Scanner(System.in);
        tower[1] = new Stack<>();
        tower[2] = new Stack<>();
        tower[3] = new Stack<>();

        for (int d = N; d > 0; d--)
            tower[1].push(d);

        System.out.println("Welcome to Tower of Hanoi!");
        System.out.println("Rules:\n" +
                "   1. Only one disk can be moved at a time.\n" +
                "   2. Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.\n" +
                "      In other words, a disk can only be moved if it is the uppermost disk on a stack.\n" +
                "   3. No larger disk may be placed on top of a smaller disk.");
        display();

        while (!isGameOver()) {
            System.out.print("Enter move (source tower, destination tower, 'undo', 'hint', 'auto-solve'): ");
            String userInput = scan.next();

            if (userInput.equalsIgnoreCase("undo")) {
                undoMove();
                previousMoveSet.pop();
                moveNumber--;
            }
            else if (userInput.equalsIgnoreCase("hint")) {
                LinkedList<Integer[]> moveList = new LinkedList<>();
                MoveList.generateMoveList(N,moveList);

                if(moveNumber > 0 && (previousMoveSet.peek()[0]!= moveList.get(moveNumber-1)[0] || previousMoveSet.peek()[1]!= moveList.get(moveNumber-1)[1])) {
                    undoMove();
                    previousMoveSet.pop();
                    moveNumber--;
                }else {
                    MoveList.minimumMovesSingle(N, moveNumber);
                    prevMove[0] = moveList.get(moveNumber)[0];
                    prevMove[1] = moveList.get(moveNumber)[1];
                    previousMoveSet.push(prevMove);
                    moveNumber++;}
            }
            else if (userInput.equalsIgnoreCase("auto-solve")) {
                LinkedList<Integer[]> moveList = new LinkedList<>();
                MoveList.generateMoveList(N,moveList);
                while(moveNumber > 0 && (previousMoveSet.peek()[0]!= moveList.get(moveNumber-1)[0] || previousMoveSet.peek()[1]!= moveList.get(moveNumber-1)[1])) {
                    undoMove();
                    previousMoveSet.pop();
                    moveNumber--;
                }
                MoveList.minimumMoves(N, moveNumber);
                moveNumber++;

            }
            else {
                String destinationInput = scan.next();
                int source = getTowerIndex(userInput);
                int destination = getTowerIndex(destinationInput);

                if (source != -1 && destination != -1) {
                    makeMove(source, destination);
                    prevMove[0] = source;
                    prevMove[1] = destination;
                    previousMoveSet.push(prevMove);
                    moveNumber++;
                } else {
                    System.out.println("Invalid tower labels. Please use A, B, or C.");
                }
            }

        }

        System.out.println("Game over! Here are the overall moves:");
        displayOverallMoveHistory();
    }

    // Data Structures: List, Stack, Queue
    public static void makeMove(int source, int destination) {
        if (isValidMove(source, destination)) {
            int disk = tower[source].pop();
            tower[destination].push(disk);
            String move = "Move disk " + disk + " from " + getTowerName(source) + " to " + getTowerName(destination);
            previousMove.offer(move);
            overallMoveHistory.add(move);
            display();
        } else {
            System.out.println("Invalid move! Try again.");
        }
    }

    // Data Structures: List, Stack
    public static void undoMove() {
        if (!overallMoveHistory.isEmpty()) {
            String lastMove = overallMoveHistory.remove(overallMoveHistory.size() - 1);

            String[] parts = lastMove.split(" ");
            int disk = Integer.parseInt(parts[2]);

            int sourceIndex = -1;
            int destinationIndex = -1;
            for (int i = 0; i < parts.length; i++) {
                if ("ABC".contains(parts[i])) {
                    if (sourceIndex == -1) {
                        sourceIndex = getTowerIndex(parts[i]);
                    } else {
                        destinationIndex = getTowerIndex(parts[i]);
                        break;
                    }
                }
            }

            if (sourceIndex != -1 && destinationIndex != -1) {
                tower[destinationIndex].pop();
                tower[sourceIndex].push(disk);
                display();
            }

        } else {
            System.out.println("No moves to undo.");
        }
    }

    // Data Structure: Stack
    public static boolean isValidMove(int source, int destination) {
        if (tower[source].isEmpty()) {
            return false;
        } else if (tower[destination].isEmpty() || tower[source].peek() < tower[destination].peek()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGameOver() {
        return tower[3].size() == N;
    }

    // Data Structure: Stack, Queue
    public static void display() {
        System.out.println("\nCurrent Towers State:\t\t\tPrevious Move:");
        int historySize = previousMove.size();
        for (int i = N - 1; i >= 0; i--) {
            String d1 = getDiskRepresentation(tower[1], i);
            String d2 = getDiskRepresentation(tower[2], i);
            String d3 = getDiskRepresentation(tower[3], i);

            System.out.println("  " + d1 + "  |  " + d2 + "  |  " + d3 + "\t\t\t\t" +
                    (!previousMove.isEmpty() ? previousMove.poll() : ""));
        }
        System.out.println("  A  |  B  |  C\n---------------\n");
    }

    public static String getDiskRepresentation(Stack<Integer> stack, int index) {
        try {
            return String.valueOf(stack.get(index));
        } catch (Exception e) {
            return " ";
        }
    }

    public static void displayOverallMoveHistory() {
        for (String move : overallMoveHistory) {
            System.out.println(move);
        }
    }

    public static String getTowerName(int towerIndex) {
        switch (towerIndex) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            default:
                return "";
        }
    }

    public static int getTowerIndex(String label) {
        switch (label.toUpperCase()) {
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            default:
                return -1;
        }
    }
}

