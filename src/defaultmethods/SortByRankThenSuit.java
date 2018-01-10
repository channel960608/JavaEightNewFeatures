package defaultmethods;

import java.util.*;

public class SortByRankThenSuit implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        int compVal =
                o1.getRank().value() - o2.getRank().value();
        if(compVal != 0)
            return compVal;
        else
            return o1.getSuit().value() - o2.getSuit().value();
    }
}
