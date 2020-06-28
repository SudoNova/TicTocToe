package SlicingFloorPlan;

public class TreeNode 
{
    int index;
    int parent;
    int rightChild;
    int leftChild;
    
    private int width;
    private int height;
    private boolean horizontal;
    
    
    public TreeNode( int index )
    {
        this.index = index;
        this.parent = index/2;
       
        leftChild = index*2+1;
        rightChild = index*2;
    }
    
    public TreeNode( int index, boolean horizontal )
    {
        this(index);
        this.horizontal = horizontal;
    }
    
    public TreeNode(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public TreeNode(boolean b)
    {
        this.horizontal = b;
    }
    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the horizontal
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @param horizontal the horizontal to set
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    
    public void setParent(int index)
    {
        parent = index;
    }
    
    
    
    
    
}
