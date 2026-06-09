import java.util.ArrayList;


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

        this.destination = destination;
        this.maxWeightKg = maxWeightKg;
        this.packages = new ArrayList<Package>();
    }

    public Container(String destination) {
        this(destination, 500.0);
    }

    public String getContainerId(){
        return this.containerId;
    }

    public String getDestination(){
        return this.destination;
    }

    public double getMaxWeightKg(){
        return this.maxWeightKg;
    }

    public boolean addPackage(Package p) {
        if((p == null) || (!p.getDestination().equals(destination)) || (getCurrentWeightKg() + p.getWeightKg() > maxWeightKg)){
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

    public String getManifest() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== %s -> %s (%d packages, %.2f / %.2f kg) ===\n",
                containerId, destination, getPackageCount(), getCurrentWeightKg(), maxWeightKg));
        for(Package pkg: packages){
            sb.append("  ").append(pkg.toString()).append("\n");
        }
        sb.append(String.format("  Container revenue: $%.2f\n", getTotalRevenue()));
        return sb.toString();
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s [%d packages, %.2f / %.2f kg]", containerId, destination, packages.size(), getCurrentWeightKg(), maxWeightKg);
    }
}
