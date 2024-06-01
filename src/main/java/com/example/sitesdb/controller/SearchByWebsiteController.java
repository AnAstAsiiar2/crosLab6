package com.example.sitesdb.controller;

import com.example.sitesdb.data.Repository;
import com.example.sitesdb.data.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchByWebsiteController {
    @FXML
    private ListView<String> buyerList;
    private Repository repository;
    private MainController mainController;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void search(ActionEvent actionEvent) {
        String searchText = ""; // Отримайте текст для пошуку з потрібного компонента
        List<Website> websites = repository.searchWebsitesByDescription(searchText);
        display(websites);
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }

    private void display(List<Website> websites) {
        if (websites.size() == 0) {
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            buyerList.setItems(noItems);
        } else {
            List<String> list = new ArrayList<>();
            int i = 1;
            for (Website website : websites) {
                String item = i++ + ") " + website.getName() + " — " + website.getDescription();
                list.add(item);
            }
            ObservableList<String> observableList = FXCollections.observableList(list);
            buyerList.setItems(observableList);
        }
        buyerList.setSelectionModel(null);
    }
}
