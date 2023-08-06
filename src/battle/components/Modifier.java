package battle.components;

public class Modifier {
    private boolean isPercentage;
    private int potency;
    private int duration;

    public Modifier(boolean isPercentage, int potency, int duration){
        this.isPercentage = isPercentage;
        this.potency = potency;
        this.duration = duration;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public int getPotency() {
        return potency;
    }

    public int getDuration() {
        return duration;
    }

    /*** Increases duration by specified amount. ***/
    public void setDuration(int amount) {
        // Modifiers with a value below 0 are considered permanent.
        if (duration < 0) return;
        duration += amount;
    }
}
