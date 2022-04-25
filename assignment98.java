package assignment98;
import java.nio.file.Path;
import java.util.*;

public class assignment98 {
	public static void main(String[] args){

		int[][] grid = new int[15][15];
		int[] startNode = {0,0};
		int[] goalNode = {1,1};
		
		int[] path = aStar(grid, startNode, goalNode);
		System.out.println(path);
	}
   
	static class Node{
		int f = 0;
		int g = 0;
		int h = 0;
		
		Node parent;
		int[] position;
		
		Node(Node parent, int[] position){
			this.parent = parent;
			this.position = position;
		}
		
		public int getF() {
			return f;
		}
		public int getG() {
			return g;
		}
		public int getH() {
			return h;
		}
		public Node getParent() {
			return parent;
		}
		public int[] getPosition() {
			return position;
		}
		
		public void setF(int f) {
			this.f = f;
		}
		public void setG(int g) {
			this.g = g;
		}
		public void setH(int h) {
			this.h = h;
		}
		public void setParent(Node parent) {
			this.parent = parent;
		}
		public void setPosition(int[] position) {
			this.position = position;
		}
		
		public boolean isEmpty() {
			return parent == null && position == null;
		}
		
	}
	
	public static int[] aStar(int[][] grid, int[] start, int[] goal) {
		// Instantiate start and goal nodes
		Node startNode = new Node(null, start);
		startNode.setF(0);
		startNode.setG(0);
		startNode.setH(0);
		
		Node goalNode = new Node(null, goal);
		goalNode.setF(0);
		goalNode.setG(0);
		goalNode.setH(0);

		// Instantiate empty lists
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();		
		
		// Add start node
		openList.add(startNode);
		
		// Perform search
		int[] finalPath = null;
		while(openList.size() > 0) {
			Node currentNode = openList.get(0);
			int currentIndex = 0;
			for(Node node : openList) {
				if(node.f < currentNode.f){
					currentNode = node;
					currentIndex = openList.indexOf(node);
				}
			}
			
			// Move current node from open list to closed list
			openList.remove(currentIndex);
			closedList.add(currentNode);
			
			// If goal is found
			if(currentNode == goalNode) {
				ArrayList<int[]> path = new ArrayList<>();
				Node current = currentNode;
				while(!current.isEmpty()) {
					path.add(current.position);
					current = current.parent;
				Collections.reverse(path);
				Object[] objPath = path.toArray();
				finalPath = new int[path.size()];
				for(int i = 0; i < objPath.length; i++) {
					finalPath[i] = (int)objPath[i];
					}
				}			
			}
		}
		return finalPath;			
	}
}
	// A* (star) Pathfinding
	// Initialize both open and closed list

	//let the openList equal empty list of nodes
	//let the closedList equal empty list of nodes
	// Add the start node
	//put the startNode on the openList (leave it's f at zero)
	// Loop until you find the end
	//while the openList is not empty
	    // Get the current node
	    //let the currentNode equal the node with the least f value
	    //remove the currentNode from the openList
	    //add the currentNode to the closedList
	    // Found the goal
	    //if currentNode is the goal
	        // The goal is found
	    // Generate children
	    //let the children of the currentNode equal the adjacent nodes
	    
	    //for each child in the children
	        // Child is on the closedList
	        //if child is in the closedList
	            //continue to beginning of for loop
	        // Create the f, g, and h values
	        //child.g = currentNode.g + distance between child and current
	        //child.h = distance from child to end
	        //child.f = child.g + child.h
	        // Child is already in openList
	        //if child.position is in the openList's nodes positions
	            //if the child.g is higher than the openList node's g
	                //continue to beginning of for loop
	        // Add the child to the openList
	        //add the child to the openList	

