package game;


import java.util.ArrayList;

public class Player {


    private static int pl_num = 1;
    private int player_id = 0;
    private int pl_pos = 0;
    private int pl_cash = 1500; // cash that the player starts with
    private boolean bankrupt = false; // manages if player is bankrupt
    private ArrayList<Tile> owns = new ArrayList<>();
    private boolean inJail = false;
    private int housesOwned = 0;
    private int hotelsOwned = 0;
    private int noJailFreeCard = 0;

    public int getNoJailFreeCard() {
        return noJailFreeCard;
    }

    public void incrNoJailFreeCard() {
        this.noJailFreeCard ++;
    }

    public Player(){
        this.player_id = this.pl_num++;
    }

    public int getHousesOwned(){
        return this.housesOwned;
    }

    public int getHotelsOwned() {
        return hotelsOwned;
    }

    public void incrHousesOwned(){
        this.housesOwned++;
    }

    public void incrHotelsOwned(){
        this.hotelsOwned++;
    }

    public int getPlayer_id() {
        return this.player_id;
    }

    public void setPl_pos(int pl_pos) {
        this.pl_pos = pl_pos;
    }

    public int getPl_pos() {

        return this.pl_pos;

    }

    public boolean getInJail(){
        return this.inJail;
    }

    public void setInJail(){
        this.inJail = true;
    }

    public int getPl_cash(){
        return this.pl_cash;
    }

    public void setPl_cash(int cash){
        this.pl_cash = cash;
    }

    public void addPl_cash(int cash) { this.pl_cash+=cash; }

    public boolean isBankrupt() {
        return bankrupt;  // return the boolean if bankrupt
    }

    public void setBankrupt(boolean bankrupt) { // Allows you to change the value of bankrupt
        this.bankrupt = bankrupt;
    }

    //get and set the tiles that the player owns
    public void addOwns(Tile t){
        this.owns.add(t);
    }

    public ArrayList<Tile> getOwns(){
        return this.owns;
    }

    public void changePlCash(int cash){
        this.pl_cash -= cash;
    }

    public void removeOwned(Tile t){
        owns.remove(t);

    }

    public void incrPos(){
        if (this.pl_pos == 39){
            this.pl_pos = 0;
        } else {
            this.pl_pos ++;
        }

    }

    public void decresePos(){
        if (this.pl_pos == 0){
            this.pl_pos = 39;
        } else {
            this.pl_pos --;
        }

    }
    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\nplayer id: " + this.player_id + "\npos: " + this.pl_pos + "\nCash: " + this.pl_cash;
        a+= "\n-----------------";
        return a;
    }
}
