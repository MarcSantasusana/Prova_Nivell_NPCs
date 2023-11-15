public class Thief extends NPC{

    private final int ITEMS_TO_SELL = 3;


    public Thief(String city, String name) {
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
