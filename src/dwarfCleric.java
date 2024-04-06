public class dwarfCleric extends classTemplate {
    private String className = "Dwarf Cleric";
    private int classHealth = 100;
    private int classStrength = 14;
    private int classIntelligence = 11;
    private int classDefense = 18;
    dwarfCleric(){
        super.initializeStats(className, classHealth, classStrength, classIntelligence, classDefense);
    }

    @Override
    public void takeDamage(int damageIncoming) {
        int damageTaken;
        damageTaken = damageIncoming - classDefense;
        super.takeDamage(damageTaken);
    }
}
