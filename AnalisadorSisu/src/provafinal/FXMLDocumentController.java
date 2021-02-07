package provafinal;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * Controlador do programa
 * 
 * @author Daniel Santos de Sena (danielsan.2000 at hotmail.com)
 * @since Fev 2021
 */
public class FXMLDocumentController {
    //Maps que amazenarao 
    private Map<String,List<String>> dadosCursosEstados = new Hashtable();
    private Map<String,List<String>> dadosCursosDemandas = new Hashtable();    
    private Map<String,List<Float>> dadosCursosMedias = new Hashtable();
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem fileOpen;

    @FXML
    private MenuItem fileClose;
    
    @FXML
    private MenuItem limparTudo;

    @FXML
    private ListView<String> listView;

    @FXML
    private BarChart<String, Integer> relacaoEstados;

    @FXML
    private CategoryAxis eixoCategoria;

    @FXML
    private NumberAxis eixoValores;
    
    @FXML
    private BarChart<String, Float> relacaoMinDemanda;

    @FXML
    private CategoryAxis eixoMinDemandaX;

    @FXML
    private NumberAxis eixoMinDemandaY;
    
    @FXML
    private Label statusLabel;

    /**
     * Irá fechar o programa.
     * 
     * @param event Clique do mouse no botao "Fechar Programa"
     */
    @FXML
    private void onFileClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Irá abrir o arquivo txt do SISU e adicionar todos cursos no listView
     * @param event Clique do mouse no botao "Abrir arquivo"
     */
    @FXML
    private void onFileOpen(ActionEvent event) {
        
        try {
            FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showOpenDialog(null);

            ArrayList<Map> analises;
            analises = Candidato.parserListaSisu(f); //Criacao de um ArrayList de Map contendo as relacoes do programa
            
            dadosCursosEstados = analises.get(0);
            dadosCursosDemandas = analises.get(1); 
            dadosCursosMedias = analises.get(2);
                       
            String cursos [] = dadosCursosEstados.keySet().toArray(new String[0]); //Vetor com os nomes dos cursos
            Arrays.sort(cursos);
            
            listView.getItems().clear();
            
            for (String c : cursos) { //Nomes dos cursos sera exibido no listView
                listView.getItems().add(c);
            }
            
            //EXTRAS
            limparTudo.setDisable(false); //Opcao "Limpar Tudo" fica disponivel
            statusLabel.setText("(Arquivo aberto! Selecione um curso.)"); //Status alterado
            
        } catch (Exception e) { //Caso nenhum arquivo seja carregado.
            statusLabel.setText("(Não foi carregado nenhum arquivo nesta ultima operação.)");   
        }
    }

    /**
     * Ira mostrar nos graficos as relacoes do curso selecionado.
     * @param event Clique do mouse em um curso no listView
     */
    @FXML
    private void onMouseClickedLV(MouseEvent event) {
        try {
            
            String curso = listView.getSelectionModel().getSelectedItem(); //Atribui para a variavel "curso" o nome clicado no listView
            
            /*
            GRAFICO 1 - Quantidade de Estados por Curso
            */
            RelacaoEstadosCurso r = new RelacaoEstadosCurso(dadosCursosEstados.get(curso)); //Envia a lista de estados do curso selecionado para ser feita a contagem
            
            eixoCategoria.setLabel("Total de Estados: " + r.qtdEstados); //Altera o label da Categoria exibindo a quantidade de estados total
            eixoValores.setLabel("Quantidade Total de Candidatos: "+ r.qtdCandidatos); //Altera o label dos Valores exibindo a quantidade total de candidatos

            BarChart.Series<String, Integer> dados = new BarChart.Series<>(); //Novo BarChart.Series para o grafico 1
            
            relacaoEstados.getData().clear(); //Limpa o grafico 1
            
            for (int i=0; i<r.eixoX.length; i++){ //Adiciona os valores da RelacaoEstadoCurso ao BarChart.Series
                dados.getData().add(new BarChart.Data<String, Integer> (r.eixoX[i], r.eixoY[i])); 
            }

            relacaoEstados.getData().add(dados); //Adiciona os valores ao grafico 1.
            
            /*
            GRAFICO 2 - Quantidade de Estados por Curso
            */
            
            //Envia a lista de demanda e de media do curso selecionado para ser feita a relacao da menor media
            RelacaoCursoDemanda dem = new RelacaoCursoDemanda(dadosCursosDemandas.get(curso), dadosCursosMedias.get(curso)); 
            
            BarChart.Series<String, Float> dadosMinDemanda = new BarChart.Series<>(); //Novo BarChart.Series para o grafico 2
            
            relacaoMinDemanda.getData().clear(); //Limpa o grafico 2
            
            for (String key : dem.demandaMedia.keySet()){ //Ira adicionar os valores analisados ao BarChart.Series
                dadosMinDemanda.getData().add(new BarChart.Data<String, Float> (key, dem.demandaMedia.get(key)));
            }
            
            relacaoMinDemanda.getData().add(dadosMinDemanda); //adiciona os dados ao grafico 2
            
            // EXTRAS
            limparTudo.setDisable(false); //Opcao "Limpar tudo" fica disponivel
            statusLabel.setText("(Curso selecionado: "+curso); //Status alterado
            
        } catch (Exception e) { //Caso clique no listView vazio
            statusLabel.setText("(Ainda não foi iniciado um arquivo. Abra um novo arquivo para iniciar.)");
        }
    }
    
    /**
     * Ira limpar os dados exibidos
     * 
     * @param event Clique do mouse no botao "Limpar Tudo"
     */
    @FXML
    private void onLimparTudo(ActionEvent event) {
        relacaoEstados.getData().clear();
        relacaoMinDemanda.getData().clear();
        listView.getItems().clear();
        eixoCategoria.setLabel("Total de Estados");
        eixoValores.setLabel("Quantidade Total de Candidatos");
        limparTudo.setDisable(true);
        statusLabel.setText("(Os dados foram limpos.)");
        
    }

    @FXML
    private void initialize() {
        assert fileOpen != null : "fx:id=\"fileOpen\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert fileClose != null : "fx:id=\"fileClose\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert limparTudo != null : "fx:id=\"limparTudo\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert relacaoEstados != null : "fx:id=\"relacaoEstados\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert eixoCategoria != null : "fx:id=\"eixoCategoria\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert eixoValores != null : "fx:id=\"eixoValores\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert relacaoMinDemanda != null : "fx:id=\"relacaoMinDemanda\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert eixoMinDemandaX != null : "fx:id=\"eixoMinDemandaX\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert eixoMinDemandaY != null : "fx:id=\"eixoMinDemandaY\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert statusLabel != null : "fx:id=\"statusLabel\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }
}
