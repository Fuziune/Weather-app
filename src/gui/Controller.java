package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import main.domain.Weather;
import service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import service.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import static java.awt.SystemColor.text;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Weather> ListVieww=new ListView<>();
    @FXML
    private ComboBox<String> conbo=new ComboBox<>();
    void PopulateList(){
        ObservableList<Weather>DocumentList= FXCollections.observableList(service.getAll());
        ListVieww.setItems(DocumentList);

        ListVieww.setOnMouseClicked(this::handleDocumentClick);
}
    List<String> casa(){
        List<String> list=new ArrayList<>();
        for(Weather weather:service.getAll())
        {int ok=0;
            String description = weather.getDescription();


            int commaIndex = description.indexOf(',');


            String substring = (commaIndex != -1) ? description.substring(0, commaIndex) : description;

            for(String string:list)
            {
                if(list.isEmpty())
                    list.add(substring);
                if(string.equals(substring))
                    ok=1;
            }
            if(ok==0)
                list.add(substring);
        }
        return list;
    }
    public void initialize(){
        PopulateList();
        conbo.setItems(FXCollections.observableList(casa()));
    }
    ObservableList<Weather> bookingObservableList;
    @FXML
    void BoxBox(ActionEvent event) {
        String searchText = conbo.getValue();
        List<Weather> sortedRepooByName = null;
        sortedRepooByName = service.getAll().stream()
                .sorted(Comparator.comparing(Weather::getStart)).collect(Collectors.toList());
        List<Weather> filteredDocuments = service.getAll().stream()
                .filter(weather -> weather.getDescription().contains(searchText) )
                .sorted(Comparator.comparing(Weather::getStart))
                .collect(Collectors.toList());

        bookingObservableList=FXCollections.observableArrayList(filteredDocuments);
        ListVieww.setItems(bookingObservableList);
        hours(event);
    }
    @FXML
    private TextArea HO;
    @FXML
    private TextField ho;

    @FXML
    void HOURS(MouseEvent event) {

    }

    @FXML
    void hours(ActionEvent event) {
        String searchText = conbo.getValue();
        int or=0;
        for(Weather weather:service.getAll())
        {
            String description = weather.getDescription();


            int commaIndex = description.indexOf(',');


            String substring = (commaIndex != -1) ? description.substring(0, commaIndex) : description;
            if(substring.equals(searchText)){
                or=or+weather.getEnd()-weather.getStart();
            }

        }
        System.out.println(or);
        HO.setText(String.valueOf(or));
    }
    @FXML
    private TextField descript;
    @FXML
    private TextField precip;

    private void handleDocumentClick(MouseEvent event)  {;

        Weather selectedDocument = ListVieww.getSelectionModel().getSelectedItem();
        int prec1=Integer.parseInt(String.valueOf(precip.getText()));
        String descript1= String.valueOf(descript.getText());
        Weather weather=new Weather(selectedDocument.getStart(),selectedDocument.getEnd(),selectedDocument.getTemeperature(),prec1,descript1);
            //System.out.println("Document clicked: " + selectedDocument.getName());
        service.update(weather);
        bookingObservableList=FXCollections.observableArrayList(service.getAll());
        ListVieww.setItems(bookingObservableList);
        }

}
