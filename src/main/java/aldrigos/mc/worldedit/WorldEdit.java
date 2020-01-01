package aldrigos.mc.worldedit;

import cn.nukkit.item.Item;

public interface WorldEdit {
    Cuboid getSelection(long playerId);
    void setSelection(long playerId, Cuboid selection);
    boolean isWand(Item i);
    void set(int level, Cuboid c, int blockId);
}
