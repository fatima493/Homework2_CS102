
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
     * DONE: removes and returns the tile in given index
     */
    public Tile getAndRemoveTile(int index) {
        Tile original = playerTiles[index];
        if (index != 14){
            for (int i = index; i < playerTiles.length-1; i++) {
                playerTiles[i]=playerTiles[i+1];
            }
            
        }
        
        playerTiles[playerTiles.length-1]=null;
        numberOfTiles--;

        return original;
    }

    /*
     * DONE: adds the given tile to the playerTiles in order
     * should also update numberOfTiles accordingly.
     * make sure playerTiles are not more than 15 at any time
     */
    public void addTile(Tile t) {
        if(numberOfTiles<15)
        {

            int index= 0;
            while (index < numberOfTiles && t.compareTo(playerTiles[index]) > 0){
                index ++;
            }

            for (int i = numberOfTiles; i > index; i--){
                playerTiles[i] = playerTiles[i-1];
            }

            playerTiles[index] = t;
            numberOfTiles ++;      
        }

        else System.out.println("You can't take more tiles");
    }

    /*
     * DONE: checks if this player's hand satisfies the winning condition
     * to win this player should have 3 chains of length 4, extra tiles
     * does not disturb the winning condition
     * @return
     */
    public boolean isWinningHand() {
        int chainCount = 0;
        boolean[] usedNumbers = new boolean[7];

        for(int i = 0; i < numberOfTiles; i++){
            if(playerTiles[i] != null && !usedNumbers[playerTiles[i].getValue()-1]){
                usedNumbers[playerTiles[i].getValue()-1] = true;

                char[] currentNumbersColors = new char[4];
                int colorCount = 0;

                currentNumbersColors[colorCount] = playerTiles[i].getColor();
                colorCount++;

                for(int j = i + 1; j < numberOfTiles; j++){
                    if(playerTiles[j] != null && playerTiles[i].getValue() == playerTiles[j].getValue()){
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

        return chainCount >= 3;

    }


    public int findPositionOfTile(Tile t) {
        /*
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].compareTo(t) == 0) {
                return i;
            }
        }
        return -1;*/
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].compareTo(t) == 0) {
                tilePosition= i;
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

    private void orderTiles() {
        int n = numberOfTiles;

        
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n && flag; j++){
                if (playerTiles[j].compareTo(playerTiles[i]) == -1){
                    Tile temp = playerTiles[i];
                    playerTiles[i] = playerTiles[j];
                    playerTiles[j] = temp;
                    flag = false;

                }
            }
        }
    }
}
