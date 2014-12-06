package com.woutwoot.rainbowbeacon;

import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.BlockFace;

/**
 * Created by Wout on 29/11/2014.
 */
public class RainbowTask implements Runnable {

    @Override
    public void run() {
        for (RainbowBlock b : RainbowBeacon.getInstance().getRainbowBlocks()) {
            b.nextColor();
            if (b.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
                Beacon beacon = (Beacon) b.getLocation().getBlock().getRelative(BlockFace.DOWN).getState();
                if (beacon != null) {
                    beacon.update();
                }
            }
        }
    }
}
