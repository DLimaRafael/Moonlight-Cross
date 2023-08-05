package battle.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battle.BattleEntity;
import battle.BattleUnits;
import battle.Spell;

public class SpellHandler {
    private List<Spell> spells = new ArrayList<>();

    private boolean usedSpell = false;
    private Scanner input = new Scanner(System.in);

    private BattleEntity owner;

    public SpellHandler(BattleEntity owner){
        this.owner = owner;
    }

    public void showSpells(){
        System.out.println("SPELLS");
        for (Spell i : spells){
            System.out.println("> " + i.getName().toUpperCase());
        }

        System.out.print("CAST: ");
        useSpell(
            seekSpellByName(input.nextLine()),
            BattleUnits.getEnemy()
        );
    }

    public boolean wasSpellUsed(){
        return usedSpell;
    }

    public void addSpell(Spell spell){
        if (spells.contains(spell)) return;
        spells.add(spell);
    }

    public void removeSpell(Spell spell){
        if (!spells.contains(spell)) return;
        spells.remove(spell);
    }

    public Spell seekSpellByName(String name){
        for (Spell s : spells){
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        System.out.println("Invalid spell name!");
        return null;
    }

    public List<Spell> getSpellList(){
        return spells;
    }
    public void setSpellList(List<Spell> spells){
        this.spells = spells;
    }

    public void useSpell(Spell spell, BattleEntity target){
        if (spell == null) return;
        System.out.println(owner.getName() + " casted " + spell.getName() + "!");
        spell.getEffect().cast(target);
        System.out.println("It dealt " + target.getDamage() + " points of damage!");
        usedSpell = true;
    }
}
