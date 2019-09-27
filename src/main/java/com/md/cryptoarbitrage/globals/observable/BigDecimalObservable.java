package com.md.cryptoarbitrage.globals.observable;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;


public class BigDecimalObservable implements ObservableValue<BigDecimal > {
    private BigDecimal newValue;

    public ChangeListener<BigDecimal> listener = new ChangeListener<BigDecimal>() {

        public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
            System.out.println("Changed......");

        }
    };


    public void addListener(ChangeListener<? super BigDecimal> listener) {

    }


    public void removeListener(ChangeListener<? super BigDecimal> listener) {

    }


    public BigDecimal getValue() {
        return newValue;
    }


    public void addListener(InvalidationListener listener) {

    }


    public void removeListener(InvalidationListener listener) {

    }

    public void setNewValue(BigDecimal newValue) {
        BigDecimal oldValue = this.newValue;
        this.newValue = newValue;
        listener.changed(this,oldValue,this.newValue);

    }
}