/**
 * Jadin Hutchinson
 * Comp 167
 * 005
 */

// Imports for Card class
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Card extends StackPane {

    // Private Fields
    private boolean flipped;
    private boolean matched;
    private String path;
    private Image image;
    private ImageView imageView;
    private int row;
    private int col;
    private int numRows;
    private int numCols;

    // Declaring a variable to create an initial image to be shown as the back of the card
    private Image backCard = new Image("/cardimages/omk.png");

    private Image blackCard = new Image("/cardimages/black.png");

    // Private variable to keep track of how many times a card is clicked
    private int clickCount;

    // Class Constructors
    public Card(){
        this.flipped = false;
        this.matched = false;
        this.path = path;
        this.image = null;
        this.imageView = new ImageView(backCard);
        this.row = 0;
        this.col = 0;
        this.numRows = 0;
        this.numCols = 0;
        this.getChildren().add(imageView);

        /**
         * setOnMousePressed is a built-in function that uses lambda expression
         * to point to a function to be executed...
         *
         * if the Card(backImg) is not flipped then it will flip to the actual image
         * under the card, then once pressed again it will go back to the Card(backImg)
         */
        this.setOnMousePressed(event -> {
            if (!flipped && !matched){
                flipImage();
            }
        });

    }

    public Card(String path){
        this.path = path;
        this.flipped = false;
        this.matched = false;
        this.image = null;
        this.imageView = new ImageView();
        this.getChildren().add(this.imageView);

        this.setOnMousePressed(event -> {
            if (!flipped){
                flipImage();
            }
            flipCard();
        });
    }

    // Class Methods
    /**
     * if the path (the String path to a specific card) is not empty
     * then the image is instantiated with that path to the image
     * and then imageView sets the card to that image
     */
    public void flipImage(){
        try {
            if (path != null) {
                image = new Image(getClass().getResourceAsStream(path));
                imageView.setImage(image);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * this method checks if the Card(backImg) is flipped (showing the backImg)
     * and if so it will set the backImg to the imageView, otherwise it will set
     * the imageView to the image under the backImg, and it will flip it at the end
     */
    public void flipCard(){
        image = new Image(getClass().getResourceAsStream(path));
        if(flipped){
            this.imageView.setImage(backCard);
        } else{
            this.imageView.setImage(image);
        }
        flipped = !flipped;
    }

    public void setCardAndImageSize(int width, int height){
        this.setPrefSize(width, height);
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }

    /**
     * This method is what loads in the image from a specific path,
     * and then it goes on to be displayed within ImageView (imageView)
     */
    public void setPath(String path){
        this.path = path;
        try {
            this.image = new Image(this.getClass().getResourceAsStream(path));
            this.flipped = false;
            this.imageView.setImage(backCard);
        } catch (Exception e) {
            System.err.println("Error " + path);
            e.printStackTrace();
        }
    }

    public void setMatched(boolean matched){
        this.matched = matched;
        this.imageView.setImage(blackCard);
    }

    public void setGridPos(int r, int c){
        this.row = r;
        this.col = c;
    }

    // Getters and Setters for Card class
    public boolean isFlipped(){
        return flipped;
    }

    public void setFlipped(boolean flipped){
        this.flipped = flipped;
    }

    public boolean isMatched(){
        return matched;
    }

    public void setIsMatched(boolean matched){
        this.matched = matched;
    }

    public String getPath(){
        return path;
    }

    public Image getImage(){
        return image;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getCol(){
        return col;
    }

    public void setCol(int col){
        this.col = col;
    }

    public int getNumRows(){
        return numRows;
    }

    public void setNumRows(int numRows){
        this.numRows = numRows;
    }

    public int getNumCols(){
        return numCols;
    }

    public void setNumCols(int numCols){
        this.numCols = numCols;
    }
}