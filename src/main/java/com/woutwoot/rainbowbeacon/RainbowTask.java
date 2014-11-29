package com.woutwoot.rainbowbeacon;

/**
 * Created by Wout on 29/11/2014.
 */
public class RainbowTask implements Runnable {

    @Override
    public void run() {
        for (RainbowBlock b : RainbowBeacon.getInstance().getRainbowBlocks()) {
            b.nextColor();
        }
    }
}
