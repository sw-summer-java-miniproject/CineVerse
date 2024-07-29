package com.ohgiraffers.MovieApp.aggregate;

import java.io.Serializable;

public class Discount implements Serializable {
    private int discountId;
    private double discountPercentage;
    private int birthMonth;
    private int birthDay;

    public Discount() {
    }

    public Discount(int discountId, double discountPercentage, int birthMonth, int birthDay) {
        this.discountId = discountId;
        this.discountPercentage = discountPercentage;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", discountPercentage=" + discountPercentage +
                ", birthMonth=" + birthMonth +
                ", birthDay=" + birthDay +
                '}';
    }
}
