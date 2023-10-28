/**
 * Class for the dog, uses Animal.Java as its parent class
 * @author Lov2
 * @version 1
 */



import java.io.PrintWriter;
import java.util.Scanner;

public class Dog extends Animal{
    private Boolean needsWalk;

    private Boolean likesBone;



    public Boolean getNeedsWalk() {
        return needsWalk;
    }


    public void setNeedsWalk(Boolean needsWalk) {
        this.needsWalk = needsWalk;
    }

    public Boolean getLikesBone() {
        return likesBone;
    }

    public void setLikesBone(Boolean likesBone) {
        this.likesBone = likesBone;
    }


    /**
     * Defualt constructor, Uses the Animal superclass constructor for its variables
     * @param name name of the dog
     * @param food the food it eats
     * @param mealsPerDay the amount of times it needs to eat
     * @param needsWalk unique to the dog class, whether the dog needs a walk
     * @param likesBone unique to the dog class, whether the dog likes bones.
     */
    public Dog(String name, String food, int mealsPerDay, Boolean needsWalk, Boolean likesBone) {
        super(name, food, mealsPerDay);
        this.needsWalk = needsWalk;
        this.likesBone = likesBone;
    }


    /**
     * Default constructor.
     */
    public Dog() {
        super();
        type = "DOG";


    }

    /**
     * Overrides the load file method in Animal if "DOG" is in the next line. Loads the dog object from the text file.
     * @param infile Scanner for the file it reads from.
     */
    @Override
    public void load(Scanner infile){
        super.load(infile);
        this.type = "DOG";
        this.likesBone = infile.nextBoolean();
        this.needsWalk = infile.nextBoolean();
    }

    /**
     * Saves the dog object to the file including the super class attributes and the dogs unique ones.
     * @param infile PrintWriter for writing the attributes to the text file.
     */
    public void save(PrintWriter infile){
        infile.println(type);
        super.save(infile);
        infile.println(likesBone);
        infile.println(needsWalk);


    }

    /**
     * ToString method using the string builder for the dog class.
     * @return Information about the dog including its super class attributes.Do
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return  sb.append(super.toString()).append(" ").append(needsWalk).append("\n").append(likesBone).toString();
    }

}
