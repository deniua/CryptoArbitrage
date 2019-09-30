
package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinbase.v2.CoinbaseExchange;


public class CoinBaseClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoinbaseExchange.class.getName());

        return exchange;
    }
}