package com.application.service.stockmarketFunction;

import com.application.entity.Stock;

/**
 * Stock lookup to find stocks based upon the symbol
 * @author aneesh
 */
public interface StockLookup {

    /**
     * Add a stock into the Stock lookup.
     * @param stock Stock to be added into a lookup of all Stocks defined.
     */
    void add(Stock stock);

    /**
     * Obtain a Stock from the stock lookup.
     * @param stockSymbol Stock symbol to look up upon.
     * @return Stock from stock lookup.
     */
    Stock get(String stockSymbol);
}
