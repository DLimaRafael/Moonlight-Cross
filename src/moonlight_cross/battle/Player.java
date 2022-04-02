package moonlight_cross.battle;

import java.util.LinkedHashMap;

public class Player extends BattleEntity {
	int MAX_MOON = 100;
	int MOON = 0;
	LinkedHashMap<String, Integer> items = new LinkedHashMap<>();
	
    public Player(){
        MAX_HP = 20;
        HP = MAX_HP;
        ATK = 5;
        DEF = 3;
        SPD = 2;
        
        NAME = "???";
        DESCRIPTION = "A descendant of a long dynasty of magic users.";
    }
    
    public void attack(BattleEntity target){
        if (target == null) return;
        System.out.println("You attack "+ target.NAME +"!");
        target.takeDamage(ATK);
        System.out.println("You deal "+ target.damage + " points of damage!");
    }
    
    public void defend() {
    	temp_stat = DEF;
    	DEF += 3;
    }
    
    // Currently a WIP, still thinking about how this should work.
    public void useItem(){
    	System.out.println("IVENTORY");
    	items.put("Potion", 5);
    	items.put("Elixir", 10);
    	items.put("Chocolate bar", 2);
    	items.put("Vigor Pills", 3);
    	for (String i : items.keySet()) {
    		System.out.println("> "+ i + "\tHEALS: " + items.get(i));
    	}
    	System.out.println();
    }
}
