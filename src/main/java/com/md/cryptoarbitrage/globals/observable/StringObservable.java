package com.md.cryptoarbitrage.globals.observable;


import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class StringObservable implements ObservableValue<String > {
    private String newValue;

    public ChangeListener<String> listener = new ChangeListener<String>() {

        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            System.out.println("Changed......");
        }
    };


    public void addListener(ChangeListener<? super String> listener) {

    }


    public void removeListener(ChangeListener<? super String> listener) {

    }


    public String getValue() {
        return newValue;
    }


    public void addListener(InvalidationListener listener) {

    }


    public void removeListener(InvalidationListener listener) {

    }

    public void setNewValue(String newValue) {
        String oldValue = this.newValue;
        this.newValue = newValue;
        listener.changed(this,oldValue,this.newValue);

    }
}