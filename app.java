import java.util.Scanner;
public class app {
private static final int D = 9;
private static final String AI = "O";
private static final String NIL = " ";
private static final String USER = "X";
private static final String XNAME = "USER";
private static final String ONAME = "AI";
private static Scanner S = new Scanner(System.in);
public static void main(String[] args) {
String[] board = new String[D];
for(int i=1; i<=D; i++){
board[i-1] = NIL;
}
// System.out.println("debug-1");
StartGame();
PrintBoard(board);
while(true){
UserMove(board);
AIMove(board);
PrintBoard(board);
}
}
private static int MiniMax(String[] board, String player){
    if(CheckWin(board, AI)){
    return 1;
    }
    else if(CheckWin(board, USER)){
    return -1;
    }
    else if(CheckTie(board)){
    return 0;
    }
    if(player == AI){
    int GlobalVal = Integer.MIN_VALUE;
    for(int i=1; i<=D; i++){
    int val = Integer.MIN_VALUE;
    if(board[i-1] == NIL){
    board[i-1] = AI;
    val = MiniMax(board, USER);
    board[i-1] = NIL;
    }
    GlobalVal = Math.max(val, GlobalVal);
    }
    return GlobalVal;
    }
    else{
    int GlobalVal = Integer.MAX_VALUE;
    for(int i=1; i<=D; i++){
    int val = Integer.MAX_VALUE;
    if(board[i-1] == NIL){
    board[i-1] = USER;
    val = MiniMax(board, AI);
    board[i-1] = NIL;
    }
    GlobalVal = Math.min(val, GlobalVal);
    }
    return GlobalVal;
    }
}
private static void AIMove(String[] board){
    int GlobalVal = Integer.MIN_VALUE;
    int position = -1;
    for(int i=1; i<=D; i++){
    int val = Integer.MIN_VALUE;
    if(board[i-1] == NIL){
    board[i-1] = AI;
    val = MiniMax(board, USER);
    board[i-1] = NIL;
    }
    if(val > GlobalVal){
    GlobalVal = val;
    position = i;
    }
    }
    InsertMove(board, position, AI);
}
private static void UserMove(String[] board){
    System.out.print("\n> ");
    try{
    int position = S.nextInt();
    InsertMove(board, position, USER);
    }
    catch(Exception e){
        System.out.println("Invalid input");
        System.exit(0);
        
    }


    
}
private static void GameTie(String[] board){
    // System.out.println("debug-2");
    PrintBoard(board);
    System.out.println("\nGame tied!");
    System.exit(0);
}
private static void GameEnd(String[] board, String player){
    PrintBoard(board);
    if(player == AI){
    System.out.println("\n"+ONAME+" wins!");
    } else {System.out.println("\n"+XNAME+" wins!");}
    System.exit(0);
}
private static boolean CheckWin(String[] board, String player){
    if((board[0] == player) && (board[1] == player) && (board[2] == player) ||
    (board[3] == player) && (board[4] == player) && (board[5] == player) ||
    (board[6] == player) && (board[7] == player) && (board[8] == player) ||
    (board[0] == player) && (board[3] == player) && (board[6] == player) ||
    (board[1] == player) && (board[4] == player) && (board[7] == player) ||
    (board[2] == player) && (board[5] == player) && (board[8] == player) ||
    (board[0] == player) && (board[4] == player) && (board[8] == player) ||
    (board[2] == player) && (board[4] == player) && (board[6] == player)){
        return true;
    }
    return false;
}

private static boolean CheckTie(String[] board){
    for(int i=1; i<=D; i++){
        if(board[i-1] == NIL){
            return false;
        }
    }
    return true;
}
private static void InsertMove(String[] board, int position, String player){
    if((position >= 1) && ((position <= D))){
        if(PositionEmpty(board, position)){
            board[position-1] = player;
            if(CheckWin(board, player)){
                GameEnd(board, player);
            }
            else if(CheckTie(board)){
                GameTie(board);
            }
        }
        else{
            System.out.print("Error! Not a valid position\n\n> ");
            int newposition = S.nextInt();
            InsertMove(board, newposition, player);
            return;
        }
        }
        else{
            System.out.print("Error! Not a valid position\n\n> ");
            int newposition = S.nextInt();
            InsertMove(board, newposition, player);
            return;
    }
}
private static boolean PositionEmpty(String[] board, int position){
    if(board[position-1] == NIL){
        return true;
    }
    return false;
}
private static void PrintBoard(String[] board){
System.out.println();
for(int i=1; i<=D; i++){
System.out.print(board[i-1]);
if(((i%3) == 0) && (i != D)){
System.out.println("\n--+---+---");
}
else if(i != D){
System.out.print(" | ");
}
}
System.out.println();
}
private static void StartGame(){
System.out.println("\nWelcome to the AI Tic-Tac-Toe\nPowered by Minimax Algorithm");
System.out.println("\nRules:\n> The User starts the game and plays first");
System.out.println("> User is assigned 'O', AI plays as 'X'");
System.out.println("> To make your move, place [1-9] at any grid box");
System.out.println("> Grid boxes being in a horizantal + downward order fashion");
System.out.println("> '1' corresponding baord[0][0], '9' being board[2][2] and others being in similar manner");
System.out.print("\nLet's start? [y/n]\n> ");
Character var = S.next().charAt(0);
var = Character.toLowerCase(var);
// System.out.println(var);
if(var == 'y'){
return;
}
else{
System.exit(0);
}
}
}
