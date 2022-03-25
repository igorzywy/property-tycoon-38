public enum CardType {

    /*
    pm - player moves to specific tile without passing anything like GO
    pmx - player moves a set number of spaces
        (this can be forwards or backwards indicated with either pos or neg amount)
    pmf - player moves to specific tile and passes things like GO
     */
    bpp("BANKPAYPLAYER"),ppp("PLAYERPAYPLAYER"),
    pm("PLAYERMOVE"),ppb("PLAYERPAYBANK"),
    ppf("PLAYERPAYFREE"),pmx("PLAYERMOVEX"),
    pmf("PLAYERMOVEFORWARD"),ppr("PLAYERPAYREPAIR"),
    jfc("JAILFREECARD")
    ;
    private final String s;
    CardType(String s) {
        this.s = s;
    }

    @Override
    public String toString(){
        return this.s;
    }
}
