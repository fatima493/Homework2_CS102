import java.util.Random;

public class OkeyGame {

    Player[] players;
    Tile[] tiles;

    Tile lastDiscardedTile;

    int currentPlayerIndex = 0;

    public OkeyGame() {
        players = new Player[4];
    }

    public void createTiles() {
        tiles = new Tile[112];
        int currentTile = 0;

        // two copies of each color-value combination, no jokers
        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[currentTile++] = new Tile(i,'Y');
                tiles[currentTile++] = new Tile(i,'B');
                tiles[currentTile++] = new Tile(i,'R');
                tiles[currentTile++] = new Tile(i,'K');
            }
        }
    }

    /*
     * TODO: distributes the starting tiles to the players
     * player at index 0 gets 15 tiles and starts first
     * other players get 14 tiles
     * this method assumes the tiles are already shuffled
     */
    public void distributeTilesToPlayers() {
       
        int cou = 0;
        while (cou < 15) {
            players[0].addTile(tiles[0]);
            
            for (int t = 0; t < tiles.length-1; t++){
                tiles[t] = tiles[t+1];
                tiles[tiles.length-1] = null;
            }

            cou++;
        }
         

        for (int j = 1; j <=3 ; j++){
            cou = 0;
            while (cou < 14){
                players[j].addTile(tiles[0]);
                
                for (int t = 0; t < tiles.length-1; t++){
                    tiles[t] = tiles[t+1];
                    tiles[tiles.length-1] = null;
                }
                cou++;
            }

        }

            
    }

    /*
     * TODO: get the last discarded tile for the current player
     * (this simulates picking up the tile discarded by the previous player)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getLastDiscardedTile() {
        if (lastDiscardedTile != null) {
            players[0].addTile(lastDiscardedTile);
            return lastDiscardedTile.toString();
        }
        return "No discarded tile yet.";
    }

    /*
     * TODO: get the top tile from tiles array for the current player
     * that tile is no longer in the tiles array (this simulates picking up the top tile)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getTopTile() {

        Tile topTile = tiles[0];
        Player currentPlayer = players[currentPlayerIndex];

        currentPlayer.addTile(topTile);

        /* assuming the top tile is the 0th index(?) we give that tile to player and shift all the tiles -1 indexes
         and returning the toString method of the topTile */
         
        for (int i = 0; i < tiles.length-1; i++){
            tiles[i] = tiles [i+1];
        }
        return topTile.toString();
    }

    /*
     * TODO: should randomly shuffle the tiles array before game starts
     */
    public void shuffleTiles() {

        int index;
        Tile temp;

        // swaps elements with a random element in array  starting from the end so it shuffles
        Random random = new Random();
        for (int i = tiles.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = tiles[index];
            tiles[index] = tiles[i];
            tiles[i] = temp;
        }
    }

    /*
     * TODO: check if game still continues, should return true if current player
     * finished the game, use isWinningHand() method of Player to decide
     */
    public boolean didGameFinish() {
        return players[currentPlayerIndex].isWinningHand();
    }

    /*
     * TODO: Pick a tile for the current computer player using one of the following:
     * - picking from the tiles array using getTopTile()
     * - picking from the lastDiscardedTile using getLastDiscardedTile()
     * You should consider if the discarded tile is useful for the computer in
     * the current status. Print whether computer picks from tiles or discarded ones.
     */
    public void pickTileForComputer() {
        // If the last discarded tile is useful, pick that
        if (lastDiscardedTile != null && players[currentPlayerIndex].findPositionOfTile(lastDiscardedTile) == -1) {
            System.out.println("Computer picked the discarded tile: " + lastDiscardedTile);
            players[currentPlayerIndex].addTile(lastDiscardedTile);
            lastDiscardedTile = null;  // Discarded tile is picked, so it's no longer available
    } else {
            String topTile = getTopTile();
            System.out.println("Computer picked the top tile: " + topTile);
            players[currentPlayerIndex].addTile(new Tile(Integer.parseInt(topTile.substring(0, 1)), topTile.charAt(1)));
        }
    }

    /*
     * TODO: Current computer player will discard the least useful tile.
     * this method should print what tile is discarded since it should be
     * known by other players. You may first discard duplicates and then
     * the single tiles and tiles that contribute to the smallest chains.
     */
    public void discardTileForComputer() {
        // discard the first tile that is a duplicate or least useful
        for (int i = 0; i < players[currentPlayerIndex].getTiles().length; i++) {
            if (players[currentPlayerIndex].getTiles()[i] != null) {
                Tile discardedTile = players[currentPlayerIndex].getAndRemoveTile(i);
                lastDiscardedTile = discardedTile;  // Set this as the last discarded tile
                System.out.println("Computer discarded tile: " + discardedTile.toString());
                break;
            }
        }

    }

    /*
     * TODO: discards the current player's tile at given index
     * this should set lastDiscardedTile variable and remove that tile from
     * that player's tiles
     */
    public void discardTile(int tileIndex) {
        Player currentPlayer = players[getCurrentPlayerIndex()];
        lastDiscardedTile = currentPlayer.playerTiles[tileIndex];
        
        System.out.println(currentPlayer.getName() + " has discarded " + currentPlayer.getAndRemoveTile(tileIndex) );

    }

    public void displayDiscardInformation() {
        if(lastDiscardedTile != null) {
            System.out.println("Last Discarded: " + lastDiscardedTile.toString());
        }
    }

    public void displayCurrentPlayersTiles() {
        players[currentPlayerIndex].displayTiles();
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

      public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void passTurnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void setPlayerName(int index, String name) {
        if(index >= 0 && index <= 3) {
            players[index] = new Player(name);
        }
    }

}
