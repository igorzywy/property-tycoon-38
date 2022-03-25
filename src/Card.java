import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static ArrayList<Card> getXLSXDataPotLuck(){
        ArrayList<Card> cards= new ArrayList<Card>();
        try {
            File file = new File("data/PropertyTycoonCardData.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 3; i < 20; i++) {
                Row row = sheet.getRow(i);
                ArrayList<Object> data = new ArrayList<Object>();
                for (int j = 0; j < 5; j++) {
                    Cell cell = row.getCell(j);
                    //getting the desc action and type
                    if (j <= 2) {
                        try {
                            data.add(cell.getStringCellValue());

                        } catch (Exception e) {
                            data.add(null);
                        }

                    }else if (j==3) {
                        try {
                            Integer num = (int) cell.getNumericCellValue();
                            if (num.equals(0)) {
                                data.add(null);
                            } else {
                                data.add(num);
                            }
                        } catch (Exception e) {
                            data.add(null);
                        }
                    }else if (j==4) {
                        try {
                            Integer num = (int) cell.getNumericCellValue();
                            if (num.equals(0)) {
                                data.add(null);
                            } else {
                                data.add(num);
                            }
                        } catch (Exception e) {
                            data.add(null);
                        }
                    }
                }
                cards.add(new Card((String) data.get(0),(String) data.get(1), (String) data.get(2), (Integer) data.get(3), (Integer) data.get(4)));
            }
            return cards;
        }catch (Exception e){
            e.printStackTrace();
        }
        return cards;
    }

    public static ArrayList<Card> getXLSXDataOpportunityKnocks(){
        ArrayList<Card> cards= new ArrayList<Card>();





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
