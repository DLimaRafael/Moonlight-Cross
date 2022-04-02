package moonlight_cross.battle;
/*
This interface is responsible for being a template for humanoid enemies.
*/

public interface HumanoidEnemy {
    // Humanoid enemies are able to speak, even if only communicating by grunts
    public void speak(int speech_id);
    // Will handle item usage when implemented.
    public void useItem();
}
