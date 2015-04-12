import rx.Observable;

import java.util.List;


public class GenericCalculator {
    private Observable<BasketItem> basketObservable;

    private Observable<Integer> totalPriceObservable;

    public GenericCalculator(List<BasketItem> items) {
        basketObservable = Observable.from(items);
        totalPriceObservable = Observable.merge(each(), offer_b1g1f(), offer_b3fp2()).scan(0, this::sum);
    }

    private Observable<Integer> offer_b3fp2() {
        return basketObservable.filter((item) -> item.onOffer(OfferEnum.BUY_THREE_FOR_THE_PRICE_OF_TWO)).buffer(3)
                               .map((eachOffer) -> {
                                   Integer each = eachOffer.get(0).getUnitPrice();
                                   int count = eachOffer.size();
                                   return count == 3 ? each * 2 : count * each;
                               });
    }

    private Observable<Integer> offer_b1g1f() {
        return basketObservable.filter((item) -> item.onOffer(OfferEnum.BUY_ONE_GET_ONE_FREE)).buffer(2)
                               .map((eachOffer) -> eachOffer.get(0).getUnitPrice());
    }

    private Observable<Integer> each() {
        return basketObservable.filter((item) -> item.onOffer(OfferEnum.NONE)).map(BasketItem::getUnitPrice);
    }

    public Observable<Integer> totalPrice() {
        return totalPriceObservable;
    }

    private Integer sum(Integer acc, Integer next) {
        return acc + next;
    }
}
