package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.hitbtc.v2.HitbtcExchange;

public class HitbtcClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HitbtcExchange.class.getName());

        return exchange;
    }
}
