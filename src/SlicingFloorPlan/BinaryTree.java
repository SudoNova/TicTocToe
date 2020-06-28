package SlicingFloorPlan;

public class BinaryTree 
{
    public int size;
    public TreeNode[] nodes;
    public TreeNode root;
    
    public BinaryTree( TreeNode root, int n)
    {
       nodes = new TreeNode[arraySize(n)];
       nodes[1] = root;
       this.root = nodes[1];
    }
    
    public void addRight(TreeNode child , int parentIndex)
    {
        if(nodes[parentIndex*2+1] == null )
        {
            nodes[parentIndex*2+1] = child;
            nodes[parentIndex*2+1].setParent(parentIndex);
            size++;
        }
        
    }
    
    public void addLeft(TreeNode child , int parentIndex)
    {
        if(nodes[parentIndex*2] == null )
        {
            nodes[parentIndex*2] = child;
            nodes[parentIndex*2].setParent(parentIndex);
            size++;
        }
    }
    
    public boolean hasRight(TreeNode p)
    {
        if(nodes[p.index*2] == null)
        {
            return false;
        }
        return true;
    }
    
    public boolean hasLeft(TreeNode p)
    {
        if(nodes[p.index*2+1] == null)
        {
            return false;
        }
        return true;
    }
    
    public int size()
    {
        return size;
    }
    
    public boolean hasChildren(TreeNode node)
    {
        if ( nodes[node.index*2]!= null && nodes[node.index*2+1]!= null )
            return true;
        return false;
    }
    
    private static int arraySize(int n)
    {
        int a ;
        int b ;
        b = 1 ;       
        for (int i = 0; i < n-1; i++) {
            a = b ;
            b = a*2+1 ;
           
        }
        return b;
    }
    
}
