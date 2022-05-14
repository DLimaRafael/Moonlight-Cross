package moonlight_cross.battle;

import java.util.LinkedHashMap;

public class Player extends BattleEntity {
	int MAX_MOON = 100;
	int MOON = 0;

    // Serves to handle the battle actions, so adding new functionality is as simple
    // as using a "actions.put()" and connecting to its implementation.
    LinkedHashMap<String, String> actions = new LinkedHashMap<>();
	
    public Player(){
        MAX_HP = 20;
        HP = MAX_HP;
        ATK = 5;
        DEF = 3;
        SPD = 2;
        
        NAME = "???";
        DESCRIPTION = "A descendant of a long dynasty of magic users.";

        actions.put("Attack", "atk");
        actions.put("Defend", "def");
        actions.put("Magic", "mgc");
        actions.put("Items", "inv");
        actions.put("Target Info", "info");
    }
    
    public void attack(BattleEntity target){
        if (target == null) return;
        System.out.println("You attack "+ target.NAME +"!");
        target.takeDamage(ATK);
        System.out.println("You deal "+ target.damage + " points of damage!");
    }
    
    // Currently a WIP, still thinking about how this should work.
    public void useItem(){
    	System.out.println("INVENTORY");
    	items.put(new Potion(), 5);
    	items.put(new Elixir(), 3);
    	items.put(new StrengthPotion(), 2);
    	for (Item i : items.keySet()) {
    		System.out.println(
    				"> " + items.get(i) + "x " + i.get_name());
    	}
    	System.out.println();
    }

    public void useSpell(){
        System.out.println("SPELLS");
        spells.add(new Fireball());
        spells.add(new Blizzard());
        for (Spell i : spells) {
            System.out.println("> " + i.get_name().toUpperCase());
        }
        System.out.println();
    }
}
