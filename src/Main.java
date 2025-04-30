
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage primaryStage){
//        Card card = new Card();
//        primaryStage.setScene(new Scene(card, 64, 64));
        GamePane gamePane = new GamePane();

//        CardGridPane cardGridPane = new CardGridPane(64);
//        cardGridPane.setCurrentRows(8);
//        cardGridPane.setCurrentCols(8);
//        cardGridPane.createCardImageList(16);
//        cardGridPane.shuffleImages();
//        cardGridPane.initCards(cardGridPane.getCurrentRows(), cardGridPane.getCurrentCols());
//        cardGridPane.setCardImages();



        Scene scene = new Scene(gamePane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("J-Card Match");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
