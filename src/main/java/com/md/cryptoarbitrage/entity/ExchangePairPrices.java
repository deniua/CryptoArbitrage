package com.md.cryptoarbitrage.entity;

import com.md.cryptoarbitrage.globals.observable.BigDecimalObservable;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;

public class ExchangePairPrices {
    private Exchange exchange;
    private CurrencyPair pair;
    private BigDecimal ask ;
    private BigDecimal bid ;
    private ImageView logo;
    private BigDecimal percent ;
    private BigDecimal min ;
    private BigDecimal max ;
    private boolean isminimal;
    private boolean ismaximal;

    public ImageView getLogo() {
        return logo;
    }

    public boolean isIsminimal() {
        return isminimal;
    }

    public void setIsminimal(boolean isminimal) {
        this.isminimal = isminimal;
    }

    public boolean isIsmaximal() {
        return ismaximal;
    }

    public void setIsmaximal(boolean ismaximal) {
        this.ismaximal = ismaximal;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    private StringProperty flagofupdates;

    public synchronized String getFlagofupdates() {
        return flagofupdates.get();
    }

    public synchronized StringProperty flagofupdatesProperty() {
        if (flagofupdates == null) {
            flagofupdates = new SimpleStringProperty();
        }
        return flagofupdates;
    }

    public synchronized void setFlagofupdates (String flagofupdates) {

        this.flagofupdates.set(flagofupdates);
    }

    public static Callback<ExchangePairPrices, Observable[]> extractor() {
        return (ExchangePairPrices epp) -> {
            return new Observable[]{epp.flagofupdatesProperty()};
        };
    }


//constructors
    public ExchangePairPrices() {

    }

    public ExchangePairPrices(Exchange exchange, CurrencyPair pair,String name) {
        this();
        this.exchange = exchange;
        this.pair = pair;
        ask = BigDecimal.valueOf(0);
        bid = BigDecimal.valueOf(0);
        percent = BigDecimal.valueOf(0);
        this.logo = (new ImageView(new Image("/images/"+name+".png")));

    }


//****************************************

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public CurrencyPair getPair() {
        return pair;
    }

    public void setPair(CurrencyPair pair) {
        this.pair = pair;
    }


    public BigDecimal getAsk() {
        return ask;
    }

    public synchronized void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public synchronized void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "ExchangePairPrices{" +
                "exchange=" + exchange +
                ", pair=" + pair +
                ", ask=" + ask +
                ", bid=" + bid +
                '}';
    }
}
