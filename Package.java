import java.util.Arrays;
import java.util.List;


public class Package {
    private static int nextTrackingNumber = 1;

    private static final List<String> VALID_DESTINATIONS = Arrays.asList(
        "Trinidad", "Barbados", "Jamaica", "Antigua", "Grenada"
    );

    private String trackingId;
    private String senderName;
    private String receiverName;
    private double weightKg;
    private int lengthCm;
    private int widthCm;
    private int heightCm;
    private String destination;
    private boolean isFragile;
    private double declaredValue;

    public Package(String senderName, String receiverName, double weightKg,
                   int lengthCm, int widthCm, int heightCm,
                   String destination, boolean isFragile, double declaredValue) {
        if(senderName == null || senderName.isEmpty()){
            throw new IllegalArgumentException("The Sender's name can't be NULL or empty!");
        }
        if(receiverName == null || receiverName.isEmpty()){
            throw new IllegalArgumentException("The Receiver's name cam't be NULL or empty!");
        }
        if(weightKg <= 0){
            throw new IllegalArgumentException("The weight is inaccurate!");
        }
        if(lengthCm <= 0 || widthCm <= 0 || heightCm <= 0){
            throw new IllegalArgumentException("The Dimensions are less than 0!");
        }
        if(!VALID_DESTINATIONS.contains(destination)){
            throw new IllegalArgumentException("Destination INVALID!");
        }

        this.trackingId = String.format("PKG-%04d", nextTrackingNumber);
        nextTrackingNumber = nextTrackingNumber + 1;

        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
        this.lengthCm = lengthCm;
        this.widthCm = widthCm;
        this.heightCm = heightCm;
        this.destination = destination;
        this.isFragile = isFragile;
        this.declaredValue = declaredValue;
    }

    public Package(String senderName, String receiverName, double weightKg,
                   int lengthCm, int widthCm, int heightCm, String destination) {
       this(senderName, receiverName, weightKg, lengthCm, widthCm, heightCm, destination, false, 0.0);
    }
    
    public String getTrackingId(){
        return this.trackingId;
    }

    public String getSenderName(){
        return this.senderName;
    }

    public String getReceiverName(){
        return this.receiverName;
    }

    public double getWeightKg(){
        return this.weightKg;
    }

    public int getLengthCm(){
        return this.lengthCm;
    }

    public int getWidthCm(){
        return this.widthCm;
    }

    public int getHeightCm(){
        return this.heightCm;
    }

    public String getDestination(){
        return this.destination;
    }

    public boolean isFragile(){
        return this.isFragile;
    }

    public double getDeclaredValue(){
        return this.declaredValue;
    }

    public int getVolumeCm3() {
        return lengthCm * widthCm * heightCm; 
    }

    public double getVolumetricWeightKg() {
        return getVolumeCm3() / 5000.0; 
    }

    public double getBillableWeightKg() {
        return Math.max(weightKg, getVolumetricWeightKg());
    }

    public double getShippingCost() {
        double ratePerKg; 
        switch (destination) {
            case "Trinidad": 
                ratePerKg = 8.00;
                break;
            
            case "Barbados":
                ratePerKg = 12.50;
                break;

            case "Jamaica":
                ratePerKg = 15.00;
                break;

            case "Antigua":
                ratePerKg = 18.00;
                break;

            case "Grenada":
                ratePerKg = 10.00;
                break;
        
            default:
                ratePerKg = 0.0;
                break;
        }

        double cost = getBillableWeightKg() * ratePerKg;

        if(isFragile){
            cost = cost * 1.25;
        }

        if(declaredValue > 0){
            cost = cost + (declaredValue*0.015);
        }

        return Math.round(cost*100) / 100.0;
    }

    @Override
    public String toString() {
        String base = String.format("PKG-%04d  %s -> %s  %s  %.2f kg  $%.2f", Integer.parseInt(trackingId.substring(4)), senderName, receiverName, destination, getBillableWeightKg(), getShippingCost());
        if(isFragile){
            base = base + "  [FRAGILE]";
        }

        return base; 
    }
}
