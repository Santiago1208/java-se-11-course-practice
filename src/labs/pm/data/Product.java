/*
 * Copyright (C) 2021 Santiago
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import static labs.pm.data.Rating.NOT_RATED;

/**
 * {@code Product} class represents properties and behaviors of product objects
 * in the Product Management System.
 * <br>
 * Each product has an ID, name, and price
 * <br>
 * Each product can have a different discount, calculated based on a 
 * {@link DISCOUNT_RATE discount rate}
 * @version 4.0
 * @author Santiago
 */
public class Product {
    
    /**
     * A constant that defines a {@link java.math.BigDecimal BigDecimal} value
     * of the discount rate.
     * <br>
     * Discount rate is 10%
     */
    public final static BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Product() {
        this(0, "no name", BigDecimal.ZERO);
    }
    
    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, NOT_RATED);
    }
    
    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    
    /**
     * Calculates discount based on a product price and 
     * {@link DISCOUNT_RATE discount rate}
     * 
     * @return a {@link java.math.BigDecimal BigDecimal} value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }
    
    public Product applyRating(Rating newRating) {
        return new Product(id, name, price, newRating);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price + " " + getDiscount() + " " + rating.getStars();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            final Product other = (Product) obj;
            return id == other.id && Objects.equals(name, other.name);
        }
        return false;
    }

}
