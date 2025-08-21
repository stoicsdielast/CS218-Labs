import java.util.HashMap;
import java.util.Map;

public class HomeAgent {
    private Map<String, String> bindingTable; // Maps MobileNode's home address to its Care-of Address

    public HomeAgent() {
        this.bindingTable = new HashMap<>();
        System.out.println("HomeAgent initialized.");
    }

    /**
     * Receives and processes a binding update from a Mobile Agent.
     * @param mobileNodeHomeAddress The home address of the Mobile Agent.
     * @param newCareOfAddress The new care-of address of the Mobile Agent.
     */
    public void receiveBindingUpdate(String mobileNodeHomeAddress, String newCareOfAddress) {
        bindingTable.put(mobileNodeHomeAddress, newCareOfAddress);
        System.out.println("HomeAgent: Binding update received for " + mobileNodeHomeAddress + ". New CoA: " + newCareOfAddress);
    }

    /**
     * Simulates forwarding a packet destined for a Mobile Agent.
     * If the Mobile Agent is away, it tunnels the packet to its CoA.
     * @param destinationHomeAddress The original home address of the intended recipient.
     * @param packetContent The content of the packet to be forwarded.
     * @param foreignAgent The ForeignAgent to which the packet should be sent if the MN is away.
     * @param mobileAgent The actual MobileAgent instance to simulate direct delivery if at home.
     */
    public void forwardPacket(String destinationHomeAddress, String packetContent, ForeignAgent foreignAgent, MobileAgent mobileAgent) {
        System.out.println("\nHomeAgent: Receiving packet for " + destinationHomeAddress);
        if (bindingTable.containsKey(destinationHomeAddress)) {
            String careOfAddress = bindingTable.get(destinationHomeAddress);
            if (careOfAddress.equals(destinationHomeAddress)) {
                // Mobile Agent is on its home network
                System.out.println("HomeAgent: MobileAgent (" + destinationHomeAddress + ") is at home. Delivering packet directly.");
                mobileAgent.receivePacket(packetContent);
            } else {
                // Mobile Agent is on a foreign network, tunnel to CoA
                System.out.println("HomeAgent: MobileAgent (" + destinationHomeAddress + ") is away. Tunneling packet to CoA: " + careOfAddress);
                foreignAgent.deliverPacketToMobileAgent(mobileAgent, packetContent); // FA helps deliver
            }
        } else {
            System.out.println("HomeAgent: No binding entry for " + destinationHomeAddress + ". Assuming at home network or not registered yet.");
            // For simplicity, assume if no binding, it's at home or a default direct delivery
            mobileAgent.receivePacket(packetContent);
        }
    }
}