package net.zomis.simon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zomis on 2014-12-16.
 */
public class AppendingGenerator implements SequenceGenerator {
    private final Random random = new Random();
    private int count;
    private List<Integer> recent;

    public AppendingGenerator(int count) {
        this.count = count;
        this.recent = new ArrayList<Integer>();
    }

    @Override
    public void generate(int numButtons, List<Integer> result) {
        while (recent.size() < count) {
            recent.add(random.nextInt(numButtons));
        }
        result.addAll(recent);
        this.count++;
    }
}
