package com.md.cryptoarbitrage.controllers;

import com.md.cryptoarbitrage.entity.ExchangePairPrices;
import com.md.cryptoarbitrage.globals.GlobalCore;
import com.md.cryptoarbitrage.globals.GlobalStageModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import org.controlsfx.control.textfield.TextFields;
import org.knowm.xchange.currency.CurrencyPair;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainAppController implements Initializable {


    //Menus

    //Tab1
    @FXML
    private Button BtnStart;
    @FXML
    private Button BtnStop;
    @FXML
    private Label GenStatusLabel;
    @FXML
    private ChoiceBox ChoiceExchange;
    @FXML
    private ComboBox ChoicePair;
    @FXML
    private TableView GenTableView;
    @FXML
    private TableColumn exchange;
    @FXML
    private TableColumn pair;
    @FXML
    private TableColumn ask;
    @FXML
    private TableColumn bid;
    @FXML
    private TableColumn logo;
    @FXML
    private TableColumn percent;
    @FXML
    private TableColumn min;
    @FXML
    private TableColumn max;
    @FXML
    private ImageView svetofor;
    @FXML
    private LineChart procentchart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private TextField textfieldminprofit;


    private
    ObservableList<XYChart.Series<String, Double>> lineChartData;


    //Tab2

    private void initFields() {

        exchange.setCellValueFactory(new PropertyValueFactory<>("exchange"));
        pair.setCellValueFactory(new PropertyValueFactory<>("pair"));
        ask.setCellValueFactory(new PropertyValueFactory<>("ask"));
        bid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
        percent.setCellValueFactory(new PropertyValueFactory<>("percent"));
        min.setCellValueFactory(new PropertyValueFactory<>("min"));
        max.setCellValueFactory(new PropertyValueFactory<>("max"));


        svetofor.setImage(new Image("/images/Red.png"));



        lineChartData = FXCollections.observableArrayList(
                new LineChart.Series<String, Double>("% прибыли при арбитраже", GlobalStageModel.globalseria)
        );

        procentchart.setData(lineChartData);



    }


    public void initialize(URL location, ResourceBundle resources) {


        GlobalCore.globalstage.setOnHiding( event -> {quitAction();} );
        initFields();

        ChoiceExchange.setItems(GlobalStageModel.Exchanges);
        ChoiceExchange.setValue(GlobalStageModel.Exchanges.get(0));
        GenStatusLabel.setText("Остановлен. Нажмите старт!");
// Listeners
        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                if (newValue != null) {
                    System.out.println((String) ChoiceExchange.getValue());
                    ObservableList<CurrencyPair> list = GlobalStageModel.getFormListCurrencyPair((String) ChoiceExchange.getValue());
                    ChoicePair.getSelectionModel().clearSelection();
                    ChoicePair.getItems().clear();
                    ChoicePair.setItems(list);

                    TextFields.bindAutoCompletion(ChoicePair.getEditor(), ChoicePair.getItems());

                }
            }
        };
        // Selected Item Changed.
        ChoiceExchange.getSelectionModel().selectedItemProperty().addListener(changeListener);

        ListChangeListener<ExchangePairPrices> listlistener = new ListChangeListener<ExchangePairPrices>() {
            @Override
            public void onChanged(Change<? extends ExchangePairPrices> c) {
                while (c.next()) {

                    if (c.wasAdded() || c.wasRemoved()) {

                        GenTableView.setItems(GlobalStageModel.TableViewlist);
                    }
                    if (c.wasUpdated()) {


                    }
                }
            }
        };

        GlobalStageModel.TableViewlist = FXCollections.observableArrayList(ExchangePairPrices.extractor());

        GlobalStageModel.TableViewlist.addListener(listlistener);


//**************************
        ChoicePair.setEditable(true);

        ChoicePair.setItems(GlobalStageModel.getFormListCurrencyPair((String) ChoiceExchange.getValue()));
        TextFields.bindAutoCompletion(ChoicePair.getEditor(), ChoicePair.getItems());
        setTimerRefresh();

    }

    private void startAction(){
        GlobalCore.logicStart();
        GenStatusLabel.setText("Система работает.");
        svetofor.setImage(new Image("/images/Green.png"));
        try {
            GlobalStageModel.profitlimit = Double.parseDouble(textfieldminprofit.getCharacters().toString());
        } catch (NumberFormatException e) {
            // e.printStackTrace();
            GlobalStageModel.profitlimit = 0;
        }
    }

    public void BtnStartOnAction(ActionEvent actionEvent) {
        startAction();
    }

    private void stopAction(){
        GlobalCore.setApplicationStart(false);
        GenStatusLabel.setText("Остановлен. Нажмите старт!");
        svetofor.setImage(new Image("/images/Red.png"));
    }

    public void BtnStopOnAction(ActionEvent actionEvent) {
        stopAction();
    }


    public void OnInputTextChanged(InputMethodEvent inputMethodEvent) {
        System.out.println(("Changed"));
    }

    public void BtnAddExchangePairOnAction(ActionEvent actionEvent) {
        if (ChoiceExchange.getValue() != null && ChoicePair.getValue() != null) {

            CurrencyPair tempcp = SearchPairCBox(ChoicePair.getValue().toString());
            if (tempcp != null)
                GlobalStageModel.UpdateExchangePairTable(tempcp, (String) ChoiceExchange.getValue());

            // }


        }
    }

    private CurrencyPair SearchPairCBox(String temppair) {
        CurrencyPair cp = null;
        for (CurrencyPair currencyPair : GlobalStageModel.ComboboxPairList) {
            if (temppair.equals(currencyPair.toString())) {
                return currencyPair;
            }
        }
        return cp;
    }

    public void BtnDeleteExchangePairOnAction(ActionEvent actionEvent) {
        ExchangePairPrices selecteditem = (ExchangePairPrices) GenTableView.getSelectionModel().getSelectedItems().get(0);
        GlobalStageModel.RemovefromExchangePairTable(selecteditem);
    }


    private void setTimerRefresh() {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                GenTableView.setItems(GlobalStageModel.TableViewlist);
                GenTableView.refresh();

              if(GlobalCore.isQuitall()) timer.cancel();



            }
        };
        timer.schedule(timerTask, 0, 1000);
    }


    public void btnclearchartact(ActionEvent actionEvent) {
        GlobalStageModel.globalseria.clear();
    }

    public void MenuBtnStartAction(ActionEvent actionEvent) {
        startAction();
    }

    public void MenuBtnStopAction(ActionEvent actionEvent) {
        stopAction();
    }

    public void MenuBtnQuitAction(ActionEvent actionEvent) {
        quitAction();
    }

    private void quitAction() {
        GlobalCore.setApplicationStart(false);
        GlobalCore.setQuitall(true);
        GlobalCore.globalstage.close();

    }

    public void MenuBtnAboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("Разработано Epic code 2019(c)");
        String s ="По вопросам модернизации или создания полноценного рабочего торгового терминала -  пишите в Telegram https://t.me/epiccode";
        alert.setContentText(s);
        alert.show();
    }
}
