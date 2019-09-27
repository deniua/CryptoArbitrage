package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;

public class BinanceClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());

        return exchange;
    }
}