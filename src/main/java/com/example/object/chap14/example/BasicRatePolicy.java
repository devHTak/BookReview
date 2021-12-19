package com.example.object.chap14.example;

import com.example.object.chap02.entity.Money;

public class BasicRatePolicy implements RatePolicy {

    @Override
    public Money calculateFee(Phone phone) {
        return phone.calculateFee().plus(this.calculateCallFee(phone.getCall()));
    }

    protected Money calculateCallFee(Call call) {
        return Money.ZERO;
    }
}
