package moonlight_cross.battle;

import moonlight_cross.battle.components.ItemEffect;

public abstract class Item {
	protected String name;
	protected String description;
	protected String function;

	protected enum Type {
		HEALING,
		BUFFING,
		DAMAGE,
		KEY
	};

	Type type;

	protected ItemEffect effect;
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getFunction(){
		return function;
	}
	public Type getType(){
		return type;
	}
	public ItemEffect getEffect(){
		return effect;
	}
	
	public void setName(String new_name) {
		name = new_name;
	}
	public void setDescription(String new_description) {
		description = new_description;
	}
	public void setFunction(String new_function){
		function = new_function;
	}
	public void setType(Type new_type){
		type = new_type;
	}
}

// Item types
// For potions, maybe if you set the amount to negative, they'll
// turn into damage items! ... Maybe.
class Heal extends ItemEffect {
	public Heal(double potency) {
		super(potency);
	}

	@Override
	public void cast(BattleEntity target) {
		target.HP += potency;
		System.out.println(target.getName() + " was healed by [" + potency + "]!");
	}
}

// For setting buffs
class Buff extends ItemEffect{
	// The duration is set to a minimum of zero, being the current turn.
	int duration = 0;
	String stat;

	public Buff(int duration, Double mod, String stat) {
		super(mod);
		this.duration = duration;
		this.stat = stat;
	}

	public void addBuff(BattleEntity target) {
		target.setMod(stat, potency, duration);
	}

	public int getDuration(){
		return duration;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}

	@Override
	public void cast(BattleEntity target) {
		addBuff(target);
	}
}

// ITEMS BELOW
/*
* Note to self:
* This needs some heavy adjustments, handling massive amounts
* of items "in-code" seems impractical and rather tedious.
*/
class Potion extends Item {
	public Potion () {
		name = "Potion";
		description = "A simple potion";
		function = "Heals a small amount of HP.";
		type = Type.HEALING;
		effect = new Heal(15);
	}
}

class Elixir extends Item {
	public Elixir () {
		name = "Elixir of Vitality";
		description = "A golden colored liquid, surely it's not...?";
		function = "Heals a moderate amount of HP";
		type = Type.HEALING;
		effect = new Heal(45);
	}
}

class StrengthPotion extends Item {
	public StrengthPotion () {
		name = "Fortifier";
		description = "A concoction made for temporarily increasing your strength, it won't give you muscles though.";
		function = "Increases ATK attribute for 3 turns";
		type = Type.BUFFING;
		effect = new Buff(3, 0.5, "ATK");
	}
}
