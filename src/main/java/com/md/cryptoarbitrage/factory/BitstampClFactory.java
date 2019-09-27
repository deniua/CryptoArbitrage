package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;

public class BitstampClFactory {
    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());

        return exchange;
    }
}
