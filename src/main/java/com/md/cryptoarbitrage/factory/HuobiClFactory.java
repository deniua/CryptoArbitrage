package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.huobi.HuobiExchange;

public class HuobiClFactory {
    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());

        return exchange;
    }
}
