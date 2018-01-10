package ParallelArrays;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class ParallelArrays {

    public static void main(String... args) {
        long[] arrayOfLong = new long[2000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));


        LocalTime time = LocalTime.now();
        System.out.println("start: " + time.toString());

        Arrays.stream(arrayOfLong).limit(1000).forEach(
                i -> System.out.print(i + " ")
        );

        LocalTime time2 = LocalTime.now();

        System.out.println();

        System.out.println("first finish: " + time2.toString());

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(1000).forEach(
                i -> System.out.print(i + " ")
        );

        LocalTime time3 = LocalTime.now();

        System.out.println();

        System.out.println("second finish: " + time3.toString());


    }

}
