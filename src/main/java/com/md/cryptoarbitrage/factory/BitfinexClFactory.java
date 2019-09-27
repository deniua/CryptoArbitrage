package com.md.cryptoarbitrage.factory;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitfinex.BitfinexExchange;


public class BitfinexClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());

        return exchange;
    }
}

