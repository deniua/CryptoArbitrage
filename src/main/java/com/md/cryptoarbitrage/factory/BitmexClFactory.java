package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitmex.BitmexExchange;

public class BitmexClFactory {


    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitmexExchange.class.getName());

        return exchange;
    }
}