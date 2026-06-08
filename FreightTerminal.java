import java.util.ArrayList;

/**
 * The hub that receives packages, packs them into containers,
 * dispatches containers, and produces financial reports.
 */
public class FreightTerminal {
    private String terminalName;
    private ArrayList<Package> pendingPackages;
    private ArrayList<Container> activeContainers;
    private ArrayList<Container> dispatchedContainers;

    public FreightTerminal(String terminalName) {
        this.terminalName = terminalName;

        this.pendingPackages = new ArrayList<Package>();
        this.activeContainers = new ArrayList<Container>();
        this.dispatchedContainers = new ArrayList<Container>();
    }

    public void receivePackage(Package p) {
        if(p != null){
            pendingPackages.add(p);
        }
    }

    public int getPendingCount() {
        return pendingPackages.size();
    }

    public int packContainers() {
        ArrayList<String> destinations = new ArrayList<String>();
        for(Package pkg: pendingPackages){
            if(!destinations.contains(pkg.getDestination())){
                destinations.add(pkg.getDestination());
            }
        }

        for(String Destination: destinations){
            Container c = new Container(Destination);
            for(Package pkg: pendingPackages){
                if(pkg.getDestination().equals(Destination)){
                    c.addPackage(pkg);
                }
            }
            activeContainers.add(c);
        }

        pendingPackages.clear();
        return destinations.size();
    }

    /**
     * TODO M9: Move all activeContainers to dispatchedContainers.
     *   Clear activeContainers. Return the count dispatched.
     */
    public int dispatchAll() {
        return 0; // TODO M9
    }

    /**
     * TODO M9: Return the sum of getTotalRevenue() across all
     *   dispatched containers.
     */
    public double getTotalRevenue() {
        return 0.0; // TODO M9
    }

    /**
     * TODO M9: Return the sum of getPackageCount() across all
     *   dispatched containers.
     */
    public int getTotalPackagesShipped() {
        return 0; // TODO M9
    }

    /**
     * TODO M9: Search pending, active containers, and dispatched
     *   containers for a package with the given tracking ID.
     *   Return the Package or null if not found.
     */
    public Package findPackage(String trackingId) {
        return null; // TODO M9
    }

    /**
     * Returns the list of active containers (for printing manifests in Driver).
     */
    public ArrayList<Container> getActiveContainers() {
        return activeContainers;
    }

    /**
     * TODO M10: Print the formatted daily report.
     * Format:
     *   === Daily Report: Port of Spain Hub ===
     *   Packages received:  12
     *   Containers packed:  5
     *   Packages shipped:   12
     *   Total revenue:      $3248.50
     *
     *   Revenue by destination:
     *     Trinidad:    $199.50 (3 packages)
     *     Barbados:    $1403.00 (3 packages)
     *     ...
     */
    public void printDailyReport() {
        // TODO M10
    }
}
