package Models;
import Comparators.ItemAmountComparator;
import Enums.ItemCondition;
import java.util.*;

public class FulfillmentCenter {
    public String Name;
    public List<Item> ProductList;
    private double maxStorage;

    public FulfillmentCenter(String name, double maxStorage){
        Name=name;
        this.maxStorage=maxStorage;
        ProductList = new ArrayList<Item>();
    }

    //          i. addProduct(Item) – Dodająca produkt. Jeśli dany produkt będzie już obecny w magazynie
    //          (produkt o tej samej nazwie istnieje) to należy zsumować ich ilość. Produkt może zostać
    //          dodany, tylko jeśli niezostanie przekroczona pojemność magazynu. Jeśli pojemność
    //           zostanie przekroczona wypisz komunikat na standardowe wyjście błędów (System.err)
    public void AddProduct(Item item){
        double actualStorage = item.Weight;
        for (Item _item: ProductList) {
            actualStorage += _item.Weight;
        }
        if(actualStorage + item.Weight > maxStorage){
            System.err.print(Name + " is full! (" + FillingStatus() + "% used.\n");
            return;
        }
        for(int i=0 ; i<ProductList.size() ; i++) {
            if (((Item)ProductList.toArray()[i]).compareTo(item) == 0){
                Item _item = ProductList.get(i);
                _item.Weight += item.Weight;
                _item.Amount += item.Amount;
                return;
            }
        }
        ProductList.add(item);
    }

    //        ii. getProduct(Item) – Zmniejszający ilość danego produktu o jeden lub usuwający go
    //            całkowicie, jeśli po zmianie wartość będzie równa 0.
    public Item GetProduct(String query){
        Item item;
        for(int i=0 ; i<ProductList.size() ; i++) {
            item = ((Item)ProductList.toArray()[i]);
            if (item.Name == query) {
                if (item.Amount <= 1) {
                    return ProductList.remove(i);
                }
                item.Weight *= (item.Amount - 1) / item.Amount;
                item.Amount -= 1;
                return ProductList.get(i);
            }
        }
        return null;
    }

    //       iii. removeProduct(Item) – usuwający dany produkt całkowicie z magazynu.
    public void RemoveProduct(String query){
        for(int i=0 ; i<ProductList.size() ; i++) {
            if (((Item)ProductList.toArray()[i]).Name == query) {
                ProductList.remove(i);
                return;
            }
        }
    }

    //        iv. search(String) - Przyjmującej nazwę produktu i zwracający go. Zastosuj Comparator
    public Item Search(String query){
        for(int i=0; i<ProductList.size();i++) {
            if (((Item) ProductList.toArray()[i]).Name == query) {
                return ProductList.get(i);
            }
        }
        return null;
    }

    //         v. searchPartial(String) – Przyjmujący fragment nazwy produktu i zwracający wszystkie
    //           produkty, które pasują.
    public List<Item> SearchPartial(String query){
        List<Item> result = new LinkedList<Item>();
        CharSequence _query = query;
        for(int i=0 ; i<ProductList.size() ; i++) {
            if(((Item)ProductList.toArray()[i]).Name.contains(_query)){
                result.add(((Item)ProductList.toArray()[i]));
            }
        }
        return result;
    }

    //        vi. countByCondition(ItemCondition) – zwracający ilość produktów o danym stanie
    public int CountByCondition(ItemCondition condition) {
        int result=0;
        for (int i = 0 ; i < ProductList.size() ; i++) {
            if (((Item) ProductList.toArray()[i]).Condition == condition)
                result++;
        }
        return result;
    }

    //       vii. summary() – wypisującą na standardowe wyjście informację o wszystkich produktach
    public void Summary(){
        for(int i=0 ; i<ProductList.size() ; i++)
            ((Item)ProductList.toArray()[i]).Print();
    }

    //      viii. sortByName() – zwracającą posortowana listę produktów – po nazwie alfabetycznie
    public void SortByName(){
        Collections.sort(ProductList);
    }

    //        ix. sortByAmount() – zwracającą posortowaną listę produktów po ilości – malejąco – zastosuj
    //          własny Comparator
    public void SortByAmount() {
        Collections.sort(ProductList,new ItemAmountComparator(true));
    }

    //         x. max() – zwracającą produkt którego jest najwięcej - zastosuj metodę Collections.max
    public Item Max(){
            Item result = Collections.max(ProductList,new ItemAmountComparator(false));
        return result;
    }

    public int FillingStatus(){
        double actualStorage=0;
        for (Item _item: ProductList
                ) {
            actualStorage+=_item.Weight;
        }
        return (int)((100*actualStorage)/maxStorage);
    }
}