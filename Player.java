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
        Tile original = playerTiles[index];
        for (int i = index; i < playerTiles.length-1; i++) {
            playerTiles[i]=playerTiles[i+1];
        }
        playerTiles[playerTiles.length-1]=null;

        return original;
    }

    /*
     * TODO: adds the given tile to the playerTiles in order
     * should also update numberOfTiles accordingly.
     * make sure playerTiles are not more than 15 at any time
     */
    public void addTile(Tile t) {
        if(numberOfTiles<15)
        {
            for (int i = 0; i < playerTiles.length; i++) {
                if(playerTiles[i]==null)
                {
                    playerTiles[i]=t;
                    numberOfTiles++;
                }
            }
        }

        else System.out.println("You can't take more tiles");
    }

    /*
     * TODO: checks if this player's hand satisfies the winning condition
     * to win this player should have 3 chains of length 4, extra tiles
     * does not disturb the winning condition
     * @return
     */
    public boolean isWinningHand() {
        int chainCount = 0;
        int currentChainLength = 1;

        for (int i = 1; i < playerTiles.length; i++) {
            if (playerTiles[i].canFormChainWith(playerTiles[i - 1])) {
                currentChainLength++;
            } else {
                if (currentChainLength == 4) {
                    chainCount++;
                }
                currentChainLength = 1;
            }
        }

        if (currentChainLength == 4) {
            chainCount++;
        }
        return chainCount == 3;
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
