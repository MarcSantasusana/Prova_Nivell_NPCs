import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean quit = false;

        List<NPC> NPCList = new ArrayList<>();

        initializeSellersAndItems(NPCList);


            do {

                try {


                int option;

                option = askInt("0. Quit \n" +
                        "1. Create new seller \n" +
                        "2. Show items of one seller \n" +
                        "3. Show sellers in one city \n" +
                        "4. Show cheapest items of every Seller in a city \n" +
                        "5. Show every item of a certain type sorted by price (asc) \n" +
                        "6. Buy item from NPC \n" +
                        "7. Sell item to NPC \n" +
                        "8. Serialize \n");

                switch (option) {

                    case 0:

                        quit = true;
                        break;

                    case 1:

                        createNPC(NPCList);


                        break;


                    case 2:

                        showItemsSeller(NPCList);


                        break;


                    case 3:

                        showSellersCity(NPCList);

                        break;


                    case 4:

                        showCheapestSellerItemCity(NPCList);

                        break;

                    case 5:

                        showItemsTypeSortedByPrice(NPCList);

                        break;

                    case 6:

                        buyItemNPC(NPCList);

                        break;

                    case 7:

                        sellItemNPC(NPCList);

                        break;

                    case 8:

                        serialize(NPCList);

                        break;

                    default:

                        System.out.println("This option is not valid");
                }

                }catch(InputMismatchException e)
                {
                  System.out.println("Introduce a number that corresponds with an option");
                } catch(NoSuchElementException e)
                {
                    System.out.println("This seller does not exist");
                }
                catch(MaxItemsException e)
                {
                    System.out.println(e.getMessage());
                }

            } while (!quit);


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



    public static NPC findSeller(List<NPC> NPCList, String name) throws NoSuchElementException
    {
       Optional<NPC> npc = NPCList.stream()
                .filter(seller -> seller.getName().equals(name))
                .findFirst();

        return npc.get();

    }

    public static void showItemsSeller(List<NPC> NPCList) throws NoSuchElementException
    {
        String nameSeller = askString("Introduce the name of the seller");

        NPC npc = findSeller(NPCList, nameSeller);

        npc.getInventory().forEach(System.out::println);
    }

    public static void showSellersCity(List<NPC> NPCList)
    {
        String city = askString("Introduce the name of the city");

        NPCList.stream()
                .filter(npc -> npc.getCity().equals(city))
                .forEach(System.out::println);

    }

    public static void showCheapestSellerItemCity(List<NPC> NPCList)
    {
        String city = askString("Introduce the name of the city");

        NPCList.stream()
                .filter(npc -> npc.getCity().equals(city) && !npc.getInventory().isEmpty())
                .forEach(npc -> {

                    System.out.println(npc.getName() + "'s cheapest item: ");

                    Collections.sort(npc.getInventory());

                    System.out.println(npc.getInventory().get(0));
                });
    }

    public static void showItemsTypeSortedByPrice(List<NPC> NPCList)
    {
        String type = askString("Introduce the type of the item");

        List<Item> sortedItems = new ArrayList<>();

        NPCList.forEach(npc -> npc.getInventory().stream()
                .filter(item -> item.getType().equals(type))
                .forEach(sortedItems::add));

        Collections.sort(sortedItems);

        sortedItems.forEach(System.out::println);
    }

    public static void buyItemNPC(List<NPC> NPCList) throws NoSuchElementException
    {
        NPCList.forEach(System.out::println);
        String nameNPC = askString("Introduce the name of the NPC you want to buy from");

        NPC npc = findSeller(NPCList, nameNPC);

        System.out.println("This is the list of all " + npc.getName() + "'s products.");

        int length = npc.getInventory().size();

        IntStream.range(0, length)
                        .forEach(index -> {
                            System.out.println(index+1);
                            System.out.println(npc.getInventory().get(index));
                        });


        int itemChosen = askInt("Which one do you want to buy?")-1;

        System.out.println("The price is " + npc.getInventory().get(itemChosen).getPrice());

        String buy = askString("Are you sure you want to buy it? (Yes/No)").toLowerCase();

        if (buy.equals("yes")) {
            npc.getInventory().remove(itemChosen);
            System.out.println("You purchased this item successfully");
        }

    }

    public static void sellItemNPC(List<NPC> NPCList) throws MaxItemsException, NoSuchElementException {

        String nameNPC = askString("Introduce the name of the NPC");
        NPC npc = findSeller(NPCList, nameNPC);

       npc.addItemSelling();
    }

    public static void serialize(List<NPC> NPCList) throws IOException {

        String name = "src/Sellers.ser";
        File outputFile = new File(name);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile));
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