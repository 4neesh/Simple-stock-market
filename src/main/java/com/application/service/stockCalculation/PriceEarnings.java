package com.application.service.stockCalculation;

import com.application.entity.Stock;
import com.application.exceptions.InputValidator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Service to compute the price earnings of a Stock with a price
 * @author aneesh
 */
public class PriceEarnings {

    DividendYield dividendYieldService;

    /**
     * Price Earnings requires dividend yield service to calculate upon.
     * @param dividendYieldImpl dividend service used in calculation.
     */
    public PriceEarnings(DividendYield dividendYieldImpl){
        this.dividendYieldService = dividendYieldImpl;
    }

    /**
     * Return the price earnings of a Stock and price.
     * @param stock Stock to calculate price earnings upon.
     * @param price price for price earnings calculation.
     * @return Price earnings of stock and price.
     */
    public BigDecimal calculate(Stock stock, BigDecimal price){
        InputValidator.validateStockAndPrice(stock, price);
        MathContext mc = new MathContext(4, RoundingMode.CEILING);
        return price.divide(dividendYieldService.calculate(stock, price),2,RoundingMode.HALF_UP);
    }

    /**
     * Return String of calculation of price earnings.
     * @param stock Stock to calculate price earnings upon.
     * @param price price for price earnings calculation.
     * @return String with price earnings of stock and price.
     */
    public String calculateToString(Stock stock, BigDecimal price){
        return "Price Earnings for {Stock:" + stock.getSymbol() + " Price:" + price+"}: " + calculate(stock, price);
    }
}
