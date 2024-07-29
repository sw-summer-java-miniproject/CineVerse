package com.ohgiraffers.MovieApp.Repository;

import com.ohgiraffers.MovieApp.aggregate.Discount;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DiscountRepository {
    private Map<Integer, Discount> discounts = new HashMap<>();
    private AtomicInteger currentId = new AtomicInteger(0);

    public void addDiscount(Discount discount) {
        int id = currentId.incrementAndGet();
        discount.setDiscountId(id);
        discounts.put(id, discount);
    }

    public void updateDiscount(Discount discount) {
        discounts.put(discount.getDiscountId(), discount);
    }

    public void deleteDiscount(int discountId) {
        discounts.remove(discountId);
    }

    public Discount findDiscountById(int discountId) {
        return discounts.get(discountId);
    }

    public Map<Integer, Discount> getAllDiscounts() {
        return discounts;
    }
}
