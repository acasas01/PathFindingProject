/*
    A Maze is made up of Cells
 */
public class Cell {
    private boolean visited;    // whether the cell has been visited (true if visited, false if not visited)
    private boolean right;      // whether the cell has right border (true if a right boundary, false if an open right)
    private boolean bottom;     // whether the cell has bottom border (true if a bottom boundary, false if an open bottom)

    // All cells are initialized to full walls
    public Cell(){
        visited = false;
        right = true;
        bottom = true;
    }

    /**********
     * Setter functions
     **********/
    public void setVisited(boolean visited) { this.visited = visited; }
    public void setRight(boolean right) { this.right = right; }
    public void setBottom(boolean bottom) { this.bottom = bottom; }

    /**********
     * Getter functions
     **********/
    public boolean getVisited() { return visited; }
    public boolean getRight() { return right; }
    public boolean getBottom() { return bottom; }
}
