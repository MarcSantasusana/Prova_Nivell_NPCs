import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class NPC implements Serializable {

    protected String city;

    protected String name;

    protected String type;

    protected List<Item> inventory;





    public NPC(String city, String name, String type)
    {
        this.city = city;
        this.name = name;
        inventory = new ArrayList<>();

        this.type = type;

    }

    public abstract void addItem(Item item);

    public abstract void addItemSelling() throws MaxItemsException;

    public String getName() {
        return name;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getCity() {
        return city;
    }


    public String askString(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        return input;

    }

    public float askFloat(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        float input = scanner.nextFloat();

        return input;

    }

    @Override
    public String toString()
    {
        return "Name: " + name + "\nType: " + type + "\nCity: " + city + "\n";

    }


}
