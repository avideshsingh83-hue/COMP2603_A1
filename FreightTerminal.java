import java.util.ArrayList;


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
                if(pkg.getDestination().equals(Destination) && pkg.isExpress()){
                    c.addPackage(pkg);
                }
            }
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

    public Package cancelPackage(String trackingId){
        for(Package pkg: pendingPackages){
            if(pkg.getTrackingId().equals(trackingId)){
                pendingPackages.remove(pkg);
                return pkg;
            }
        }
        return null;
    }

    public String getMostProfitableDestination(){
        String bestRevenueDestination = "";
        double maxRevenue = 0.0;
        for(Container c: dispatchedContainers){
            if(c.getTotalRevenue() > maxRevenue){
                maxRevenue = c.getTotalRevenue();
                bestRevenueDestination = c.getDestination();
            }
        }
        return bestRevenueDestination;
    }

    public void printPackagesByDestination(){
        for(Container c: dispatchedContainers){
            System.out.println("Destination: "+ c.getDestination());
            for(Package pkg: c.getPackages()){
                System.out.println("  "+ pkg.toString());
            }
            System.out.printf("  Subtotal: $%.2f\n", c.getTotalRevenue());
        }
    }

    /**
     * Returns the list of active containers (for printing manifests in Driver).
     */
    public ArrayList<Container> getActiveContainers() {
        return activeContainers;
    }

    public void printDailyReport() {
        StringBuilder sb = new StringBuilder();
         sb.append(String.format("\n=== Daily Report: %s ===\n", terminalName));
         sb.append(String.format("Packages received:  %d\n", getPendingCount() + getTotalPackagesShipped()));
         sb.append(String.format("Containers packed:  %d\n", activeContainers.size() + dispatchedContainers.size()));
         sb.append(String.format("Packages shipped:   %d\n", getTotalPackagesShipped()));
         sb.append(String.format("Total revenue:      $%.2f\n", getTotalRevenue()));
         
         sb.append("\nRevenue by destination:\n");
        for(Container c: dispatchedContainers){
            sb.append(String.format("  %-12s $%.2f (%d packages)\n", c.getDestination() + ":", c.getTotalRevenue(), c.getPackageCount()));

        }

        System.out.println(sb.toString());
    }
}
