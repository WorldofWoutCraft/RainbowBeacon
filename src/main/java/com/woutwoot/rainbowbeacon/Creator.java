package com.woutwoot.rainbowbeacon;

import org.bukkit.entity.Player;

/**
 * Created by Wout on 29/11/2014.
 */
public class Creator {

    private Player player;

    public Creator(Player player) {
        this.setPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            if (((Player) o).getUniqueId().equals(this.getPlayer().getUniqueId())) {
                return true;
            }
        } else if (o instanceof Creator) {
            if (((Creator) o).getPlayer().getUniqueId().equals(getPlayer().getUniqueId())) {
                return true;
            }
        }
        return false;
    }

}
