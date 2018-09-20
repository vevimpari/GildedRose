package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        setItems(new ArrayList<Item>());
        getItems().add(new Item("+5 Dexterity Vest", 10, 20));
        getItems().add(new Item("Aged Brie", 2, 0));
        getItems().add(new Item("Elixir of the Mongoose", 5, 7));
        getItems().add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        getItems().add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        getItems().add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality();
}


	
    public static void updateQuality()
    {
        for (int i = 0; i < getItems().size(); i++)
        {
            if ((!"Aged Brie".equals(getItems().get(i).getName())) && !"Backstage passes to a TAFKAL80ETC concert".equals(getItems().get(i).getName())) 
            {
                if (getItems().get(i).getQuality() > 0)
                {
                    if (!"Sulfuras, Hand of Ragnaros".equals(getItems().get(i).getName()))
                    {
                        getItems().get(i).setQuality(getItems().get(i).getQuality() - 1);
                    }
                }
            }
            else
            {
                if (getItems().get(i).getQuality() < 50)
                {
                    getItems().get(i).setQuality(getItems().get(i).getQuality() + 1);

                    if ("Backstage passes to a TAFKAL80ETC concert".equals(getItems().get(i).getName()))
                    {
                        if (getItems().get(i).getSellIn() < 11)
                        {
                            if (getItems().get(i).getQuality() < 50)
                            {
                                getItems().get(i).setQuality(getItems().get(i).getQuality() + 1);
                            }
                        }

                        if (getItems().get(i).getSellIn() < 6)
                        {
                            if (getItems().get(i).getQuality() < 50)
                            {
                                getItems().get(i).setQuality(getItems().get(i).getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!"Sulfuras, Hand of Ragnaros".equals(getItems().get(i).getName()))
            {
                getItems().get(i).setSellIn(getItems().get(i).getSellIn() - 1);
            }

            if (getItems().get(i).getSellIn() < 0)
            {
                if (!"Aged Brie".equals(getItems().get(i).getName()))
                {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(getItems().get(i).getName()))
                    {
                        if (getItems().get(i).getQuality() > 0)
                        {
                            if (!"Sulfuras, Hand of Ragnaros".equals(getItems().get(i).getName()))
                            {
                                getItems().get(i).setQuality(getItems().get(i).getQuality() - 1);
                            }
                        }
                    }
                    else
                    {
                        getItems().get(i).setQuality(getItems().get(i).getQuality() - getItems().get(i).getQuality());
                    }
                }
                else
                {
                    if (getItems().get(i).getQuality() < 50)
                    {
                        getItems().get(i).setQuality(getItems().get(i).getQuality() + 1);
                    }
                }
            }
        }
    }



	public static List<Item> getItems() {
		return items;
	}



	public static void setItems(List<Item> items) {
		GildedRose.items = items;
	}

}
