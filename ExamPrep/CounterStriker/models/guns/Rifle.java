package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        try {
            this.setBulletsCount(this.getBulletsCount() - 10);
            return 10;
        } catch (IllegalArgumentException ex) {
            this.setBulletsCount(0);
            return 0;
        }
    }
}
