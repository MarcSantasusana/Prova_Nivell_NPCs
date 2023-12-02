import java.io.Serializable;

public class Item implements Comparable, Serializable {

    private String name;
    private String type;
    private float price;
    private int wearPercentage;

    public Item(String name, String type, float price)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.wearPercentage = 0;

    }

    public String getName() {
        return name;
    }

    public int getWearPercentage() {
        return wearPercentage;
    }

    public void setWearPercentage(int wearPercentage) {
        this.wearPercentage += wearPercentage;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void applyTax(float tax)
    {
        price += price*tax;
    }

    @Override
    public int compareTo(Object o) {

        Item item = (Item)o;

        return Float.compare(this.price, item.getPrice());

    }

    @Override
    public String toString()
    {
        return "Name: " + name + "\nType: " + type + "\nWear percentage: " + wearPercentage + "%" +
                "\nPrice: " + price + " €\n";

    }
}
