public class Item implements Comparable{

    private String name;
    private String type;
    private float price;
    private int wearPercentage;

    public Item(String name, String type, float price)
    {
        this.name = name;
        this.price = price;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public int getWearPercentage() {
        return wearPercentage;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {

        Item item = (Item)o;

        return Float.compare(this.price, item.getPrice());

    }
}
