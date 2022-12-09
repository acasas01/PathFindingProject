// Names: Alan Casas, Taha Rao
// x500s:casas034, rao0004
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class MyMaze{
    Cell[][] maze;
    //Cell[][] mazeCopy;
    int startRow;
    int endRow;
    int totalRow;
    int totalCol;
    double startEndDist;
    int counter = 0; 
    Cell[][] mazeCopy;
    Cell[][] mazeCopy1;
    Cell[][] mazeCopy2;



    public MyMaze(int rows, int cols, int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
        totalRow = rows;
        totalCol = cols;
        maze = new Cell[rows][cols];
        mazeCopy = new Cell[rows][cols];
        mazeCopy1 = new Cell[rows][cols];
        mazeCopy2 = new Cell[rows][cols];


        for (int i =0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = new Cell();
                mazeCopy[i][j] = new Cell();
                mazeCopy1[i][j] = new Cell();
                mazeCopy2[i][j] = new Cell();

            }
        }

        double entranceRow = (double)startRow + .5;
        double exitRow = (double)endRow + .5;
        startEndDist = Math.sqrt(( Math.abs((exitRow - entranceRow) * (exitRow - entranceRow)) + (totalCol * totalCol)));

    }

    //copy constructor
    public MyMaze(MyMaze other, int which){

        if(which == 1){
            this.maze = other.mazeCopy;
        }else if(which == 2){
            this.maze = other.mazeCopy1;
        }else if(which == 3){
            this.maze = other.mazeCopy2;
        }

        this.startRow = other.startRow;
        this.endRow = other.endRow;
        this.totalCol = other.totalCol;
        this.totalRow = other.totalRow;
        this.startEndDist = other.startEndDist;
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int n) {

        int totalRow;
        int totalCol;
        Random ranObj = new Random();
        int startRow;
        int endRow;


            totalRow = n;
            totalCol = n;
            startRow = ranObj.nextInt(totalRow);
            endRow = ranObj.nextInt(totalRow);
        // if (level == 1){// inputed option levels
        //     totalRow = 5;
        //     totalCol = 5;
        //     startRow = ranObj.nextInt(totalRow);
        //     endRow = ranObj.nextInt(totalRow);
        // }
        // else if(level == 2){
        //     totalRow = 7;
        //     totalCol = 7;
        //     startRow = ranObj.nextInt(totalRow);
        //     endRow = ranObj.nextInt(totalRow);
        // }
        // else{//level 3
        //     totalRow = 100;
        //     totalCol = 100;
        //     startRow = ranObj.nextInt(totalRow);
        //     endRow = ranObj.nextInt(totalRow);
        // }

        MyMaze generated = new MyMaze(totalRow,totalCol,startRow,endRow);
        Stack1Gen<int[]> stack = new Stack1Gen<int[]>();
        int[] start = {startRow,0};//int  array of coord

        stack.push(start);
        generated.maze[startRow][0].setVisited(true);
        generated.mazeCopy[startRow][0].setVisited(true);
        generated.mazeCopy1[startRow][0].setVisited(true);
        generated.mazeCopy2[startRow][0].setVisited(true);


        int ranDirection;

        while(!stack.isEmpty()) { //getting a random neighbor
            ranObj = new Random();
            int row = stack.top()[0];
            int col = stack.top()[1];
            if (row == endRow && col == totalCol-1) { //end cell
                generated.maze[row][col].setRight(false);
                generated.mazeCopy[row][col].setRight(false);
                generated.mazeCopy1[row][col].setRight(false);
                generated.mazeCopy2[row][col].setRight(false);

            }

            int[] possible = new int[4];//possible directions
            int index = 0;

            if(col + 1 < totalCol && !generated.maze[row][col+1].getVisited()) { //right
                possible[index] = 0;
                index++;
            }
            if(row + 1 < totalRow && !generated.maze[row+1][col].getVisited()) { //bottom
                possible[index] = 1;
                index++;
            }
            if(row - 1 > -1 && !generated.maze[row-1][col].getVisited()) { //up
                possible[index] = 2;
                index++;
            }
            if (col - 1 > -1 && !generated.maze[row][col-1].getVisited()) { //left
                possible[index] = 3;
                index++;
            }
            if (index == 0) {//dead end case
                ranDirection = -1;
            }
            else {
                int[] valids = new int[index];//available choices for directions
                System.arraycopy(possible, 0, valids, 0, index);
                int randIndex = ranObj.nextInt(index); // random valid direction
                ranDirection = valids[randIndex]; //chosen random direction from possible
            }

            if (ranDirection == -1) {//takes off the top item from the stack. riding off of dead end index
                stack.pop();
                continue;
            }

            if (ranDirection == 0) {//if direction is right and cell to the right exists and is not visited
                stack.push(new int[]{row, col+1});
                generated.maze[row][col].setRight(false);
                generated.maze[row][col+1].setVisited(true);

                generated.mazeCopy[row][col].setRight(false);
                generated.mazeCopy[row][col+1].setVisited(true);

                generated.mazeCopy1[row][col].setRight(false);
                generated.mazeCopy1[row][col+1].setVisited(true);

                generated.mazeCopy2[row][col].setRight(false);
                generated.mazeCopy2[row][col+1].setVisited(true);
                
            }
            else if (ranDirection == 1) {//if direction is bottom and cell to the bottom exists and isnt visited
                stack.push(new int[]{row + 1, col});
                generated.maze[row][col].setBottom(false);
                generated.maze[row + 1][col].setVisited(true);

                generated.mazeCopy[row][col].setBottom(false);
                generated.mazeCopy[row + 1][col].setVisited(true);

                generated.mazeCopy1[row][col].setBottom(false);
                generated.mazeCopy1[row + 1][col].setVisited(true);

                generated.mazeCopy2[row][col].setBottom(false);
                generated.mazeCopy2[row + 1][col].setVisited(true);

            }
            else if (ranDirection == 2) {//if direction is up and cell above exists and isn't visited
                stack.push(new int[]{row - 1, col});
                generated.maze[row - 1][col].setBottom(false);
                generated.maze[row - 1][col].setVisited(true);

                generated.mazeCopy[row - 1][col].setBottom(false);
                generated.mazeCopy[row - 1][col].setVisited(true);

                
                generated.mazeCopy1[row - 1][col].setBottom(false);
                generated.mazeCopy1[row - 1][col].setVisited(true);
                
                generated.mazeCopy2[row - 1][col].setBottom(false);
                generated.mazeCopy2[row - 1][col].setVisited(true);
            }
            else if (ranDirection == 3) {//going left
                stack.push(new int[]{row, col-1});
                generated.maze[row][col - 1].setRight(false);
                generated.maze[row][col - 1].setVisited(true);

                generated.mazeCopy[row][col - 1].setRight(false);
                generated.mazeCopy[row][col - 1].setVisited(true);

                generated.mazeCopy1[row][col - 1].setRight(false);
                generated.mazeCopy1[row][col - 1].setVisited(true);

                generated.mazeCopy2[row][col - 1].setRight(false);
                generated.mazeCopy2[row][col - 1].setVisited(true);
            }
        }

        for(int i = 0; i< generated.maze.length; i++){ //setting all cells to false, thus maze will have only walls
            for(int j = 0; j < generated.maze[0].length; j++){
                generated.maze[i][j].setVisited(false);

                generated.mazeCopy[i][j].setVisited(false);
                generated.mazeCopy1[i][j].setVisited(false);
                generated.mazeCopy2[i][j].setVisited(false);


            }
        }
        
        return generated;
    }



    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        String wall = "";
        counter = 0;
        wall += "   ";
        for (int a = 0; a < maze[0].length; a++) { //top walls
            wall += "---   ";
        }

        wall+="\n";
        for (int i = 0; i < maze.length; i++) {
            if(i == startRow){//opening for the starting row
                wall+= "   ";
            }
            else{
                wall += " | ";
            }
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j].getVisited() == true) {//checks to see if the cell has been visited if true then places star
                    wall += " * ";
                    counter++;

                } else {
                    wall += "   ";//if not visited open space is printed.

                }
                if (maze[i][j].getRight() == true) {//checks to see if the right has a wall

                    wall += " | ";
                } else {
                    wall += "   ";// if right is not valid prints empty space

                }
            }
            wall += "\n";
            wall += "   ";
            for (int k = 0; k < maze[0].length; k++) {
                if (maze[i][k].getBottom() == true) {//bottom case for the maze
                    wall += "---   ";
                }

                else {
                    wall += "      ";
                }

            }
            wall+="\n";

        }
        System.out.println(wall);

    }
    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void bfs() {

        Q1Gen<int[]> queue = new Q1Gen<int[]>();
        queue.add(new int[]{startRow,0});

        while(queue.length() > 0){

            int[] coord = queue.remove();
            maze[coord[0]][coord[1]].setVisited(true);//current cell as visited
            counter++;
            if(coord[0] == endRow && coord[1] == maze[0].length-1){ //reached end point case
                break;
            }

            if (coord[0] + 1 < maze.length && !maze[coord[0] + 1][coord[1]].getVisited() && !maze[coord[0]][coord[1]].getBottom()){//go down
                queue.add(new int[] {coord[0] + 1, coord[1]});
            }

            if (coord[0] - 1 >= 0  && !maze[coord[0] - 1][coord[1]].getVisited() && !maze[coord[0] -1][coord[1]].getBottom()){//go up
                queue.add(new int[] {coord[0] - 1, coord[1]});
            }

            if (coord[1] + 1 < maze[0].length && !maze[coord[0]][coord[1] + 1].getVisited() && !maze[coord[0]][coord[1]].getRight()){//go right
                queue.add(new int[] {coord[0], coord[1] + 1});
            }

            if (coord[1] - 1 >= 0 && !maze[coord[0]][coord[1] -1].getVisited() && !maze[coord[0]][coord[1] - 1].getRight()){//go left
                queue.add(new int[] {coord[0], coord[1] - 1});
            }
        }

        //printMaze();
    }

    public void dfs() {

        //Q1Gen<int[]> queue = new Q1Gen<int[]>();
        //queue.add(new int[]{startRow,0});
        Stack1Gen<int[]> stack = new Stack1Gen<int[]>();
        stack.push(new int[]{startRow,0});
        while(!stack.isEmpty()){

            int[] coord = stack.pop();
            maze[coord[0]][coord[1]].setVisited(true);//current cell as visited
            counter++;
            if(coord[0] == endRow && coord[1] == maze[0].length-1){ //reached end point case
                break;
            }

            if (coord[0] + 1 < maze.length && !maze[coord[0] + 1][coord[1]].getVisited() && !maze[coord[0]][coord[1]].getBottom()){//go down
                stack.push(new int[] {coord[0] + 1, coord[1]});
            }

            if (coord[0] - 1 >= 0  && !maze[coord[0] - 1][coord[1]].getVisited() && !maze[coord[0] -1][coord[1]].getBottom()){//go up
                stack.push(new int[] {coord[0] - 1, coord[1]});
            }

            if (coord[1] + 1 < maze[0].length && !maze[coord[0]][coord[1] + 1].getVisited() && !maze[coord[0]][coord[1]].getRight()){//go right
                stack.push(new int[] {coord[0], coord[1] + 1});
            }

            if (coord[1] - 1 >= 0 && !maze[coord[0]][coord[1] -1].getVisited() && !maze[coord[0]][coord[1] - 1].getRight()){//go left
                stack.push(new int[] {coord[0], coord[1] - 1});
            }
        }

        //printMaze();
    }

    public void AstarEuc( ) {


        //Q1Gen<int[]> queue = new Q1Gen<int[]>();
        //queue.add(new int[]{startRow,0});
        // Stack1Gen<int[]> stack = new Stack1Gen<int[]>();
        // stack.push(new int[]{startRow,0});

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>();
        pq.add(new int[]{startRow,0},1);



        //System.out.println(startEndDist);

        while(!pq.isEmpty()){

            int[] coord = pq.next();
            maze[coord[0]][coord[1]].setVisited(true);//current cell as visited
            counter++;
            if(coord[0] == endRow && coord[1] == maze[0].length-1){ //reached end point case
                break;
            }

            if (coord[0] + 1 < maze.length && !maze[coord[0] + 1][coord[1]].getVisited() && !maze[coord[0]][coord[1]].getBottom()){//go down
                pq.add(new int[] {coord[0] + 1, coord[1]},EuclideanEval(new int[] {coord[0] + 1, coord[1]}));
            }

            if (coord[0] - 1 >= 0  && !maze[coord[0] - 1][coord[1]].getVisited() && !maze[coord[0] -1][coord[1]].getBottom()){//go up
                pq.add(new int[] {coord[0] - 1, coord[1]}, EuclideanEval(new int[] {coord[0] - 1, coord[1]}));
            }

            if (coord[1] + 1 < maze[0].length && !maze[coord[0]][coord[1] + 1].getVisited() && !maze[coord[0]][coord[1]].getRight()){//go right
                pq.add(new int[] {coord[0], coord[1] + 1},EuclideanEval(new int[] {coord[0], coord[1] + 1}));
            }

            if (coord[1] - 1 >= 0 && !maze[coord[0]][coord[1] -1].getVisited() && !maze[coord[0]][coord[1] - 1].getRight()){//go left
                pq.add(new int[] {coord[0], coord[1] - 1},EuclideanEval(new int[] {coord[0], coord[1]-1}));
            }
        }

        //printMaze();
    }

    public void AstarSumEdge( ) {


        //Q1Gen<int[]> queue = new Q1Gen<int[]>();
        //queue.add(new int[]{startRow,0});
        // Stack1Gen<int[]> stack = new Stack1Gen<int[]>();
        // stack.push(new int[]{startRow,0});

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>();
        pq.add(new int[]{startRow,0},1);



        //System.out.println(startEndDist);

        while(!pq.isEmpty()){

            int[] coord = pq.next();
            maze[coord[0]][coord[1]].setVisited(true);//current cell as visited
            counter++;
            if(coord[0] == endRow && coord[1] == maze[0].length-1){ //reached end point case
                break;
            }

            if (coord[0] + 1 < maze.length && !maze[coord[0] + 1][coord[1]].getVisited() && !maze[coord[0]][coord[1]].getBottom()){//go down
                pq.add(new int[] {coord[0] + 1, coord[1]},SumEdgeDist(new int[] {coord[0] + 1, coord[1]}));
            }

            if (coord[0] - 1 >= 0  && !maze[coord[0] - 1][coord[1]].getVisited() && !maze[coord[0] -1][coord[1]].getBottom()){//go up
                pq.add(new int[] {coord[0] - 1, coord[1]}, SumEdgeDist(new int[] {coord[0] - 1, coord[1]}));
            }

            if (coord[1] + 1 < maze[0].length && !maze[coord[0]][coord[1] + 1].getVisited() && !maze[coord[0]][coord[1]].getRight()){//go right
                pq.add(new int[] {coord[0], coord[1] + 1},SumEdgeDist(new int[] {coord[0], coord[1] + 1}));
            }

            if (coord[1] - 1 >= 0 && !maze[coord[0]][coord[1] -1].getVisited() && !maze[coord[0]][coord[1] - 1].getRight()){//go left
                pq.add(new int[] {coord[0], coord[1] - 1},SumEdgeDist(new int[] {coord[0], coord[1]-1}));
            }
        }

        //printMaze();
    }

    public int EuclideanEval(int[] coords){

        double startRow = (double)coords[0] + .5;
        double startCol = (double)coords[1] - .5;

        double exitRow = (double)endRow + .5;
        double exitCol = (double)totalCol - .5;

        double dist = Math.sqrt(( Math.abs((startRow - exitRow) * (startRow - exitRow)) + Math.abs((startCol - exitCol) * (startCol - exitCol))));
        double heuristic = startEndDist - dist;
        //System.out.println("heurstic: " + heuristic + " move: " + startRow + ", " + startCol);
        return (int)heuristic;
    }

    public int SumEdgeDist(int[] coords){

        double startRow = (double)coords[0] + .5;
        double startCol = (double)coords[1] - .5;

        
        double exitRow = (double)endRow + .5;
        double exitCol = (double)totalCol - .5;

        int heuristic =(int) (startEndDist - (Math.abs(startRow - exitRow) + Math.abs(startCol - exitCol)));


        return heuristic;
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze /
                 */

        //System.out.println("Enter Level Number: 1 (5x5), 2 (5x20), or 3 (20x20) @");
        //Scanner s = new Scanner(System.in);
        //int lvl = s.nextInt();

        //System.out.println("Enter a search strategy: Number: 1 BFS, 2 DFS, 3 A*");
        //int strat = s.nextInt();

        long t1,t2,t3,t4;
        MyMaze maze = makeMaze(1000);
        MyMaze mazeCopy = new MyMaze(maze,1);
        MyMaze mazeCopy2 = new MyMaze(maze,2);
        MyMaze mazeCopy3 = new MyMaze(maze,3);



        
        long startTime = System.currentTimeMillis();
        maze.AstarSumEdge();
        long endTime = System.currentTimeMillis();
        t1 = endTime - startTime;


        startTime = System.currentTimeMillis();
        mazeCopy.AstarEuc();
        endTime = System.currentTimeMillis();
        t2 = endTime - startTime;

        startTime = System.currentTimeMillis();
        mazeCopy2.bfs();
        endTime = System.currentTimeMillis();
        t3 = endTime - startTime;

        startTime = System.currentTimeMillis();
        mazeCopy3.dfs();
        endTime = System.currentTimeMillis();
        t4 = endTime - startTime;
        

        System.out.println("Euc: " + t2 + " ms count: " + maze.counter);
        System.out.println("Cheb: " + t1 + "ms count: " + mazeCopy.counter);
        
        System.out.println("bfs: " + t3 + " ms count: " + mazeCopy2.counter);
        System.out.println("dfs: " + t4 + " ms count: " + mazeCopy3.counter);

                
    }

}
