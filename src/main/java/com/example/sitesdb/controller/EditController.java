package com.example.sitesdb.controller;

import com.example.sitesdb.data.Repository;
import com.example.sitesdb.data.Website;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditController {
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
    private Website selectedWebsite;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSelectedWebsite(Website selectedWebsite) {
        this.selectedWebsite = selectedWebsite;
        nameField.setText(selectedWebsite.getName());
        urlField.setText(selectedWebsite.getUrl());
        categoryField.setText(selectedWebsite.getCategory());
        descriptionField.setText(selectedWebsite.getDescription());
        pagesField.setText(Integer.toString(selectedWebsite.getPages()));  // Виправлено
        visitorsField.setText(Integer.toString(selectedWebsite.getVisitors()));  // Виправлено
        yearFoundedPicker.setValue(java.time.LocalDate.of(selectedWebsite.getYearFounded(), 1, 1));
    }

    public void saveChanges(ActionEvent actionEvent) {
        String name = nameField.getText();
        String url = urlField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        int pages = Integer.parseInt(pagesField.getText());  // Виправлено
        int visitors = Integer.parseInt(visitorsField.getText());  // Виправлено
        int yearFounded = yearFoundedPicker.getValue().getYear();

        selectedWebsite.setName(name);
        selectedWebsite.setUrl(url);
        selectedWebsite.setCategory(category);
        selectedWebsite.setDescription(description);
        selectedWebsite.setPages(pages);  // Виправлено
        selectedWebsite.setVisitors(visitors);  // Виправлено
        selectedWebsite.setYearFounded(yearFounded);

        repository.updateWebsite(selectedWebsite.getId(), selectedWebsite);
        mainController.closeStage();
        mainController.updateListsView(repository.getAll());
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }
}
