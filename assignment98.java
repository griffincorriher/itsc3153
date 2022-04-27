package assignment98;
import java.util.*;

public class assignment98 {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		assignment98 a = new assignment98();
		
		int[][] grid = generateGrid();

		// Enter start coordinates
		System.out.println("Enter starting x value");
		int startX = s.nextInt();
		s.nextLine();
		System.out.println("Enter starting y value");
		int startY = s.nextInt();
		s.nextLine();
		Position start = new Position(startX, startY);
		
		// Enter goal coordinates
		System.out.println("Enter goal x value");
		int goalX = s.nextInt();
		s.nextLine();
		System.out.println("Enter goal y value");
		int goalY = s.nextInt();
		Position goal = new Position(goalX,goalY);
		
		s.nextLine();
		s.close();

		// Perform a* search
		a.aStar(grid, start, goal);
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
		
	
	public void aStar(int[][] grid, Position start, Position goal) {
		
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
		ArrayList<Node> frontier = new ArrayList<Node>();
		ArrayList<Node> visited = new ArrayList<Node>();		
		
		// Add start node
		frontier.add(startNode);
		
		// Perform search
		
		// Loop through frontier
		while(frontier.size() > 0) {
			
			// Get current node
			Node currentNode = frontier.get(0);
			int currentIndex = 0;			
			for(Node node : frontier) {
//				System.out.println("Current node f: " + currentNode.f);
//				System.out.println("Current node position: " + currentNode.getNodePosition().toWord());
//				System.out.println("Frontier node f: " + node.f);
//				System.out.println("Frontier node position: " + node.getNodePosition().toWord());


				if(node.f < currentNode.f) {
					currentNode = node;
					currentIndex = frontier.indexOf(node);
				}
			}
///			System.out.println("Current node: " + currentNode);
///            System.out.println(currentNode.toWord());

			// Move current node from frontier to visited
			frontier.remove(currentIndex);
			visited.add(currentNode);
			
//			System.out.println("Current Node: " + currentNode + "\n" + "Goal node: " + goalNode);
//			System.out.println(currentNode.toWord() + "\n");
			
//			System.out.println("current node position: " + currentNode.getNodePosition());
//			System.out.println("current node position: " + goalNode.getNodePosition());		
			
			// If goal is found, print the path	

//			System.out.println(currentNode.toWord());

			if(currentNode.getNodePosition().toWord().equals(goalNode.getNodePosition().toWord())) {
				Node[] finalPath = null;
				System.out.println("Path found!");
		    	System.out.println("Start node: " + startNode);
				ArrayList<Node> node = new ArrayList<>();
				Node current = currentNode;
				while(!current.isEmpty()) {
					node.add(current);
					current = current.parent;
				Collections.reverse(node);
				Object[] objPath = node.toArray();
				finalPath = new Node[node.size()];
				for(int i = 0; i < objPath.length; i++) {
					finalPath[i] = (Node)objPath[i];
//					System.out.println(finalPath[i]);
					}
				}	
			    for(Node p: finalPath) {
			    	
//			    	System.out.println(p);
			    	System.out.print(p.getNodePosition().toWord() + "->");
//			    	System.out.println(p.toWord());
//			    	System.out.println("Node parent: " + p.parent.toWord());
			    }
			    break;
			}
		ArrayList<Node> children = new ArrayList<>();	
		adjacentCell location[] = new adjacentCell[4];
		location[0] = new adjacentCell(0,-1);
		location[1] = new adjacentCell(0,1);
		location[2] = new adjacentCell(-1,0);
		location[3] = new adjacentCell(1,0);
		for(adjacentCell p : location) {
			Node p1 = new Node(currentNode, currentNode.getNodePosition());
			Position newPosition = new Position(currentNode.getNodePositionx()+ p.getxValue(), currentNode.getNodePositiony() + p.getyValue());
			p1.setNodePosition(newPosition);
            if(p1.getNodePositionx() > (grid.length - 1) ||
               p1.getNodePositionx() < 0 ||
               p1.getNodePositiony() > grid.length - 1 ||
               p1.getNodePositiony() < 0) {
            	continue;
            }
            
            if(grid[p1.getNodePositionx()][p1.getNodePositiony()] != 0){
            	continue;
            	}
            
            Node newNode = new Node(currentNode, newPosition);
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
	         for(Node n : frontier) {
	        	 if(c1 == n && c1.g > n.g) {
	        		 continue;
	        	 	}
	         	}
	         
	         frontier.add(c1);
			}
		}
		
	}
	
	public static int[][] generateGrid(){
		int[][] grid = new int[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				grid[i][j] = 0;
			}
		}
		for( int[] i: grid) {
			System.out.println(Arrays.toString(i));
		}		
		return grid;
	}
}
