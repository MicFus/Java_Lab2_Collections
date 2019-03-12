package com.company;
import Enums.ItemCondition;
import Models.FulfillmentCenter;
import Models.FulfillmentCenterContainer;
import Models.Item;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FulfillmentCenter myCenter = new FulfillmentCenter("ABM", 10);
        FulfillmentCenterContainer myContainer = new FulfillmentCenterContainer();
        int choice=1;
        String query;

                                // Simple Menu only for tests
        while(choice!=0) {
            System.out.print("\n\n\n Menu choise - FulfillmentCenter(1-10),\n FulfillmentCenterContainer(11-14): ");
            choice = scanner.nextInt();

            switch(choice) {
                case 1:             // ADD PRODUCT
                    System.out.println("Name:");
                    query = scanner.next();
                    System.out.println("amount:");
                    int amount = scanner.nextInt();
                    System.out.println("weight (X,XX):");
                    double weight = scanner.nextDouble();
                    myCenter.AddProduct(new Item(query, ItemCondition.NEW, weight, amount));
                    break;

                case 2:             // GET PRODUCT
                    query = scanner.next();
                    myCenter.GetProduct(query);
                    break;

                case 3:             // REMOVE PRODUCT
                    query = scanner.next();
                    myCenter.RemoveProduct(query);
                    break;

                case 4:             // SEARCH
                    query = scanner.next();
                    myCenter.Search(query);
                    break;

                case 5:             // SEARCH PARTIAL
                    query = scanner.next();
                    List<Item> partialList = myCenter.SearchPartial(query);
                    for (Item item : partialList)
                        item.Print();
                    break;

                case 6:             // COUNT BY CONDITION
                    int i = scanner.nextInt();
                    myCenter.CountByCondition(ItemCondition.values()[i - 1]);
                    break;

                case 7:             // SUMMARY
                    myCenter.Summary();
                    break;

                case 8:             // SORT BY NAME
                    myCenter.SortByName();
                    break;
                case 9:             // SORT BY AMOUNT
                    myCenter.SortByAmount();
                    break;
                case 10:            // FIND MAX
                    Item max = myCenter.Max();
                    System.out.println("max: " + max.Amount);
                    break;


                case 11:            //  ADD CENTER
                    System.out.print("Name(key): ");
                    String name= scanner.next();
                    System.out.print("Max storage: ");
                    double Max = scanner.nextInt();
                    myContainer.AddCenter(name,Max);
                    break;

                    case 12:        //  REMOVE CENTER
                        System.out.print("Name(key): ");
                    String key= scanner.next();
                    FulfillmentCenter tmpCenter = myContainer.RemoveCenter(key);
                    if(tmpCenter!=null)
                        tmpCenter.Summary();
                    break;

                case 13:            //  FIND EMPTY
                    List<FulfillmentCenter> tmpList = myContainer.FindEmpty();
                    for (FulfillmentCenter center: tmpList)
                        center.Summary();
                    break;

                case 14:            //  SUMMARY
                    myContainer.Summary();
                    break;
            }
        }
    }
}