/**
 * Jadin Hutchinson
 * Comp 167
 * 005
 */

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class GamePane extends BorderPane {
    /**
     * These are the levels and their grids:
     * Level 1 - 2x3
     * Level 2 - 2x4
     * Level 3 - 4x4
     * Level 4 - 4x6
     * Level 5 - 6x6
     * Level 6 - 8x8
     */

    // Pane fields
    private CardGridPane cardGridPane;
    private HBox statusPane;
//    private CommandPane commandPane;

    // Other private fields
    private int rows;
    private int cols;
    private int numClicks;
    private int numMatched;
    private int clickCount = 0;
    private Card clickedCardOne = null;
    private Card clickedCardTwo = null;

    // Button to exit the card-match game
    private Button exitButton;

    // Button for initializing a new game
    private Button newGameButton;

    // Label for amount of turns
    private Label turnCount;

    // Timer
    private AnimationTimer timer;
    private long startTime;
    private boolean timerRunning = false;

    // Sounds
    AudioClip matchAudio = new AudioClip(getClass().getResource("/match.mp3").toString());
    AudioClip nomatchAudio = new AudioClip(getClass().getResource("/nomatch.mp3").toString());


    // Class Constructors
    public GamePane(){
        // Cards of 64x64 are made and a custom CardGridPane is made
        int cardSize = 64;
        this.cardGridPane = new CardGridPane(cardSize);

        // Sets 8 rows, 8 columns
        cardGridPane.setCurrentRows(8);
        cardGridPane.setCurrentCols(8);
        // A list of 16 unique card images is created
        // As you need 32 pairs for 64 cards, and the images may repeat
        cardGridPane.createCardImageList(16);
        // Randomly shuffles the cards each time the game starts
        cardGridPane.shuffleImages();
        // Initializes the card objects and populates the grid
        cardGridPane.initCards(cardGridPane.getCurrentRows(), cardGridPane.getCurrentCols());
        // Assigns the shuffled images to each card in the grid
        cardGridPane.setCardImages();
        // This puts the cardGridPane in the middle of the GamePane
        this.setCenter(this.cardGridPane);

        // Sounds
        String match = "match.mp3";
        Media media1 = new Media(new File(match).toURI().toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);

        String nomatch = "nomatch.mp3";
        Media media2 = new Media(new File(nomatch).toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);

        // A (H)orizontal box is made with 10px space and it is centered
        HBox cmdPane = new HBox(10);
        cmdPane.setAlignment(Pos.CENTER);

        // This creates a new BorderPane and then centers the cmdPane
        BorderPane root = new BorderPane();
        root.setCenter(cmdPane);

        for (int i = 0; i < cardGridPane.getMAXROWS(); i++) {
            for (int j = 0; j < cardGridPane.getMAXCOLS(); j++) {
                cardGridPane.getCard(i, j).setDisable(true);
            }
        }

        // Combo box that contains the level options (Drop-Down Menu)
        ComboBox<String> lvlOptions = new ComboBox<>();
        lvlOptions.getItems().addAll(
                "Level 1 - 2x3",
                "Level 2 - 2x4",
                "Level 3 - 4x4",
                "Level 4 - 4x6",
                "Level 5 - 6x6",
                "Level 6 - 8x8"
        );

        /**
         * This is the ActionEvent for setting the levels
         *
         * When whatever (selectedLevel) equals one of the String values
         * that is in the drop-down menu, it will set the rows and columns
         * equal to such conditions
         */
        lvlOptions.setOnAction(e -> {
            String selctedLevel = lvlOptions.getValue();

            if (selctedLevel.equals("Level 1 - 2x3")) {
                rows = 2;
                cols = 3;
            }
            else if (selctedLevel.equals("Level 2 - 2x4")) {
                rows = 2;
                cols = 4;
            }
            else if (selctedLevel.equals("Level 3 - 4x4")) {
                rows = 4;
                cols = 4;
            }
            else if (selctedLevel.equals("Level 4 - 4x6")) {
                rows = 4;
                cols = 6;
            }
            else if (selctedLevel.equals("Level 5 - 6x6")) {
                rows = 6;
                cols = 6;
            }
            else {
                rows = 8;
                cols = 8;
            }

            cardGridPane.setCurrentRows(rows);
            cardGridPane.setCurrentCols(cols);
           // cardGridPane.createCardImageList(16);
            cardGridPane.initCards(rows, cols);
            registerCardListeners();
            cardGridPane.shuffleImages();
//            cardGridPane.setCardImages();
            this.setCenter(this.cardGridPane);

        });

        newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
           newGame();
        });

        // This is the (exit) button
        exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        // This is the label for the amount of turns
        // A StatusPane that keeps track of the user turns
        // is made using an HBox and is placed at the top
        turnCount = new Label("Turns: " + clickCount);
        statusPane = new HBox(10);
        statusPane.setAlignment(Pos.TOP_CENTER);
        statusPane.getChildren().add(turnCount);
        this.setTop(statusPane);

        // This adds the ComboBox and the exit button to the cmdPane
        // Then the cmdPane is placed at the bottom of the GamePane
        cmdPane.getChildren().addAll(lvlOptions, newGameButton, exitButton);
        this.setBottom(cmdPane);

         timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timerRunning && now - startTime >= 800_000_000) {
                    timerRunning = false;
                    timer.stop();
                    if (clickedCardOne != null && clickedCardTwo != null) {
                        // This gets the path (clickedCardOne and checks if it is equal to (clickedCardTwo) and if so...
                        if (clickedCardOne.getPath().equals(clickedCardTwo.getPath())) {
                            // It will set their isMatched state to true...
                            clickedCardOne.setMatched(true);
                            clickedCardTwo.setMatched(true);
                            // Then increase the amount of matches
                            numMatched++;
                            clickCount++;
                            // play sound upon a match
                            matchAudio.play();

                            // when there are all matches, show game over pop-up
                            if (numMatched == (rows * cols) / 2){
                                javafx.application.Platform.runLater(() -> showGameOver());
                            }
                        } else {
                            // Then flip the Card (backImg) back over.
                            clickedCardOne.flipCard();
                            clickedCardTwo.flipCard();
                            // play a sound upon no match
                            nomatchAudio.play();
                        }
                        clickedCardOne = null;
                        clickedCardTwo = null;

                    }
                }
            }
         };
        registerCardListeners();
        cardGridPane.shuffleImages();
    }

    public GamePane(int cardSize){
        this();
    }

    // Class Methods
    public void newGame(){
        cardGridPane.setCurrentRows(rows);
        cardGridPane.setCurrentCols(cols);
        cardGridPane.initCards(rows, cols);
        cardGridPane.shuffleImages();
        registerCardListeners();
        clickCount = 0;
        updateTurnCount();
    }

    public void registerCardListeners(){
        // loop through each row and column of the grid
        for (int i = 0; i < cardGridPane.getCurrentRows(); i++){
            for (int j = 0; j < cardGridPane.getCurrentCols(); j++){
                // get the card at a certain position on the gridpane
                Card card = cardGridPane.getCard(i, j);

                // If there is a card at a certain position then,
                // the MouseEvent is executed
                if (card != null) {
                    card.setOnMousePressed(e -> {
                        handleCard(card);
                        updateTurnCount();
                    });
                }
            }
        }
    }

    // message pop-up logic for when the game is over
    private void showGameOver() {
        Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
        gameOver.setTitle("Game Over");
        gameOver.setHeaderText(null);
        gameOver.setContentText("Game Over");
        gameOver.showAndWait();

    }

    // handleCard logic for card matching
    private void handleCard(Card card) {
        // if the cards are already matched or faced up, then skip
        if(card.isFlipped()||card.isMatched()){return;}
        card.flipCard();
        // if this is the first card clicked then store it in (clickedCardOne)
        if (clickedCardOne == null) {
            clickedCardOne = card;
            // otherwise if it is not (clickedCardOne) then store it in (clickedCardTwo)
        } else if (clickedCardTwo == null && card != clickedCardOne) {
            clickedCardTwo = card;
            clickCount++;
            startTime = System.nanoTime();
            timerRunning = true;
            timer.start();
        }

        // makes sure the timer is started again if both cards have been clicked
        if (clickedCardOne != null && clickedCardTwo != null) {
            timer.start();
        }
    }

    // this private helper method is used to update the turnCount Label whenever a turn is taken
    private void updateTurnCount(){
        turnCount.setText("Turns: " + clickCount);
    }
}