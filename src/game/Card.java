package game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Card class holds the information about a single card
 */
public class Card {
    private static int card_id_incrementer = 0;
    private int card_id = 0;
    private CardType type = null;
    private String desc = null;
    private String action = null;
    private Integer amount = null;
    private Integer perHotel = null;
    private String typeS = null;

    /**
     * Constructor of the Card class assigns all variables
     * @param desc The description of the card
     * @param action What the card does
     * @param type The type of card
     * @param amount The amount the card will have a player move or amount to be added or deducted
     *               from a players bankroll
     * @param perHotel The amount to pay per hotel if paying for repairs
     */
    public Card(String desc, String action, String type, Integer amount, Integer perHotel){
        CardType c = CardType.valueOf(type);
        card_id = card_id_incrementer++;
        this.type = c;
        this.desc = desc;
        this.action = action;
        this.amount = amount;
        this.perHotel = perHotel;
        this.typeS = type;

    }

    /**
     * Gets the type of the card
     * @return int The type of the Card
     */
    public CardType getType(){
        return this.type;
    }

    /**
     * Gets the amount the card holds
     * @return int The amount the card holds
     */
    public int getAmount(){
        return this.amount;
    }

    /**
     * Gets the price per hotel if the card is a player pay repair card
     * @return int The amount per hotel
     */
    public int getHotelPrice(){
        return this.perHotel;
    }

    /**
     * Gets the data of the cards from a .csv file and
     * @return ArrayList Card The arraylist of all the cards from the .csv file not in order
     */
    public static ArrayList<Card> getXLSXDataPotLuck(){
        ArrayList<Card> cards= new ArrayList<Card>();
        String path = "data/PotLuckCardData.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String headerLine = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                cards.add(new Card(values[0],values[1],values[2],
                        Integer.parseInt(values[3]),Integer.parseInt(values[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(cards);
        return cards;
    }
    /**
     * Gets the data of the cards from a .csv file and
     * @return ArrayList Card The arraylist of all the cards from the .csv file not in order
     */
    public static ArrayList<Card> getXLSXDataOpportunityKnocks(){
        ArrayList<Card> cards= new ArrayList<Card>();
        String path = "data/OpportunityKnocksCardData.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String headerLine = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                cards.add(new Card(values[0],values[1],values[2],
                        Integer.parseInt(values[3]),Integer.parseInt(values[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(cards);
        return cards;

    }


    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\ncard_id: " + card_id + " card_type: " + this.type + " String type: " + this.typeS +" desc: " + this.desc
                + " action: " + this.action + " amount: " + this.amount + " perHotel: "
                + this.perHotel;
        a+= "\n-----------------";
        return a;
    }
}
