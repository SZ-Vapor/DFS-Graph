
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Steven
 */
public class Main {

    static ArrayList<String> original = new ArrayList<String>();
    static ArrayList<Integer> fromNode = new ArrayList<Integer>();
    static ArrayList<Integer> toNode = new ArrayList<Integer>();
    static ArrayList<Integer> cost = new ArrayList<Integer>();
    static int vertices;
    static Graph g;

    public static void main(String args[]) throws FileNotFoundException {
        ArrayList<Integer> fromNodeSort = fromNode;
        Collections.sort(fromNodeSort);
        ArrayList<Integer> toNodeSort = toNode;
        Collections.sort(toNodeSort);

        Scanner usIn = new Scanner(new File("lab.txt"));
        Scanner userIn = new Scanner(System.in);

        while (usIn.hasNext()) {
            String sz = usIn.nextLine();
            String szS[] = sz.split(" ");

            if (!sz.equals("")) {
                if (sz.charAt(0) != '#') {
                    if (!sz.contains("#")) {
                        if (szS.length == 1) {
                            vertices = Integer.parseInt(sz);
                        }
                        original.add(sz);
                    }
                }
            }
        }
        usIn.close();
        addInfoToLists();

        //g = new Graph(vertices);
        g = new Graph(6);

        //for (int i = 0; i < toNode.size(); i++) {
        //  g.addEdge(toNode.get(i), fromNode.get(i), cost.get(i));
        // }
        g.addEdge(0, 1, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(0, 4, 3);
        g.addEdge(4, 3, 5);
        g.addEdge(3, 5, 1);

        g.displayMatrix();
        System.out.println("");
        DFS(g.adJ, 0,4);

    }

    static void DFS(int adjacency_matrix[][], int source,int destination) {
        
            int steps=0;
            int pop=0;
            Stack<Integer> stepStack = new Stack<>();
            Stack<Integer> stack = new Stack<>();
            int number_of_nodes = adjacency_matrix[source].length - 1;
            int visited[] = new int[number_of_nodes + 1];
            int element = source;
            int i = source;
            System.out.print(element + "\t");
            visited[source] = 1;
            stack.push(source);
            while (!stack.isEmpty()) {
                element = stack.peek();
                i = element;
                while (i <= number_of_nodes) {
                    if (adjacency_matrix[element][i] == 1 && visited[i] == 0) {
                        steps+=stepStack.push(g.costMatrix[element][i]);
                        stack.push(i);
                        visited[i] = 1;
                        //steps+=g.costMatrix[stack.peek()][i];
                        
                        element = i;
                       
                        i = 1;
                        System.out.print(element + "\t");
                        //System.out.println("\nPeeked: "+stack.peek());
                        
                        steps+=stepStack.pop();
                        //System.out.println("\nSteps:"+steps);
                        continue;
                    }
                    i++;
                }
              //pop= stack.pop();
               stack.pop();
              
                //steps+=g.costMatrix[stack.pop()][stack.peek()];
                /*System.out.println("\nPopped "+stack.pop());
                if(!stack.isEmpty())
                System.out.println("\nPeeked "+stack.peek());*/
                
              //  if(stack.size()>1){
                   // steps+=g.costMatrix[stack.pop()][stack.peek()];
                   // break;
              //  }
                
            }
            System.out.println("\nTOTAL STEPS: "+steps);
        
    }

    static void addInfoToLists() {
        ArrayList<String> elements = new ArrayList<String>();
        for (int i = 0; i < original.size(); i++) {
            String split[] = original.get(i).split("\\s+");

            for (String line : split) {
                elements.add(line);
            }
        }
        //vertices = Integer.parseInt(original.get(0));
        elements.remove(0);
        original.remove(0);
        for (int i = 0; i < elements.size(); i += 3) {
            fromNode.add(Integer.parseInt(elements.get(i)));
        }

        for (int i = 1; i < elements.size(); i += 3) {
            toNode.add(Integer.parseInt(elements.get(i)));
        }

        for (int i = 2; i < elements.size(); i += 3) {
            cost.add(Integer.parseInt(elements.get(i)));
        }

    }

}
