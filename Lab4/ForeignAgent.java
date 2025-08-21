import java.util.ArrayList;
import java.util.List;

public class ForeignAgent {
    private String networkAddress;
    private List<MobileAgent> registeredAgents; // List of Mobile Agents currently on this foreign network

    public ForeignAgent(String networkAddress) {
        this.networkAddress = networkAddress;
        this.registeredAgents = new ArrayList<>();
        System.out.println("ForeignAgent created at network: " + networkAddress);
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    /**
     * Registers a Mobile Agent when it enters this foreign network.
     * @param agent The MobileAgent entering the network.
     */
    public void registerMobileAgent(MobileAgent agent) {
        if (!registeredAgents.contains(agent)) {
            registeredAgents.add(agent);
            System.out.println("ForeignAgent (" + networkAddress + ") registered MobileAgent: " + agent.getHomeAddress());
        }
    }

    /**
     * Simulates the Foreign Agent delivering a tunneled packet to the Mobile Agent.
     * @param mobileAgent The MobileAgent instance to deliver to.
     * @param packetContent The content of the packet.
     */
    public void deliverPacketToMobileAgent(MobileAgent mobileAgent, String packetContent) {
        if (registeredAgents.contains(mobileAgent)) {
            System.out.println("ForeignAgent (" + networkAddress + ") delivering tunneled packet to MobileAgent: " + mobileAgent.getHomeAddress());
            mobileAgent.receivePacket(packetContent);
        } else {
            System.out.println("ForeignAgent (" + networkAddress + "): MobileAgent " + mobileAgent.getHomeAddress() + " not registered here.");
        }
    }
}