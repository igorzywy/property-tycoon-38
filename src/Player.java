public class Player {

    static int pl_num = 1;
    int player_id = 0;
    int pl_pos = 0;
    public Player(){
        this.player_id = this.pl_num++;
    }

    public int getPlayer_id() {
        return this.player_id;
    }

    public int getPl_pos() {
        return this.pl_pos;
    }

    public void incrPos(){
        if (this.pl_pos > 39){
            this.pl_pos = 0;
        } else {
            this.pl_pos++;
        }

    }
}
