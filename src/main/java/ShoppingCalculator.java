import rx.Observable;

import java.util.List;

/**
 * Calculates the Price of a basket of items.
 * A functional reactive solution using Java 8 and lambdas.
 * I thought I'd give a reactive solution a go, since it felt like a natural fit.
 * Also a reactive solution should prove more amenable should the need arise
 * to deal with a large number of baskets, and/or calculate a running total as we shop.
 */
public class ShoppingCalculator {
    public static final int APPLE_PRICE_EACH = 35;
    public static final int BANANA_PRICE_EACH = 20;
    public static final int MELON_PRICE_EACH = 50;
    public static final int LIME_PRICE_EACH = 15;

    Observable<String> basketObservable;

    public ShoppingCalculator(List<String> items) {
        basketObservable = Observable.from(items);
    }

    public Observable<Integer> totalPrice() {
        Observable<Integer> itemPriceObservable =
            Observable.merge(applePriceEach(), bananaPriceEach(), melonPriceOffer(), limePriceOffer()).startWith(0);
        return itemPriceObservable.scan(this::sum);
    }

    private Observable<Integer> applePriceEach() {
        return basketObservable.filter("Apple"::equals).map((item) -> APPLE_PRICE_EACH);
    }

    private Observable<Integer> bananaPriceEach() {
        return basketObservable.filter("Banana"::equals).map((item) -> BANANA_PRICE_EACH);
    }

    private Observable<Integer> melonPriceOffer() {
        return basketObservable.filter("Melon"::equals).buffer(2).map((eachOffer) -> MELON_PRICE_EACH);
    }

    private Observable<Integer> limePriceOffer() {
        return basketObservable.filter("Lime"::equals).buffer(3)
                               .map((eachOffer) -> eachOffer.size() == 3 ? LIME_PRICE_EACH * 2 : LIME_PRICE_EACH * eachOffer.size() );
    }

    private Integer sum(Integer acc, Integer next) {
        return acc + next;
    }
}
