public class Tile {

    static int tile_id_incrementer = 0;
    int tile_id = 0;
    public Tile(){
        tile_id = tile_id_incrementer++;
    }

    public int getTile_id() {
        return this.tile_id;
    }
}
