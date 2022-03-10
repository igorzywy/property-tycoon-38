import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Card {
    static int card_id_incrementer = 0;
    int card_id = 0;
    CardType type = null;
    String desc = null;
    String action = null;

    public Card(CardType type, String desc, String action){

        card_id = card_id_incrementer++;
        this.type = type;
        this.desc = desc;
        this.action = action;


    }

    public static ArrayList<Card> getXLSXDataPotLuck(){
        ArrayList<Card> cards= new ArrayList<Card>();





        return cards;
    }

    public static ArrayList<Card> getXLSXDataOpportunityKnocks(){
        ArrayList<Card> cards= new ArrayList<Card>();





        return cards;
    }

    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\ncard_id: " + card_id;
        a+= "\n-----------------";
        return a;
    }
}
