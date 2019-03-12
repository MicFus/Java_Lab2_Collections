package Models;

import Enums.ItemCondition;

public class Item implements Comparable<Item>{
    public String Name;
    public ItemCondition Condition;
    public double Weight;
    public int Amount;

    public Item(String name, ItemCondition condition, double weight, int amount){
        Name=name;
        Condition=condition;
        Weight=weight;
        Amount = amount;
    }

    public void Print(){
        System.out.println("Name:" + Name + "\nCondiotion: " + Condition
                            + "\nWeight: " + Weight + "\nAmount: " + Amount +"\n");
    }

    @Override
    public int compareTo(Item o) {
        return this.Name.compareTo(o.Name);
    }
}