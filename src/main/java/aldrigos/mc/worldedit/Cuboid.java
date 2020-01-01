package aldrigos.mc.worldedit;

import cn.nukkit.math.Vector3;

public class Cuboid {
    public Vector3 P1, P2;

    public boolean contains(Vector3 pos){
        double x1, x2, y1, y2, z1, z2;
        x1 = Math.min(P1.x, P2.x);
        x2 = Math.max(P1.x, P2.x);

        if(pos.x < x1 || pos.x > x2)
            return false;

        y1 = Math.min(P1.y, P2.y);
        y2 = Math.max(P1.y, P2.y);

        if(pos.y < y1 || pos.y > y2)
            return false;

        z1 = Math.min(P1.z, P2.z);
        z2 = Math.max(P1.z, P2.z);

        if(pos.z < z1 || pos.z > z2)
            return false;

        return true;
    }

    public boolean isComplete() {
        return P1 != null && P2 != null;
    }
}
