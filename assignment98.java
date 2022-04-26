package assignment98;
import java.util.*;

public class assignment98 {
    public static void main(String[] args){

        int[][] grid = new int[15][15];
        Position start = new Position(0,0);
        Position goal = new Position(1,1);

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
    	
    	public int getX() {
    		return x;
    	}
    	public int getY() {
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
        public Position getPosition() {
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
        public void setPosition(Position position) {
            this.position = position;
        }

        public boolean isEmpty() {
            return parent == null && position == null;
        }

    }
public static int[] aStar(int[][] grid, Position start, Position goal) {
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
