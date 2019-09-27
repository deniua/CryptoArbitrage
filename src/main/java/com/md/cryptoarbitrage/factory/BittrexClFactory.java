package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bittrex.BittrexExchange;

public class BittrexClFactory {
    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());

        return exchange;
    }
}
