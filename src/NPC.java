import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class NPC implements Serializable {

    protected String city;

    protected String name;

    protected List<Item> inventory;

    public NPC(String city, String name)
    {
        this.city = city;
        this.name = name;
        inventory = new ArrayList<>();

    }

    public abstract void addItem(Item item);

    public String getName() {
        return name;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getCity() {
        return city;
    }

    public abstract int getMaxItems();
}
