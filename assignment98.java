package assignment98;
import java.util.*;

public class assignment98 {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		
		int[][] grid = generateGrid();
//		int[][] grid = new int[15][15];
		
		int startX = s.nextInt();
		s.nextLine();
		int startY = s.nextInt();
		s.nextLine();
		int goalX = s.nextInt();
		s.nextLine();
		int goalY = s.nextInt();
		s.nextLine();
		s.close();
		
		Position start = new Position(startX, startY);
		Position goal = new Position(goalX,goalY);
		
		Position[] path = aStar(grid, start, goal);
		System.out.println(path);
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
			return parent == null && position == null;
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
		
	
	public static Position[] aStar(int[][] grid, Position start, Position goal) {
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
		Position[] finalPath = null;
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
				ArrayList<Position> path = new ArrayList<>();
				Node current = currentNode;
				while(!current.isEmpty()) {
					path.add(current.getNodePosition());
					current = current.parent;
				Collections.reverse(path);
				Object[] objPath = path.toArray();
				finalPath = new Position[path.size()];
				for(int i = 0; i < objPath.length; i++) {
					finalPath[i] = (Position)objPath[i];
					}
				}	
				return finalPath;	
			}
		ArrayList<Node> children = new ArrayList<>();	
		adjacentCell location[] = new adjacentCell[4];
		location[0] = new adjacentCell(0,-1);
		location[1] = new adjacentCell(0,1);
		location[2] = new adjacentCell(-1,0);
		location[3] = new adjacentCell(1,0);
		
		for(adjacentCell p : location) {
			Node p1 = new Node(currentNode.parent, currentNode.getNodePosition());
			Position newPosition = new Position(currentNode.getNodePositionx() + p.getxValue(), currentNode.getNodePositiony() + p.getyValue());
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
            
            Node newNode = new Node(currentNode, currentNode.getNodePosition());
            children.add(newNode);
			}
		for(Node c1 : children) {
			for(Node c2 : closedList) {
				if(c1 == c2) {
					continue;
					}
				}
	         c1.g = currentNode.g + 1;
	         c1.h = Math.abs(c1.getNodePositionx() - goalNode.getNodePositionx()) + Math.abs(c1.getNodePositiony() - goalNode.getNodePositiony());
	         c1.f = c1.g + c1.h;
	         
	         for(Node o : openList) {
	        	 if(c1 == o && c1.g > o.g) {
	        		 continue;
	        	 	}
	         	}
	         openList.add(c1);
			}
		}
	}
	
	public static int[][] generateGrid(){
		int[][] grid = new int[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; i < 15; i++) {
				grid[i][j] = 0;
			}
		}
		return grid;
}
