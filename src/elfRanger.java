public class elfRanger extends classTemplate{
    private String className = "Elf Ranger";
    private int classHealth = 90;
    private int classStrength = 12;
    private int classIntelligence = 14;
    private int classDefense = 14;
    elfRanger(){
        super.initializeStats(className, classHealth, classStrength, classIntelligence, classDefense);
    }

    @Override
    public void takeDamage(int damageIncoming) {
        int damageTaken;
        damageTaken = damageIncoming - classDefense;
        super.takeDamage(damageTaken);
    }
}

