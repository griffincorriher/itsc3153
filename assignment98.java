// Used these two resources to see how to implement a PriortiyQueue with comparator
// https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
// https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/

package assignment98;
import java.util.*;

public class assignment98 {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		boolean startSearch = true;

		
		while(startSearch){
			assignment98 a = new assignment98();
//			int[][] grid = generateGrid();
			
			
			  int[][] grid =   {{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,0, 1, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
								{0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
								{0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0},
								{0, 0, 0, 0, 0, 0, 0, 1, 0, 0,0, 0, 0, 0, 0}, 
								{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
								{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0}};
			 
			
			Position start = new Position(0,0);
			Position goal = new Position(0,0);
			// Get user input
			while(start.toWord().equals(goal.toWord())) {
				System.out.println("Select start location");
				start = userInput(grid, s);
				System.out.println("Select goal location");
				goal = userInput(grid, s);
				if(start.toWord().equals(goal.toWord())) {
					System.out.println("Start cannont be the same as goal!");
				}
			}

			// Perform A* search
			a.aStar(grid, start, goal);


			// Search again?
			String response = null;
			while (response == null || !response.equals("y") || !response.equals("n")) {
				System.out.print("Would you like to search again? (y or n) > ");
				response = s.nextLine().toLowerCase();
				System.out.println();
				switch(response) {
					case ("y"):
						startSearch = true;
						break;
					case ("n"):
						startSearch = false;
						break;
					default:
						System.out.println("Not a valid response");
						break;
				} 
				if(response.equals("y") || response.equals("n")) {
					break;
				}
			}
		}
	}
   
	static class Position{
		int x = 0;
		int y = 0;
		
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getx() {
			return x;
		}
		public int gety() {
			return y;
		}
		public String toWord() {
			String result = "(" + getx() + "," + gety() + ")";
			return result;
		}
		
	    @Override
	    public boolean equals(Object obj) 
	    {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        return true;
	    }
		
	}
		
	static class Node{

		int f = 0;
		int g = 0;
		int h = 0;
		
		Node parent;
		Position position;
		
		Node(Node parent, Position position){
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
		
		public Position getNodePosition() {
			return position;
		}
		public int getNodePositionx() {
			return position.x;
		}
		public int getNodePositiony() {
			return position.y;
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
		public void setNodePosition(Position position) {
			this.position = position;
		}
				
		public boolean isEmpty() {
			return parent == null;
		}
		
		public String toWord() {
			String result = "parent: " + getParent() + "\n" + "position: " + getNodePosition().toWord();
			return result;
		}		
	}
	

	public class nodeComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.f > o2.f) {
				return 1;
			}
			else if(o1.f < o2.f) {
				return -1;
			}
			return 0;
		}
	}
	
	
	class adjacentCell{
		private int xValue;
		private int yValue;

		public adjacentCell(int xValue, int yValue) {
			this.xValue = xValue;
			this.yValue = yValue;
		}
		
		public int getxValue() {
			return xValue;
		}
		public int getyValue() {
			return yValue;
		}	
	}
		
	
	public static Position userInput(int[][] grid, Scanner s) {
		boolean valid = false;
		Position position = null;
		while(!valid) {
			System.out.print("Enter x coordinate > ");
		    while(!s.hasNextInt()) {
		        System.out.println("Input is not a valid integer!");
				System.out.print("Enter x coordinate > ");
		        s.next();
		    }
			int x = s.nextInt();
			s.nextLine();
			System.out.print("Enter y coordinate > ");
		    while(!s.hasNextInt()) {
		        System.out.println("Input is not a valid integer!");
				System.out.print("Enter y coordinate > ");
		        s.next();
		    }
			int y = s.nextInt();
			s.nextLine();
			if(grid[y][x] != 1) {
				valid = true;
				position = new Position(x,y);
				}
			else {
				System.out.println("Cell is blocked, please choose again.");
			}
		}
		return position;
	}
	
	public void aStar(int[][] grid, Position start, Position goal) {
		System.out.println();
		System.out.println("Searching for a path...");
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
		PriorityQueue<Node> frontier = new PriorityQueue<>(5, new nodeComparator());
		ArrayList<Node> visited = new ArrayList<Node>();	
		
		// Add start node
		frontier.add(startNode);
				
		// Loop through frontier
		while(frontier.size() > 0) {
	
			// Get current node
			Node currentNode = frontier.remove();
			for(Node n : frontier) {
				if(n.f < currentNode.f) {
					currentNode = n;

				}
			}

			// Move current node from frontier to visited
			frontier.remove(currentNode);
			visited.add(currentNode);
			
			// If goal is found, print the path	
			if(currentNode.getNodePosition().toWord().equals(goalNode.getNodePosition().toWord())) {	    	
				Node[] finalPath = null;		    	
				ArrayList<Node> nodeList = new ArrayList<>();
				Node current = currentNode;
				while(!current.isEmpty()) {
					nodeList.add(current);
					current = current.parent;
					
				// Reverses path for printing
				Collections.reverse(nodeList);
				Object[] objPath = nodeList.toArray();
				finalPath = new Node[nodeList.size()/2];
				for(int i = 0; i < objPath.length/2; i++) {
					finalPath[i] = (Node)objPath[i];
					}
				}	
				
				// Prints path
			    updateGrid(grid, finalPath, startNode, goalNode);  
			    break;
			}
		ArrayList<Node> children = new ArrayList<>();	
		adjacentCell location[] = new adjacentCell[4];
		location[0] = new adjacentCell(0,-1);
		location[1] = new adjacentCell(0,1);
		location[2] = new adjacentCell(-1,0);
		location[3] = new adjacentCell(1,0);
		for(adjacentCell p : location) {
			Position newPosition = new Position(currentNode.getNodePositionx()+ p.getxValue(), currentNode.getNodePositiony() + p.getyValue());
			Node p1 = new Node(currentNode, newPosition);
            if(p1.getNodePositionx() > (grid.length - 1) ||
            	p1.getNodePositionx()  < 0 ||
            	p1.getNodePositiony()  > grid.length - 1 ||
            	p1.getNodePositiony()  < 0) {
            	continue;
            }
            
            if(grid[newPosition.gety()][newPosition.getx()] != 0){
            	continue;
            	}
            
            Node newNode = new Node(p1, newPosition);
            children.add(newNode);
			}
		for(Node c1 : children) {
			for(Node c2 : visited) {
				if(c1 == c2) {
					continue;
					}
				}
	         c1.g = currentNode.g + 1;
	         c1.h = Math.abs(c1.getNodePositionx() - goalNode.getNodePositionx()) + Math.abs(c1.getNodePositiony() - goalNode.getNodePositiony());
	         c1.f = c1.g + c1.h;
	         for(Node c3 : frontier) {
	        	 if(c1.equals(c3)){
	        		 if(c1.g > c3.g) {
	        			 continue;
	        		 }
	        	 }
	         }
	         frontier.add(c1);
			}
		System.out.print(frontier.size());
		}
	}

	public static int[][] generateGrid(){
		// Generate blank grid
		int[][] grid = new int[15][15];
				
		// Generate grid
		System.out.println("Grid (1 indicates blocked cell, 0 indicates free cell)");
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				grid[i][j] = 0;
			}
		}

		// Generate 10% of blocked cells
		int[] blockedCells = new int[(int)Math.ceil(15*15/10)+1];
		Arrays.fill(blockedCells, 1);
		Random r = new Random();
		for(int c = 0; c < blockedCells.length; c++) {
			int i = r.nextInt(15);
			int j = r.nextInt(15);
			if(grid[i][j]==1) {
				c--;
			}
			grid[i][j] = blockedCells[c];
		}
		for(int[] i: grid) {
			System.out.println(Arrays.toString(i));
		}		
		System.out.println();
		return grid;
	}
	
	private void updateGrid(int[][] grid, Node[] finalPath, Node startNode, Node goalNode) {
		System.out.println("Path found!");
		System.out.println();
		System.out.println("Updated grid with path");
		System.out.println("2: start node, 3: path, 4: goal node");
		
		// Update grid
		grid[startNode.getNodePositiony()][startNode.getNodePositionx()] = 2;
		grid[goalNode.getNodePositiony()][goalNode.getNodePositionx()] = 4;
		for(int[] r : grid) {
			System.out.println(Arrays.toString(r));
			}
		for(Node n : finalPath) {
			System.out.println("Next step...");
			grid[n.getNodePositiony()][n.getNodePositionx()] = 3;
			grid[goalNode.getNodePositiony()][goalNode.getNodePositionx()] = 4;
			for(int[] r : grid) {
				System.out.println(Arrays.toString(r));
			}
			System.out.println();
		}		
	    System.out.println();
	    System.out.println("Final path taken from " + startNode.getNodePosition().toWord() + " to " + goalNode.getNodePosition().toWord());
    	System.out.print(startNode.getNodePosition().toWord());
	    for(Node p: finalPath) {
	    	System.out.print("->" + p.getNodePosition().toWord());
	    }
    	System.out.println();
    	System.out.println();
	}	
}
