package moonlight_cross.battle;

import java.util.LinkedHashMap;

import moonlight_cross.battle.components.Inventory;

public class Player extends BattleEntity {
	int MAX_MOON = 100;
	int MOON = 0;

    // Serves to handle the battle actions, so adding new functionality is as simple
    // as using a "actions.put()" and connecting to its implementation.
    LinkedHashMap<String, String> actions = new LinkedHashMap<>();

    Inventory inv = new Inventory();
	
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
    
    @Override
    public void attack(BattleEntity target){
        if (target == null) return;
        System.out.println("You attack "+ target.NAME +"!");
        target.takeDamage(ATK);
        System.out.println("You deal "+ target.damage + " points of damage!");
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
