/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhanggas.controllers.penerbit;

import com.dhanggas.config.Bootinitializable;
import com.dhanggas.controllers.HomeController;
import com.dhanggas.entiry.Penerbit;
import com.dhanggas.services.PenerbitRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FormPenerbitController implements Bootinitializable {

    @Autowired
    private HomeController homeController;
    @Autowired
    private PenerbitRepository penerbitRepository;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txturl;
    private ApplicationContext springContext;

    private Boolean update;

    private Penerbit penerbit;

    public Boolean isUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    private void clearField() {
        txtkode.clear();
        txtnama.clear();
        txturl.clear();
    }

    @FXML
    private void doSave(ActionEvent event) {
        if (isUpdate()) {
            try {
                this.penerbit.setNama(txtnama.getText());
                this.penerbit.setWeb(txturl.getText());
                penerbitRepository.save(this.penerbit);
                doBack(event);
                clearField();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.penerbit.setKode(txtkode.getText());
                this.penerbit.setNama(txtnama.getText());
                this.penerbit.setWeb(txturl.getText());
                penerbitRepository.save(this.penerbit);
                initConstruct();
                clearField();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void doBack(ActionEvent event) {
        homeController.tampilPenerbit(event);
    }

    /**
     * di gukan untuk membuat obyek baru / tambah data
     */
    @Override
    public void initConstruct() {
        setUpdate(false);
        this.txtkode.setEditable(true);
        this.penerbit = new Penerbit();

    }

    /**
     * di gukan untuk update data yang udah ada
     */
    public void initConstruct(Penerbit penerbit) {
        setUpdate(true);
        this.txtkode.setEditable(false);
        this.penerbit = penerbit;
        this.txtkode.setText(penerbit.getKode());
        this.txtnama.setText(penerbit.getNama());
        this.txturl.setText(penerbit.getWeb());
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scene/inner/penerbit/Form.fxml"));
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
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext = springContext;
    }

}
