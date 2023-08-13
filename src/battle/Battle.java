package battle;

import java.util.Scanner;

import engine.InputHandler;
import engine.Render;

public final class Battle {
    Scanner input = new Scanner(System.in);
    
    static Player player;
    static Actor enemy;
    
    boolean turnEnd = false;
    
    public void battleStart(){
    	BattleUnits.setPlayer(new Player());
    	BattleUnits.setEnemy(new Knight());
        if (BattleUnits.getPlayer() == null || BattleUnits.getEnemy() == null) return;
    	player = BattleUnits.getPlayer();
    	enemy = BattleUnits.getEnemy();
        battleControl();
    }
    
    public void battleEnd(){
        if (player.isAlive) {
            System.out.println("You've defeated " + enemy.getName() + "!");
        } else {
            System.out.println(enemy.getName() + " has defeated you!");
        }
        input.close();
    }
    
    /*************************************************************************
    * Controls the flow of battle, alternating between enemy and player turns,
    * recalculating modifiers, etc.
    * !!!This needs refactoring for multiple enemies/allies!!!
    **************************************************************************/
    public void battleControl(){
        while(player.isAlive && enemy.isAlive){
            Render.clearScreen();
            printUI();
            playerTurn();
            if (turnEnd) {
                System.out.println(player.getAtk());
                System.out.println(player.getDef());
                System.out.println(enemy.getAtk());
                System.out.println(enemy.getDef());
                enemyTurn();
                System.out.print("...");
                try {System.in.read();} catch (Exception e){}
            }
        }
        battleEnd();
    }
    
    public void printUI(){
        Render.printSeparator('=');
        System.out.println(String.format("HP: [%d/%d]", player.getHp(), player.getMaxHp()));
        System.out.println(String.format("MP: [%d/%d]", player.MOON, player.MAX_MOON));
        // System.out.println("Enemy HP: "+ enemy.HP + "\nYour HP: "+ player.HP);
        Render.printSeparator('=');
    }    
    
    public void playerTurn(){
        player.calcMods();
        System.out.println("Player's turn.");
        System.out.println("Choose your action...");
        // There must be a better way to do this.
        for (String i : player.actions.keySet()) {
        	System.out.println(player.actions.get(i) + " - " + i);
        }
        
        /************************************************************************* 
        * Loops until the player takes an action that should end their turn. This avoids
        * having to check for invalid commands, as the InputHandler already deals with that
        * on its own, making input handling much easier.
        **************************************************************************/
        // Converts action Map into a String array.
        String[] actionList = player.actions.values().toArray(new String[0]);
        String action = InputHandler.readString("> ", actionList);

        if (action.equalsIgnoreCase("info")){
            enemy.showInfo();
            System.out.print("...");
            try {System.in.read();} catch (Exception e){}
            return;
        }
        // Attacking
        if (action.equalsIgnoreCase("atk")) {
            player.attack(enemy);
        // Spells
        } else if (action.equalsIgnoreCase("mgc")){
            player.mgc.showSpells();
            if (player.mgc.wasSpellUsed() == false) return;
        // Inventory
        } else if (action.equalsIgnoreCase("inv")) {
        	player.inv.showInv();
            if (player.inv.wasItemUsed() == false) return;
        // Defending
        } else if (action.equalsIgnoreCase("def")){
            player.defend();
        }

        turnEnd = true;
    }
    
    public void enemyTurn(){
        enemy.attack(player);
    }
    
}
