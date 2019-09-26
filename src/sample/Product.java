package sample;

public abstract class Product implements Item {
    // Fields
    int id;
    String type;
    String manufacturer;
    String name;

    // Methods

    /**
     * This method is a getter for the id field.
     * @return The id field
     */
    public int getId() {
        return id;
    }

    /**
     * This method is a setter for the name field.
     * @param name The value to set the name field to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is a getter for the name field.
     * @return The name field
     */
    public String getName() {
        return name;
    }

    /**
     * This method is a setter for the manufacturer field.
     * @param manufacturer The value to set the manufacturer field to
     */
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    /**
     * This method is a getter for the manufacturer field.
     * @return The name field
     */
    public String getManufacturer() {
        return manufacturer;
    }

}
