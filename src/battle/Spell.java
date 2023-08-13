package battle;

import battle.components.Entity;

public abstract class Spell extends Entity {
    public Spell(String name){
        super(name);
    }
    
    protected int cost;
    protected Effect effect;

    protected enum Type {
        FIRE,
        WATER,
        WIND,
        NEUTRAL
    }

    Type type;
    
    protected Type element = Type.NEUTRAL;

    public int getCost(){
        return cost;
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

    public Effect getEffect() {
        return effect;
    }
    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}

class Fireball extends Spell {
    public Fireball(){
        super("Fireball");
        DESCRIPTION = "The most basic of all offensive spells!";
        cost = 5;
        element = Type.FIRE;
        effect = new DamageEffect(false, 10);
    }
}

class Blizzard extends Spell {
    public Blizzard(){
        super("Blizzard");
        DESCRIPTION = "Sends shards of crystallized ice at high velocity.";
        cost = 5;
        element = Type.WATER;
        effect = new DamageEffect(false, 10);
    }
}