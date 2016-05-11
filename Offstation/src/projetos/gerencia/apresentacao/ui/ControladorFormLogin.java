package projetos.gerencia.apresentacao.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import projetos.gerencia.Principal;
import projetos.gerencia.exceptions.LoginException;

public class ControladorFormLogin implements Initializable {

    @FXML
    private Rectangle retanguloNotificacao;
    
    @FXML
    private TableView<ArrayList<String>> tabelaListaPecas;

    @FXML
    private TitledPane expansorListaPecas;
    
    @FXML
    private Label labelNotificacao;
    
    @FXML
    private Label labelLogin;

    @FXML
    private Label labelSenha;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;    
    
    @FXML
    private void pularCampoLogin(KeyEvent event){
        this.esconderNofificacao();
        if(event.getCode() == KeyCode.ENTER){
            this.txtSenha.requestFocus();
        }
    }
 
    @FXML
    private void pularCampoSenha(KeyEvent event){
        
        this.esconderNofificacao();
        
        if(event.getCode() == KeyCode.ENTER){
            this.logar();
        }
    }
    
    @FXML
    private void esconderNofificacao(){
        this.retanguloNotificacao.setVisible(false);
        this.labelNotificacao.setVisible(false);
    }
    
    @FXML
    private void exibirNofificacao(String msg){
        this.labelNotificacao.setText(msg);
        this.retanguloNotificacao.setVisible(true);
        this.labelNotificacao.setVisible(true);
    }
    
    private void criarForm(){//Criar o formulário do Login.

        Stage formLogin = (Stage) btnLogin.getScene().getWindow();
        formLogin.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FormPrincipal.fxml"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Sistema OFFStation");
            
            stage.getIcons().add(new Image(getClass().getResourceAsStream("./icone.png")));
            
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ControladorFormLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void logar(){//Logar aqui
     if ((this.txtLogin.getText().isEmpty()) ) {

            System.err.println("Login Vazio.");
            //this.labelLogin.setTextFill(Color.RED);
            this.exibirNofificacao("Campo 'Login' vazio!");
            this.txtLogin.requestFocus();
            //this.txtLogin.setStyle("-fx-text-fill: red;");

        } else if((this.txtSenha.getText().isEmpty())){
            
            System.err.println("Senha Vazia.");
            //this.labelSenha.setTextFill(Color.RED);
            this.exibirNofificacao("Campo 'Senha' vazio!");
            this.txtSenha.requestFocus();
            
            
        }else {
            
            this.labelLogin.setTextFill(Color.BLACK);
            //this.txtLogin.setStyle("-fx-text-fill: black;");
            this.labelSenha.setTextFill(Color.BLACK);
            //this.txtSenha.setStyle("-fx-text-fill: black;");
            
            String senha = this.txtSenha.getText();
            String cpf = this.txtLogin.getText();
            
            try {
                
                Principal.getInstancia().fazerLogin(cpf, senha);
                this.criarForm();
     
            } catch (LoginException ex) {
                this.exibirNofificacao(ex.getMessage());    
            } 
        }   
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        this.logar();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.labelNotificacao.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("./icone_info.png"))));
            
        //this.labelLogin.setTextAlignment(TextAlignment.LEFT);
            
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtLogin.requestFocus();
            }
        });
        
        
        Principal.getInstancia().log("Iniciou");
        //System.out.println("Iniciou");
     
        this.btnLogin.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    logar();
                
                }
            }
        
        });
        
        //List<String> dados = Arrays.asList("Peça01", "Quantidade01", "Preço01");
        
        //ObservableList lista = FXCollections.observableList(dados);
        
        //this.tabelaListaPecas.getColumns().get(0);
        
        
    }

}
