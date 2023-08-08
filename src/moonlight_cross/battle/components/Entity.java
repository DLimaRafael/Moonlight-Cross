package moonlight_cross.battle.components;

public abstract class Entity {
    protected String NAME = "";
    protected String DESCRIPTION = "";

    public Entity(String name){
        this.NAME = name;
    }
    
    public String getName(){
        return this.NAME;
    }
    public void setName(String newName){
        this.NAME = newName;
    }

    public String getDescription(){
        return this.DESCRIPTION;
    }
    public void setDescription(String newDescription){
        this.DESCRIPTION = newDescription;
    }

}
