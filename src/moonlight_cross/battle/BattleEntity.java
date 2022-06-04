package moonlight_cross.battle;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    HashMap<String, ArrayList<Double>> mods = new HashMap<>();
    List<Spell> spells = new ArrayList<>();
    
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
        HP = Math.max(amount, 0);
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
    
    public String getName(){
        return NAME;
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    public void attack(BattleEntity target){
    	// Checks if there's a modifier applicable to Attack
    	int atk_mod = (int) (getMod("atk") * ATK);
        target.takeDamage(ATK + atk_mod);
    }
    
    public void takeDamage(int amount){
        // Checks if there's a modifier applicable to Defense
    	double def_mod = (getMod("def") * DEF);
        /* Casts the final result as an int, since, if the modifier is
        * initially lower than 1, it'll simply become zero. */
        System.out.println(def_mod + " " + 3 + 0.5);
        damage = Math.max(amount - (int) (DEF + def_mod), 0);
        setHp(HP - damage);
    }
    
    public void defend(){
        // Increases defense by 50%
        System.out.println(NAME + " is defending!");
        setMod("def", 0.50, 1);
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
                // makes mod equal to the actual effect.
    			mod = mods.get(i).get(0);
    		}
    	}
    	return mod;
    }
    
    public void setMod(String stat, Double mod, int duration) {
    	mods.put(stat, new ArrayList<Double>());
        mods.get(stat).add(mod);
        mods.get(stat).add(Double.valueOf(duration));
        //System.out.println(mods);
    }
    
    public void calcMods() {
    	if (mods.isEmpty()) return; // Self-explanatory
    	// Iterates through mods for every String key in there
    	for (String j : mods.keySet()) {
            Double mod_duration = mods.get(j).get(1);
    		// Checks if modifier expired.
    		if (mod_duration <= 0) {
                //System.out.println("Removing modifier " + j + "...");
                mods.remove(j);
            } else {
                // Reduces duration counter otherwise
                mod_duration -= 1;
                mods.get(j).set(1, mod_duration);
                //System.out.println("Reduced duration counter of " + j + " modifier.");
            }
    	}
        //System.out.println("New modifier list: " + mods);
    }
}



































