package battle.components;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Scanner;

import battle.Actor;
import battle.BattleUnits;
import battle.Item;

public class Inventory {
    private HashMap<Item, Integer> items = new LinkedHashMap<>();
    
    private Scanner input = new Scanner(System.in);
    
    private boolean usedItem = false;

    private Actor owner;
    
    /****************************************
    * Checks whether an item was used or not.
    *****************************************/

    public Inventory(Actor owner){
        this.owner = owner;
    }

    public boolean wasItemUsed(){
        return usedItem;
    }

    public void showInv(){
        System.out.println("INVENTORY");
        for (Item i : items.keySet()) {
            System.out.println("> " + items.get(i) + "x " + i.getName());
        }

        System.out.print("USE: ");
        useItem(input.nextLine(), BattleUnits.getPlayer());
    }

    // Currently a WIP, still thinking about how this should work.
    public void useItem(String item, Actor target){
        Item i = seekItem(item);
        if (i == null) return;
        System.out.println(owner.getName() + " used [" + item.toUpperCase() + "]!");
        i.getEffect().cast(target);
        removeItem(item, 1);
        usedItem = true;
    }

    // Redundant method, will be removed later.
    public boolean hasItem(String item){
        for (Item i : items.keySet()){
            if (i.getName().equalsIgnoreCase(item)) return true;
        }
        return false;
    }

    public Item seekItem(String item){
        // Guard clause returning null in case there is no such item
        if (hasItem(item) == false) {
            System.out.println("Invalid item!");
            return null;
        }
        for (Item i : items.keySet()){
            if (i.getName().equalsIgnoreCase(item)) return i;
        }
        return null;
    }

    public void addItem(Item a, int quant){
        for (Item i : items.keySet()){
            if (i.getClass() == a.getClass()){
                items.replace(i, items.get(i) + quant);
                return;
            }
        }
        items.put(a, quant);
    }

    public void removeItem(String item, int quant){
        Item i = seekItem(item);
        items.replace(i, (items.get(i) - quant));
        if (items.get(i) <= 0){
            items.remove(i);
        }
    }
}
