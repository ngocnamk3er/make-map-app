package main;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Pane pane;
    private TextArea textArea;
    private Image[] allBrick;
    private BufferedImage bufferedImage;
    @Override
    public void start(Stage stage) throws IOException {
        loadAllBrick();
        scene = new Scene(new Group(), 1344, 768);
        stage.setScene(scene);
        stage.show();
        pane = new Pane();
        scene.setRoot(pane);
        textArea = new TextArea();
        textArea.setPrefHeight(350);
        textArea.setPrefWidth(450);
        textArea.setWrapText(true);
        pane.getChildren().add(textArea);
        for(int i=0;i<72;i++){
            Button button = new Button();
            button.setFont(Font.loadFont(App.class.getResourceAsStream("m6x11.ttf"), 25));
            button.setBackground(new Background(new BackgroundImage(allBrick[i],BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
            button.setPrefWidth(64);
            button.setPrefHeight(64);
            button.setLayoutX(500+i%12*64);
            button.setLayoutY(i/12*64);
            button.setText(String.valueOf(i));
            button.setTextFill(Color.RED);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String oldText = textArea.getText();
                    if(oldText.length()==0||textArea.getText().charAt(textArea.getText().length()-1)=='\n'){
                        textArea.setText(textArea.getText()+"{");
                    }
                    if(textArea.getText().length()%66==61){
                        if(button.getText().length()==1){
                            textArea.setText(textArea.getText()+button.getText()+" ");
                        }else{
                            textArea.setText(textArea.getText()+button.getText());
                        }
                    }else{
                        if(button.getText().length()==1){
                            textArea.setText(textArea.getText()+button.getText()+" ,");
                        }else{
                            textArea.setText(textArea.getText()+button.getText()+",");
                        }
                    }
                    System.out.println(textArea.getText().length()%66);
                    if(textArea.getText().length()%66==63){
                        // System.out.println("xuong dong");
                        textArea.setText(textArea.getText()+"},");
                        textArea.setText(textArea.getText()+"\n");
                    }
                    if(textArea.getText().length()==792){
                        textArea.setText(textArea.getText()+"\n"+"--------------------------------");
                    }
                }
                
            });
            pane.getChildren().add(button);
        }
        // textArea.setText("{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,");
        // System.out.println(textArea.getText().length());//61
        // textArea.setText("{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n");
        // System.out.println(textArea.getText().length());//
        // textArea.setText("{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n{16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16},\n");
        // System.out.println(textArea.getText().length());//792
    }
    private void loadAllBrick(){
        allBrick=new Image[72];
        try {
            bufferedImage = ImageIO.read(App.class.getResourceAsStream("AllBricks.png"));
            for(int i=0;i<72;i++){
                allBrick[i]=SwingFXUtils.toFXImage(bufferedImage.getSubimage(i%12*64, i/12*64, 64, 64), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }

}