package main.java.com.javadventure.gamedriver.utils;

/**
 * We want all input to be trimmed for whitespace and put into lowercase
 */
public class InputSanitizer {
    public String san(String input){
        return input.trim().toLowerCase();
    }

    public static void main(String[] args){
        InputSanitizer san = new InputSanitizer();
        System.out.println(san.san("  This Ds Di  ss  sdd   "));
    }
}
