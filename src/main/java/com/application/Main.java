package com.application;

import com.application.entity.Stock;
import com.application.entity.Trade;
import com.application.entity.enums.TradeType;
import com.application.entity.enums.VotingType;
import com.application.service.stockCalculation.DividendYield;
import com.application.service.stockCalculation.impl.DividendYieldImpl;
import com.application.service.stockCalculation.PriceEarnings;
import com.application.service.stockCalculation.VolWeightedStockPrice;
import com.application.service.stockmarketFunction.*;
import com.application.service.stockmarketFunction.TradeBookGeometricMean;
import com.application.service.stockmarketFunction.impl.TradeBookImpl;
import com.application.service.stockmarketFunction.impl.TradeRecorderImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Super simple stock marker
 * @author aneesh
 */
public class Main {
    private static List<Stock> stockList;
    private static List<Trade> tradeList;
    private static List<String> calculationList;
    private static TradeBook tradeBook = new TradeBookImpl();
    private static TradeRecorder tradeRecorder = new TradeRecorderImpl(tradeBook);

    public static void main(String[] args) {
            stockList = populateStocks();
            tradeList = populateTrades();
            calculationList = populateCalculations();
            printStocks();
            printTrades();
            printCalculations();
    }


    private static void printCalculations() {

        System.out.println("\nGBCE All Share Index: " + new TradeBookGeometricMean(tradeBook).calculateGeometricMean());
        System.out.println("\nStock Calculations: ");
        for(String calculation : calculationList){
            System.out.println(calculation);
        }
    }

    private static void printTrades() {
        System.out.println("\nTrades placed:");
        for(Trade t : tradeList){
            System.out.println(t);
        }
    }

    private static void printStocks() {
        System.out.println("Stocks traded:");
        for(Stock s : stockList){
            System.out.println(s);
        }
    }

    private static List<String> populateCalculations() {

        List<String> calculationsToReturn = new ArrayList<>();

        VolWeightedStockPrice vw = new VolWeightedStockPrice(tradeBook);
        DividendYield dy = new DividendYieldImpl();
        PriceEarnings pe = new PriceEarnings(dy);
        calculationsToReturn.add(dy.calculateToString(Stock.getStockLookup().get("POP"), BigDecimal.valueOf(.03)));
        calculationsToReturn.add(dy.calculateToString(Stock.getStockLookup().get("JOE"), BigDecimal.valueOf(8.0)));
        calculationsToReturn.add(pe.calculateToString(Stock.getStockLookup().get("ALE"), BigDecimal.valueOf(4.8)));
        calculationsToReturn.add(pe.calculateToString(Stock.getStockLookup().get("GIN"), BigDecimal.valueOf(2.13)));
        calculationsToReturn.add(vw.calculateToString(Stock.getStockLookup().get("POP"), 15));
        calculationsToReturn.add(vw.calculateToString(Stock.getStockLookup().get("GIN"), 15));

        return calculationsToReturn;
    }

    private static List<Trade> populateTrades() {
        List<Trade> tradesToReturn = new ArrayList<>();
        tradesToReturn.add(new Trade(Stock.getStockLookup().get("POP"),10,TradeType.BUY_ORDER, BigDecimal.valueOf(10), tradeRecorder));
        tradesToReturn.add(new Trade(Stock.getStockLookup().get("POP"),6,TradeType.BUY_ORDER, BigDecimal.valueOf(17), tradeRecorder));
        tradesToReturn.add(new Trade(Stock.getStockLookup().get("ALE"),4,TradeType.BUY_ORDER, BigDecimal.valueOf(12.34), tradeRecorder));
        tradesToReturn.add(new Trade(Stock.getStockLookup().get("JOE"),120,TradeType.BUY_ORDER, BigDecimal.valueOf(7.99), tradeRecorder));
        tradesToReturn.add(new Trade(Stock.getStockLookup().get("GIN"),7,TradeType.BUY_ORDER, BigDecimal.valueOf(3.45), tradeRecorder));
        return tradesToReturn;
    }

    private static List<Stock> populateStocks() {
        List<Stock> stocksToReturn = new ArrayList<>();
        stocksToReturn.add(new Stock("TEA", VotingType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(100)));
        stocksToReturn.add(new Stock("POP", VotingType.COMMON, BigDecimal.valueOf(8), BigDecimal.ZERO, BigDecimal.valueOf(100)));
        stocksToReturn.add(new Stock("ALE", VotingType.COMMON, BigDecimal.valueOf(23), BigDecimal.ZERO, BigDecimal.valueOf(60)));
        stocksToReturn.add(new Stock("GIN", VotingType.PREFERRED, BigDecimal.valueOf(8), BigDecimal.valueOf(2), BigDecimal.valueOf(100)));
        stocksToReturn.add(new Stock("JOE", VotingType.COMMON, BigDecimal.valueOf(13), BigDecimal.ZERO, BigDecimal.valueOf(250)));


        return stocksToReturn;
    }


}
