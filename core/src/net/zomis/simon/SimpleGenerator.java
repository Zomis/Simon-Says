package net.zomis.simon;

import java.util.List;
import java.util.Random;

public class SimpleGenerator implements SequenceGenerator {
    private final Random random;
    private int count;

    public SimpleGenerator(int count) {
        this.count = count;
        this.random = new Random();
    }

    @Override
    public void generate(int numButtons, List<Integer> result) {
        for (int i = 0; i < count; i++) {
            result.add(random.nextInt(numButtons));
        }
        count++;
    }
}
