package SlicingFloorPlan;

public class SlicingFloorplan 
{
    
    BinaryTree tree;
    
    public SlicingFloorplan(TreeNode root,int rects)
    {
        tree = new BinaryTree(root, rects );
    }
    
    public int w(TreeNode v)
    {
        //if v is internal
        if(tree.hasChildren(v))
        {
              TreeNode w = tree.nodes[v.leftChild];
              TreeNode z = tree.nodes[v.rightChild];
            
              if(v.isHorizontal() == true )
                   return Math.max( w(w), w(z) );
            
              else
                   return ( w(w) + w(z) );
        }
        //if v is external
        return v.getWidth();
    }
    
    
    public int h(TreeNode v)
    {
        //if v is internal
        if(tree.hasChildren(v))
        {
              TreeNode w = tree.nodes[v.leftChild];
              TreeNode z = tree.nodes[v.rightChild];
            
              if(v.isHorizontal() == false )
                   return Math.max( h(w), h(z) );
            
              else
                   return ( h(w) + h(z) );
        }
        //if v is external
        return v.getHeight();
    }
    
    public void setMinWidth(TreeNode node, int width)
    {
        if( ! tree.hasChildren(node))
            node.setWidth(width);
    }
    
    public void setMinHeight(TreeNode node, int height)
    {
        if( ! tree.hasChildren(node))
            node.setHeight(height);
    }
    
    
    public int getMinWidthOfRectangle()
    {
       return w(tree.nodes[1]);
    }
    
    public int getMinHeightOfRectangle()
    {
       return h(tree.nodes[1]);
    }
    
    public static void main(String [] args)
    {
        SlicingFloorplan floorPlan = new SlicingFloorplan(new TreeNode(1,true), 6 );
        floorPlan.tree.addRight(new TreeNode(3, false), 1);
        floorPlan.tree.addLeft(new TreeNode(10,10), 3);    //E
        floorPlan.tree.addRight(new TreeNode(30,10), 3);
        floorPlan.tree.addLeft(new TreeNode(2, false), 1);
        floorPlan.tree.addLeft(new TreeNode(10,30), 2);    //A
        floorPlan.tree.addRight(new TreeNode(5, true), 2);
        floorPlan.tree.addLeft(new TreeNode(30,10), 5);    //B
        floorPlan.tree.addRight(new TreeNode(11, false), 5);
        floorPlan.tree.addLeft(new TreeNode(20, 20), 11);  //C
        floorPlan.tree.addRight(new TreeNode(10,20), 11);  //D
        
        
        System.out.println("Minimum Width of floorplan:" + floorPlan.getMinWidthOfRectangle()+"\nMinimum height of floorplan:" + floorPlan.getMinHeightOfRectangle());
     
    }
    
}
