import java.util.Arrays;
import java.util.List;


public class QueenBoard{
  private int[][]board;
  //private int counts;

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
    if (!checkBoard()){
      throw new IllegalStateException("Board Is Not In Cleared State");
    }
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
    if (!checkBoard()){
      throw new IllegalStateException("Board Is Not In Cleared State");
    }
    return countHelper(0,0);

  }

  public int countHelper(int column, int counts){
    if (column==board.length) { return counts+1;}
    // else{
    for (int rows=0;rows<board.length;rows++){
      if (addQueen(rows,column)){
        counts = countHelper(column+1,counts);
      }

      removeQueen(rows,column);
    }
    return counts;
  //}
  }


  private boolean checkBoard(){
    for (int rows=0;rows<board.length;rows++){
      for (int columns=0;columns<rows;columns++){
        if (board[rows][columns]!=0)return false;
      }
    }
    return true;
  }





//------------------------------------------------------------------------------------------------------------------------------------------------------------
//TESTING

//testcase must be a valid index of your input/output array
public static void runTest(int i){
  QueenBoard b;
  int[]tests =   {1,2,3,4,5,8};
  int[]answers = {1,0,0,2,10,92};
  if(i >= 0 && i < tests.length ){
    int size = tests[i];
    int correct = answers[i];
    b = new QueenBoard(size);
    int ans  = b.countSolutions();

    if(correct==ans){
      System.out.println("PASS board size: "+tests[i]+" "+ans);
    }else{
      System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
    }
  }
}




  public static void main(String[] args){
    //QueenBoard chess = new QueenBoard(Integer.parseInt(args[0]));
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
    //System.out.println(chess.solve());
    //System.out.println(chess);
  //  System.out.println(chess.toStringDebug());
  //  System.out.println(chess.countSolutions());
/*
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
    runTest(5);
    runTest(6);
    */
  }


}
