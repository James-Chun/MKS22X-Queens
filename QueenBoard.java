import java.util.Arrays;
import java.util.List;


public class QueenBoard{
  private int[][]board;
  public int counts = 0;

  public QueenBoard(int size){
    board = new int[size][size];
    for (int row=0;row<board.length;row++){
      for (int column=0;column<board[row].length;column++){

        board[row][column]=0;

      }
    }
  }

  private boolean addQueen(int r, int c){  //adds Queen to given coordinates and also marks all areas covered by the queen
    if (board[r][c]==0){
      board[r][c]=-1;
      for (int i=1;i<board.length;i++){
        if (i+c<board.length){
          board[r][c+i]++;
          if (r+i<board.length){
            board[r+i][c+i]++;
          }
          if (r-i>=0){
            board[r-i][c+i]++;
          }
        }
      }
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c){  //removes the queen at the given area and eliminates all the areas covered by it
    if (board[r][c]==-1){
      board[r][c]=0;
      for (int i=1;i<board.length;i++){
        if (i+c<board.length){
          board[r][c+i]--;
          if (r+i<board.length){
            board[r+i][c+i]--;
          }
          if (r-i>=0){
            board[r-i][c+i]--;
          }
        }
      }
      return true;
    }
    return false;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'  Will use -1 to represent queens
  *all others are displayed as underscores '_'              Will use ints to represent not possible (2 will mean two queens conflictions)
  *There are spaces between each symbol:                    0 represents possible placements for queens
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){ //displaying the board
    String visual = "";
    for (int row=0;row<board.length;row++){
      for (int column=0;column<board[row].length;column++){

        if (board[row][column]==-1){
          visual = visual + "Q ";
        }
        else if (board[row][column]>=0){
          visual = visual + "_ ";
        }

      }
      visual = visual +"\n";
    }
    return visual;
  }

  public String toStringDebug(){  //displaying the board with
    String visual = "";
    for (int row=0;row<board.length;row++){
      for (int column=0;column<board[row].length;column++){

        visual = visual + board[row][column] + "  ";

      }
      visual = visual +"\n";
    }
    return visual;
  }


  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    return helper(0);
  }

  public boolean solved(){
    for (int i=0;i<board.length;i++){
      if (board[i][board.length-1]==-1)return true;
    }
    return false;
  }

  public boolean helper(int column){
    if (column>board.length-1)return true;
    else if (solved()==false && column==board.length)return false;
    for (int rows=0;rows<board.length;rows++){
      if (addQueen(rows,column) && helper(column+1)){
        return true;
      }

      removeQueen(rows,column);
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    int count=0;
    for (int i=0;i<board.length;i++){
      addQueen(i,0);
      if (helper(1)){
        count++;
      }
      clear();
    }
    return count;
  }

  public void countHelper(int column){
    if (column>board.length-1)counts++;
    for (int rows=0;rows<board.length;rows++){
      if (addQueen(rows,column) && helper(column+1)){
        counts++;
      }

      removeQueen(rows,column);
    }
  }

  public void clear(){
    for (int rows=0;rows<board.length;rows++){
      for (int columns=0;columns<board.length;columns++){
        board[rows][columns]=0;
      }
    }
  }


  public static void main(String[] args){
    QueenBoard chess = new QueenBoard(Integer.parseInt(args[0]));
    /*
    for (int i=0;i<chess.board.length;i++){
      chess.addQueen(i,0);
    }
    chess.addQueen(2,2);
    System.out.println(chess.solve());
    System.out.println(chess.toStringDebug());
    System.out.println(chess);
    chess.removeQueen(2,2);
    System.out.println(chess.toStringDebug());
    */
    System.out.println(chess.solve());
    System.out.println(chess);
    System.out.println(chess.toStringDebug());
    System.out.println(chess.countSolutions());


  }


}
