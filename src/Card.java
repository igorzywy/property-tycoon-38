public class Card {
    static int card_id_incrementer = 0;
    int card_id = 0;

    public Card(){
        card_id = card_id_incrementer++;
    }

    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\ncard_id: " + card_id;
        a+= "\n-----------------";
        return a;
    }
}
