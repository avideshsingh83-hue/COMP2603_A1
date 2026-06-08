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

    public int dispatchAll() {
        for(Container c: activeContainers){
            dispatchedContainers.add(c);
        }
        activeContainers.clear();
        return dispatchedContainers.size();
    }

    public double getTotalRevenue() {
        double sum = 0.0;
        for(Container c: dispatchedContainers){
            sum = sum + c.getTotalRevenue();
        }
        return sum;
    }

    public int getTotalPackagesShipped() {
        int pkgCount = 0;
        for(Container c: dispatchedContainers){
            pkgCount = pkgCount + c.getPackageCount();
        }
        return pkgCount;
    }

    public Package findPackage(String trackingId) {
        for(Package pkg: pendingPackages){
            if(pkg.getTrackingId().equals(trackingId)){
                return pkg;
            }
        }
        for(Container c: activeContainers){
            for(Package pkg: c.getPackages()){
                if(pkg.getTrackingId().equals(trackingId)){
                    return pkg;
                }
            }
        }
        for(Container c: dispatchedContainers){
            for(Package pkg: c.getPackages()){
                if(pkg.getTrackingId().equals(trackingId)){
                    return pkg;
                }
            }
        }
        return null;
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
