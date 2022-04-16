package moonlight_cross.battle;

public class Player extends BattleEntity {
	int MAX_MOON = 100;
	int MOON = 0;
	
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
    
    // Currently a WIP, still thinking about how this should work.
    public void useItem(){
    	System.out.println("INVENTORY");
    	items.put(new Potion(), 5);
    	items.put(new Elixir(), 3);
    	items.put(new StrengthPotion(), 2);
    	System.out.println("ITEM NAME\t\tTYPE\t\tPOWER");
    	for (Item i : items.keySet()) {
    		System.out.println(
    				"> " + i.get_name());
    	}
    	System.out.println();
    }
}
