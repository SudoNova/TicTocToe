package TicTacToe;

import java.util.Scanner;
import java.util.Stack;

//Minimax is not effective, so this game is
public class Game
{
	Scanner in = new Scanner(System.in);
	// A, the first player is human (X)
	// B, the second player is computer (O)
	// First turn is human's
	static TicTacToe game;
	static GameTree tree;

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		game = new TicTacToe();
		tree = new GameTree(new int[3][3]);
		initTree(tree);
		Node currentState = tree.root;
		boolean gameOver = false;
		int step = 0;
		loop : while (!gameOver)
		{
			System.out.println("Enter number of cell to put mark\n Enter 0 to see all choices with least scores (in minimax algorithm");
			int choice = in.nextInt();
			boolean nextRound = false;
			boolean printed = true;
			switch (choice)
			{
				case 0:
					Stack<TicTacToe> stack = new Stack<>();
					int worstChoice = currentState.miniMaxScore(step);
					for (Node i:currentState.children)
					{
						if (i.miniMaxScore(step) == worstChoice)
						{
							TicTacToe temp = new TicTacToe();
							temp.board = i.board;
							stack.push(temp);
						}
					}
					if (stack.isEmpty()) printed = false;
					else 
					{
						for (int i=0; i<stack.size(); i++)
							System.out.println(stack.pop() + "\n");
					}
					break;

				default:
					game.player = game.X;
					game.putMark((choice-1)/3, (choice-1)%3);
					for (Node i:currentState.children)
						if (i.isSimiliarTo(game)) {
							currentState = i;
							nextRound = true;
							break;
						}
					step++;
					if(game.isOver())
					{
						gameOver = true;
						continue loop; 
					}
					int bestChoice = currentState.miniMaxScore(step);
					for (Node i:currentState.children)
					{
						if (i.miniMaxScore(step) == bestChoice)
						{
							currentState = i;
							game.board = currentState.board;
							nextRound = true;
							break;
						}
					}
					step++;
					if(game.isOver())
					{
						gameOver = true;
						continue loop; 
					}
					break;
			}
			if (!printed) System.out.println("Nothing to show");
			if (nextRound) {
				System.out.println(game);
				System.out.println("\n---------------Next Round--------------");
				
			}
		}
		System.out.println(game);
		int winningPlayer = game.winner();
		if (winningPlayer != 0)
		{
			System.out.print(winningPlayer==-1?'O':'X');
			System.out.println(" Wins");
		}
		else
		System.out.println("Tie");
	}

	private static void initTree(GameTree tree)
	{
		Node node = tree.root;
		initNodes(0, node);
		
	}
	private static void initNodes(int step, Node node)
	{
		for (int counter=0; counter < 9-step; counter++)
		{
			TicTacToe test = new TicTacToe();
			test.board = node.board;
			tree.insert(node, counter, generateChild(node, counter,step));
			if (!test.isOver()) initNodes(step+1, node.children[counter]);
		}
	}
	private static Node generateChild (Node parent, int counter, int step)
	{
		//X is sign for player A
		//Y is sign for player B
		int [][] board = new int[3][3];
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				board[i][j] = parent.board[i][j];
		Node child = new Node(null, null, parent);
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
			{
				if (board[i][j] == TicTacToe.EMPTY) 
				{
					if (counter == 0)
					{
						int move = 0;
						int mod = step%2;
						switch (mod)
						{
							case 0:
								move = TicTacToe.X;
								break;

							case 1:
								move = TicTacToe.O;
								break;
						}
						board[i][j] = move;
						child.board = board;
						return child;
						
					}
					counter--;
				}
			}
		return null;
	}

}
