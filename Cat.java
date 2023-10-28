/**
 * Cat class using the Animal class as its parent.
 * @author Lov2
 * @version 1
 */



import java.io.PrintWriter;
import java.util.Scanner;

public class Cat extends Animal{

    private boolean canShare;

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }


    /**
     * Default constructor.
     */

    public Cat(){
        super();
        type = "CAT";
    }


    /**
     * Constructor for the cat class. Uses its own unique attribute along with the super class attributes from Animal
     * @param name name for the cat class
     * @param food the favourite food
     * @param mealsPerDay how many times an animal eats in a day
     * @param canShare the cats unique attribute. Determines if the cat can share a run with other cats
     */
    public Cat(String name, String food, int mealsPerDay, boolean canShare) {
        super(name,food,mealsPerDay);
        this.canShare = canShare;
        this.type = "CAT";
    }


    /**
     * Used to load a cat object from the textfile. Only works and overrides the superclass load if "CAT" is present.
     * @param infile scanner used to read file.
     */
    @Override
    public void load(Scanner infile){
        super.load(infile);
        this.type = "CAT";
        this.canShare = infile.nextBoolean();
    }

    /**
     * Used to save a cat object to the file. Used when called in the Animal method if "CAT" is present
     * @param infile PrintWriter for the text file.
     */
    public void save(PrintWriter infile) {
        infile.println(type);
        super.save(infile);
        infile.println(canShare);

    }

    /**
     * ToString method for the cat class. Uses a String builder to create the string.
     * @returns all information about a cat object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return  sb.append(super.toString()).append(" ").append(canShare).toString();
    }
}

