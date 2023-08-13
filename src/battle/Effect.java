package battle;

public abstract class Effect {
    int potency = 0;
    boolean is_overtime = false;

    //Overloaded method- With and without is_overtime
    public Effect(boolean is_overtime, int potency) {
        this.is_overtime = is_overtime;
        this.potency = potency;
    }
    public Effect(int potency){
        this.potency = potency;
    }

    public int getPotency(){
        return potency;
    }

    public abstract void cast(Actor target);
}

class DamageEffect extends Effect {
    public DamageEffect(boolean is_overtime, int potency) {
        super(is_overtime, potency);
    }

    @Override
    public void cast(Actor target) {
        target.takeDamage(this.getPotency());
    }
}

class HealingEffect extends Effect {
    public HealingEffect(boolean is_overtime, int amount) {
        super(is_overtime, amount);
    }

    @Override
    public void cast(Actor target){
        target.setHp(target.getHp() + this.getPotency());
        System.out.println(target.getName() + " was healed by " + this.getPotency() + " HP!");
    }
}

class Buff extends Effect{
	// The duration is set to a minimum of zero, being the current turn.
	int duration = 0;
	String stat;

	public Buff(int duration, int mod, String stat) {
		super(mod);
		this.duration = duration;
		this.stat = stat;
	}

	//
	public void addBuff(Actor target) {
		target.setMod(stat, potency, duration, true);
	}

	public int getDuration(){
		return duration;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}

	@Override
	public void cast(Actor target) {
		addBuff(target);
	}
}

