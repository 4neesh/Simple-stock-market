package com.application.service.stockCalculation;

import com.application.entity.Stock;

import java.math.BigDecimal;

/**
 * Dividend yield interface for calculating dividend.
 * @author aneesh
 */
public interface DividendYield {

    /**
     * calculate the dividend yield
     * @param stock Stock of dividend calculation.
     * @param price price for dividend calculation.
     * @return Dividend yield for Stock and price depending upon VotingType of Stock.
     */
    BigDecimal calculate(Stock stock, BigDecimal price);

    /**
     * Return String of calculation of dividend yield.
     * @param stock stock for dividend calculation.
     * @param price price for dividend calculation.
     * @return
     */
    String calculateToString(Stock stock, BigDecimal price);
}
