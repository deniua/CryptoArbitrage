package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.poloniex.PoloniexExchange;

public class PoloniexClFactory {
    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());

        return exchange;
    }

}
