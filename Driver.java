public class Driver {
    public static void main(String[] args) {
        FreightTerminal terminal = new FreightTerminal("Port of Spain Hub");

        Package p1 = new Package("Alice", "Bob", 5.0, 40, 30, 20, "Trinidad");
        terminal.receivePackage(p1);

        Package p2 = new Package("Carol", "Dan", 2.0, 60, 40, 40, "Barbados", true, 500);
        terminal.receivePackage(p2);

        Package p3 = new Package("Eve", "Frank", 10.0, 30, 30, 30, "Jamaica");
        terminal.receivePackage(p3);

        Package p4 = new Package("Grace", "Hank", 3.5, 50, 50, 50, "Barbados", false, 200);
        terminal.receivePackage(p4);

        Package p5 = new Package("Ivy", "Jack", 8.0, 20, 20, 20, "Trinidad", true, 1000);
        terminal.receivePackage(p5);

        Package p6 = new Package("Kim", "Leo", 1.5, 100, 60, 40, "Antigua");
        terminal.receivePackage(p6);

        Package p7 = new Package("Mia", "Noah", 15.0, 40, 40, 30, "Jamaica", true, 750);
        terminal.receivePackage(p7);

        Package p8 = new Package("Olivia", "Pat", 6.0, 35, 25, 15, "Grenada");
        terminal.receivePackage(p8);

        Package p9 = new Package("Quinn", "Ray", 4.0, 45, 35, 25, "Trinidad", false, 100);
        terminal.receivePackage(p9);

        Package p10 = new Package("Sara", "Tim", 20.0, 80, 60, 50, "Barbados", true, 2000);
        terminal.receivePackage(p10);

        Package p11 = new Package("Uma", "Vic", 0.5, 15, 10, 10, "Grenada");
        terminal.receivePackage(p11);

        Package p12 = new Package("Will", "Xia", 12.0, 50, 40, 30, "Antigua", true, 300);
        terminal.receivePackage(p12);

        System.out.println("=== Pending: "+ terminal.getPendingCount() +" packages ===");

        System.out.println(p1.toString());
        System.out.printf("Shipping cost: $" + p1.getShippingCost() +"\n");
        System.out.println();

        terminal.packContainers();
        System.out.println("Packed into "+ terminal.getActiveContainers().size() +" containers\n");

        for(Container c: terminal.getActiveContainers()){
            System.out.printf(c.getManifest());
            System.out.println();
        }

        terminal.dispatchAll();
        System.out.printf("Dispatched "+ terminal.getTotalPackagesShipped() +" containers\n");

        terminal.printDailyReport();
        System.out.println();

        terminal.findPackage("PKG-0005");
        System.out.println("Found: " + terminal.findPackage("PKG-0005"));

        terminal.findPackage("PKG-9999");
        if(terminal.findPackage("PKG-9999") == null){
            System.out.println("PKG-9999: Not found");
        }
        
    }
}
