public class Environment {
	
	private int agentLocation;
	private boolean rooms[];
	
	public int getAgentLocation() {
		return agentLocation;
	}
	
	public void setAgentLocation(int agentLocation) {
		this.agentLocation = agentLocation;
	}
	
	public boolean[] getRooms() {
		return rooms;
	}
	
	public void setRooms(boolean rooms[]) {
		this.rooms = rooms;
	}
	
	public Environment(int agentLocation, boolean[] rooms){
		this.agentLocation = agentLocation;
		this.rooms = rooms;
	}
}