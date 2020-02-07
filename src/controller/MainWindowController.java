package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Haslo;
import java.util.Map;
import java.util.function.Predicate;

public class MainWindowController {

    private EnterWindowController enterWindowController;
    private Stage primaryStage;
    private ObservableList<Haslo> haslaList = FXCollections.observableArrayList();
    private ObservableList<Haslo> findList = FXCollections.observableArrayList();
    private Haslo haslo = new Haslo();

    public ObservableList<Haslo> getHaslaList() {
        return haslaList;
    }

    public void setHaslaList(ObservableList<Haslo> haslaList) {
        this.haslaList = haslaList;
    }

    public void addToHaslaList(Haslo haslo) {
        this.haslaList.add(haslo);
    }

    public void setTabela() {
        Map<String, Haslo> map = haslo.init();
        boolean i = false;
        for (Map.Entry<String, Haslo> item : map.entrySet()) {
            haslaList.add(item.getValue());
        }
    }

    public void setWstepController(EnterWindowController enterWindowController, Stage primaryStage) {
        this.enterWindowController = enterWindowController;
        this.primaryStage = primaryStage;
        setTabela();
        tabela.setItems(haslaList);
    }

    public void initialize() {
//        columnId.setCellValueFactory(new PropertyValueFactory<Haslo, String>("Id"));
        columnHaslo.setCellValueFactory(new PropertyValueFactory<Haslo, String>("Haslo"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<Haslo, String>("Opis"));
        columnDlugosc.setCellValueFactory(new PropertyValueFactory<Haslo, String>("Dlugosc"));
    }

    @FXML
    private TableView<Haslo> tabela;

//    @FXML
//    private TableColumn<Haslo, String> columnId;

    @FXML
    private TableColumn<Haslo, String> columnHaslo;

    @FXML
    private TableColumn<Haslo, String> columnOpis;

    @FXML
    private TableColumn<Haslo, String> columnDlugosc;

    @FXML
    private TextField textFiltruj;

    @FXML
    private Button buttonClear, buttonOdswiez;

    @FXML
    private Button buttonDodaj;

    @FXML
    private Button buttonKoniec;


    @FXML
    void dodaj() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/AdditionWindow.fxml"));
            AnchorPane pane = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Okno edycji");
            Scene scene = new Scene(pane);
            AdditionWindowController additionWindowController = loader.getController();
            additionWindowController.setMainWindowController(this, secondaryStage);
            secondaryStage.setScene(scene);
            secondaryStage.setResizable(false);
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void filtruj() {
        FilteredList<Haslo> filteredList = new FilteredList<>(haslaList, e -> true);
        textFiltruj.setOnKeyPressed(e -> {
            textFiltruj.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Haslo>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user.getHaslo().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Haslo> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(sortedList);
        });
    }

    @FXML
    void koniec() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Czy na pewno chcesz wyjść i zapisac zmiany?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //To DO zapis pliku
            System.exit(0);
        }else if(alert.getResult() == ButtonType.NO){
            System.exit(0);
        }
    }

    @FXML
    void odswiez() {
        tabela.setItems(haslaList);
        textFiltruj.clear();
    }

    @FXML
    void wyczysc() {
        tabela.setItems(haslaList);
        textFiltruj.clear();
    }

}
