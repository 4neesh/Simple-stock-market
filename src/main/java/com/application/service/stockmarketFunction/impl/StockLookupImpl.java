package com.application.service.stockmarketFunction.impl;

import com.application.entity.Stock;
import com.application.exceptions.StockException;
import com.application.service.stockmarketFunction.StockLookup;

import java.util.HashMap;
import java.util.Map;

public class StockLookupImpl implements StockLookup {
    private Map<String, Stock> stockMap = new HashMap<>();
    @Override
    public void add(Stock stock) {
        stockMap.put(stock.getSymbol(), stock);
    }

    @Override
    public Stock get(String stockSymbol) {
        Stock stock = stockMap.get(stockSymbol);
        if(stock == null){
            throw new StockException("Stock has not been created: '" + stockSymbol + "'");
        }
        return stockMap.get(stockSymbol);
    }
}
