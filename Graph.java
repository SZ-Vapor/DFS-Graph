/**
 *
 * @author 
 */
public class Graph {

    static int size;
    static int costMatrix[][];
    int adjMatrix[][];
    boolean[] visited;

    /**
     * Constructor
     * @param size: number of vertices
     */
    public Graph(int size){
        this.size=size;
        costMatrix = new int[size][size];
        adjMatrix = new int[size][size];
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {
                costMatrix[i][j]=-1;
                adjMatrix[i][j]=0;
            }
        visited = new boolean[size];
    }
    
    /**
     * Sets proper cost between two nodes in costMatrix and creates an edge between two nodes
     * @param x: node x
     * @param y: node y
     * @param cost: distance between x and y (length of edge) 
     */
    public void addEdge(int x, int y, int cost) {
        adjMatrix[x][y]=1;
        adjMatrix[y][x]=1;
        costMatrix[x][y] = cost;
        costMatrix[y][x] = cost;
    }
    /**
     * @param x
     * @param y
     * @return returns is there exists an edge between x and y 
     */
    public boolean isConnected(int x, int y){
        return adjMatrix[x][y]==1||adjMatrix[y][x]==1;
    }
   /**
    * @param x 
    * @param y 
    * @return returns the cost of the edge between x and y 
    */
    public int getCostMatrixValueAt(int x,int y){
        return costMatrix[x][y];
    }
    /**
     * Prints the adjacency matrix for a graph (0 means no edge exits,
     * and 1 means an edge exists between 2 nodes)
     */
    public void displayAdjMatrix(){
        System.out.println("ADJACENCY MATRIX:\n");
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                System.out.print(String.format("%4s", adjMatrix[i][j]));
            System.out.println();
        }
    }
     /**
     * Prints the cost matrix for a graph (-1 means no edge exits,
     * and any other number is the cost of the edge between 2 nodes)
     */
    public void displayCostMatrix(){
        System.out.println("COST MATRIX:\n");
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                System.out.print(String.format("%4s", costMatrix[i][j]));
            System.out.println();
        }
    }

}
