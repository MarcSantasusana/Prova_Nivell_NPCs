import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MaxItemsException, IOException {

        boolean quit = false;

        List<NPC> NPCList = new ArrayList<>();

        initializeSellersAndItems(NPCList);

        do {


            int option;

            option = askInt("0. Quit \n" +
                    "1. Create new seller \n" +
                    "2. Add item to seller \n" +
                    "3. Show items of one seller \n" +
                    "4. Show sellers in one city \n" +
                    "5. Show cheapest items of every Seller in a city \n" +
                    "6. Show every item of a certain type sorted by price (asc) \n" +
                    "7. Buy item to NPC \n" +
                    "8. Sell item to NPC \n" +
                    "9. Serialize \n");

            switch (option) {

                case 0:

                    quit = true;
                    break;

                case 1:

                    createNPC(NPCList);



                    break;

                case 2:

                    addItemToNPC(NPCList);



                    break;

                case 3:

                    showItemsSeller(NPCList);



                    break;


                case 4:

                    showSellersCity(NPCList);

                    break;


                case 5:

                    showCheapestSellerItemCity(NPCList);

                    break;

                case 6:

                    showItemsTypeSortedByPrice(NPCList);

                    break;

                case 7:

                    buyItemNPC(NPCList);

                    break;

                case 8:

                    sellItemNPC(NPCList);

                    break;

                case 9:

                    serialize(NPCList);

                default:

                    System.out.println("This option is not valid");
            }

        }while(!quit);
    }

    public static int askInt(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        int input = scanner.nextInt();

        return input;

    }

    public static String askString(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        return input;

    }

    public static float askFloat(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        float input = scanner.nextFloat();

        return input;

    }

    public static void createNPC(List<NPC> NPCList)
    {

        String type = askString("Introduce the type of the seller (Thief, Merchant, Peasant").toLowerCase();
        String name = askString("Introduce the name of the seller");
        String city = askString("Introduce the city of the seller");

        switch(type)
        {
            case "thief":

                NPCList.add(new Thief(city,name));

                break;

            case "merchant":

                NPCList.add(new Merchant(city, name));

                break;

            case "peasant":

                NPCList.add(new Peasant(city, name));

                break;

            default:

                System.out.println("This type of seller does not exist");
        }


    }

    public static void addItemToNPC(List<NPC> NPCList)
    {
        String nameSeller = askString("Introduce the name of the seller");

        NPC npc = findSeller(NPCList, nameSeller);

        String nameItem = askString("Introduce the name of the item");
        String typeItem = askString("Introduce the type of the item");
        float priceItem = askFloat("Introduce the price of the item");

        npc.addItem(new Item(nameItem, typeItem, priceItem));


    }

    public static NPC findSeller(List<NPC> NPCList, String name)
    {
        NPC npc = null;

        for(NPC seller : NPCList)
        {
            if(seller.getName().equals(name))
            {
                npc = seller;
            }
        }

        return npc;

    }

    public static void showItemsSeller(List<NPC> NPCList)
    {
        String nameSeller = askString("Introduce the name of the seller");

        NPC npc = findSeller(NPCList, nameSeller);

            for(Item item : npc.getInventory()) {
                System.out.println(item.getName());
                System.out.println(item.getType());
                System.out.println(item.getPrice() + "\n");
            }

    }

    public static void showSellersCity(List<NPC> NPCList)
    {
        String city = askString("Introduce the name of the city");

        for(NPC npc : NPCList)
        {
            if(npc.getCity().equals(city))
            {
                System.out.println(npc.getName());
                System.out.println(npc.getCity());
            }
        }
    }

    public static void showCheapestSellerItemCity(List<NPC> NPCList)
    {
        String city = askString("Introduce the name of the city");

        for(NPC npc : NPCList)
        {
            if(npc.getCity().equals(city))
            {
                Item cheapestItem = npc.getInventory().get(0);

                System.out.println("City: " + npc.getCity());
                System.out.println("Seller's name: " + npc.getName());

                for(Item item : npc.getInventory())
                {
                    if(item.getPrice() < cheapestItem.getPrice())
                    {
                        cheapestItem = item;
                    }

                }

                System.out.println("Item name: " + cheapestItem.getName());
                System.out.println("Item type: " + cheapestItem.getType());
                System.out.println("Item price: " + cheapestItem.getPrice());
            }
        }
    }

    public static void showItemsTypeSortedByPrice(List<NPC> NPCList)
    {

        String type = askString("Introduce the type of the item");

        List<Item> sortedItems = new ArrayList<>();

        for(NPC npc : NPCList)
        {
            for(Item item : npc.getInventory())
            {
                if(item.getType().equals(type))
                {
                    sortedItems.add(item);
                }
            }
        }

        Collections.sort(sortedItems);

        for(Item item : sortedItems)
        {
            System.out.println(item.getName());
            System.out.println(item.getType());
            System.out.println(item.getPrice());
        }



    }

    public static void buyItemNPC(List<NPC> NPCList)
    {
        String nameNPC = askString("Introduce the name of the NPC");

        System.out.println("This is the list of all this NPC's products.");

        for(NPC npc : NPCList)
        {
            if(npc.getName().equals(nameNPC))
            {
                for(Item item : npc.getInventory())
                {
                    System.out.println(item.getName());
                    System.out.println(item.getType());
                    System.out.println(item.getPrice());
                }
            }
        }

        int itemChosen = askInt("Which one do you want to buy?");

        for(NPC npc : NPCList)
        {
            if (npc.getName().equals(nameNPC)) {

                System.out.println("The price is " + npc.getInventory().get(itemChosen).getPrice());

                String buy = askString("Are you sure you want to buy it? (Yes/No)").toLowerCase();

                if (buy.equals("yes")) {
                    npc.getInventory().remove(itemChosen);
                    System.out.println("You purchased this item successfully");
                }

            }

        }

    }

    public static void sellItemNPC(List<NPC> NPCList) throws MaxItemsException {

        String nameNPC = askString("Introduce the name of the NPC");
        NPC npc = findSeller(NPCList, nameNPC);

        if(npc.getInventory().size() >= npc.getMaxItems())
        {
            throw new MaxItemsException("The inventory is full! The seller can't buy an item");
        }

        else {

            String nameItem = askString("Introduce the name of the item");
            String typeItem = askString("Introduce the type of the item");
            float priceItem = askFloat("Introduce the price of the item");

            npc.addItem(new Item(nameItem, typeItem, priceItem));

            System.out.println("You sold the item to the seller successfully");
        }
    }

    public static void serialize(List<NPC> NPCList) throws IOException {
        String path = "src";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(NPCList);
        out.close();
    }


    public static void initializeSellersAndItems(List<NPC> NPCList)
    {
        Peasant peasant1 = new Peasant("Barcelona", "Marc");
        Item item1 = new Item("Table", "Furniture", 32);
        Item item2 = new Item("Ball", "Toys", 12);

        peasant1.addItem(item1);
        peasant1.addItem(item2);

        NPCList.add(peasant1);

        Thief thief1 = new Thief("Girona", "Pere");
        Item item3 = new Item("Chair", "Furniture", 34);
        Item item4 = new Item("Game", "Toys", 6);

        thief1.addItem(item3);
        thief1.addItem(item4);

        NPCList.add(thief1);

        Merchant merchant1 = new Merchant("Barcelona", "Anna");
        Item item5 = new Item("Car", "Vehicle", 7000);
        Item item6 = new Item("Horse", "Animal", 2000);

        merchant1.addItem(item5);
        merchant1.addItem(item6);

        NPCList.add(merchant1);





    }

}