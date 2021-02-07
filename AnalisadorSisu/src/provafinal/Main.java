package provafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A classe Main irá iniciar o programa Analisador SISU
 * 
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Analisador SISU - Quantidade de Candidatos com Estado Diferente e Media minima por Demanda do Curso");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
