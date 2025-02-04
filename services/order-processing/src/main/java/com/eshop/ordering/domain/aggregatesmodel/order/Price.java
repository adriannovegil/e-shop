package com.eshop.ordering.domain.aggregatesmodel.order;

import com.eshop.ordering.domain.exceptions.OrderingDomainException;
import com.eshop.ordering.domain.base.ValueObject;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static java.util.Objects.isNull;

@Getter
@ToString
public class Price extends ValueObject {

    private final Double value;

    private Price(Double price) {
        if (isNull(price) || price < 0) {
            throw new OrderingDomainException("Price is not valid");
        }

        this.value = price;
    }

    public static Price of(Double price) {
        return new Price(price);
    }

    public static Price empty() {
        return new Price(0D);
    }

    public static Price sum(Price a, Price b) {
        return Price.of(a.getValue() + b.getValue());
    }

    public Price multiply(Units units) {
        return Price.of(value * units.getValue());
    }

    public boolean lessThan(Price price) {
        return value < price.getValue();
    }

    public boolean greaterThan(Price price) {
        return value > price.getValue();
    }

    @Override
    protected List<Object> getEqualityComponents() {
        return List.of(value);
    }
}
