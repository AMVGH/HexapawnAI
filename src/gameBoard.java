import java.util.Random;

public class gameBoard {
    classTemplate[][] gameBoard = new classTemplate[3][3];

    public void displayBoard(){


    }

    public void initializePlayerCharacterSelections(classTemplate[] characterSelections){
        for (int i = 0; i < 3; i++){
            gameBoard[0][i] = characterSelections[i];
        }
    }

    public void initializeAICharacterSelctions(){
        //Random Selction of a Class
        for (int i = 0; i < 3; i++){
            classTemplate temp;
            Random rand = new Random();
            int randomSelection = rand.nextInt(1, 6);
            if (randomSelection == 1) {
                temp = new humanBarbarian();
                gameBoard[2][i] = temp;
            } else if (randomSelection == 2) {
                temp = new elfRanger();
                gameBoard[2][i] = temp;
            } else if (randomSelection == 3) {
                temp = new tieflingWarlock();
                gameBoard[2][i] = temp;
            } else if (randomSelection == 4) {
                temp = new dwarfCleric();
                gameBoard[2][i] = temp;
            } else if (randomSelection == 5) {
                temp = new elfWizard();
                gameBoard[2][i] = new elfWizard();
            }
        }
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
