package com.md.cryptoarbitrage.factory;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.cexio.CexIOExchange;

public class CexioClFactory {

    public static Exchange createExchange() {

        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CexIOExchange.class.getName());

        return exchange;
    }

}
