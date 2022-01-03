package halfLife;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTests {
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsNull() {
        Player player = new Player(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailWhenNameIsEmpty() {
        Player player = new Player("     ", 10);
    }

    @Test
    public void testSetNameShouldSetTheCorrectName() {
        Player player = new Player("Java", 10);
        assertEquals("Java", player.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthShouldThrowWhenHealthIsNegative() {
        Player player = new Player("Java", -1);
    }

    @Test
    public void testSetHealthShouldSetTheHealthCorrectly() {
        Player player = new Player("Java", 10);
        assertEquals(10, player.getHealth());
    }

    @Test
    public void testConstructorShouldCreateTheCorrectObject() {
        Player player = new Player("Java", 10);
        assertEquals("Java", player.getUsername());
        assertEquals(10, player.getHealth());
        assertEquals(0, player.getGuns().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageShouldThrowWhenPlayerIsDead() {
        Player player = new Player("Java", 0);
        player.takeDamage(24);
    }

    @Test
    public void testTakeDamageShouldSetTheHealthCorrectly() {
        Player player = new Player("Java", 15);
        player.takeDamage(24);
        assertEquals(0, player.getHealth());
        Player player1 = new Player("Java", 25);
        player1.takeDamage(15);
        assertEquals(10, player1.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunShouldFailWhenGunIsNull() {
        Player player = new Player("Java", 15);
        player.addGun(null);
    }

    @Test
    public void testAddGunShouldAddTheGunCorrectly() {
        Player player = new Player("Java", 15);
        Gun gun = new Gun("gun", 45);
        player.addGun(gun);
        assertEquals(1, player.getGuns().size());
        assertEquals(gun, player.getGun("gun"));
    }

    @Test
    public void testRemoveGunShouldRemoveTheGun() {
        Player player = new Player("Java", 15);
        Gun gun = new Gun("gun", 45);
        player.addGun(gun);
        assertTrue(player.removeGun(gun));
        assertFalse(player.removeGun(gun));
        assertEquals(0, player.getGuns().size());
    }

    @Test
    public void testGetGunShouldReturnNullWhenGunIsNotInTheList() {
        Player player = new Player("Java", 15);
        Gun gun = new Gun("gun", 45);
        assertNull(player.getGun("gun1"));
    }


    @Test
    public void testGetGunShouldReturnTheCorrectGun() {
        Player player = new Player("Java", 15);
        Gun gun = new Gun("gun", 45);
        player.addGun(gun);
        assertEquals(gun, player.getGun("gun"));
    }

}
