package com.application.service.stockmarketFunction.impl;

import com.application.entity.Stock;
import com.application.entity.Trade;
import com.application.exceptions.InputValidator;
import com.application.service.stockmarketFunction.TradeBook;

import java.util.*;

/**
 * Implementation of { @Code TradeBook}
 * @author aneesh
 */
public class TradeBookImpl implements TradeBook {

    private Map<String, List<Trade>> stockTradeListMap = new HashMap<>();

    @Override
    public List<Trade> getTradesSinceMinutes(Stock stock, int minutes) {
        InputValidator.validateStockAndMinutes(stock, minutes);
        Date minutesAgo = new Date(System.currentTimeMillis() - (minutes * 60 * 1000));

        if(stockTradeListMap.containsKey(stock.toString())){
        List<Trade> tradesForStock = stockTradeListMap.get(stock.toString());
        Collections.sort(tradesForStock); //trade implements comparable.
        List<Trade> tradesSinceMinutes = new ArrayList<>();
        for(int i = 0; i<tradesForStock.size(); i++){
            if(tradesForStock.get(i).getTimeStamp().before(minutesAgo)){
                break;
            }
            tradesSinceMinutes.add(tradesForStock.get(i));
        }
        return tradesSinceMinutes;

    }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Trade> getAllTrades() {
        List<Trade> allTrades = new ArrayList<>();
        for(String stock : stockTradeListMap.keySet()){
            allTrades.addAll(stockTradeListMap.get(stock));
        }
        return allTrades;
    }

    public Map<String, List<Trade>> getStockTradeListMap() {
        return stockTradeListMap;
    }
}
