package moonlight_cross.battle.components;

import moonlight_cross.battle.BattleEntity;

public abstract class ItemEffect {
	protected double potency = 0.0;

	public ItemEffect (double potency){
		this.potency = potency;
	}

	public abstract void cast(BattleEntity target);
}
