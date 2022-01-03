package T03_Inheritance.lab.random_array_list;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList<Object> {
    public Object getRandomElement(){
        int index = new Random().nextInt(super.size());
        Object obj = super.get(index);
        super.remove(index);
        return obj;
    }

}
