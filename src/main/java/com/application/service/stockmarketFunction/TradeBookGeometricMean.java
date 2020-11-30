package com.application.service.stockmarketFunction;

import com.application.entity.Trade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Service to compute the geometric mean of a trade book.
 * @author aneesh
 */
public class TradeBookGeometricMean {

    private TradeBook tradeBook;

    /**
     * Geometric mean is calculated for a specified trade book.
     * @param tradeBookArg Trade book used to calculate the mean within.
     */
    public TradeBookGeometricMean(TradeBook tradeBookArg){
        tradeBook = tradeBookArg;
    }

    /**
     * Calculate the geometric mean of the trade book.
     * @return Geometric mean for the trade book.
     */
    public BigDecimal calculateGeometricMean(){

        List<Trade> listOfTrades = tradeBook.getAllTrades();

        BigDecimal allPrices = BigDecimal.ONE;
        for(Trade trade : listOfTrades){
            allPrices = allPrices.multiply(trade.getPrice());
        }

        BigDecimal geometricMean = new BigDecimal(Math.pow(allPrices.doubleValue(), 1D/ listOfTrades.size()));
        return geometricMean.setScale(2, RoundingMode.HALF_UP);

    }
}
