import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class runs a Kennel
 *
 * @author Lynda Thomas, Chris Loftus and Faisal Rezwan, LOV2
 * @version 3 (20th February 2023)
 */
public class KennelDemo {
    private String filename; // holds the name of the file
    private Kennel kennel; // holds the kennel
    private Scanner scan; // so we can read from keyboard

    private KennelDemo() {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information (PetsRus.txt is the default): ");
        filename = scan.nextLine();

        kennel = new Kennel();
    }

    /*
     * initialise() method runs from the main and reads from a file
     */
    private void initialise() {
        Animal animal = new Animal();
        System.out.println("Using file " + filename);

        try {
            kennel.load(animal, filename);
        } catch (FileNotFoundException e) {
            System.err.println("The file: " + filename + " does not exist. Assuming first use and an empty file." +
                    " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e) {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /*
     * runMenu() method runs from the main and allows entry of data etc
     */
    private void runMenu() {
        String response;
        do {
            printMenu();
            System.out.println("What would you like to do:");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    admitAnimal();
                    break;
                case "2":
                    changeKennelName();
                    break;
                case "3":
                    printAll();
                    break;
                case "4":
                    searchForAnimal();
                    break;
                case "5":
                    removeAnimal();
                    break;
                case "6":
                    setKennelCapacity();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu() {
        System.out.println("1 -  add a new Pet");
        System.out.println("2 -  set up Kennel name");
        System.out.println("3 -  display all Pets");
        System.out.println("4 -  search for a Pet");
        System.out.println("5 -  remove a Pet");
        System.out.println("6 -  set kennel capacity");
        System.out.println("q - Quit");
        System.out.println("File will be saved when you quit.");
    }

    private void setKennelCapacity() {
        System.out.print("Enter max number of Pets: ");
        int max = scan.nextInt();
        scan.nextLine();
        kennel.setCapacity(max);
    }

    /*
     * printAll() method runs from the main and prints status
     */
    private void printAll() {
        System.out.println(kennel);
    }

    /*
     * save() method runs from the main and writes back to file
     */
    private void save() {
        try {
            kennel.save(filename);
        } catch (IOException e) {
            System.err.println("Problem when trying to write to file: " + filename);
        }
    }

    private void removeAnimal() {
        System.out.println("which animal do you want to remove");
        String animaltoberemoved;
        animaltoberemoved = scan.nextLine();
        kennel.removeCat(animaltoberemoved);
    }

    private void searchForAnimal() {
        System.out.println("which animal do you want to search for");
        String name = scan.nextLine();
        Animal animal = kennel.search(name);
        if (animal!= null) {
            System.out.println(animal.toString());
        } else {
            System.out.println("Could not find animal: " + name);
        }
    }

    private void changeKennelName() {
        System.out.println("Please enter new kennel name");
        String name = scan.nextLine();
        kennel.setName(name);
    }

    private void admitAnimal() {

        boolean sr = false;
        boolean NeedsWalk = false;
        boolean likesBone = false;
        System.out.println("name of Pet is?");
        String name = scan.nextLine();
        System.out.println("Type of pet? \n C - for cat \n D - for dog");
        String type = scan.nextLine().toUpperCase();
        if(type.equals("C") || type.equals("D")){
        }
        else
        {
            throw new IllegalArgumentException(("ERROR ANIMAL IS NOT RECOGNISED....")); //Error which breaks the system if the incorrect animal is entered
        }
        System.out.println("What is its favourite food?");
        String fav = scan.nextLine();

        /**
         * Here it has a check to make sure that any input that is less than 0 gives the user an error and resets the check.
         */
        System.out.println("How many times is it fed a day? (as a number the number should be more than 0)");
        int numTimes = 0;
        do {
            try {
                System.out.println("Answers less than 0 or 0 itself will not be allowed.");
                numTimes = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("invalid input");
            }
            scan.nextLine();
        }

        while (numTimes <= 0);

        /**
         * Checks to see if the animal entered is a Cat or Dog and runs the respective method if so
         */

        Animal animal = new Animal();
        if (type.equals("C")) {
            System.out.println("Animal entered is a cat");
            System.out.println("Can it share a run? (Y/N)");
            String sharesRuns = scan.nextLine().toUpperCase();
            if (sharesRuns.equals("Y")) {
                sr = true;
            }
            animal = new Cat(name, fav, numTimes, sr);
            animal.setType("CAT");
        } else if (type.equals("D")) {
            System.out.println("Animal entered is a dog");
            System.out.println("Does it a need a walk? (Y/N)");
            String walk = scan.nextLine().toUpperCase();
            if (walk.equals("Y")) {
                NeedsWalk = true;
            }
            System.out.println("Does it like bones? (Y/N)");
            String bone = scan.nextLine().toUpperCase();
            if (bone.equals("Y")) {
                likesBone = true;
            }
            animal = new Dog(name, fav, numTimes, NeedsWalk, likesBone);
            animal.setType("DOG");
        }


        kennel.addAnimal(animal);

        ArrayList<Owner> owners = getOwners();
        for (Owner o : owners) {
            animal.addOriginalOwner(o);
        }
    }

/**
 * Array list for the owners.
 */
    private ArrayList<Owner> getOwners() {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        String answer;
        do {
            System.out.println("Enter owner-name");
            String ownName = scan.nextLine();
            System.out.println("Enter owner-phone");
            String ownPhone = scan.nextLine();
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            System.out.println("Another owner (Y/N)?");
            answer = scan.nextLine().toUpperCase();
        } while (!answer.equals("N"));
        return owners;
    }


    // /////////////////////////////////////////////////
    public static void main(String args[]) {
        System.out.println("**********HELLO***********");
        KennelDemo demo = new KennelDemo();
        demo.initialise();
        demo.runMenu();
        demo.printAll();
        // MAKE A BACKUP COPY OF cats.txt JUST IN CASE YOU CORRUPT IT
        demo.save();
        System.out.println("***********GOODBYE**********");
    }
}
