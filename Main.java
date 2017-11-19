
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
    static int steps = 0;
    static int smallestNode;
    static ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
    static ArrayList<Integer> fromNodeSort = new ArrayList<Integer>();
    static ArrayList<Integer> toNodeSort = new ArrayList<Integer>();

    public static void main(String args[]) throws FileNotFoundException {

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

        Collections.sort(fromNodeSort);
        Collections.sort(toNodeSort);
        if (toNodeSort.get(0) < fromNodeSort.get(0)) {
            smallestNode = toNodeSort.get(0);
        } else {
            smallestNode = fromNodeSort.get(0);
        }

        //g = new Graph(vertices);
        g = new Graph(vertices);

        for (int i = 0; i < toNode.size(); i++) {
            g.addEdge(fromNode.get(i), toNode.get(i), cost.get(i));
        }

        long start = System.nanoTime();
        if (foundNode()) {
            long end = System.nanoTime();
            String output = "";
            for (int i = 0; i < visitedNodes.size(); i++) {
                if (i != visitedNodes.size() - 1) {
                    output += visitedNodes.get(i) + ", ";
                } else {
                    output += "and " + visitedNodes.get(i);
                }
            }

            System.out.println("Me smash adventurers! Me find them after searching rooms " + output + "!\n"
                    + "Me have Strength +8 but still me non-violently usher puny invaders out of me home! Me pacifist! Me only take " + steps + " steps! Moo!\n"
                    + "\nMe find adventurers in " + (end - start) / 1000000.0 + " milliseconds! Me the fastest Minotaur alive!");
        } else {
            long end = System.nanoTime();
            String output = "";
            for (int i = 0; i < visitedNodes.size(); i++) {
                if (i != visitedNodes.size() - 1) {
                    output += visitedNodes.get(i) + ", ";
                } else {
                    output += "and " + visitedNodes.get(i);
                }
            }
            System.out.println("Me no find adventurers. Me bring shame to me family.\nMe searched rooms " + output + ". Me took " + steps + " steps.\n\nMe walked around labyrinth for "
                    + (end - start) / 1000000.0 + " milliseconds but me no find adventurers.");
        }

    }

    public static boolean foundNode() {
        return foundNode(fromNode.get(0), 13);
    }

    private static boolean foundNode(int source, int destination) {
        Stack<Integer> stepStack = new Stack<Integer>();
        Stack<Integer> nodeStack = new Stack<Integer>();
        int visited[] = new int[vertices + 1];
        int nodeNumber = source;
        int i = source;
        visitedNodes.add(i);

        visited[source] = 1;
        nodeStack.push(source);
        OUTER:
        while (!nodeStack.isEmpty()) {
            nodeNumber = nodeStack.peek();
            i = nodeNumber;
            if (source == 0) {
                while (i > smallestNode - 1) {//ERROR IF START AT ZERO. ELSE WORKS GOOD. IF REMOVE EQUALS SIGN, THEN ZERO WILL NOT BE PRINTED IF NOT STARTING AT ZERO
                    if (g.isConnected(nodeNumber, i) && visited[i] == 0) {
                        steps += stepStack.push(g.getCostMatrix(nodeNumber, i));
                        nodeStack.push(i);

                        visited[i] = 1;
                        nodeNumber = i;
                        smallestNode = 1;

                        visitedNodes.add(nodeNumber);
                        if (nodeNumber == destination) {
                            return true;
                        }
                    }
                    i--;
                }
            } else {
                while (i >= smallestNode - 1) {//ERROR IF START AT ZERO. ELSE WORKS GOOD. IF REMOVE EQUALS SIGN, THEN ZERO WILL NOT BE PRINTED IF NOT STARTING AT ZERO
                    if (g.isConnected(nodeNumber, i) && visited[i] == 0) {
                        steps += stepStack.push(g.getCostMatrix(nodeNumber, i));
                        nodeStack.push(i);

                        visited[i] = 1;
                        nodeNumber = i;
                        smallestNode = 1;

                        visitedNodes.add(nodeNumber);
                        if (nodeNumber == destination) {
                            return true;
                        }
                    }
                    i--;
                }
            }

            nodeNumber = nodeStack.peek();
            i = nodeNumber;

            while (i <= vertices - 1) {
                if (g.isConnected(nodeNumber, i) && visited[i] == 0) {
                    steps += stepStack.push(g.getCostMatrix(nodeNumber, i));
                    nodeStack.push(i);

                    visited[i] = 1;
                    nodeNumber = i;
                    i = 1;
                    visitedNodes.add(nodeNumber);
                    if (nodeNumber == destination) {
                        return true;
                    }
                }
                i++;
            }
            nodeStack.pop();
            if (!stepStack.isEmpty()) {
                steps += stepStack.pop();
            }
        }
        return false;

    }

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
