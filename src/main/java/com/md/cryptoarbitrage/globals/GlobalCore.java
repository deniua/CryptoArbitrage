package com.md.cryptoarbitrage.globals;


import com.md.cryptoarbitrage.entity.ExchangePairPrices;
import com.md.cryptoarbitrage.workers.TickerMaster;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import static java.lang.Thread.sleep;

public class GlobalCore {
    public static Stage globalstage;
    private static boolean applicationStarted = false;
    private static boolean quitall = false;

    public static boolean isQuitall() {
        return quitall;
    }

    public static void setQuitall(boolean quitall) {
        GlobalCore.quitall = quitall;
    }

    public static boolean isApplicationStart() {
        return applicationStarted;
    }

    public static void setApplicationStart(boolean applicationStarted) {
        GlobalCore.applicationStarted = applicationStarted;
    }

    public static void logicStart() {

        setApplicationStart(true);
        TickerMaster tm = new TickerMaster(1500);
        SetJFXTask();

    }

    private synchronized static void CalculatePercentSystem() {
        Platform.runLater(GlobalCore::run);
    }


    private static void SetJFXTask() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {

                while (true) {
                    if (GlobalCore.isQuitall()) {
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


    private static void run() {
        BigDecimal min = BigDecimal.valueOf(0);
        BigDecimal max = BigDecimal.valueOf(0);

        double maxpercent = 0.0d;

        long datats = new Date().getTime();
        long finalDatats = datats;
        Optional<ExchangePairPrices> minimalstream = GlobalStageModel.TableViewlist
                .stream()
                .filter(x -> ((finalDatats - x.getTimestamp()) < 2500))
                .min(Comparator.comparing(ExchangePairPrices::getAsk));

        min = minimalstream.get().getAsk();
        minimalstream.get().setMin(min);

        Optional<ExchangePairPrices> maximalstream = GlobalStageModel.TableViewlist
                .stream()
                .filter(x -> ((finalDatats - x.getTimestamp()) < 2500))
                .min(Comparator.comparing(ExchangePairPrices::getBid));
        max = maximalstream.get().getBid();
        maximalstream.get().setMax(max);

        for (ExchangePairPrices epp : GlobalStageModel.TableViewlist) {
            datats = new Date().getTime();
            if ((datats - epp.getTimestamp()) > 2500) {
                epp.setMin(BigDecimal.valueOf(0));
                epp.setMax(BigDecimal.valueOf(0));
                epp.setPercent(BigDecimal.valueOf(0));
                continue;
            }


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
    }
}
