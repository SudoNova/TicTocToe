/** Simulation of a Tic- Tac- Toe (does not do strategy). */
package TicTacToe;
public class TicTacToe
{
	protected static final int X = 1, O = -1; // players
	protected static final int EMPTY = 0; // empty
	protected int board[][] = new int[3][3]; // game board
	protected int player; //current player
	/** Constructor*/
	public TicTacToe()
	{
		clearBoard();
	}
	/** Clears the board */
	public void clearBoard()
	{
		 for (int i=0; i<3; i++)
			 for  (int j=0; j<3; j++)
				 board[i][j] = EMPTY; // every cell should be empty
		 player = X; // the first player is 'X'
	}
	/** Puts an X or O mark at position i,j */
	public void putMark (int i, int j) throws IllegalArgumentException
	{
		if ((i<0) || (i>2) || (j<0) || (j>2))
			throw new IllegalArgumentException("Invalid board position");
		if (board[i][j] != EMPTY)
			throw new IllegalArgumentException("Board position occupied");
		board [i][j] = player; // place the mark for the current player
		player = -player; //switch players (uses fact O = -X)
		
	}
	/** Checks whether the board configuration is a win for the given player */
	public boolean isWin (int mark)
	{
		return ((board[0][0] + board[0][1] + board[0][2] == mark*3) ||
				(board[1][0] + board[1][1] + board[1][2] == mark*3) ||
				(board[2][0] + board[2][1] + board[2][2] == mark*3) ||
				(board[0][0] + board[1][0] + board[2][0] == mark*3) ||
				(board[0][1] + board[1][1] + board[2][1] == mark*3) ||
				(board[0][2] + board[1][2] + board[2][2] == mark*3) ||
				(board[0][0] + board[1][1] + board[2][2] == mark*3) ||
				(board[2][0] + board[1][1] + board[0][2] == mark*3)
				);
	}
	/** Returns the winning player or 0 to indicate a tie */
	public int winner()
	{
		if (isWin(X))
			return (X);
		else if (isWin(O))
			return (O);
		else
			return(0);
	}
	/** Returns a simple character string showing the current board */
	public String toString()
	{
		String s = "";
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
			switch (board [i][j])
			{
				case X:
					s += "X";
					break;
				case O:
					s += "O";
					break;
				case EMPTY:
					s += " ";
					break;
			}
			if (j<2) s += "|";
			}
			if (i<2) s+= "\n-----\n";
		}
		return s;
			}
	/** Test run of a simple game */
	public static void main (String[] args)
	{
		TicTacToe game = new TicTacToe();
		/* X moves: */ /* O moves: */
		game.putMark(1,1); game.putMark(O,2);
		game.putMark(2,2); game.putMark(O,O);
		game.putMark(O,1); game.putMark(2,1);
		game.putMark(1,2); game.putMark(1,O);
		game.putMark(2,O);
		System.out.println(game.toString());
		int winningPlayer = game.winner();
		if (winningPlayer != 0)
		System.out.println(winningPlayer + " 'Wins");
		else
		System.out.println("Tie");
		}
	//Own modifications
	public boolean isOver()
	{
		if (isWin(X) || isWin(O)) return true;
		boolean end = true; 
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				if (board[i][j] == TicTacToe.EMPTY)
				{
					end = false;
					break;
				}
		if (end) return true;
		return false;
	}
}
	
	

			
	

