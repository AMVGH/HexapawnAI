import java.util.Random;

public class gameBoard {
    classTemplate[][] gameBoard = new classTemplate[3][3];

    public void displayBoard(){

    }

    public void updatePlayerCharacterSelections(classTemplate[] characterSelections){


    }

    public void battle(classTemplate characterOne, classTemplate characterTwo){
        while (characterOne.isAlive() && characterTwo.isAlive()) {
            Random rand = new Random();
            int random = rand.nextInt(0, 2);
            if (random == 0) {
                if (characterOne.attackChance() == true) {
                    characterTwo.takeDamage(characterOne.dealDamage());
                    displayFight(characterOne, characterTwo);
                } else {
                    continue;
                }
            } else if (random == 1) {
                if (characterTwo.attackChance() == true) {
                    characterOne.takeDamage(characterTwo.dealDamage());
                    displayFight(characterTwo, characterOne);
                } else {
                    continue;
                }
            }
        }
        if (characterOne.isAlive()){
            System.out.println(characterOne.getName() + " Comes Out Victorious!");
        } else System.out.println(characterTwo.getName() + " Comes Out Victorious!");
    }

    public void displayFight(classTemplate characterOne, classTemplate characterTwo){
        System.out.println();
        System.out.println("----------------------");
        characterTwo.displayHealth();
        characterOne.displayHealth();
        System.out.println("----------------------");
        System.out.println();
    }




}
