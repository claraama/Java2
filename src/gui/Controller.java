package gui;

import Domain.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.Service;
import repository.Repository;

import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }



    @FXML
    private ListView<Question> list_allFX;
    @FXML
    private TextField searchfield_FX;

    ObservableList<Question> listOBS= FXCollections.observableArrayList();
    ObservableList<Question> answered=FXCollections.observableArrayList();

    public void initialize()
    {
        service.ReadfromDB();
        /*

       Comparator<Question> compQ= Comparator
               .comparing(Question::getScore);
        Vector<Question> sortedQ=service.getAll().stream()
                        .sorted(compQ)
                                .collect(Collectors.toCollection(Vector::new));
        for(Question q:sortedQ)
            listQ.add(q);

        list_allFX.setItems(listQ);

         */
        service.sortbyscore().forEach(q -> listOBS.add(q));
        list_allFX.setItems(listOBS);
    }
    @FXML
    void searchquestions(ActionEvent event)
    {

        var searchtext=searchfield_FX.getText();
        String array[]=searchtext.split(" "); //va fi un sir de stringuri din textul initial
        var searchedList= service.filter(array[0],Integer.parseInt(array[1]));
        listOBS.clear();
        list_allFX.setItems(listOBS);
        searchedList.forEach(question ->listOBS.add(question));
        list_allFX.setItems(listOBS);
    }

    @FXML
    private ListView<Question> listANSWERED_FX;
    @FXML
    private TextField answerFX;

    @FXML
    void answerquestion(ActionEvent event)
    {
        Question selected=list_allFX.getSelectionModel().getSelectedItem();
        var answer= answerFX.getText();

        selected.setUserA(answer);
        answered.add(selected);
        listANSWERED_FX.setItems(answered);

       // service.deleteQuestion(selected);
        listOBS.remove(selected);
        list_allFX.setItems(listOBS);
        list_allFX.refresh();
    }

}
