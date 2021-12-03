package gameLogic.logicManager;

import gameLogic.Bullet;
import java.util.List;

public class BulletManager extends LogicManager{
    public List<Bullet> bulletList;

    public void addBullet(Bullet bullet){
        bulletList.add(bullet);
    }

    public void removeBullet(Bullet bullet)
    {
        bulletList.remove(bullet);
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

}
