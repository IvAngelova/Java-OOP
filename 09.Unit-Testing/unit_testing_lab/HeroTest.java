package unit_testing_lab;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HeroTest {
    @Test
    public void testHeroGainsExperienceWhenTargetDies() {
       /* Weapon weapon = new Weapon() {
            @Override
            public int getAttackPoints() {
                return 0;
            }

            @Override
            public int getDurabilityPoints() {
                return 0;
            }

            @Override
            public void attack(Target target) {

            }
        }; */

        Weapon weapon = Mockito.mock(Weapon.class);

        Hero hero = new Hero("Hero", weapon);

       /* Target target = new Target() {
            @Override
            public int getHealth() {
                return 0;
            }

            @Override
            public void takeAttack(int attackPoints) {

            }

            @Override
            public int giveExperience() {
                return 100;
            }

            @Override
            public boolean isDead() {
                return true;
            }
        };  */

        Target target = mock(Target.class);

        when(target.isDead()).thenReturn(true);
        when(target.giveExperience()).thenReturn(100);

        hero.attack(target);
        assertEquals(100, hero.getExperience());
    }

    @Test
    public void testWhenHeroKillsTargetInventoryShouldAddNewLoot() {

        Weapon weapon = Mockito.mock(Weapon.class);

        Hero hero = new Hero("Hero", weapon);
        Target target = mock(Target.class);

        when(target.isDead()).thenReturn(true);

        //target.getLoot() по default връща axe = null
        when(target.getLoot()).thenReturn(new Axe(17, 69));

        hero.attack(target);
        List<Weapon> inventory = hero.getInventory();

        assertEquals(1, inventory.size());
        Weapon loot = inventory.get(0); // и тук ще имаме NullPointerException ако не сетнем стойност
        assertEquals(17, loot.getAttackPoints());
        assertEquals(69, loot.getDurabilityPoints());
    }

}