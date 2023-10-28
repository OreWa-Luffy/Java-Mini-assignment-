import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * To model a Kennel - a collection of Animals
 *
 * @author Chris Loftus and Faisal Rezwan
 * @version 2 (20th February 2019)
 */
public class Kennel  {
    private String name;
    private ArrayList<Animal> animals;
    private int nextFreeLocation;
    private int capacity;

    /**
     * Creates a kennel with a default size 20
     */
    public Kennel() {
        this(20);
    }

    /**
     * Create a kennel
     *
     * @param maxNoAnimals The capacity of the kennel
     */
    public Kennel(int maxNoAnimals) {
        nextFreeLocation = 0; // no Animals in collection at start
        capacity = maxNoAnimals;
        animals = new ArrayList<Animal>(capacity); // set up default. This can
        // actually be exceeded
        // when using ArrayList but we
        // won't allow that
        // to happen.
    }

    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the kennel e.g. "PetsRUs"
     *
     * @param theName
     */
    public void setName(String theName) {
        name = theName;
    }

    /**
     * Set the size of the kennel
     *
     * @param capacity The max Animal we can house
     */
    public void setCapacity(int capacity) {


        // This should really check to see if we already have cats
        // in the kennel and reducing the capacity would lead to evictions!
        this.capacity = capacity;
    }

    /**
     * Maximum capacity of the kennels
     *
     * @return The max size of the kennel
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method gets the value for the name attribute. The purpose of the
     * attribute is: The name of the Kennel e.g. "PetsRus"
     *
     * @return String The name of the kennel
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the number of Animal in a kennel
     *
     * @return int Current number of Animal in the kennel
     */
    public int getNumOfAnimals() {
        return nextFreeLocation;
    }

    /**
     * Enables a user to add an Animal to the Kennel
     *
     * @param animal A new animal to home
     */
    public void addAnimal(Animal animal) {
        if (nextFreeLocation >= capacity) {
            System.out.println("Sorry kennel is full - cannot add");
            return;
        }
        // we add in the position indexed by nextFreeLocation
        // This starts at zero
        animals.add(animal);

        // now increment index ready for next one
        nextFreeLocation = nextFreeLocation + 1;
    }

    /**
     * Enables a user to delete an animal from the Kennel.
     * @param who The animal to remove
     */
    public void removeCat(String who) {
        Animal which = null;
        // Search for the cat by name
        for (Animal c : animals) {
            if (who.equals(c.getName())) {
                which = c;
            }
        }
        if (which != null) {
            animals.remove(which); // Requires that Cat has an equals method
            System.out.println("removed " + who);
            nextFreeLocation = nextFreeLocation - 1;
        } else {
            System.err.println("cannot remove - not in kennel");
        }
    }

    /**
     * @return String showing all the information in the kennel
     */
    public String toString() {
        Collections.sort(animals);
        StringBuilder results = new StringBuilder("Data in Kennel ").append(name).append(" is: \n");
        for (Animal c : animals) {
            results.append(c.toString()).append("\n");
        }
        return results.toString();
    }

    /**
     * Returns an array of the inmates in the kennels
     *
     * @return An array of the correct size
     */
    public Animal[] obtainAllAnimals() {

        Animal[] result = new Animal[animals.size()];
        result = animals.toArray(result);
        return result;


    }


    /**
     * Searches for and returns the inmate, if found
     *
     * @param name The name of the inmate
     * @return The inmate or else null if not found
     */
    public Animal search(String name) {

        Animal search = null;
        for (Animal contain : animals) {
            if (name.equals(contain.getName())) {
                search = contain;



            }

        }
        if(search == null){
            System.out.println("ERROR Animal not found search will return nothing");
        }
        return search;
    }

    /**
     * Reads in Kennel information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(Animal newAnimal, String infileName) throws IOException{
        // Using try-with-resource. We will cover this in Lecture 8, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        // But if you don't understand it now, don't worry about it.
        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {
            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");
            name = infile.next();
            capacity = infile.nextInt();
            /**
             * Does a check to see if the line contains "DOG" or "CAT" and runs the respective method if so.
             */

            while (infile.hasNext()) {

                if(infile.next().equals("DOG")) {
                    newAnimal = new Dog();
                    newAnimal.load(infile);
                    animals.add(newAnimal);
                } else{
                    newAnimal = new Cat();
                    newAnimal.load(infile);
                    animals.add(newAnimal);
                }
            }
        }
    }

    /**
     * Saves the kennel information
     *
     * @param filename The file to save to
     * @throws IOException If some IO error occurs
     */
    public void save(String filename) throws IOException {
        // Using try-with-resource. We will cover this in Lecture 8, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        // But if you don't understand it now, don't worry about it.
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            outfile.println(name);
            outfile.println(capacity);
            for (Animal c : animals) {
                c.save(outfile);
            }
        }
    }
}
