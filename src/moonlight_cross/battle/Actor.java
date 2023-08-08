package moonlight_cross.battle;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

import moonlight_cross.battle.components.Entity;

/*
Different from the interfaces, which will allocate enemies in specific types,
the abstract class Enemy just identifies them as an enemy, implementing
default methods that won't need to be built by the entities themselves, 
such as setters and getters.
*/
public abstract class Actor extends Entity {
    public Actor(String name){
        super(name);
    }
    
    // Stats
    protected int MAX_HP = 0;
    protected int HP = 0;
    protected int ATK = 0;
    protected int DEF = 0;
    protected int SPD = 0;
    
    protected boolean isAlive = true;
    protected int damage = 0; // Variable to keep track of damage dealt/received.

    // Modifiers
    /********************************************************************************
     * The way this works is simple, whenever you modify a stat, instead of changing
     * the variable itself, you multiply it by the entry corresponding to it inside
     * mods, this leaves the original attribute intact, allowing for cleansing
     * of buffs/debuffs
    *********************************************************************************/
    HashMap<String, ArrayList<Double>> mods = new HashMap<>();
    
    // Getters and Setters
    // Note: Rework this for more adaptable code.
    public int getAtk(){
        return this.ATK;
    }
    public void setAtk(int amount){
        this.ATK = amount;
    }
    
    public int getHp(){
        return this.HP;
    }
    public void setHp(int amount){
        this.HP = Math.max(amount, 0);
        if (this.HP <= 0){
            this.isAlive = false;
        }
    }
    
    public int getDef(){
        return this.DEF;
    }
    public void setDef(int amount){
        this.DEF = amount;
    }
    
    public int getSpd(){
        return this.SPD;
    }
    public void setSpd(int amount){
        this.SPD = amount;
    }

    public int getDamage() {
        return this.damage;
    }

    /*
     * Methods for battle behavior, although this could be transported
     * to a component, considering some outside-of-battle entities might
     * have basic attributes, but lack any of these functions.
     */

    public void attack(Actor target){
    	// Checks if there's a modifier applicable to Attack
    	int atk_mod = (int) (getMod("atk") * ATK);
        target.takeDamage(ATK + atk_mod);
    }
    
    public void takeDamage(int amount){
        // Checks if there's a modifier applicable to Defense
    	double def_mod = (getMod("def") * DEF);
        /*
        * Casts the final result as an int, since, if the modifier is
        * initially lower than 1, it'll simply become zero.
        */
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
    */

    /***
     * Function for getting a specific modifier from the mods list.
     * @return the modifier's potency as a double.
     ***/
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
    }
    
    /***
     * Called every turn to check and update the modifier list.
     ***/
    public void calcMods() {
    	if (mods.isEmpty()) return; // Self-explanatory
    	// Iterates through mods for every String key in there
    	for (String j : mods.keySet()) {
            Double mod_duration = mods.get(j).get(1);
    		// Checks if modifier expired.
    		if (mod_duration <= 0) {
                mods.remove(j);
            } else {
                // Reduces duration counter otherwise
                mod_duration -= 1;
                mods.get(j).set(1, mod_duration);
            }
    	}
    }
}



































