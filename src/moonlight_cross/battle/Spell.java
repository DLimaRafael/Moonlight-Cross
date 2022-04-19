package moonlight_cross.battle;

public abstract class Spell {
    protected String name;
    protected String description;
    protected Type type;
    protected int cost;

    public abstract void cast(BattleEntity target);

    public String get_name(){
        return name;
    }
    public String get_description(){
        return description;
    }
    public Type get_type(){
        return type;
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
    public void set_type(Type new_type){
        type = new_type;
    }
    public void set_cost(int new_cost){
        cost = new_cost;
    }
}

// Enum for damage classes
enum Type {
    FIRE,
    WATER,
    WIND
}

class Fireball extends Spell {
    int damage;
    public Fireball (){
        name = "Fireball";
        description = "The most basic of all offensive spells!";
        type = Type.FIRE;
        cost = 5;
        damage = 10;
    }

    @Override
    public void cast(BattleEntity target) {
        System.out.println("A " + name + " has been casted.");
        target.takeDamage(damage);
    }

}