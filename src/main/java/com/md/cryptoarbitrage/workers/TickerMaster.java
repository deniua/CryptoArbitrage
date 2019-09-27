package com.md.cryptoarbitrage.workers;


import com.md.cryptoarbitrage.globals.GlobalCore;
import com.md.cryptoarbitrage.globals.GlobalStageModel;

public class TickerMaster implements Runnable {
    int timeout;
    private Thread thread;
    private boolean suspendFlag;


    public TickerMaster(int timeout) {
        this.timeout = timeout;
        this.suspendFlag = false;
        thread = new Thread(this, "TickerMaster");
        thread.start();
    }




    public synchronized void setSuspendFlagTrue() {
        suspendFlag = true;
    }

    public synchronized void resumeFromSuspend() {
        suspendFlag = false;
        notify();
    }


    public void run() {
        Thread current = Thread.currentThread();


        while (GlobalCore.isApplicationStart()) {
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

            for (int i = 0; i < GlobalStageModel.TableViewlist.size(); i++) {
                new TickerWorker(GlobalStageModel.TableViewlist.get(i).getExchange().toString(), GlobalStageModel.TableViewlist.get(i));
            }


            try {
                current.sleep(this.timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
