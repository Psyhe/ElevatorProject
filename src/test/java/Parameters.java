import java.util.ArrayList;
import java.util.List;

public class Parameters {
    public static int[] statusTestParameters1() {
        int tab[] = new int[30];

        for (int i = 0; i < 10; i++) {
            tab[i*3] = i;
            tab[i*3+1] = 0;
            tab[i*3+2] = 0;
        }
        tab[1] = 9;
        tab[2] = 9;
        tab[4] = 4;
        tab[5] = 5;
        tab[7] = 1;
        tab[8] = 1;

        return tab;
    }

    public static int[] statusTestParameters2() {
        int tab[] = new int[6];

        for (int i = 0; i < 2; i++) {
            tab[i*3] = i;
            tab[i*3+1] = 0;
            tab[i*3+2] = 0;
        }

        return tab;
    }

    public static List<List<Integer>> customList(int tab[]) {
        List<List<Integer>> set = new ArrayList<>();

        for (int i = 0; i < tab.length; i+=3) {
            List<Integer> list = new ArrayList<>();
            list.add(tab[i]);
            list.add(tab[i+1]);
            list.add(tab[i+2]);
            set.add(list);
        }
        
        return set;
    }
}
