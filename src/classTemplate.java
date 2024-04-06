import java.util.Random;
import java.lang.Math;

public abstract class classTemplate {
    private String Name;
    private int Health;
    private int Strength;
    private int Intelligence;
    private int Defense;
    private String characterOwnership = "XX";
    private boolean canTakeTurn = true;
    private boolean isAlive = true;

    //Initializes stats based upon what is passed from daughter character class
    public void initializeStats(String className, int classHealth, int classStrength, int classIntelligence, int classDefense){
        Name = className;
        Health = classHealth;
        Strength = classStrength;
        Intelligence = classIntelligence;
        Defense = classDefense;
    }
    //Displays Character Statistics
    public void displayStats(){
        String displayStatistics = "Class Name: " + Name
                +"\nClass Health: " + Health
                +"\nClass Strength: " + Strength
                +"\nClass Intelligence " + Intelligence
                +"\nClass Defense: " + Defense;
        System.out.println("----------------------");
        System.out.println(displayStatistics);
        System.out.println("----------------------");

    }

    public void displayHealth(){
        System.out.println(Name +"'s Health: " + Health);
    }
    //Incorporates Intelligence. The lower the intelligence on the class, the higher chance that an attack "misses". (Turn is skipped)
    public boolean attackChance(){
        Random random = new Random();
        int randomNum = random.nextInt(8, 18);
        if (Intelligence >= randomNum){
            canTakeTurn = true;
            System.out.println(Name + "'s Attack Hit!");
        } else {
            canTakeTurn = false;
            System.out.println(Name + "'s Attack Missed!");
        }
        return canTakeTurn;
    }
    //Returns Damage Number
    public int dealDamage() {
        Random random = new Random();
        int damageNum = random.nextInt(Strength);
        return damageNum;
    }
    //Updates Health Based on Incoming Damage
    public void takeDamage(int damageIncoming){
        //Must be absolute value or there is a chance of subtracting a negative according to armor. (Character will gain health upon being hit)
        Health = Health - Math.abs(damageIncoming);
        if (Health <= 0){
            //Cleanup so no negative numbers are shown in combat
            Health = 0;
            //Update state
            isAlive = false;
            canTakeTurn = false;
        }
    }
    //Used to see whether a peice belongs to an AI or Player.
    public String getCharacterOwnership(){
        return characterOwnership;
    }

    //Sets ownership to either an AI or Player
    public void setCharacterOwnership(String characterOwner){
        characterOwnership = characterOwner;
    }
    //If false no turns should be awarded to character, used to drive a lot of interactions within game
    public boolean isAlive() {
        return isAlive;
    }
    public String getName(){
        return Name;
    }
}
