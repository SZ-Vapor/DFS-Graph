
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    public static int costMatrix[][];
    public int adJ[][];
    boolean[] visited;
    static ArrayList<Integer> nodes = new ArrayList<Integer>();

    public Graph(int size){
        this.size=size;
        costMatrix = new int[size][size];
        adJ = new int[size][size];
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
            {
                costMatrix[i][j]=0;
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
        return costMatrix[x][y]!=0||costMatrix[y][x]!=0;
    }
    
    public void displayMatrix(){
        System.out.print("    ");
        for(int i=0;i<size;i++){
            System.out.print(String.format("%4s", i));
        }
        System.out.println();
        
        for(int i=0;i<size;i++){
            System.out.print(String.format("%4s", i+"| "));
            for(int j=0;j<size;j++)
                System.out.print(String.format("%4s", adJ[i][j]));
            System.out.println();
        }
    }

}
