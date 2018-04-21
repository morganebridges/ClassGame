package main.java.com.javadventure.Items;

public class Gold extends Item {

    public Gold(int count) {
        super("Gold", "A shiny Gold Coin", false, false, false, count, 1);
        this.lookList.add("gold");
        this.lookList.add("money");
        this.lookList.add("cash");
        this.lookList.add("pieces");
        this.lookList.add("gp");
    }
}
