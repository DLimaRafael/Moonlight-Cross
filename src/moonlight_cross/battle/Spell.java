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

    // Amount refers to the effect intensity.
    protected int amount = 0;

    public Effect(boolean is_overtime, Type element, int amount) {
        this.is_overtime = is_overtime;
        this.element = element;
        this.amount = amount;
    }

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
    public DamageEffect(boolean is_overtime, Type element, int amount) {
        super(is_overtime, element, amount);
    }

    @Override
    public void Cast(BattleEntity target) {
        target.takeDamage(amount);
    }
}

class HealingEffect extends Effect {
    public HealingEffect(boolean is_overtime, Type element, int amount) {
        super(is_overtime, element, amount);
    }

    @Override
    public void Cast(BattleEntity target){
        target.setHp(target.getHp() + amount);
        System.out.println(target.NAME + " was healed by " + amount + " HP!");
    }
}

class Fireball extends Spell {
    public Fireball (){
        name = "Fireball";
        description = "The most basic of all offensive spells!";
        cost = 5;
        effect = new DamageEffect(false, Type.FIRE, 5);
    }
}

class Blizzard extends Spell {
    public Blizzard (){
        name = "Blizzard";
        description = "Sends shards of crystallized ice at high velocity.";
        cost = 5;
        effect = new DamageEffect(false, Type.WATER, 5);
    }
}