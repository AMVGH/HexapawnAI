import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        gameBoard gameBoard = new gameBoard();
        int userChoice;

        do {
            displayMenu();
            userChoice = sc.nextInt();
            switch (userChoice){
                case 1:
                    int characterChoice;


                    System.out.println("Before you can begin your journey, you must chose 3 comrades to join you on your adventure..." +
                            "\n");
                    displayCharacters();

                    System.out.print("Selction 1: ");
                    characterChoice = sc.nextInt();
                    classTemplate characterOne = chosenCharacter(characterChoice);
                    System.out.print("Selction 2: ");
                    characterChoice = sc.nextInt();
                    classTemplate characterTwo = chosenCharacter(characterChoice);
                    System.out.print("Selction 3: ");
                    characterChoice = sc.nextInt();
                    classTemplate characterThree = chosenCharacter(characterChoice);

                    classTemplate[] chosenCharacters = {characterOne, characterTwo, characterThree};

                    gameBoard.updatePlayerCharacterSelections(chosenCharacters);


                    break;
            }
        } while (userChoice != 2);

    }

    public static void displayMenu(){
        System.out.println("1 - Start A Match" +
                "\n2 - Quit");
    }

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
