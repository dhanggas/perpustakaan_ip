
package com.dhanggas.controllers.buku;

import com.dhanggas.config.Bootinitializable;
import com.dhanggas.controllers.HomeController;
import com.dhanggas.entiry.Buku;
import com.dhanggas.services.BukuRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ListBukuController implements Bootinitializable{
    @Autowired
    private FormBukuController formBuku;
    @Autowired
    private HomeController homeController;
    @Autowired
    private BukuRepository bukuRepository;

    private ApplicationContext springContext;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Buku> tableView;
    @FXML
    private TableColumn<Buku, String> columnISPN;
    @FXML
    private TableColumn<Buku, String> columnJudul;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtispn;
    @FXML
    private TextField txtpenerbit;
    @FXML
    private TextField txtpengarang;
    @FXML
    private TextField txtterbit;
    @FXML
    private TextField txtjumlah;
    @FXML
    private TextArea txtjudul;
    
    private void setField(Buku b){
        txtkode.setText(b.getId());
        txtispn.setText(b.getIsbn());
        txtpenerbit.setText(b.getPenerbit().getNama());
        txtpengarang.setText(b.getPengarang());
        txtterbit.setText(b.getTanggalTerbit().toString());
        txtjumlah.setText(b.getJumlah().toString());
        txtjudul.setText(b.getJudul());
    }
    private void clearField(){
         txtkode.clear();
        txtispn.clear();
        txtpenerbit.clear();
        txtpengarang.clear();
        txtterbit.clear();
        txtjumlah.clear();
        txtjudul.clear();
    }

    @Override
    public void initConstruct() {
        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Buku > values, Buku oldValue, Buku newValue)->{
            if (newValue != null) {
                setField(newValue);
                
            }else  {
               clearField();
            }
    });
        tableView.getItems().clear();
        tableView.getItems().addAll(bukuRepository.findAll());
                
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scene/inner/buku/list.fxml"));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.println("Can not scane");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnISPN.setCellValueFactory(new PropertyValueFactory<Buku, String>("isbn"));
        columnJudul.setCellValueFactory(new PropertyValueFactory<Buku, String>("judul"));
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext =springContext;
    }

    @FXML
    private void doAdd(ActionEvent event) {
        homeController.setCenterlayout(formBuku.initView());
    }
    
}
