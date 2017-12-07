
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Steven
 */
public class Graph {

    static int size;
    static int costMatrix[][];
    int adJ[][];
    boolean[] visited;

    public Graph(int size){
        this.size=size;
        costMatrix = new int[size][size];
        adJ = new int[size][size];
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {
                //costMatrix[i][j]=0;
                adJ[i][j]=0;
            }
        
        //
        visited = new boolean[size];
    }
    

    public void addEdge(int x, int y, int cost) {
        adJ[x][y]=1;
        adJ[y][x]=1;
        costMatrix[x][y] = cost;
        costMatrix[y][x] = cost;
    }
    
    public boolean isConnected(int x, int y){
        return adJ[x][y]==1||adJ[y][x]==1;
    }
    public int[][] getAdjacencyMatrix(){
        return adJ;
    }
    public int getCostMatrixValueAt(int x,int y){
        return costMatrix[x][y];
    }
    
    public void displayMatrix(){
        System.out.println("ADJACENCY MATRIX: ");
        
        System.out.println();
        
        for(int i=0;i<size;i++){
            //System.out.print(String.format("%4s", i+"| "));
            for(int j=0;j<size;j++)
                System.out.print(String.format("%4s", adJ[i][j]));
            System.out.println();
        }
    }
    
    public void displayCostMatrix(){
        System.out.println("COST MATRIX: ");
        
        System.out.println();
        
        for(int i=0;i<size;i++){
            //System.out.print(String.format("%4s", i+"| "));
            for(int j=0;j<size;j++)
                System.out.print(String.format("%4s", costMatrix[i][j]));
            System.out.println();
        }
    }

}
