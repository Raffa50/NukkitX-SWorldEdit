package aldrigos.mc.worldedit;

import aldrigos.mc.worldedit.commands.*;
import aldrigos.mc.worldedit.listeners.*;

import cn.nukkit.plugin.PluginBase;

import java.util.Map;

public class WorldEditPlugin extends PluginBase {
    WorldEdit api;

    public WorldEdit getApi(){
        return api;
    }

    public Map<Long, Cuboid> getSelection(){
        return ((WordEditApiImpl)api).selection;
    }

    @Override
    public void onLoad(){
        if(getServer() == null)
            System.out.println("NOPE!");
        api = new WordEditApiImpl(getServer());
    }

    @Override
    public void onEnable(){
        var cm = this.getServer().getCommandMap();
        cm.register("/wand", new WandCmd());
        cm.register("/set", new SetCmd(this));
        cm.register("/expand", new ExpandCmd(this));
        cm.register("/shift", new ShiftCmd(this));

        getServer().getPluginManager().registerEvents(new SelectionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerWorldChangeListener(this), this);

        getServer().getLogger().info("[WG]SimpleWorldEdit by Aldrigo R. ENABLED!");
    }

    @Override
    public void onDisable(){
        getServer().getLogger().info("[WG]disabled");
    }
}
