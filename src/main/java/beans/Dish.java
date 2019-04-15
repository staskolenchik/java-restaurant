package beans;

public class Dish {
    private int id;
    private String name;
    private String description;
    private String dishType;
    private double dishPrice;

    public Dish() {
    }

    public Dish(int id, String name, String description, String dishType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishType = dishType;
    }

    public Dish(int id, String name, String description, String dishType, double dishPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishType = dishType;
        this.dishPrice = dishPrice;
    }

    public Dish(String dishName, double dishPrice) {
        this.name = dishName;
        this.dishPrice = dishPrice;
    }

    public Dish(String dishName, String dishDescription, String dishType, double dishPrice) {
        this.name = dishName;
        this.description = dishDescription;
        this.dishType = dishType;
        this.dishPrice = dishPrice;
    }

    public Dish(String dishName, String dishDescription, String dishType) {
        this.name = dishName;
        this.description = dishDescription;
        this.dishType = dishType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }
}
