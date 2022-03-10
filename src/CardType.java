public enum CardType {
    BANKPAYPLAYER("bpp"),PLAYERPAYPLAYER("ppp"),PLAYERMOVE("pm"),PLAYERPAYBANK("ppb")
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
