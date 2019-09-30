package com.md.cryptoarbitrage.factory;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.coinbene.CoinbeneExchange;

public class CoinBeneClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CoinbeneExchange.class.getName());

        return exchange;
    }

}