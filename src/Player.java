public class Player {

    static int pl_num = 1;
    int player_id = 0;
    int pl_pos = 0;
    boolean bankrupt = false; // manages if player is bankrupt
    public Player(){
        this.player_id = this.pl_num++;
    }

    public int getPlayer_id() {
        return this.player_id;
    }

    public int getPl_pos() {
        System.out.println("pos");
        return this.pl_pos;

    }

    public boolean isBankrupt() {
        return bankrupt;  // return the boolean if bankrupt
    }

    public void setBankrupt(boolean bankrupt) { // Allows you to change the value of bankrupt
        this.bankrupt = bankrupt;
    }

    public void incrPos(){
        if (this.pl_pos > 39){
            this.pl_pos = 0;
        } else {
            this.pl_pos ++;
        }

    }
    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\nplayer id: " + this.player_id + "\npos: " + this.pl_pos;
        a+= "\n-----------------";
        return a;
    }
}
