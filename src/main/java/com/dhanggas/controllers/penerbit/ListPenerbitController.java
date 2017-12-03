package com.dhanggas.controllers.penerbit;

import com.dhanggas.config.Bootinitializable;
import com.dhanggas.controllers.HomeController;
import com.dhanggas.entiry.Penerbit;
import com.dhanggas.services.PenerbitRepository;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ListPenerbitController implements Bootinitializable {

    @Autowired
    private PenerbitRepository penerbitRepository;

    @Autowired
    private FormPenerbitController formPenerbit;
    @Autowired
    private HomeController homeController;

    private ApplicationContext springContext;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Penerbit> tableView;
    @FXML
    private TableColumn<Penerbit, String> columnkode;
    @FXML
    private TableColumn<Penerbit, String> columnnama;
    @FXML
    private TableColumn<Penerbit, String> columnurl;

    @Override
    public void initConstruct() {
        tableView.getItems().clear();
        tableView.getItems().addAll(penerbitRepository.findAll());

    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scene/inner/penerbit/list.fxml"));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.println("Can not scane");
            ex.printStackTrace();
            return null;
        }
//return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnkode.setCellValueFactory(new PropertyValueFactory<Penerbit, String>("kode"));
        columnnama.setCellValueFactory(new PropertyValueFactory<Penerbit, String>("nama"));
        columnurl.setCellValueFactory(new PropertyValueFactory<Penerbit, String>("web"));

        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Penerbit> values, Penerbit oldValue, Penerbit penerbit) -> {
            btnUpdate.setDisable(penerbit == null);
            btnUpdate.setOnAction(e -> {
                doUdate(e, penerbit);
            });
            btnDelete.setDisable(penerbit == null);
            btnDelete.setOnAction(e -> {
                doDelete(e, penerbit);
            });
        });
    }

    private void doUdate(ActionEvent e, Penerbit penerbit) {
        homeController.setCenterlayout(formPenerbit.initView());
        formPenerbit.initConstruct(penerbit);
    }

    private void doDelete(ActionEvent e, Penerbit penerbit) {
        penerbitRepository.delete(penerbit);
        initConstruct();
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext = springContext;
    }

    @FXML
    private void doAdd(ActionEvent event) {
        homeController.setCenterlayout(formPenerbit.initView());
        formPenerbit.initConstruct();
    }

}
