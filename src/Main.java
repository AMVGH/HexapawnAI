import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Initializations
        Scanner sc = new Scanner(System.in);
        gameBoard gameBoard = new gameBoard();
        //Menu variable
        int userChoice;
        do {
            displayMenu();
            userChoice = sc.nextInt();
            switch (userChoice){
                case 1:
                    //Variable used to select user's characters
                    int characterChoice;
                    //Choice display
                    System.out.println("Before you can begin your journey, you must chose 3 comrades to join you on your adventure..." +
                            "\n");
                    displayCharacters();

                    //Array initialized to store selected characters
                    classTemplate[] chosenCharacters = new classTemplate[3];
                    //Character selection
                    for (int i = 0; i < 3; i++){
                        System.out.print("Selection "+(i+1)+": ");
                        characterChoice = sc.nextInt();
                        classTemplate temp = chosenCharacter(characterChoice);
                        temp.setCharacterOwnership("P1");
                        chosenCharacters[i] = temp;
                    }
                    System.out.println();
                    //Sets Up Initial Game Board
                    gameBoard.boardSetup(chosenCharacters);
                    //Displays Game Board
                    gameBoard.displayBoard();





                    break;
            }
            //Quit
        } while (userChoice != 2);

    }

    //Outer display menu for either quitting or beginning a match
    public static void displayMenu(){
        System.out.println("1 - Start A Match" +
                "\n2 - Quit");
    }

    //Character choice, stat, and display menu
    public static void displayCharacters(){
        System.out.println("1 - Human Barbarian" +
                "\n" +
                "\n\"Filled with animalistic fury, the Human Barbarian charges headfirst into Battle." +
                "\nWhen his rage takes over, he gains super human strength and resiliance," +
                "\ncoming alive in the chaos of combat\"");
        classTemplate templateClass = new humanBarbarian();
        System.out.println();
        templateClass.displayStats();
        System.out.println();
        System.out.println("2 - Elf Ranger" +
                "\n" +
                "\n\"The Elf Ranger tracks down dangerous monsters with relentless focus, " +
                "\nguarding the borderlands. Even in the harshes of conditions, she will find a" +
                "\nway to survive\"");
        templateClass = new elfRanger();
        System.out.println();
        templateClass.displayStats();
        System.out.println();
        System.out.println("3 - Tiefling Warlock" +
                "\n" +
                "\n\"He made a pact with an otherworldly being and was fundamentally changed as a result." +
                "\nHis magic is stronger than ever... but at what cost?\"");
        templateClass = new tieflingWarlock();
        System.out.println();
        templateClass.displayStats();
        System.out.println();
        System.out.println("4 - Dwarf Cleric" +
                "\n" +
                "\n\"The Dwarf Cleric answers to a divine purpose, calling upon the power of gods. Her" +
                "\ndevotion to her party members, and willingness to smite down foes with her trusty" +
                "\nmace makes her a force to be reckoned with\"");
        templateClass = new dwarfCleric();
        System.out.println();
        templateClass.displayStats();
        System.out.println();
        System.out.println("5 - Elf Wizard" +
                "\n" +
                "\n\"Elegant and unearthly, the Elf Wizard is a brillant and formidable spell caster." +
                "\nEntranced by the promise of power, his quest for magical knowledge never ends.\"");
        templateClass = new elfWizard();
        System.out.println();
        templateClass.displayStats();
        System.out.println();
    }

    //Helper method to assist in character selection
    public static classTemplate chosenCharacter(int selection){
        classTemplate chosenCharacter = null;
        if (selection == 1){
            chosenCharacter = new humanBarbarian();
        } else if (selection == 2) {
            chosenCharacter = new elfRanger();
        } else if (selection == 3) {
            chosenCharacter = new tieflingWarlock();
        } else if (selection == 4) {
            chosenCharacter = new dwarfCleric();
        } else if (selection == 5) {
            chosenCharacter = new elfWizard();
        }
        return chosenCharacter;
    }
}
