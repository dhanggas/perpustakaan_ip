package com.dhanggas.controllers;

import com.dhanggas.controllers.buku.ListBukuController;
import com.dhanggas.config.Bootinitializable;
import com.dhanggas.controllers.buku.FormBukuController;
import com.dhanggas.controllers.penerbit.ListPenerbitController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HomeController implements Bootinitializable {
    @Autowired
    private FormBukuController formBuku;
    @Autowired
    private ListBukuController listBuku;
    @Autowired
    private ListPenerbitController listPenerbit;

    @FXML
    private BorderPane homeLayout;

    public void setCenterlayout(Node node) {
        this.homeLayout.setCenter(node);
        this.homeLayout.autosize();
    }

    private ApplicationContext springContext;

    @Override
    public void initConstruct() {
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scene/Home.fxml"));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.println("Can not scane");
            ex.printStackTrace();
            return null;
        }
    }

    public void doClose(ActionEvent e) {
        Platform.exit();
    }

    public void tampilBuku(ActionEvent e) {
        setCenterlayout(listBuku.initView());
        listBuku.initConstruct();

    }

    public void tampilPenerbit(ActionEvent e) {
        setCenterlayout(listPenerbit.initView());
        listPenerbit.initConstruct();

    }

    public void tampilAnggota(ActionEvent e) {

    }

    public void tampilPeminjaman(ActionEvent e) {

    }

    public void tampilPengembalian(ActionEvent e) {

    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext = springContext;
    }

}
