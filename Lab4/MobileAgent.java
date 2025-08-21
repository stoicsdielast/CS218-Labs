public class MobileAgent {
    private String homeAddress;
    private String currentAddress; // Care-of Address (CoA)
    private boolean isHomeNetwork;

    public MobileAgent(String homeAddress) {
        this.homeAddress = homeAddress;
        this.currentAddress = homeAddress; // Starts on its home network
        this.isHomeNetwork = true;
        System.out.println("MobileAgent created at Home Network: " + homeAddress);
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public boolean isOnHomeNetwork() {
        return isHomeNetwork;
    }

    /**
     * Simulates the Mobile Agent moving to a foreign network.
     * @param foreignAgent The ForeignAgent representing the new network.
     */
    public void moveToForeignNetwork(ForeignAgent foreignAgent) {
        System.out.println("\nMobileAgent (" + homeAddress + ") is leaving home network and moving to " + foreignAgent.getNetworkAddress());
        this.currentAddress = foreignAgent.getNetworkAddress(); // CoA is the FA's address for simplicity
        this.isHomeNetwork = false;
        foreignAgent.registerMobileAgent(this); // FA becomes aware of the MN
        System.out.println("MobileAgent (" + homeAddress + ") arrived at Foreign Network. New Care-of Address: " + currentAddress);
    }

    /**
     * Sends a binding update to the Home Agent to register its new CoA.
     * @param homeAgent The HomeAgent instance.
     */ 
    public void sendBindingUpdate(HomeAgent homeAgent) {
        if (!isHomeNetwork) {
            System.out.println("MobileAgent (" + homeAddress + ") sending binding update to HomeAgent with new CoA: " + currentAddress);
            homeAgent.receiveBindingUpdate(this.homeAddress, this.currentAddress);
        } else {
            System.out.println("MobileAgent (" + homeAddress + ") is on home network, no binding update needed.");
        }
    }

    /**
     * Simulates the Mobile Agent receiving a packet.
     * @param packet The content of the packet.
     */
    public void receivePacket(String packet) {
        System.out.println("MobileAgent (" + homeAddress + ") received packet: '" + packet + "' at " + currentAddress);
    }
}