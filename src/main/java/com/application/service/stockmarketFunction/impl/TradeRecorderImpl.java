package com.application.service.stockmarketFunction.impl;

import com.application.entity.Trade;
import com.application.service.stockmarketFunction.TradeBook;
import com.application.service.stockmarketFunction.TradeRecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code TradeRecorder}
 * @author aneesh
 */
public class TradeRecorderImpl implements TradeRecorder {
    private final TradeBook tradeBook;

    public TradeRecorderImpl(TradeBook tradeBook){

        this.tradeBook = tradeBook;
    }

    public void recordTrade(Trade trade) {

        if(stockTradeListDoesNotContainStock(trade)){
            tradeBook.getStockTradeListMap().put(trade.getStock().toString(), new ArrayList<>());
        }
        List<Trade> tradeList = tradeBook.getStockTradeListMap().get(trade.getStock().toString());

        tradeList.add(trade);

    }


    private boolean stockTradeListDoesNotContainStock(Trade trade) {
        return !tradeBook.getStockTradeListMap().containsKey(trade.getStock().toString());
    }
}
