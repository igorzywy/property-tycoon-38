import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Card {
    static int card_id_incrementer = 0;
    int card_id = 0;
    CardType type = null;
    String desc = null;
    String action = null;
    Integer amount = null;
    Integer perHotel = null;
    String typeS = null;

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

    public static Deque<Card> getXLSXDataPotLuck(){
        ArrayList<Card> cards= new ArrayList<Card>();
        Deque<Card> dCard = new ArrayDeque<>();
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
        dCard.addAll(cards);
        return dCard;
    }

    public static Deque<Card> getXLSXDataOpportunityKnocks(){
        ArrayList<Card> cards= new ArrayList<Card>();
        Deque<Card> dCard = new ArrayDeque<>();
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
        dCard.addAll(cards);
        return dCard;

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
