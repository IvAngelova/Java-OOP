package heroRepository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;

    @Before
    public void SetUp() {
        this.heroRepository = new HeroRepository();
    }

    @Test
    public void testConstructorShouldCreateCorrectObject() {
        assertEquals(0, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHeroShouldThrowWhenHeroIsNull() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroShouldThrowWhenHeroIsAlreadyAdded() {
        Hero hero = new Hero("test_name", 1);
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void testCreateHeroShouldAddTheCorrectHero() {
        Hero hero = new Hero("test_name", 1);
        String expected = "Successfully added hero test_name with level 1";
        assertEquals(expected, heroRepository.create(hero));
        assertEquals(1, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveShouldThrowWhenNameIsNull(){
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveShouldThrowWhenNameIsEmpty(){
        heroRepository.remove("    ");
    }

    @Test
    public void testRemoveShouldReturnTheCorrectBoolean(){
        Hero hero = new Hero("test_name", 1);
        Hero hero1 = new Hero("test_name1", 2);
        heroRepository.create(hero);
        heroRepository.create(hero1);

        assertTrue(heroRepository.remove(hero.getName()));
        assertFalse(heroRepository.remove("test_name2"));
    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnTheCorrectHero(){
        Hero hero = new Hero("test_name", 1);
        Hero hero1 = new Hero("test_name1", 2);
        heroRepository.create(hero);
        heroRepository.create(hero1);
        assertEquals(hero1, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnNullIfRepoIsEmpty(){
        assertNull( heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHeroShouldReturnTheHeroWithGivenName(){
        Hero hero = new Hero("test_name", 1);
        Hero hero1 = new Hero("test_name1", 2);
        heroRepository.create(hero);
        heroRepository.create(hero1);
        assertEquals(hero, heroRepository.getHero("test_name"));
    }

    @Test
    public void testGetHeroShouldReturnNullWhenSuchHeroDoesNotExist(){
        Hero hero = new Hero("test_name", 1);
        Hero hero1 = new Hero("test_name1", 2);
        heroRepository.create(hero);
        heroRepository.create(hero1);
        assertNull(heroRepository.getHero("test"));
    }

    @Test
    public void testGetHeroesShouldReturnTheCorrectCollection(){
        Hero hero = new Hero("test_name", 1);
        Hero hero1 = new Hero("test_name1", 2);
        List<Hero> heroList = List.of(hero, hero1);
        heroRepository.create(hero);
        heroRepository.create(hero1);
        int index = 0;
        for (Hero h : heroRepository.getHeroes()) {
            assertEquals(heroList.get(index++), h);
        }
        assertEquals(2, heroRepository.getHeroes().size());
    }


}
