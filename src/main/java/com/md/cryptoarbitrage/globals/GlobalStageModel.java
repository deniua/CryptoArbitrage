package com.md.cryptoarbitrage.globals;


import com.md.cryptoarbitrage.entity.ExchangePairPrices;
import com.md.cryptoarbitrage.factory.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;

import java.util.Map;

public class GlobalStageModel {


    public static ObservableList<CurrencyPair> ComboboxPairList = null;

    public static ObservableList<XYChart.Data<String, Double>> globalseria = FXCollections.observableArrayList();

    public static ObservableList<ExchangePairPrices> TableViewlist;

    public static double profitlimit;


    public static ObservableList<String> Exchanges =
            FXCollections.observableArrayList("Binance", "Bitfinex", "Bittrex", "Bitmex", "Bitstamp",
                    "CexIO", "HitBTC", "Huobi", "Kraken", "Poloniex", "Yobit","CoinBene","CoinBase","BiBox");
    //"BitBay" ,"CoinEgg"

    // methods

    public static ObservableList<CurrencyPair> getFormListCurrencyPair(String exchange) {
        ObservableList<CurrencyPair> list = null;

        Exchange tmpexchange = ExchangeFromString(exchange);
        list = getFormListCurrencyPairLocal(tmpexchange);
        ComboboxPairList = list;
        return list;
    }


    public static Map<CurrencyPair, CurrencyPairMetaData> getFormListCurrencyPairMap(String exchange) {
        Exchange tmpexchange = ExchangeFromString(exchange);
        return tmpexchange.getExchangeMetaData().getCurrencyPairs();


    }

    private static Exchange ExchangeFromString(String exchangestring) {

        if ("Binance" == exchangestring) {

            return BinanceClFactory.createExchange();
        }
        if ("Bitfinex" == exchangestring) {

            return BitfinexClFactory.createExchange();
        }

        if ("Bittrex" == exchangestring) {

            return BittrexClFactory.createExchange();
        }
        if ("Bitmex" == exchangestring) {

            return BitmexClFactory.createExchange();
        }
        if ("Bitstamp" == exchangestring) {

            return BitstampClFactory.createExchange();
        }
        if ("CexIO" == exchangestring) {

            return CexioClFactory.createExchange();
        }
        if ("HitBTC" == exchangestring) {

            return HitbtcClFactory.createExchange();
        }
        if ("Huobi" == exchangestring) {

            return HuobiClFactory.createExchange();
        }
        if ("Kraken" == exchangestring) {

            return KrakenClFactory.createExchange();
        }
        if ("Poloniex" == exchangestring) {

            return PoloniexClFactory.createExchange();
        }
        if ("Yobit" == exchangestring) {

            return YobitClFactory.createExchange();
        }

        if ("CoinBene" == exchangestring) {

            return CoinBeneClFactory.createExchange();
        }

        if ("BiBox" == exchangestring) {

            return BiBoxClFactory.createExchange();
        }

        if ("CoinBase" == exchangestring) {

            return CoinBaseClFactory.createExchange();
        }

        if ("BitBay" == exchangestring) {

            return BitBayClFactory.createExchange();
        }

        if ("CoinEgg" == exchangestring) {

            return CoinEggClFactory.createExchange();
        }

        return null;
    }


    private static ObservableList<CurrencyPair> getFormListCurrencyPairLocal(Exchange exchange) {
        ObservableList<CurrencyPair> list = FXCollections.observableArrayList();
        for (CurrencyPair cp : exchange.getExchangeMetaData().getCurrencyPairs().keySet()) {
            list.add(cp);
        }
        return list;
    }

    public static void UpdateExchangePairTable(CurrencyPair cp, String exchange) {
        TableViewlist.add(new ExchangePairPrices(ExchangeFromString(exchange), cp, exchange));
    }

    public static void RemovefromExchangePairTable(ExchangePairPrices row) {
        TableViewlist.remove(row);
    }


}

