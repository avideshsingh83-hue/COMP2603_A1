import java.util.ArrayList;

/**
 * Represents a shipping container bound for a single destination.
 * Holds packages and enforces a maximum weight capacity.
 */
public class Container {
    private static int nextContainerId = 1;

    private String containerId;
    private String destination;
    private double maxWeightKg;
    private ArrayList<Package> packages;

    public Container(String destination, double maxWeightKg) {
        if(destination == null){
            throw new IllegalArgumentException("Destination must not be NULL!");
        }
        if(maxWeightKg <= 0){
            throw new IllegalArgumentException("Inaccurate weight!");
        }

        this.containerId = String.format("CNT-%03d", nextContainerId);
        nextContainerId = nextContainerId + 1;

        this.maxWeightKg = maxWeightKg;
        this.packages = new ArrayList<Package>();
    }

    public Container(String destination) {
        this(destination, 500.0);
    }

    public String getContainerId(){
        return this.containerId;
    }

    public String getDesination(){
        return this.destination;
    }

    public double getMaxWeightKg(){
        return this.maxWeightKg;
    }

    public boolean addPackage(Package p) {
        if(p == null || p.getDestination() != this.destination || getCurrentWeightKg() + p.getWeightKg() > maxWeightKg){
            return false;
        }
        else{
            packages.add(p);
            return true;
        }
    }

    public double getCurrentWeightKg() {
        double sum=0.0;
        for(Package pkgs: packages){
            sum = sum + pkgs.getWeightKg();
        }
        return sum; 
    }

    public double getRemainingCapacityKg() {
        return maxWeightKg - getCurrentWeightKg(); 
    }

    public int getPackageCount() {
        return packages.size(); 
    }

    public double getTotalRevenue() {
        double sum = 0.0;
        for(Package pkgs: packages){
            sum = sum + pkgs.getShippingCost();
        }
        return sum;
    }

    /**
     * TODO M9: Build and return the multi-line manifest string.
     * Format:
     *   === CNT-001 -> Trinidad (3 packages, 17.00 / 500.00 kg) ===
     *     PKG-0001  Alice -> Bob  Trinidad  5.00 kg  $40.00
     *     PKG-0005  Ivy -> Jack  Trinidad  8.00 kg  $95.00  [FRAGILE]
     *     ...
     *     Container revenue: $199.50
     * Each package line is indented with 2 spaces.
     * Use StringBuilder and String.format.
     */
    public String getManifest() {
        return ""; // TODO M9
    }

    /**
     * Returns the list of packages (needed by FreightTerminal.findPackage).
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    /**
     * TODO M9: Return a one-line summary:
     *   "CNT-001 -> Trinidad [3 packages, 17.00 / 500.00 kg]"
     */
    @Override
    public String toString() {
        return ""; // TODO M9
    }
}
