package TicTacToe;

public class GameTree
{

	Node root;

	GameTree(int[][] board)
	{
		// TODO Auto-generated constructor stub
		root = new Node(board, null, null);
//		updateTree();

	}

	GameTree(Node root)
	{
		this.root = root;
		
	}

	public int size()
	{
		return size(root);
	}

	private int size(Node node)
	{

		int size = 1;
		if (node.isInternal())
		{

			for (Node i : node.children)
			{
				node = i;
				size += size(i);
			}

		}
		return size;

	}

	public boolean isEmpty()
	{
		return (root == null);
	}

	public void insert(Node parent, int index, Node child)
	{
		Node[] array = parent.children;
		if (array != null)
		{
			Node[] temp = new Node[array.length + 1];
			for (int i = 0; i < index; i++)
				temp[i] = array[i];
			for (int i = index; i < array.length; i++)
				temp[i + 1] = array[i];
			temp[index] = child;
			array = temp;
			child.parent.children = array;
		} else
		{
			array = new Node[1];
			array[0] = child;
			child.parent.children = array;
		}
//		updateTree();
	}
	
	public int height()
	{
		return height(root);
	}
	private int height(Node node)
	{
		int height;
		int max = Integer.MIN_VALUE;
		if (node.isExternal()) height = 0;
		else
		{ for (Node i:node.children)
			{
		max = Math.max(max, height(i));	
			}
		height = max;
		height ++;
		}
		
		return height;
	}
	
}

class Node
{
	int preOrder_id;
	int postOrder_id;
	int inOrder_id;
	int score;
	protected Node[] children;
	protected Node parent;
	protected int[][] board = new int[3][3];
	public int miniMaxScore (int step)
	{
		return miniMaxScore(step, this);
		
	}
	private int miniMaxScore(int step, Node node)
	{
		int mod = step%2;
		
		//0 means it's human's turn and must consider worst cases
		//1 means it's bot's turn and must consider best cases
		if (node.isExternal())
		{
			TicTacToe test = new TicTacToe();
			test.board = node.board;
			switch (mod)
			{
				case 0:
					if (test.isWin(TicTacToe.X)) return 1;
					if (test.isWin(TicTacToe.O)) return -1;
					return 0;

				case 1:
					if (test.isWin(TicTacToe.O)) return 1;
					if (test.isWin(TicTacToe.X)) return -1;
					return 0;	
			}
		}
		switch (mod)
		{
			case 0:
				int min = 2;
				for (Node i:node.children)
					min = Math.min(miniMaxScore(step, i), min);
				return min;

			case 1:
				int max = -2;
				for (Node i:node.children)
					max = Math.max(miniMaxScore(step, i), max);
				return max;
		}
		return 0;
	}
	public boolean isSimiliarTo(TicTacToe game)
	{
		int[][] board1= board;
		int[][] board2 = game.board;
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				if (board1[i][j] != board2[i][j])
					return false;
		return true;
		
	}
	boolean isInternal()
	{
		if (children != null)
			return true;
		return false;
	}

	boolean isExternal()
	{
		if (children == null)
			return true;
		return false;
	}

	boolean isRoot()
	{
		if (parent == null)
			return true;
		return false;
	}

	
	public int depth()
	{
		Node temp = this;
		int height = 0;
		while (temp.parent != null)
		{
			height++;
			temp = temp.parent;
		}
		return height;
	}

	public Node(int[][] board, Node[] children, Node parent)
	{
		// TODO Auto-generated constructor stub
		this.board = board;
		this.children = children;
		this.parent = parent;
	}
	

}
