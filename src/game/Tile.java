package game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Tile class holds all the information about a single tile
 */
public class Tile {


    private int tile_id = 0;
    private String tile_name = "";
    private String tileGrp = "";
    private String tile_action = "";
    private boolean tile_can_be_bought;
    private Integer price, rent_unimproved, rent_1h, rent_2h, rent_3h, rent_4h, rent_hotel;
    private Integer owned_by = 0;
    private boolean mortgaged = false;
    private int houses = 0;
    private int hotels = 0;
    private int housePrice = 0;

    //constructor

    /**
     * Constructor of Tile
     * @param tile_id Assigns the tile ID
     * @param name Assigns the tile name
     * @param group Assigns the tile group
     * @param action Assigns the tile action
     * @param can_be_bought Assigns whether the tiles can be bought
     * @param price Sets the tile price
     * @param rent_unimproved Sets the rent of the tile
     * @param rent_1h Sets the rent of the tile with 1 house
     * @param rent_2h Sets the rent of the tile with 2 house
     * @param rent_3h Sets the rent of the tile with 3 house
     * @param rent_4h Sets the rent of the tile with 4 house
     * @param rent_hotel Sets the rent of the tile with 1 hotel
     * @param housePrice Sets the cost of 1 house
     */
    public Tile(Integer tile_id, String name, String group, String action, boolean can_be_bought, Integer price,
                Integer rent_unimproved, Integer rent_1h, Integer rent_2h, Integer rent_3h, Integer rent_4h,
                Integer rent_hotel, Integer housePrice){
        this.tile_id = tile_id - 1;
        tile_name = name;
        tileGrp = group;
        tile_action = action;
        tile_can_be_bought = can_be_bought;
        this.price = price;
        this.rent_unimproved = rent_unimproved;
        this.rent_1h = rent_1h;
        this.rent_2h = rent_2h;
        this.rent_3h = rent_3h;
        this.rent_4h = rent_4h;
        this.rent_hotel = rent_hotel;
        this.housePrice = housePrice;

    }


    /**
     * Gets the rent for the tile with the hotel
     * @return int The amount the rent will be with a hotel on this tile
     */
    public int getRentHotel(){
        return rent_hotel;
    }

    /**
     * Gets the rent of the tile when it is unimproved
     * @return int The rent of the tile when it is unimproved
     */
    public int getRentUnimp(){
        return rent_unimproved;
    }

    /**
     * Gets the rent of the tile with 1 house
     * @return int The rent of the tile with 1 house
     */
    public int get1HRent(){
        return rent_1h;
    }
    /**
     * Gets the rent of the tile with 2 house
     * @return int The rent of the tile with 2 house
     */
    public int get2HRent(){
        return rent_2h;
    }
    /**
     * Gets the rent of the tile with 3 house
     * @return int The rent of the tile with 3 house
     */
    public int get3HRent(){
        return rent_3h;
    }
    /**
     * Gets the rent of the tile with 4 house
     * @return int The rent of the tile with 4 house
     */
    public int get4HRent(){
        return rent_4h;
    }

    /**
     * Resets the tiles amount of houses and hotels and to not be mortgaged
     */
    public void resetTile(){
        this.hotels = 0;
        this.houses = 0;
        this.mortgaged = false;
    }

    /**
     * Gets the number of hotels
     * @return int The number of hotels
     */
    public int getHotels() {
        return hotels;
    }

    /**
     * Gets the number of houses
     * @return int The number of houses
     */
    public int getHouses() {
        return houses;
    }

    /**
     * Increases the number of hotels by 1
     */
    public void incrHotels() {
        this.hotels++;
    }
    /**
     * Increases the number of houses by 1
     */
    public void incrHouses(){
        this.houses++;
    }
    /**
     * Decreases the number of hotels by 1
     */
    public void decrHotels() {
        this.hotels--;
    }
    /**
     * Decreases the number of houses by 1
     */
    public void decrHouses(){
        this.houses--;
    }

    /**
     * Gets the name of the tile
     * @return String The name of the tile
     */
    public String getTileName(){
        return tile_name;
    }

    /**
     * Gets the price of a tile
     * @return Integer The price of a tile
     */
    public Integer getPrice(){
        return price;
    }

    /**
     * Gets the tile ID
     * @return int The tile ID
     */
    public int getTile_id() {
        return this.tile_id;
    }

    /**
     * Gets whether this tile is mortgaged
     * @return boolean True of the tile is mortgaged and False otherwise
     */
    public boolean getMortgaged(){return this.mortgaged;}

    /**
     * Gets whether this tile can be bought
     * @return boolean True if tile can be bought and False if otherwise
     */
    public boolean getCanBeBought(){
        return tile_can_be_bought;
    }

    /**
     * Gets the owner of the tile
     * @return Integer The ID of the player that owns the tile
     */
    public Integer getOwnedBy(){
        return owned_by;
    }

    /**
     * Changes the status of mortgaged
     */
    public void flipMortgaged(){
        this.mortgaged = !mortgaged;
    }

    /**
     * Set the ID of the player to own the tile
     * @param i The ID of the player
     */
    public void setOwnedBy(Integer i){
        owned_by = i;
    }

    /**
     * Gets the group of the tile
     * @return String The group of the tile
     */
    public String getGroup(){
        return this.tileGrp;
    }

    /**
     * Gets the price of 1 house
     * @return int The price of 1 house
     */
    public int getHousePrice(){
        return this.housePrice;
    }

    /**
     * Gets the data from a .csv file to be stored in the tile
     * @return ArrayList Tile The list of all the tiles from the .csv file
     */
    public static ArrayList<Tile> getXLSXData(){
        ArrayList<Tile> tiles= new ArrayList<Tile>();
        String path = "data/PropertyTycoonBoardData.csv";
        String line = "";


        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String headerLine = br.readLine();
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                tiles.add(new Tile(Integer.parseInt(values[0]),values[1],values[2],values[3],Boolean.parseBoolean(values[4]),
                        Integer.parseInt(values[5]),Integer.parseInt(values[6]),Integer.parseInt(values[7]),
                        Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]),
                        Integer.parseInt(values[11]),Integer.parseInt(values[12])));
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
                + this.tile_name + " grp: " + this.tileGrp
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
