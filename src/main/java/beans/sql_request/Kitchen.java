package beans.sql_request;

public class Kitchen {
    private int kitchenId;
    private String kitchenName;
    private String kitchenDescription;
    private String kitchenType;
    private byte kitchenQuantity;
    private String kitchenStatus;

    public Kitchen() {
    }

    public Kitchen(int kitchenId, String kitchenName, String kitchenDescription, String kitchenType,
                   byte kitchenQuantity, String kitchenStatus) {
        this.kitchenId = kitchenId;
        this.kitchenName = kitchenName;
        this.kitchenDescription = kitchenDescription;
        this.kitchenType = kitchenType;
        this.kitchenQuantity = kitchenQuantity;
        this.kitchenStatus = kitchenStatus;
    }

    public int getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(int kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public String getKitchenDescription() {
        return kitchenDescription;
    }

    public void setKitchenDescription(String kitchenDescription) {
        this.kitchenDescription = kitchenDescription;
    }

    public String getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(String kitchenType) {
        this.kitchenType = kitchenType;
    }

    public byte getKitchenQuantity() {
        return kitchenQuantity;
    }

    public void setKitchenQuantity(byte kitchenQuantity) {
        this.kitchenQuantity = kitchenQuantity;
    }

    public String getKitchenStatus() {
        return kitchenStatus;
    }

    public void setKitchenStatus(String kitchenStatus) {
        this.kitchenStatus = kitchenStatus;
    }
}
