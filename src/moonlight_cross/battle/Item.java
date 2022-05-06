package moonlight_cross.battle;

public abstract class Item {
	protected String name;
	protected String description;
	protected String function;
	protected String type;

	protected Object effect;
	
	public String get_name() {
		return name;
	}
	public String get_description() {
		return description;
	}
	public String get_function(){
		return function;
	}
	public String get_type(){
		return type;
	}
	
	public void set_name(String new_name) {
		name = new_name;
	}
	public void set_description(String new_description) {
		description = new_description;
	}
	public void set_function(String new_function){
		function = new_function;
	}
	public void set_type(String new_type){
		type = new_type;
	}
}

// Item types

// For potions, maybe if you set the amount to negative, they'll
// turn into damage items! ... Maybe.
class Heal {
	double effect_amount = 0.0;

	public Heal (double power) {
		effect_amount = power;
	}
	public void heal(BattleEntity target) {
		target.HP += effect_amount;
		System.out.println(target.NAME + " was healed by [" + effect_amount + "]!");
	}
}

// For setting buffs
class Buff {
	// The duration is set to a minimum of zero, being the current turn.
	int duration = 0;
	Double mod = 0.0;
	String stat;
	public Buff(int duration, Double mod, String stat) {
		this.duration = duration;
		this.mod = mod;
		this.stat = stat;
	}
	public void add_buff(BattleEntity target) {
		target.setMod(stat, mod, duration);
	}

	public int get_duration(){
		return duration;
	}
	public void set_duration(int duration){
		this.duration = duration;
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
		type = "Healing";
		effect = new Heal(15);
	}
}

class Elixir extends Item {
	public Elixir () {
		name = "Elixir of Vitality";
		description = "A golden colored liquid, surely it's not...?";
		function = "Heals a moderate amount of HP";
		type = "Healing";
		effect = new Heal(45);
	}
}

class StrengthPotion extends Item {
	public StrengthPotion () {
		name = "Fortifier";
		description = "A concoction made for temporarily increasing your strength, it won't give you muscles though.";
		function = "Increases ATK attribute for 3 turns";
		type = "Utility";
		effect = new Buff(3, 0.5, "ATK");
	}
}
