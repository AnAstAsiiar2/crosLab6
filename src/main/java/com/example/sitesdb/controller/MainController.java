package com.example.sitesdb.controller;

import com.example.sitesdb.SitesApplication;
import com.example.sitesdb.data.DataBaseConnector;
import com.example.sitesdb.data.DataBaseRepository;
import com.example.sitesdb.data.Repository;
import com.example.sitesdb.data.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView saleList;
    private Stage newWindow;
    private Repository repository;
    private MultipleSelectionModel<Website> selectionMode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new DataBaseRepository(new DataBaseConnector("websiteDB"));
        selectionMode = saleList.getSelectionModel();
        updateListsView(repository.getAll());
    }

    public void updateListsView(List<Website> sales) {
        if (sales.size() == 0) {
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            saleList.setSelectionModel(null);
            saleList.setItems(noItems);
        } else {
            ObservableList<Website> list = FXCollections.observableList(sales);
            saleList.setSelectionModel(selectionMode);
            saleList.setItems(list);
        }
    }

    @FXML
    public void deleteSale(ActionEvent actionEvent) {
        Website toDelete = (Website) saleList.getSelectionModel().getSelectedItem();
        repository.deleteWebsite(toDelete.getId());
        updateListsView(repository.getAll());
    }

    @FXML
    public void addWebsite(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(SitesApplication.class.getResource("add-sale-form.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Add website");
        newWindow.setScene(new Scene(root, 600, 400));
        AddController addController = loader.getController();
        addController.setRepository(repository);
        addController.setMainController(this);
        newWindow.show();
    }

    @FXML
    public void editWebsite(ActionEvent actionEvent) {
        Website selectedSale = (Website) saleList.getSelectionModel().getSelectedItem();
        newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(SitesApplication.class.getResource("edit-sale-form.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Edit Website");
        newWindow.setScene(new Scene(root, 600, 400));
        EditController editController = loader.getController();
        editController.setRepository(repository);
        editController.setSelectedWebsite(selectedSale); // Оновлення цього рядка
        editController.setMainController(this);
        newWindow.show();
    }

    @FXML
    public void closeStage() {
        newWindow.close();
    }

    public void searchByAddress(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(SitesApplication.class.getResource("search-by-address-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Show payments by address");
        newWindow.setScene(new Scene(root, 600, 300));
        SearchByAddressController searchByCodeController = loader.getController();
        searchByCodeController.setRepository(repository);
        searchByCodeController.setMainController(this);
        newWindow.show();
    }

    public void searchByProduct(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(SitesApplication.class.getResource("search-by-service-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Show summary");
        newWindow.setScene(new Scene(root, 600, 250));
        SearchByWebsiteController searchByProductController = loader.getController();
        searchByProductController.setRepository(repository);
        searchByProductController.setMainController(this);
        newWindow.show();
    }
}
