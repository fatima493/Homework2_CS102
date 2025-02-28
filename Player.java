
public class Player {
    String playerName;
    Tile[] playerTiles;
    int numberOfTiles;

    public Player(String name) {
        setName(name);
        playerTiles = new Tile[15]; // there are at most 15 tiles a player owns at any time
        numberOfTiles = 0; // currently this player owns 0 tiles, will pick tiles at the beggining of the game
    }

    /*
     * TODO: removes and returns the tile in given index
     */
    public Tile getAndRemoveTile(int index) {
        return null;
    }

    /*
     * TODO: adds the given tile to the playerTiles in order
     * should also update numberOfTiles accordingly.
     * make sure playerTiles are not more than 15 at any time
     */
    public void addTile(Tile t) {

    }

    /*
     * DONE: checks if this player's hand satisfies the winning condition
     * to win this player should have 3 chains of length 4, extra tiles
     * does not disturb the winning condition
     * @return
     */
    public boolean isWinningHand() {
        //chain count 
        int chainCount = 0;
        
        //already setted every index false by java
        boolean[] usedNumbers = new boolean[7];
        
        //player's tiles count;
        int tilesCount = playerTiles.length;
        
        
        for(int i = 0; i < tilesCount; i++){
            
            if(playerTiles[i] != null){
                if(!usedNumbers[playerTiles[i].getValue()-1]){
                    
                    //set isChecked to true
                    usedNumbers[playerTiles[i].getValue()-1] = true;

                    //create current number's colors list to check 
                    char[] currentNumbersColors = new char[4];
                    int colorCount = 0;


                    //add current tile's color to the colors list
                    currentNumbersColors[colorCount] = playerTiles[i].getColor();
                    colorCount++;
                    
                    for(int j = i + 1; j < tilesCount; j++){
                        //control for the same values
                        if(playerTiles[j] != null && playerTiles[i].getValue() == playerTiles[j].getValue()){
                            //control for the color
                            boolean colorExists = false;
                            for (int c = 0; c < colorCount; c++) {
                                if (currentNumbersColors[c] == playerTiles[j].getColor()) {
                                    colorExists = true;
                                    break;
                                }
                            }
                            if (!colorExists) {
                                currentNumbersColors[colorCount] = playerTiles[j].getColor();
                                colorCount++;
                            } 

                        }
                    }

                    if(colorCount == 4){
                        chainCount++;
                    }
                }
                
                
            }
        } 
        if(chainCount<3){
            return false;
        }else{
            return true;
        }
    }


    public int findPositionOfTile(Tile t) {
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].compareTo(t) == 0) {
                tilePosition = i;
            }
        }
        return tilePosition;
    }

    public void displayTiles() {
        System.out.println(playerName + "'s Tiles:");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.print(playerTiles[i].toString() + " ");
        }
        System.out.println();
    }

    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }
}
