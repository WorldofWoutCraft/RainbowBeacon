package com.woutwoot.rainbowbeacon;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Created by Wout on 29/11/2014.
 */
public class RainbowBlock {

    private Location location;
    private int data = 1;
    private int maxData = 15;

    public RainbowBlock(Location l) {
        this.setLocation(l);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void nextColor() {
        if (data >= 15) {
            data = 1;
            return;
        }
        data++;
        location.getBlock().setType(Material.STAINED_GLASS);
        location.getBlock().setData((byte) data);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Location) {
            if (o.equals(this.getLocation())) {
                return true;
            }
        } else if (o instanceof RainbowBlock) {
            if (((RainbowBlock) o).getLocation().equals(this.getLocation())) {
                return true;
            }
        }
        return false;
    }

}
