package com.md.cryptoarbitrage.workers;


import com.md.cryptoarbitrage.entity.ExchangePairPrices;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Date;

public class TickerWorker implements Runnable {

    private String name;
    private Thread thread;

    private ExchangePairPrices linktoTableRowEPP;

    private boolean suspendFlag;


    public TickerWorker(String name, ExchangePairPrices epp) {

        this.name = name;

        this.suspendFlag = false;

        this.linktoTableRowEPP = epp;

        System.out.println("Thread init... " + this.name);
        thread = new Thread(this, this.name);
        thread.setPriority(9);
        thread.start();

    }

    public Thread getThread() {
        return thread;
    }

    public void run() {


        //suspend block
        synchronized (this) {
            while (suspendFlag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //----------
        MarketDataService marketDataService = linktoTableRowEPP.getExchange().getMarketDataService();
        try {

            Ticker nr = marketDataService.getTicker(linktoTableRowEPP.getPair());
            linktoTableRowEPP.setAsk(nr.getAsk());
            linktoTableRowEPP.setBid(nr.getBid());
            linktoTableRowEPP.setTimestamp(new Date().getTime());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setSuspendFlagTrue() {
        suspendFlag = true;
    }

    public void resumeFromSuspend() {
        suspendFlag = false;
        notify();
    }


}
