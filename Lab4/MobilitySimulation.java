public class MobilitySimulation {
    public static void main(String[] args) {
        System.out.println("--- Starting Mobility Management Simulation ---");

        // 1. Initialization
        // Create a Mobile Agent with a home address
        MobileAgent studentMobileAgent = new MobileAgent("192.168.1.100");

        // Create a Home Agent for the mobile agent's home network
        HomeAgent universityHomeAgent = new HomeAgent();

        // Create a Foreign Agent representing a different network (e.g., a cafe)
        ForeignAgent cafeForeignAgent = new ForeignAgent("10.0.0.1");

        // --- Scenario 1: Mobile Agent is on its Home Network ---
        System.out.println("\n--- Scenario 1: Mobile Agent at Home ---");
        System.out.println("MobileAgent's current location: " + studentMobileAgent.getCurrentAddress());

        // Simulate a packet destined for the Mobile Agent's home address
        // HA will deliver directly as MN is at home (binding table empty or shows home address)
        universityHomeAgent.forwardPacket(studentMobileAgent.getHomeAddress(), "Hello from Home Network!", cafeForeignAgent, studentMobileAgent);

        // --- Scenario 2: Mobile Agent Moves to Foreign Network ---
        System.out.println("\n--- Scenario 2: Mobile Agent Moves Away ---");
        studentMobileAgent.moveToForeignNetwork(cafeForeignAgent);

        // Mobile Agent sends a binding update to its Home Agent
        studentMobileAgent.sendBindingUpdate(universityHomeAgent);

        // Simulate a packet destined for the Mobile Agent's home address again
        // This time, HA should tunnel it to the Foreign Agent (CoA)
        universityHomeAgent.forwardPacket(studentMobileAgent.getHomeAddress(), "Important Lecture Update!", cafeForeignAgent, studentMobileAgent);

        // --- Scenario 3: Another Packet while still away ---
        System.out.println("\n--- Scenario 3: Another Packet while Away ---");
        universityHomeAgent.forwardPacket(studentMobileAgent.getHomeAddress(), "Assignment Due Soon Reminder!", cafeForeignAgent, studentMobileAgent);

        System.out.println("\n--- Mobility Management Simulation Completed ---");
    }
}