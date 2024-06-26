import java.util.Random;

public class gameBoard {
    classTemplate[][] gameBoard = new classTemplate[3][3];

    public void displayBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(gameBoard[i][j].getCharacterOwnership() + "\t");
            }
            System.out.println();
        }
    }

    public void initializePlayerCharacterSelections(classTemplate[] characterSelections){
        for (int i = 0; i < 3; i++){
            gameBoard[0][i] = characterSelections[i];
        }
    }

    public void boardSetup(classTemplate[] characterSelections){
        initializeAICharacterSelctions();
        initializePlayerCharacterSelections(characterSelections);
        for (int i = 0; i < 3; i++){
            classTemplate temp = new humanBarbarian();
            gameBoard[1][i] = temp;
        }
    }

    public void initializeAICharacterSelctions(){
        //Random Selection of a Class
        for (int i = 0; i < 3; i++){
            classTemplate temp;
            Random rand = new Random();
            int randomSelection = rand.nextInt(1, 6);
            if (randomSelection == 1) {
                temp = new humanBarbarian();
                temp.setCharacterOwnership("AI");
                gameBoard[2][i] = temp;
            } else if (randomSelection == 2) {
                temp = new elfRanger();
                temp.setCharacterOwnership("AI");
                gameBoard[2][i] = temp;
            } else if (randomSelection == 3) {
                temp = new tieflingWarlock();
                temp.setCharacterOwnership("AI");
                gameBoard[2][i] = temp;
            } else if (randomSelection == 4) {
                temp = new dwarfCleric();
                temp.setCharacterOwnership("AI");
                gameBoard[2][i] = temp;
            } else if (randomSelection == 5) {
                temp = new elfWizard();
                temp.setCharacterOwnership("AI");
                gameBoard[2][i] = temp;
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

    public void updateState(int[] integerBoard){
        //iterate through board
        //if boardLocation [i][j] == 1 set P1
        //if
    }
}
