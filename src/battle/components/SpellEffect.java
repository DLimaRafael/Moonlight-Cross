package battle.components;

import battle.BattleEntity;

public abstract class SpellEffect {
    protected boolean is_overtime = false;

    // Amount refers to the effect intensity.
    protected int potency = 0;

    public SpellEffect(boolean is_overtime, int potency) {
        this.is_overtime = is_overtime;
        this.potency = potency;
    }

    // Cast is declared here and not in the Spell class because if
    // a spell doesn't have an active effect in battle, it makes no
    // sense to allow it to be cast. If it does something else, like
    // a key spell to a certain part of the game, then it's not a default
    // spell, same as key items would work, they're exceptions!
    public abstract void cast(BattleEntity target);
}