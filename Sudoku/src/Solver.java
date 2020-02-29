
public class Solver {
	
	private int[][] board;
	public static final int VOID = 0;
	public static final int BOARD_SIZE = 9;
	
	//Create the board in which we will solve
		public static int[][] BOARD = {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
	
	public Solver(int[][] board) {
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	//Check the 3x3 box that the number is in for duplicates
	private boolean checkBox(int row, int col, int number) {
		int boxRow = row - (row % 3);
		int boxCol = col - (col % 3);
		
		for (int i = boxRow; i < boxRow + 3; i++) {
			for (int j = boxCol; j < boxCol + 3; j++) {
				if(board[i][j] == number) {
					return false;
				}
		}
	}
	return true;
  }
	
	//Check the column of the number for duplicates
	private boolean checkCol(int col, int number) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][col] == number) {
				return false;
			}
		}
	  return true;
	}
	
	//Check the row of the number for duplicates
	private boolean checkRow(int row, int number) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[row][i] == number) {
				return false;
			}
		}
	  return true;
	}
	
	//Check all 3 conditions to see if number is valid choice
	private boolean isValid(int row, int col, int number) {
		if(checkBox(row, col, number) && checkCol(col, number) && checkRow(row, number)) {
			return true;
		}
	  return false;
	}
	
	//Solve the board using backtracking
	public boolean solver() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				
				if (board[row][col] == VOID) {
					for(int value = 1; value <= BOARD_SIZE; value++) {
						if(isValid(row, col, value)) {
							board[row][col] = value;
							
							if(solver()) {
								return true;
							} else {
								board[row][col] = VOID;
							}
						}
					}
				  return false;		
				}
			}
		}
	  return true;
	}
	
	//Print out the board
	public void print() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(j == 8) {
				   System.out.print(board[i][j]);
				} else {
				   System.out.print(board[i][j] + ", ");
				}
			}
			System.out.println();
		}
	}
	
	//Solve the board and display output message
	public static void main(String[] args) {
		Solver newboard = new Solver(BOARD);
		newboard.print();
		System.out.println("Board Solves To:");
		
		if (newboard.solver()) {
			newboard.print();
		} else {
			System.out.println("Unsolvable");
		}
	}
}