package battle;

public final class BattleUnits {
    private static Player player;
    private static Actor enemy;
    
    public static void setPlayer(Player p){
        player = p;
    }
    
    public static Player getPlayer(){
    	return player;
    }
    
    public static void setEnemy(Actor e){
        enemy = e;
    }
    
    public static Actor getEnemy(){
    	return enemy;
    }
}
