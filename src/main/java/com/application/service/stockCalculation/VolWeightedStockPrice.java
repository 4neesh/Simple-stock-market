package com.application.service.stockCalculation;

import com.application.entity.Stock;
import com.application.entity.Trade;
import com.application.exceptions.InputValidator;
import com.application.service.stockmarketFunction.TradeBook;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Service to compute the volume weighted stock price for a stock within the past specified minutes.
 * @author aneesh
 */
public class VolWeightedStockPrice {

    private TradeBook tradeBook;

    /**
     *
     * @param tradeBookArg trade book used to calculate volume weighted stock price from.
     */
    public VolWeightedStockPrice(TradeBook tradeBookArg){
        tradeBook = tradeBookArg;
    }

    /**
     * Calculate the volume weighted stock price for a certain stock for the past defined minutes.
     * @param stock Stock to calculate the volume weighted stock price upon.
     * @param minutes the minutes since present to accumulate trades of the stock for within the calculation.
     * @return volume weighted stock price of Stock for minutes specified.
     */
    public BigDecimal calculate(Stock stock, int minutes){

        InputValidator.validateStockAndMinutes(stock, minutes);
        List<Trade> trades = tradeBook.getTradesSinceMinutes(stock, minutes);

        BigDecimal totalTradedVolume = new BigDecimal(0);
        BigDecimal totalQuantity = new BigDecimal(0);

        for(int i = 0; i<trades.size(); i++){
            Trade currentTrade = trades.get(i);
            BigDecimal tradeVolume = currentTrade.getPrice().multiply(BigDecimal.valueOf(currentTrade.getQuantity()));
            totalQuantity = totalQuantity.add(BigDecimal.valueOf(currentTrade.getQuantity()));
            totalTradedVolume = totalTradedVolume.add(tradeVolume);

        }

        return totalTradedVolume.divide(totalQuantity,2, RoundingMode.HALF_UP);

    }

    /**
     * String of calculation for volume weighted stock price.
     * @param stock Stock to calculate volume weighted stock price.
     * @param minutes the minutes since present to accumulate trades of the stock for within the calculation.
     * @return string with volume weighted stock price of Stock for minutes specified.
     */
    public String calculateToString(Stock stock, int minutes){
        return  "Volume weighted stock price for {Stock:" + stock.getSymbol() + " Past minutes:" + minutes+"}: " + calculate(stock, minutes);
    }
}
