package game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {

    private static int tile_id_incrementer = 0;
    private int tile_id = 0;
    private String tile_name = "";
    private String tile_grp = "";
    private String tile_action = "";
    private boolean tile_can_be_bought;
    private Integer price, rent_unimproved, rent_1h, rent_2h, rent_3h, rent_4h, rent_hotel;
    private Integer owned_by = null;
    private boolean mortgaged = false;

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

    public String getTileName(){
        return tile_name;
    }
    public Integer getPrice(){
        return price;
    }

    public int getTile_id() {
        return this.tile_id;
    }

    public boolean getCanBeBought(){
        return tile_can_be_bought;
    }
    public void setCanBeBought(boolean c){
        this.tile_can_be_bought = c;
    }

    public Integer getOwnedBy(){
        return owned_by;
    }

    public void flipMortgaged(){
        this.mortgaged = !mortgaged;
    }

    public void setOwnedBy(Integer i){
        owned_by = i;
    }

    public String getGroup(){
        return this.tile_grp;
    }

    public static ArrayList<Tile> getXLSXData(){
        ArrayList<Tile> tiles= new ArrayList<Tile>();
        String path = "data/PropertyTycoonBoardData.csv";
        String line = "";


        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String headerLine = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                tiles.add(new Tile(values[1],values[2],values[3],Boolean.parseBoolean(values[4]),
                        Integer.parseInt(values[5]),Integer.parseInt(values[6]),Integer.parseInt(values[7]),
                        Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]),
                        Integer.parseInt(values[11])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
                + " " + this.rent_3h + " " + this.rent_4h + " " + this.rent_hotel +
                " owned by: " + owned_by + " mortgaged: " + mortgaged;
        a+= "\n-----------------";
        return a;
    }
}
