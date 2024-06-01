package com.example.sitesdb.controller;

import com.example.sitesdb.data.Repository;
import com.example.sitesdb.data.Website;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class AddController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField urlField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField pagesField;
    @FXML
    private TextField visitorsField;
    @FXML
    private DatePicker yearFoundedPicker;

    private Repository repository;
    private MainController mainController;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addWebsite() {
        // Retrieve data from the fields
        String name = nameField.getText();
        String url = urlField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        int pages = Integer.parseInt(pagesField.getText());
        int visitors = Integer.parseInt(visitorsField.getText());
        int yearFounded = yearFoundedPicker.getValue().getYear();


        Website website = new Website(0, name, url, category, description, pages, visitors, yearFounded);
        repository.addWebsite(website);
        mainController.closeStage();
        mainController.updateListsView(repository.getAll());
    }

    @FXML
    public void cancel() {
        mainController.closeStage();
    }
}
