package com.petshop.catalog.domain.product;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class ProductPrice {

    private static final int SCALE = 2;

    private BigDecimal value;

    private ProductPrice(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        if (value.scale() > SCALE) {
            throw new IllegalArgumentException("Product price must have at most 2 decimal places");
        }

        this.value = value.setScale(SCALE, RoundingMode.UNNECESSARY);
    }

    public static ProductPrice from(BigDecimal value) {
        return new ProductPrice((value));
    }

    public static ProductPrice from(String value) {
        return new ProductPrice(new BigDecimal(value));
    }

    public BigDecimal value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPrice)) return false;
        ProductPrice that = (ProductPrice) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
