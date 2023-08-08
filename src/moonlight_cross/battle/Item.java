package moonlight_cross.battle;

import moonlight_cross.battle.components.Entity;

public abstract class Item extends Entity {
	public Item(String name){
		super(name);
	}
	protected String function;

	protected enum Type {
		HEALING,
		BUFFING,
		DAMAGE,
		KEY
	};

	Type type;

	protected Effect effect;
	public Effect getEffect(){
		return effect;
	}

	public Type getType(){
		return type;
	}
	public void setType(Type new_type){
		type = new_type;
	}
	
	public void setFunction(String new_function){
		function = new_function;
	}
	public String getFunction(){
		return function;
	}
}

// Item types
// For potions, maybe if you set the amount to negative, they'll
// turn into damage items! ... Maybe.
class Heal extends Effect {
	public Heal(int potency) {
		super(potency);
	}

	@Override
	public void cast(Actor target) {
		target.setHp( 
			target.getHp() + this.getPotency() 
		);
		System.out.println(target.getName() + " was healed by [" + this.getPotency() + "]!");
	}
}

// ITEMS BELOW
/*
* Note to self:
* This needs some heavy adjustments, handling massive amounts
* of items "in-code" seems impractical and rather tedious.
*/
class Potion extends Item {
	public Potion(){
		super("Potion");
		DESCRIPTION = "A simple potion";
		function = "Heals a small amount of HP.";
		type = Type.HEALING;
		effect = new Heal(15);
	}
}

class Elixir extends Item {
	public Elixir(){
		super("Elixir of Vitality");
		DESCRIPTION = "A golden colored liquid, surely it's not...?";
		function = "Heals a moderate amount of HP";
		type = Type.HEALING;
		effect = new Heal(45);
	}
}

class StrengthPotion extends Item {
	public StrengthPotion(){
		super("Fortifier");
		DESCRIPTION = "A concoction made for temporarily increasing your strength, it won't give you muscles though.";
		function = "Increases ATK attribute for 3 turns";
		type = Type.BUFFING;
		effect = new Buff(3, 0.5, "ATK");
	}
}
