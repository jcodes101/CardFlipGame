
// Imports for CardGridPane Class
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;

public class CardGridPane extends GridPane {

    // Private Fields
    private Card[][] cards;
    private ArrayList<String> cardList;
    private int MAXROWS = 8;
    private int MAXCOLS = 8;
    private int currentRows;
    private int currentCols;
    private int cardSize;

    // Class Constructors
    public CardGridPane() {
        cards = new Card[MAXROWS][MAXCOLS];
        this.cardList = new ArrayList<>();

        for (int i = 0; i < MAXROWS; i++) {
            for (int j = 0; j < MAXCOLS; j++) {
                cards[i][j] = new Card();
                cards[i][j].setCardAndImageSize(64,64);
                this.getChildren().add(cards[i][j]);
            }
        }
    }

    public CardGridPane(int cardSize) {
        this();
        this.setCardSize(cardSize);
    }

    // Class Methods
    /**
     * Each Card Object in the 2D Array (cards) is given a path from
     * an index in the ArrayList (cardList)
     */
    public void setCardImages(){
        int index = 0;
        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
               cards[i][j].setPath(cardList.get(index));
               index++;
            }
        }
    }

    public void shuffleImages(){
        Collections.shuffle(cardList);
    }

    public Card getCard(int r, int c){
        return cards[r][c];
    }

    /**
     * This method makes the CardGridPane based off of
     * the amount of cards that are set by the user
     *
     * The rows and cols are took in and this is how many Card Objects are needed
     * to be added to the CardGridPane
     *
     * For each row(s) and col(s) a new Card Object is created at that index
     * and then at that index, the size is set and then it is added to the
     * GridPane by using "this.add" which adds the Card Object to a specific
     * location using j and i
     */
    public void initCards(int rows, int cols){
        // *
        this.currentRows = rows;
        this.currentCols = cols;
        this.cards = new Card[rows][cols];
        this.getChildren().clear();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cards[i][j] = new Card();
                cards[i][j].setCardAndImageSize(cardSize, cardSize);
                this.add(cards[i][j], j, i);
            }
        }
        createCardImageList(rows*cols);
        shuffleImages();
        setCardImages();
    }

    /**
     * First the ArrayList is cleared to make it empty
     *
     * This equation is done because -> (currentRows * currentCols)/2
     * two cards have to have the same picture in order to be matched
     *
     * The counter is used to keep track of which numbers are matched
     * hence the (image_0, image_1, etc.)
     *
     * The for loop goes through and makes a total amount of matches
     * (ex. 4rows * 4cols = 16 but /2 = 8 total matches that are available)
     *
     * Then two of the same images are added to the ArrayList (cardList)
     */
    public void createCardImageList(int size){
        cardList.clear();
        int counter = 0;
        for (int i = 0; i < (currentRows * currentCols)/2; i++) {
            cardList.add("cardimages/image_" + counter + ".png");
            cardList.add("cardimages/image_" + counter + ".png");
            counter++;
        }
    }

    // Getters and Setters for CardGridPane
    public Card[][] getCard() {
        return cards;
    }

    public void setCard(Card[][] card) {
        this.cards = cards;
    }

        // Start of ArrayList Gs&Ss
    public int getCardListSize(){
        return cardList.size();
    }

    public String getCardList(int index){
        return cardList.get(index);
    }

    public String setCardList(int index, String item){
        return cardList.set(index, item);
    }

    public void addCard(String item){
        cardList.add(item);
    }

    public String removeCard(int index) {
        return cardList.remove(index);
    }
        // End of ArrayList Gs&Ss

    public int getMAXROWS() {
        return MAXROWS;
    }

    public void setMAXROWS(int MAXROWS) {
        this.MAXROWS = MAXROWS;
    }

    public int getMAXCOLS() {
        return MAXCOLS;
    }

    public void setMAXCOLS(int MAXCOLS) {
        this.MAXCOLS = MAXCOLS;
    }

    public int getCurrentRows() {
        return currentRows;
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

    public int getCurrentCols() {
        return currentCols;
    }

    public void setCurrentCols(int currentCols) {
        this.currentCols = currentCols;
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }

}
