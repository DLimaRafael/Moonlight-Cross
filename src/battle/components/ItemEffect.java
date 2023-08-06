package battle.components;

import battle.BattleEntity;

public abstract class ItemEffect {
	protected int potency = 0;

	public ItemEffect (int potency){
		this.potency = potency;
	}

	public abstract void cast(BattleEntity target);
}
