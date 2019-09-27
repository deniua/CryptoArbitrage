package com.md.cryptoarbitrage.globals;


import com.md.cryptoarbitrage.entity.ExchangePairPrices;
import com.md.cryptoarbitrage.workers.TickerMaster;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class GlobalCore {
    private static boolean applicationStarted = false;

    public static boolean isApplicationStart() {
        return applicationStarted;
    }

    public static void setApplicationStart(boolean applicationStarted) {
        GlobalCore.applicationStarted = applicationStarted;
    }

    public static void logicStart() {

        setApplicationStart(true);
        TickerMaster tm = new TickerMaster(2000);
        // setTimerRefresh();
        SetJFXTask();

    }

    private synchronized static void CalculatePercentSystem() {
        //Platform.runLater(() ->
        // {
        BigDecimal min = BigDecimal.valueOf(2000000000);
        BigDecimal max = BigDecimal.valueOf(0);
        ExchangePairPrices minimal = null;
        ExchangePairPrices maximal = null;
        double maxpercent = 0.0d;

        for (ExchangePairPrices epp : GlobalStageModel.TableViewlist) {
            if (epp.getBid().compareTo(max) == 1) {
                max = epp.getBid();
                maximal = epp;
            }
            if (epp.getAsk().compareTo(min) == -1) {
                min = epp.getAsk();
                minimal = epp;
            }
            epp.setIsmaximal(false);
            epp.setIsminimal(false);
        }

        if (minimal != null) minimal.setIsminimal(true);
        if (maximal != null) maximal.setIsmaximal(true);


        for (ExchangePairPrices epp : GlobalStageModel.TableViewlist) {
            epp.setMin(min);
            epp.setMax(max);
            try {
                double dmin = min.doubleValue();
                double dbid = epp.getBid().doubleValue();
                double dpercent = ((dbid / dmin) - 1) * 100;
                if (dpercent > maxpercent) maxpercent = dpercent;
                epp.setPercent(BigDecimal.valueOf(dpercent).setScale(3, RoundingMode.CEILING));
            } catch (Exception e) {
                e.getMessage();
            }


        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String localtime = LocalDateTime.now().format(formatter).toString();
        if (maxpercent > GlobalStageModel.profitlimit) {
            GlobalStageModel.globalseria.add(new XYChart.Data<String, Double>(localtime, maxpercent));
        }
        // GlobalStageModel.globalGraphList.add(new GraphTimelineElement(LocalDateTime.now().format(formatter).toString(),maxpercent));

        // });
    }


    private static void SetJFXTask() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {

                while (true) {
                    if (isCancelled()) {
                        break;
                    }
                    if (GlobalCore.isApplicationStart()) {
                        CalculatePercentSystem();
                    }
                    sleep(1500);
                }
                return null;
            }
        };


        Thread jfxtimer = new Thread(task);
        jfxtimer.setName("JfxTimer");
        jfxtimer.start();

    }


}
