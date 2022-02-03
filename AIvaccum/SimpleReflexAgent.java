public class SimpleReflexAgent {

	public static void main(String[] args) 
	{
		Environment environment;
		
		//	Process command-line arguments
		if (args.length < 1)
		{
			System.out.println("Usage: java SimpleReflexAgent start-location roomA-status roomB-status");
			return;
		}
		else
		{
			int agentLocation = Integer.parseInt(args[0]);
			boolean rooms[] = new boolean[2];
			rooms[0] = Boolean.parseBoolean(args[1]);
			rooms[1] = Boolean.parseBoolean(args[2]); 
			
			environment = new Environment(agentLocation, rooms);
		}
		
		System.out.println("Agent Started...");
	

		
		for(int i=0; i<5; i++)
		{
			System.out.println("Environment Status:");
			System.out.println("\tAgent Location: " + environment.getAgentLocation());
			
			for (int locationIndex = 0; locationIndex < 2; locationIndex++)
			{
				System.out.print("\tLocation " + locationIndex + ": ");
				
				if (environment.getRooms()[locationIndex])
					System.out.println("Dirty");
				else
					System.out.println("Clean");
			}
			
			System.out.println();
			
			
			Percept percept = perceiveEnvironment(environment);
			
			AgentAction action = reflexVacuumAgent(percept);
			
			performAction(action, environment);
		}
	}

	
	private static Percept perceiveEnvironment(Environment environment)
	{
		//	TODO: Implement the code to examine the environment 
		//		  and returns a percept that the agent can reason about.
	}
	
	
	private static void performAction(AgentAction action, Environment environment)
	{
		
		// TODO: This method implements the agent's actuators, 
		//		 i.e., it performs a given action in the environment.
		
		//	HINT: Use a switch-case statement that takes advantage of the enum
		switch (action) {
			case Clean:
				
				System.out.println("Action: Clean");
				break;
			case MoveRight:
				
				System.out.println("Action: Move Right");
				break;
			case MoveLeft:
				
				System.out.println("Action: Move Left");
				break;
			case NoOp:
				
				break;
		}
		
		
		//	REQUIREMENT: Use the following messages, exactly, as needed:
		//				"Action: Clean"
		//				"Action: Move Left"
		//				"Action: Move Right"	
		
	}
	
	
	private static AgentAction reflexVacuumAgent(Percept percept)
	{
		//	TODO: Implements a Simple Reflex Agent 
		//		  for the Vacuum World scenario
		
		//	HINT: Refer to the slides
	}

}