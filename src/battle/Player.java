package battle;

import java.util.LinkedHashMap;

import battle.components.Inventory;
import battle.components.SpellHandler;

public class Player extends BattleEntity {
	int MAX_MOON = 100;
	int MOON = 0;

    // Serves to handle the battle actions, so adding new functionality is as simple
    // as using a "actions.put()" and connecting to its implementation.
    LinkedHashMap<String, String> actions = new LinkedHashMap<>();

    Inventory inv = new Inventory(this);
    SpellHandler mgc = new SpellHandler(this);
	
    public Player(){
        MAX_HP = 20;
        HP = MAX_HP;
        ATK = 5;
        DEF = 3;
        SPD = 10;
        
        NAME = "???";
        DESCRIPTION = "A descendant of a long dynasty of magic users.";

        actions.put("Attack", "atk");
        actions.put("Defend", "def");
        actions.put("Magic", "mgc");
        actions.put("Items", "inv");
        actions.put("Target Info", "info");

        inv.addItem(new Potion(), 3);
        inv.addItem(new StrengthPotion(), 2);

        mgc.addSpell(new Fireball());
    }
    
    @Override
    public void attack(BattleEntity target){
        super.attack(target);
        System.out.println("You attack "+ target.getName() +"!");
        System.out.println("You deal "+ target.damage + " points of damage!");
    }
}
