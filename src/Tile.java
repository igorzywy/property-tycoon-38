import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tile {

    static int tile_id_incrementer = 0;
    int tile_id = 0;
    String tile_name = "";
    String tile_grp = "";
    String tile_action = "";
    boolean tile_can_be_bought;
    Integer price, rent_unimproved, rent_1h, rent_2h, rent_3h, rent_4h, rent_hotel;
    Integer owened_by = null;

    //constructor
    public Tile(String name, String group, String action, boolean can_be_bought, Integer price, Integer rent_unimproved, Integer rent_1h, Integer rent_2h, Integer rent_3h, Integer rent_4h, Integer rent_hotel){
        tile_id = tile_id_incrementer++;
        tile_name = name;
        tile_grp = group;
        tile_action = action;
        tile_can_be_bought = can_be_bought;
        this.price = price;
        this.rent_unimproved = rent_unimproved;
        this.rent_1h = rent_1h;
        this.rent_2h = rent_2h;
        this.rent_3h = rent_3h;
        this.rent_4h = rent_4h;
        this.rent_hotel = rent_hotel;

    }

    public int getTile_id() {
        return this.tile_id;
    }


    public static ArrayList<Tile> getXLSXData(){
        ArrayList<Tile> tiles= new ArrayList<Tile>();
        try {
            File file = new File("data/PropertyTycoonBoardData.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            for (int i = 4; i < 44; i++) {
                Row row = sheet.getRow(i);
                ArrayList<Object> data = new ArrayList<Object>();
                for (int j = 1; j < 12 ; j++) {
                    Cell cell = row.getCell(j);
                    //adding the String columns so name, grp and action
                    if (j<=3){
                        try{
                            data.add(cell.getStringCellValue());

                        }catch (Exception e){
                            data.add(null);
                        }
                    //adding the can be bought
                    }else if (j == 4){
                        String b = cell.getStringCellValue();
                        if (b.equals("Yes")){
                            Boolean a = Boolean.TRUE;
                            data.add(a);
                        }else{
                            Boolean a = Boolean.FALSE;
                            data.add(a);
                        }
                    //adding the prices of things that can be bought
                    }else if (j>4){
                        try{
                            Integer num = (int) cell.getNumericCellValue();
                            if (num.equals(0)){
                                data.add(null);
                            }else{
                                data.add(num);
                            }
                        }catch (Exception e){
                            data.add(null);
                        }
                    }
                }
                tiles.add(new Tile((String) data.get(0), (String) data.get(1),(String) data.get(2),(Boolean) data.get(3),(Integer) data.get(4), (Integer) data.get(5),(Integer) data.get(6),(Integer) data.get(7), (Integer) data.get(8),(Integer) data.get(9), (Integer) data.get(10)));
            }
            return tiles;
        }catch (Exception e){
            e.printStackTrace();
        }
        return tiles;
    }
    @Override
    public String toString(){
        String a = "-----------------";
        a+=  "\ntile id: " + this.tile_id + " name: "
                + this.tile_name + " grp: " + this.tile_grp
                + " action: " + this.tile_action + " can be bought: " +
                this.tile_can_be_bought + " cost: " + this.price
                + " rent in order: " + this.rent_unimproved
                + " " + this.rent_1h + " " + this.rent_2h
                + " " + this.rent_3h + " " + this.rent_4h + " " + this.rent_hotel + " ";
        a+= "\n-----------------";
        return a;
    }
}
