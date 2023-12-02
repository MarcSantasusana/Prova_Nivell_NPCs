public class Merchant extends NPC{

    private final static int ITEMS_TO_SELL = 7;
    private final static float TAX = 0.04F;
    private final static String TYPE = "Merchant";

    public Merchant(String city, String name) {
        super(city, name, TYPE);
    }

    @Override
    public void addItem(Item item)
    {

        item.applyTax(TAX);

        inventory.add(item);

    }

    @Override
    public void addItemSelling() throws MaxItemsException {

        if(inventory.size() >= ITEMS_TO_SELL)
        {
            throw new MaxItemsException("The inventory is full! The seller can't buy an item");
        }

        else {

            String nameItem = askString("Introduce the name of the item");
            String typeItem = askString("Introduce the type of the item");
            float priceItem = askFloat("Introduce the price of the item");

            this.addItem(new Item(nameItem, typeItem, priceItem));

            System.out.println("You sold the item to the seller successfully");
        }
    }


}
