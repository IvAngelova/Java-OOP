package CounterStriker.models.guns;

public class Pistol extends GunImpl {
    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        try {
            this.setBulletsCount(this.getBulletsCount() - 1);
            return 1;
        } catch (IllegalArgumentException ex) {
            this.setBulletsCount(0);
            return 0;
        }
    }
}
