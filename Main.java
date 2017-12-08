import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author
 */
public class Main {

    static ArrayList<String> original = new ArrayList<String>();
    static ArrayList<Integer> fromNode = new ArrayList<Integer>();
    static ArrayList<Integer> toNode = new ArrayList<Integer>();
    static ArrayList<Integer> cost = new ArrayList<Integer>();
    static int vertices;
    static Graph g;
    static int steps = 0;
    static int smallestNode;
    static ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
    static ArrayList<Integer> fromNodeSort = new ArrayList<Integer>();
    static ArrayList<Integer> toNodeSort = new ArrayList<Integer>();

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        Scanner usIn = new Scanner(new File("lab.txt"));

        while (usIn.hasNext()) {
            String line = usIn.nextLine();

            String splitLine[] = line.split(" ");

            if (!line.equals("")) {
                if (line.charAt(0) != '#') {
                    if (!line.contains("#")) {
                        if (splitLine.length == 1) {
                            vertices = Integer.parseInt(line);//sets # of vertices to int found in first line of text file
                        }
                        original.add(line);
                    }
                }
            }
        }
        usIn.close();//Close scanner object
        addInfoToLists();

        /**
         * Finds the smallest node by sorting the two lists and checks their
         * elements at 0
         */
        Collections.sort(fromNodeSort);
        Collections.sort(toNodeSort);
        if (toNodeSort.get(0) < fromNodeSort.get(0)) {
            smallestNode = toNodeSort.get(0);
        } else {
            smallestNode = fromNodeSort.get(0);
        }

        /**
         * Create new graph object and create edges based off information stored
         * in lists from text file
         */
        g = new Graph(vertices);
        for (int i = 0; i < toNode.size(); i++) {
            g.addEdge(fromNode.get(i), toNode.get(i), cost.get(i));
        }

        long start = System.nanoTime();//for time result
        if (foundNode()) {
            /**
             * String building if node 13 is found
             */
            String room = "";
            long end = System.nanoTime();//for time result
            String output = "";
            for (int i = 0; i < visitedNodes.size(); i++) {
                if (i != visitedNodes.size() - 1) {
                    room = "rooms";
                    output += visitedNodes.get(i) + ", ";
                } else if (visitedNodes.size() == 1) {
                    room = "room";
                    output += visitedNodes.get(i);
                } else {
                    room = "rooms";
                    output += "and " + visitedNodes.get(i);
                }
            }

            System.out.println("\nMe smash adventurers! Me find them after searching " + room + " " + output + "!\n"
                    + "Me have Strength +8 but still me non-violently usher puny invaders out of me home! Me pacifist! Me only take " + steps + " steps! Moo!\n"
                    + "\nMe find adventurers in " + (end - start) / 1000000.0 + " milliseconds! Me the fastest Minotaur alive!");
        } else {
            /**
             * String building if node 13 is not found
             */
            String room = "";
            long end = System.nanoTime();//for time result
            String output = "";
            for (int i = 0; i < visitedNodes.size(); i++) {
                if (i != visitedNodes.size() - 1) {
                    room = "rooms";
                    output += visitedNodes.get(i) + ", ";
                } else if (visitedNodes.size() == 1) {
                    room = "room";
                    output += visitedNodes.get(i);
                } else {
                    room = "rooms";
                    output += "and " + visitedNodes.get(i);
                }

            }
            System.out.println("\nMoo! Me no find adventurers! Me bring shame to me family...\nMe searched  " + room + " " + output
                    + ". Me took " + steps + " steps.\n\nMe walked around labyrinth for "
                    + (end - start) / 1000000.0 + " milliseconds, but me no find adventurers.");
        }

    }

    public static boolean foundNode() {
        return foundNode(fromNode.get(0), 13);

    }

    /**
     *
     * @param source: Starting node(first node available in the text file)
     * @param destination: Ending node (node 13 in this instance)
     * @return returns true if the destination node is found, false if not
     */
    private static boolean foundNode(int source, int destination) {
        Stack<Integer> stepStack = new Stack<Integer>();//keeps track of the steps taken
        Stack<Integer> nodeStack = new Stack<Integer>();//pushes nodes when they are visited, and pops when a node is a "dead end"
        int visited[] = new int[vertices + 1];
        int nodeNumber = source;
        int i = source;
        visitedNodes.add(i);//adds source to list of visited nodes
        visited[source] = 1;
        nodeStack.push(source);

        if (source == destination) {
            return true;//return true if source IS the destination (node 13)
        }

        while (!nodeStack.isEmpty()) {
            nodeNumber = nodeStack.peek();//last value pushed to nodeStack
            i = nodeNumber;

            while (i >= smallestNode) {//this loop covers all values LESS than the source node (used if source is something other than 0)
                if (g.isConnected(nodeNumber, i) && visited[i] == 0) {//if an edge eists and NOT visited yet
                    steps += stepStack.push(g.getCostMatrixValueAt(nodeNumber, i));//push cost of edge
                    nodeStack.push(i);//push current node number

                    visited[i] = 1;//sets visited to 1 (where 1 means TRUE)
                    nodeNumber = i;

                    i = source;//resets i back to source node number
                    visitedNodes.add(nodeNumber);//add nodeNumber to visited list

                    if (nodeNumber == destination) {
                        return true;//return true if destination (node 13) is found
                    }
                }
                i--;

            }

            nodeNumber = nodeStack.peek();//last value pushed to nodeStack
            i = nodeNumber;

            while (i < vertices) {//this loop covers all values GREATER than the source node
                if (g.isConnected(nodeNumber, i) && visited[i] == 0) {//if an edge eists and NOT visited yet
                    steps += stepStack.push(g.getCostMatrixValueAt(nodeNumber, i));//push cost of edge
                    nodeStack.push(i);//push current node number

                    visited[i] = 1;//sets visited to 1 (where 1 means TRUE)

                    nodeNumber = i;

                    i = source;//resets i back to source node number
                    visitedNodes.add(nodeNumber);//add nodeNumber to visited list

                    if (nodeNumber == destination) {
                        return true;//return true if destination (node 13) is found
                    }
                }
                i++;

            }

            nodeStack.pop();//pops node number when no other edge is available
            if (!stepStack.isEmpty()) {
                steps += stepStack.pop();//pops step count when no other edge is available (minotaur is retracing steps)
            }
        }
        return false;//return false if destination (node 13) is not found

    }

    /**
     * Adds information from the text file to lists in order to
     * use/manipulate//determine the cost, number of vertices, smallest node,
     * etc...
     */
    static void addInfoToLists() {
        ArrayList<String> elements = new ArrayList<String>();
        for (int i = 0; i < original.size(); i++) {
            String split[] = original.get(i).split("\\s+");

            for (String line : split) {
                elements.add(line);
            }
        }
        vertices = Integer.parseInt(original.get(0));
        elements.remove(0);
        original.remove(0);
        for (int i = 0; i < elements.size(); i += 3) {
            fromNode.add(Integer.parseInt(elements.get(i)));
            fromNodeSort.add(Integer.parseInt(elements.get(i)));
        }

        for (int i = 1; i < elements.size(); i += 3) {
            toNode.add(Integer.parseInt(elements.get(i)));
            toNodeSort.add(Integer.parseInt(elements.get(i)));
        }

        for (int i = 2; i < elements.size(); i += 3) {
            cost.add(Integer.parseInt(elements.get(i)));
        }

    }

}
