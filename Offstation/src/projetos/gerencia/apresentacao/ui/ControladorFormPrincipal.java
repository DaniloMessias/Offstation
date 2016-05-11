package projetos.gerencia.apresentacao.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import projetos.gerencia.apresentacao.ControlarProduto;
import projetos.gerencia.apresentacao.ui.excessoes.CampoVazioException;
import projetos.gerencia.negocio.produto.IProduto;
import projetos.gerencia.negocio.produto.Peca;
import projetos.gerencia.negocio.produto.Produto;
import projetos.gerencia.negocio.produto.Servico;

public class ControladorFormPrincipal implements Initializable{

    //**INICIO COMPONENTES PRODUTOS**
    
    @FXML
    private Rectangle retanguloNotificacao;
    @FXML
    private Label labelNotificacao;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtQuantidadeProduto;
    @FXML
    private Button btnAddProdutos;
    @FXML
    private TableView<List<Produto>> tabelaListaProdutos;
    @FXML
    private Button btnBuscarProdutos;
    @FXML
    private TextField txtBuscaNomeProduto;
    @FXML
    private TextField txtPrecoProduto;
    @FXML
    private TextField txtMarcaProduto;
    @FXML
    private TextField txtBuscaCodProduto;
    @FXML
    private TextField txtIdProduto;
    @FXML
    private ComboBox<String> cbxTipoProduto;
    @FXML
    private Button btnListarProdutos;
    @FXML
    private TitledPane expansorListaProdutos;
    
    private ControlarProduto controlarProduto;

    //**FIM COMPONENTES PRODUTOS**
    
    @FXML
    private void expandirLista(TitledPane expansorLista) {
        if(expansorLista.getMaxHeight() == 0){
            expansorLista.setMaxHeight(326);
        }else{
            expansorLista.setMaxHeight(0);
        }
    }

    private void esconderNofificacao(){
        this.retanguloNotificacao.setVisible(false);
        this.labelNotificacao.setVisible(false);
    }
   
    private void exibirNofificacao(String msg){
        //this.retanguloNotificacao.setFill(Color.LIGHTGREEN);//;Style("-fx-text-fill: green;");
        this.retanguloNotificacao.setVisible(true);
        
        this.labelNotificacao.setVisible(true);
        this.labelNotificacao.setText(msg);
      // this.labelNotificacao.setTextFill(Color.GREEN);
        
        
    }
    
    
    //String nomeProduto, String quantidadeProduto, String precoProduto, String marcaProduto, String idProduto, int tipoProduto
    private void adicionarProduto() throws CampoVazioException{
          
        if(this.txtIdProduto.getText().isEmpty()){
            throw  new CampoVazioException("Campo 'Id' vazio.");
        }else if(this.txtNomeProduto.getText().isEmpty()){
            throw  new CampoVazioException("Campo 'Nome' vazio.");
        }else if(this.txtQuantidadeProduto.getText().isEmpty()){
            throw  new CampoVazioException("Campo 'Quantidade' vazio.");
        }else if(this.txtPrecoProduto.getText().isEmpty()){
            throw  new CampoVazioException("Campo 'Preço' vazio.");
        }else if((!this.txtMarcaProduto.isDisabled()) && this.txtMarcaProduto.getText().isEmpty()){
            throw  new CampoVazioException("Campo 'Marca' vazio.");
        }else{
            //ADD AQUI O PRODUTO
            
            try{
                double preco = Double.parseDouble(this.txtPrecoProduto.getText());
                
                try{
                    int id = Integer.parseInt(this.txtIdProduto.getText());
                    
                    //this.exibirNofificacao(String.valueOf(id));
                    try{
                        int quantidade = Integer.parseInt(this.txtQuantidadeProduto.getText());
                        String marca;
                        String nome = this.txtNomeProduto.getText();
                        Produto produto;  
            
                        if(this.txtMarcaProduto.isDisabled()){
                            marca = "OffStation";
                            this.controlarProduto.salvar(new Servico(0, quantidade, preco, nome, marca));
                        }else{
                            marca = this.txtMarcaProduto.getText();
                            int as = id;
                            this.controlarProduto.salvar(new Peca(0, quantidade, preco, nome, marca));
                            
                        }
                        
                        //Produto a = new Peca(0, 0, 10.0, "xdv", "asdad");
                        //salvarProduto(a);
                        

                    }catch(NumberFormatException nfex){
                        this.exibirNofificacao("O campo 'preço' deve possuir um número no formato 00.00");
                    }
                    
                }catch(NumberFormatException nfex){
                    this.exibirNofificacao("O campo 'id' deve possuir um número");
                }

            }catch(NumberFormatException nfex){
                this.exibirNofificacao("O campo 'preço' deve possuir um número no formato 00.00");
            }
            
        }
        
        
        
    }
    
    private void preencherTabela(TableView<List<Produto>> tabela, List<Produto> listaProdutos){
    
        
        
    }
    
    private void bloquearComponentesProduto(boolean estado){
    
        this.txtIdProduto.setDisable(estado);
        this.txtNomeProduto.setDisable(estado);
        this.txtQuantidadeProduto.setDisable(estado);
        this.txtPrecoProduto.setDisable(estado);
        
    }
    
    private void salvarProduto(Produto produto){
        new ControlarProduto().salvar(produto);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        this.cbxTipoProduto.setItems(FXCollections.observableList(Arrays.asList("Produto", "Serviço")));
        this.cbxTipoProduto.getSelectionModel().select(0);
        
     this.controlarProduto = new ControlarProduto();
     
     
        
        this.labelNotificacao.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("./icone_info.png"))));
        this.expansorListaProdutos.setOnMouseClicked((MouseEvent event) -> {
            expandirLista(this.expansorListaProdutos);
        });
        
        this.cbxTipoProduto.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            bloquearComponentesProduto(false);
            
            if(newValue.equals("Produto")){
                txtMarcaProduto.setDisable(false);
            }else{
                txtMarcaProduto.setDisable(true);
            }
        
            
        });
        
        this.btnAddProdutos.setOnAction((ActionEvent event) -> {
            try {
               this.adicionarProduto();
            } catch (CampoVazioException cvex) {
               this.exibirNofificacao(cvex.getMessage());
            }
        });
        
    }
}
