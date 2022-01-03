package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int BULLETS_PER_BARREL = 10;
    private static final int CAPACITY_BARREL = 10;
    private static final int SHOT_BULLETS_WHEN_PULL_THE_TRIGGER = 1;
    private static final int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() == 0) {
            this.setTotalBullets(this.getTotalBullets() - CAPACITY_BARREL);
            this.setBulletsPerBarrel(CAPACITY_BARREL);
        }

        this.setBulletsPerBarrel(this.getBulletsPerBarrel() - SHOT_BULLETS_WHEN_PULL_THE_TRIGGER);

        return SHOT_BULLETS_WHEN_PULL_THE_TRIGGER;
    }
}
