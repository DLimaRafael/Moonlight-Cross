package moonlight_cross.battle;

public class BattleUnits {
    private static Player player;
    private static BattleEntity enemy;
    
    public static void setPlayer(Player p){
        player = p;
    }
    
    public static Player getPlayer(){
    	return player;
    }
    
    public static void setEnemy(BattleEntity e){
        enemy = e;
    }
    
    public static BattleEntity getEnemy(){
    	return enemy;
    }
}
