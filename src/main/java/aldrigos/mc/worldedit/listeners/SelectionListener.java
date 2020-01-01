package aldrigos.mc.worldedit.listeners;

import aldrigos.mc.worldedit.*;
import aldrigos.mc.worldedit.WorldEdit;
import cn.nukkit.Player;
import cn.nukkit.event.*;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;

import java.util.Map;

public class SelectionListener implements Listener {
    private final Map<Long, Cuboid> selection;
    private final WorldEdit api;

    public SelectionListener(WorldEditPlugin p){
        selection = p.getSelection();
        api = p.getApi();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!api.isWand(e.getItem()))
            return;

        setFirstPos(e.getPlayer(), e.getBlock().getLocation());
        e.setCancelled();
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(!api.isWand(e.getItem()))
            return;

        var player = e.getPlayer();
        var pos = e.getBlock().getLocation();

        switch (e.getAction()){
            case RIGHT_CLICK_BLOCK:
                if(!selection.containsKey(player.getId()))
                    selection.put(player.getId(), new Cuboid());

                selection.get(player.getId()).P2 = pos;
                Messages.SET_P2.send(player, print(pos));
                break;
            case LEFT_CLICK_BLOCK:
                setFirstPos(player, pos);
                break;
        }
    }

    private void setFirstPos(Player player, Location pos) {
        if (!selection.containsKey(player.getId()))
            selection.put(player.getId(), new Cuboid());

        selection.get(player.getId()).P1 = pos;
        Messages.SET_P1.send(player, print(pos));
    }

    private String print(Vector3 pos){
        return String.format("X: %d Y: %d Z: %d", (int)pos.x, (int)pos.y, (int)pos.z);
    }
}
