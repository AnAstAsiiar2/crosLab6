package com.example.sitesdb.controller;

import com.example.sitesdb.data.Repository;
import com.example.sitesdb.data.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SearchByAddressController {
    @FXML
    private ListView listProducts;
    @FXML
    private TextField codeField;
    private Repository repository;
    private MainController mainController;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void search(ActionEvent actionEvent) {
        String address = codeField.getText();
        display(repository.getWebsitesByCategory(address));
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }
    private void display(List<Website> items){
        if(items.isEmpty()){
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            listProducts.setItems(noItems);
        }
        else {
            int i = 0;
            for (Website u : items){
                i++;
                u.setId(i);
            }
            ObservableList<Website> list = FXCollections.observableList(items);
            listProducts.setItems(list);
        }
        listProducts.setSelectionModel(null);
    }
}
