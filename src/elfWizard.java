public class elfWizard extends classTemplate{
    private String className = "Elf Wizard";
    private int classHealth = 100;
    private int classStrength = 13;
    private int classIntelligence = 14;
    private int classDefense = 12;
    elfWizard(){
        super.initializeStats(className, classHealth, classStrength, classIntelligence, classDefense);
    }

    @Override
    public void takeDamage(int damageIncoming) {
        int damageTaken;
        damageTaken = damageIncoming - classDefense;
        super.takeDamage(damageTaken);
    }

}
