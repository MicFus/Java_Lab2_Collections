package Comparators;
import Models.Item;
import java.util.Comparator;

public class ItemAmountComparator implements Comparator<Item> {
    boolean _upToDown;
    public ItemAmountComparator(boolean upToDown){ _upToDown=upToDown;}

    @Override
    public int compare(Item o1, Item o2) {
        int ret = (_upToDown ? -1:1);
        if (o1.Amount > o2.Amount)
            return ret;
        else if(o1.Amount < o2.Amount)
            return -ret;
        return 0;
    }
}
