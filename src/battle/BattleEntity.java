package battle;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import battle.components.Modifier;

/*
Different from the interfaces, which will allocate enemies in specific types,
the abstract class BattleEntity just identifies them as a BattleEntity, implementing
default methods that won't need to be built by the entities themselves, 
such as setters and getters.
*/
public abstract class BattleEntity {
    // Entity Stats
    protected int MAX_HP = 0;
    protected int HP = 0;
    protected int ATK = 0;
    protected int DEF = 0;
    protected int SPD = 0;
    
    protected String NAME = "";
    protected String DESCRIPTION = "";
    
    protected boolean isAlive = true;
    protected int damage = 0; // Variable to keep track of damage dealt/received.

    // Modifiers
    /********************************************************************************
     * Modifiers are temporary entities, created and deleted dynamically. You can have
     * both percentage based modifiers and addition based modifiers, the first is
     * for buffs and debuffs, the latter is for equipment.
    *********************************************************************************/
    HashMap<String, List<Modifier>> mods = new HashMap<>();
    
    // Getters and Setters
    // Note: Rework this for more adaptable code.
    public int getAtk(){
        return ATK;
    }
    public void setAtk(int amount){
        ATK = amount;
    }
    
    public int getMaxHp(){
        return MAX_HP;
    }
    public void setMaxHp(int amount){
        MAX_HP = amount;
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
    public void setName(String newName){
        NAME = newName;
    }

    public String getDescription(){
        return DESCRIPTION;
    }
    public void setDescription(String newDescription){
        DESCRIPTION = newDescription;
    }

    public int getDamage() {
        return damage;
    }

    public int getTotalModifier(String attrName, int attrValue) {
        int totalModifier = 0;
        List<Modifier> statModifiers = mods.get(attrName);
        if (statModifiers != null) {
            for (Modifier modifier : statModifiers) {
                if (modifier.isPercentage()) {
                    totalModifier += (attrValue * modifier.getPotency()) / 100;
                } else {
                    totalModifier += modifier.getPotency();
                }
            }
        }
        return totalModifier;
    }

    /*
     * Methods for battle behavior, although this could be transported
     * to a component, considering some outside-of-battle entities might
     * have basic attributes, but lack any of these functions.
     */

    public void attack(BattleEntity target){
        damage = ATK + getTotalModifier("atk", ATK);
        target.takeDamage(damage);
    }
    
    public void takeDamage(int amount){
        // Checks if there's a modifier applicable to Defense
        int modifiedDef = DEF + getTotalModifier("def", DEF);
        damage = Math.max(amount - modifiedDef, 0);
        setHp(HP - damage);
    }
    
    public void defend(){
        // Increases defense by 50%
        setMod("def", 50, 1, true);
    }
    public void showInfo(){
        System.out.println("NAME: "+NAME+"\n"+DESCRIPTION);
    }
    
    /*
     * This collection of functions serve the purpose of giving support
     * to a buff/debuff system by matching Strings and modifiers, applying
     * them to stats and cleaning afterwards.
    */
    
    public void setMod(String stat, int potency, int duration, boolean isPercentage) {
        List<Modifier> statModifiers = mods.get(stat);
        if (statModifiers == null) {
            statModifiers = new ArrayList<>();
            mods.put(stat, statModifiers);
        }
        statModifiers.add(new Modifier(isPercentage, potency, duration));
    }
    
    /***
     * Called every turn to check and update the modifier list.
     ***/
    public void calcMods() {
        // Update durations and remove expired modifiers using Java 8 streams
        mods.forEach((attr, modifierList) -> 
            modifierList.removeIf(mod -> {
                if (mod.getDuration() > 0) {
                    mod.setDuration(-1); // Reduce duration otherwise
                    if (mod.getDuration() == 0) return true;
                }
                return false;
            })
        );
    
        // Remove empty lists from the mods map using Java 8 streams
        mods.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}