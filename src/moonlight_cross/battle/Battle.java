package moonlight_cross.battle;

import java.util.Scanner;

public class Battle {
    Scanner input = new Scanner(System.in);
    
    static Player player;
    static BattleEntity enemy;
    
    boolean turn_end = false;
    
    public static void battleStart(){
    	BattleUnits.setPlayer(new Player());
    	BattleUnits.setEnemy(new Knight());
        if (BattleUnits.getPlayer() == null || BattleUnits.getEnemy() == null) return;
    	player = BattleUnits.getPlayer();
    	enemy = BattleUnits.getEnemy();
        new Battle().battleControl();
    }
    
    public void battleEnd(){
        System.out.println(
        	"Battle finished!\nEnemy HP: "+enemy.HP+
        	"\nPlayer HP: "+player.HP
        );
        input.close();
    }
    
    /* Controls the flow of battle, alternating between enemy and player turns,
    * recalculating modifiers, etc.
    */
    public void battleControl(){
        while(player.isAlive || enemy.isAlive){
            if (!player.isAlive || !enemy.isAlive) break;
            turn_end = false;
            printUI();
            playerTurn();
            if (turn_end){
                player.calcMods();
                enemyTurn();
                System.out.println("Press Enter to finish round.");
                try {System.in.read();} catch (Exception e){}
            }
        }
        battleEnd();
    }
    
    public void printUI(){
        System.out.println("Enemy HP: "+ enemy.HP + "\nYour HP: "+ player.HP);
    }    
    
    public void playerTurn(){
        System.out.println("Player's turn.");
        System.out.println("Choose your action...");
        // There must be a better way to do this.
        for (String i : player.actions.keySet()) {
        	System.out.println(player.actions.get(i) + " - " + i);
        }
        System.out.print("> ");
        String action = input.next();
        
        // Disables the enemy turn, since this is an action that shouldn't
        // finish the player's turn.
        if (action.equalsIgnoreCase("info")){
            enemy.showInfo();
            System.out.println("Press Enter to go back...");
            try {System.in.read();} catch (Exception e){}
            return;
        }
        // Attacking
        if (action.equalsIgnoreCase("atk")) {
            player.attack(enemy);
        // Spells
        } else if (action.equalsIgnoreCase("mgc")){
            player.useSpell();
            return;
        // Inventory
        } else if (action.equalsIgnoreCase("inv")) {
        	player.inv.showInv();
            if (player.inv.wasItemUsed() == false) return;
        // Defending
        } else if (action.equalsIgnoreCase("def")){
            player.defend();
        // Invalid Commands
        } else {
        	System.out.println("Invalid command \"" + action + "\".");
        	return;
        }
        turn_end = true;
    }
    
    public void enemyTurn(){
        enemy.attack(player);
    }
    
}
