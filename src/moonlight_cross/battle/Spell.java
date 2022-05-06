package moonlight_cross.battle;

public abstract class Spell {
    protected String name;
    protected String description;
    protected int cost;
    protected Effect effect;

    public String get_name(){
        return name;
    }
    public String get_description(){
        return description;
    }
    public int get_cost(){
        return cost;
    }

    public void set_name(String new_name){
        name = new_name;
    }
    public void set_description(String new_description){
        description = new_description;
    }
    public void set_cost(int new_cost){
        cost = new_cost;
    }
}

// Enum for damage classes
enum Type {
    FIRE,
    WATER,
    WIND,
    NEUTRAL
}

// Effect classes

// Exists to avoid repeated code
abstract class Effect {
    protected boolean is_overtime = false;
    protected Type element = Type.NEUTRAL;
    protected int amount = 0;

    // Cast is declared here and not in the Spell class because if
    // a spell doesn't have an active effect in battle, it makes no
    // sense to allow it to be cast. If it does something else, like
    // a key spell to a certain part of the game, then it's not a default
    // spell, same as key items would work, they're exceptions!
    public abstract void Cast(BattleEntity target);

    public Type getElement() {
        return element;
    }

    public void setElement(Type element) {
        this.element = element;
    }
}

class DamageEffect extends Effect {
    public DamageEffect(int damage, Type element, boolean is_overtime) {
        this.amount = damage;
        this.element = element;
        this.is_overtime = is_overtime;
    }

    @Override
    public void Cast(BattleEntity target) {
        target.takeDamage(amount);
    }
}

class HealingEffect extends Effect {
    @Override
    public void Cast(BattleEntity target){

    }
}

class Fireball extends Spell {
    public Fireball (){
        name = "Fireball";
        description = "The most basic of all offensive spells!";
        cost = 5;
        effect = new DamageEffect(5, Type.FIRE, false);
    }
}

class Blizzard extends Spell {
    public Blizzard (){
        name = "Blizzard";
        description = "Sends shards of crystallized ice at high velocity.";
        cost = 5;
        effect = new DamageEffect(5, Type.WATER, false);
    }
}