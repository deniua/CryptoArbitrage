package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.kraken.KrakenExchange;

public class KrakenClFactory {
    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());

        return exchange;
    }
}
