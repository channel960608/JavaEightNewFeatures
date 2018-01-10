package Lambda;

import java.util.Arrays;
import java.util.List;

public class Lambda {


    public static void main(String[] args){

        Arrays.asList("a", "b", "c", "d").forEach( (String e) -> {
            System.out.println(e);
        });

        Arrays.asList("a", "b", "c", "d").sort( (e1, e2) -> e1.compareTo(e2));


    }
}


