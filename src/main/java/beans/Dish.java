package beans;

public class Dish {
    private int id;
    private String name;
    private String description;
    private String dishType;

    public Dish() {
    }

    public Dish(int id, String name, String description, String dishType) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
