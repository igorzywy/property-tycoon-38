public class Player {

    static int pl_num = 1;
    int player_id = 0;
    int pl_pos = 0;
    int pl_cash = 1500; // cash that the player starts with
    boolean bankrupt = false; // manages if player is bankrupt
    public Player(){
        this.player_id = this.pl_num++;
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
    public int getPl_cash(){
        return this.pl_cash;
    }

    public  void setPl_cash(int cash){
        this.pl_cash = cash;
    }

    public boolean isBankrupt() {
        return bankrupt;  // return the boolean if bankrupt
    }

    public void setBankrupt(boolean bankrupt) { // Allows you to change the value of bankrupt
        this.bankrupt = bankrupt;
    }

    public void incrPos(){
        if (this.pl_pos == 39){
            this.pl_pos = 0;
        } else {
            this.pl_pos ++;
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
