public class Merchant extends NPC{

    private final int ITEMS_TO_SELL = 7;
    private final int TAX  = 4;
    public Merchant(String city, String name) {
        super(city, name);
    }

    @Override
    public void addItem(Item item)
    {

        inventory.add(item);

    }

    @Override
    public int getMaxItems() {
        return ITEMS_TO_SELL;
    }
}
