package moonlight_cross.battle;

import static java.lang.Integer.max;

import java.util.HashMap;

/*
Different from the interfaces, which will allocate enemies in specific types,
the abstract class Enemy just identifies them as an enemy, implementing
default methods that won't need to be built by the entities themselves, 
such as setters and getters.
*/
public abstract class BattleEntity {
    // Enemy Stats
    int MAX_HP = 0;
    int HP = 0;
    int ATK = 0;
    int DEF = 0;
    int SPD = 0;
    
    String NAME = "";
    String DESCRIPTION = "";
    
    boolean isAlive = true;
    int temp_stat = 0; // Variable to hold stats for calculations
    int damage = 0; // Variable to keep track of damage dealt/received.
    
    // Modifiers
    /*
     * The way this works is simple, whenever you modify a stat, instead of changing
     * the variable itself, you multiply it by the entry corresponding to it inside
     * mods, this leaves the original attribute intact, allowing for cleansing
     * of buffs/debuffs
     * */
    HashMap<String, Double> mods = new HashMap<>();
    HashMap<Item, Integer> items = new HashMap<>();
    
    // Getters and Setters
    // Note: Rework this for more adaptable code.
    public int getAtk(){
        return ATK;
    }
    public void setAtk(int amount){
        ATK = amount;
    }
    
    public int getHp(){
        return HP;
    }
    public void setHp(int amount){
        HP = max(amount, 0);
        if (HP == 0){
            isAlive = false;
        }
    }
    
    public int getDef(){
        return DEF;
    }
    public void setDef(int amount){
        DEF = amount;
    }
    
    public int getSpd(){
        return SPD;
    }
    public void setSpd(int amount){
        SPD = amount;
    }
    
    public void attack(BattleEntity target){
    	// Checks if there's a modifier applicable to Attack
    	int atk_mod = (int) (getMod("atk") * ATK);
        target.takeDamage(ATK + atk_mod);
    }
    
    public void takeDamage(int amount){
    	int def_mod = (int) (getMod("def") * DEF);
        damage = max(amount - (DEF + def_mod), 0);
        setHp(HP - damage);
    }
    
    public void showInfo(){
        System.out.println("NAME: "+NAME+"\n"+DESCRIPTION);
    }
    
    /*
     * This collection of functions serve the purpose of giving support
     * to a buff/debuff system by matching Strings and modifiers, applying
     * them to stats and cleaning afterwards.
     * 
     * Yes, I know it isn't ideal, probably, but doing it the proper way doesn't
     * seem half as fun so this is what we're going with for now.
     * */
    public double getMod(String stat) {
    	double mod = 0;
    	for (String i : mods.keySet()) {
    		if (stat.equalsIgnoreCase(i)) {
    			mod += mods.get(i);
    		}
    	}
    	return mod;
    }
    
    public void setMod(String stat, double mod) {
    	mods.put(stat, mod);
    }
    
    public void resetStat(String stat) {
    	if (mods.isEmpty()) return; // Self-explanatory
    	// Iterates through mods for every String key in there
    	for (String j : mods.keySet()) {
    		// Checks if current item is what the function is looking for
    		if (stat.equalsIgnoreCase(j)) {
    			System.out.println(j+ " "+ mods.get(j));
    		}
    	}
    }
    
    // Functions relating to Inventory use
    public void addItem(Item item, int amount) {
    	items.put(item, amount);
    }
    
    public void removeItem(Item item, int amount) {
    	items.remove(item);
    }
}



































