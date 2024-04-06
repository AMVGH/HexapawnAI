import java.util.Random;
public class humanBarbarian extends classTemplate{
    private String className = "Human Barbarian";
    private int classHealth = 110;
    private int classStrength = 16;
    private int classIntelligence = 13;
    private int classDefense = 14;
    humanBarbarian(){
        super.initializeStats(className, classHealth, classStrength, classIntelligence, classDefense);
    }

    @Override
    public void takeDamage(int damageIncoming) {
        int damageTaken;
        damageTaken = damageIncoming - classDefense;
        super.takeDamage(damageTaken);
    }
}

