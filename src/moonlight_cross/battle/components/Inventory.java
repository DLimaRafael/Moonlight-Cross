package moonlight_cross.battle.components;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

import moonlight_cross.battle.Item;

public class Inventory {
    HashMap<Item, Integer> items = new LinkedHashMap<>();

    public void showInv(){
        System.out.println("INVENTORY");
        Scanner input = new Scanner(System.in);
        
        for (Item i : items.keySet()) {
    		System.out.println(
    				"> " + items.get(i) + "x " + i.get_name());
    	}
        
        System.out.print("USE: ");
        try{
            useItem(input.nextLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        input.close();
    }

    // Currently a WIP, still thinking about how this should work.
    public void useItem(String item){
        Item i = seekItem(item);
    }

    public boolean hasItem(String item){
        for (Item i : items.keySet()){
            if (i.get_name() == item) return true;
        }
        return false;
    }

    public Item seekItem(String item){
        if (!hasItem(item)) {
            System.out.println("Invalid item!");
            return null;
        }
        for (Item i : items.keySet()){
            if (i.get_name() == item) return i;
        }
        return null;
    }

    public void addItem(Item a, int quant){
        for (Item i : items.keySet()){
            if (i.getClass() == a.getClass()){
                System.out.println("Updating...");
                items.replace(i, items.get(i) + quant);
                return;
            }
        }
        items.put(a, quant);
    }

    public void removeItem(String item, int quant){
        Item i = seekItem(item);
        items.replace(i, items.get(i) - quant);
        if (items.get(i) <= 0){
            items.remove(i);
        }
    }
}
