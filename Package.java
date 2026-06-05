import java.util.Arrays;
import java.util.List;

/**
 * Represents a single cargo item in the Caribbean freight system.
 * Each package is auto-assigned a unique tracking ID in the format PKG-XXXX.
 */
public class Package {
    private static int nextTrackingNumber = 1;

    private static final List<String> VALID_DESTINATIONS = Arrays.asList(
        "Trinidad", "Barbados", "Jamaica", "Antigua", "Grenada"
    );

    // Your constructors (M2, M3) must assign them.
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

    // --- Computed methods ---

    /**
     * TODO M5: Return lengthCm * widthCm * heightCm
     */
    public int getVolumeCm3() {
        return 0; // TODO M5
    }

    /**
     * TODO M5: Return getVolumeCm3() / 5000.0
     */
    public double getVolumetricWeightKg() {
        return 0.0; // TODO M5
    }

    /**
     * TODO M5: Return Math.max(weightKg, getVolumetricWeightKg())
     */
    public double getBillableWeightKg() {
        return 0.0; // TODO M5
    }

    /**
     * TODO M6: Implement the shipping cost formula.
     *   1. Look up rate per kg by destination
     *   2. cost = getBillableWeightKg() * ratePerKg
     *   3. If fragile: cost *= 1.25
     *   4. If declaredValue > 0: cost += declaredValue * 0.015
     *   5. Round: Math.round(cost * 100) / 100.0
     */
    public double getShippingCost() {
        return 0.0; // TODO M6
    }

    /**
     * TODO M7: Return a string in this format:
     *   "PKG-0001  Alice -> Bob  Trinidad  5.00 kg  $40.00"
     * If fragile, append "  [FRAGILE]" at the end.
     * Use String.format for formatting.
     */
    @Override
    public String toString() {
        return ""; // TODO M7
    }
}
