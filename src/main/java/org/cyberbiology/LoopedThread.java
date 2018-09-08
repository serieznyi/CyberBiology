package org.cyberbiology;

public class LoopedThread extends Thread {
    private boolean worked = false;

    LoopedThread(Runnable runnable) {
        super(runnable);
    }

    public void run() {
        this.worked = true;

        while (worked) {
            super.run();
        }
    }

    @Override
    public void interrupt() {
        this.worked = false;

        super.interrupt();
    }
}
