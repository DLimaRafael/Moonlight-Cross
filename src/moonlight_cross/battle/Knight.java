package moonlight_cross.battle;

import moonlight_cross.battle.interfaces.HumanoidBehavior;

public class Knight extends BattleEntity implements HumanoidBehavior {

    // Enemy Stats
    public Knight(){
        MAX_HP = 20;
        HP = MAX_HP;
        ATK = 5;
        DEF = 2;
        SPD = 2;
        
        NAME = "Knight";
        DESCRIPTION = "A fellow knight who agreed to spar with you.";
    }
    
    @Override
    public void attack(BattleEntity target){
        super.attack(target);
        System.out.println(NAME + " swings his wooden sword at you!");
        System.out.println("It deals "+ target.damage + " points of damage!");
    }
    
    // Default behavior for all enemies of this type.
    @Override
    public void speak(int speech_id){}
    
    @Override
    public void useItem(){
        System.out.println(NAME + " used a potion!");
        System.out.println("It restores 5 HP!");
        setHp(HP + 5);
    }
}
