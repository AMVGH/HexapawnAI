public class tieflingWarlock extends classTemplate{
    private String className = "Tiefling Warlock";
    private int classHealth = 95;
    private int classStrength = 10;
    private int classIntelligence = 15;
    private int classDefense = 12;
    tieflingWarlock(){
        super.initializeStats(className, classHealth, classStrength, classIntelligence, classDefense);
    }

    @Override
    public void takeDamage(int damageIncoming) {
        int damageTaken;
        damageTaken = damageIncoming - classDefense;
        super.takeDamage(damageTaken);
    }
}
