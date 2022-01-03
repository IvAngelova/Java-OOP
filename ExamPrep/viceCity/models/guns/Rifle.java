package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int BULLETS_PER_BARREL = 50;
    private static final int CAPACITY_BARREL = 50;
    private static final int SHOT_BULLETS_WHEN_PULL_THE_TRIGGER = 5;
    private static final int TOTAL_BULLETS = 500;

    public Rifle(String name) {
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
