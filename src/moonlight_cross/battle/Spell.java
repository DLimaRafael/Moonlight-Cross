package moonlight_cross.battle;

import moonlight_cross.battle.components.SpellEffect;

public abstract class Spell {
    protected String name;
    protected String description;
    protected int cost;
    protected SpellEffect effect;

    protected enum Type {
        FIRE,
        WATER,
        WIND,
        NEUTRAL
    }
    
    protected Type element = Type.NEUTRAL;

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getCost(){
        return cost;
    }

    public void setName(String new_name){
        name = new_name;
    }
    public void setDescription(String new_description){
        description = new_description;
    }
    public void setCost(int new_cost){
        cost = new_cost;
    }

    public Type getElement() {
        return element;
    }
    public void setElement(Type element) {
        this.element = element;
    }
}

class DamageEffect extends SpellEffect {
    public DamageEffect(boolean is_overtime, int potency) {
        super(is_overtime, potency);
    }

    @Override
    public void cast(BattleEntity target) {
        target.takeDamage(potency);
    }
}

class HealingEffect extends SpellEffect {
    public HealingEffect(boolean is_overtime, int amount) {
        super(is_overtime, amount);
    }

    @Override
    public void cast(BattleEntity target){
        target.setHp(target.getHp() + potency);
        System.out.println(target.NAME + " was healed by " + potency + " HP!");
    }
}

class Fireball extends Spell {
    public Fireball (){
        name = "Fireball";
        description = "The most basic of all offensive spells!";
        cost = 5;
        element = Type.FIRE;
        effect = new DamageEffect(false, 5);
    }
}

class Blizzard extends Spell {
    public Blizzard (){
        name = "Blizzard";
        description = "Sends shards of crystallized ice at high velocity.";
        cost = 5;
        element = Type.WATER;
        effect = new DamageEffect(false, 5);
    }
}