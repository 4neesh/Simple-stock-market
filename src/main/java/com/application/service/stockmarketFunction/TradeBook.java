package com.application.service.stockmarketFunction;

import com.application.entity.Stock;
import com.application.entity.Trade;

import java.util.List;
import java.util.Map;

/**
 * TradeBook interface for {@code Trade}s
 * @author aneesh
 */
public interface TradeBook {
    /**
     * Obtain all trades made for {@code stock} from the past {@code minutes} minutes.
     * @param stock trade book for stock.
     * @param minutes trade book for past minutes.
     * @return all trades for { @param stock} for the past { @param minutes} minutes.
     */
    List<Trade> getTradesSinceMinutes(Stock stock, int minutes);

    /**
     * Obtain all trades made within the trade book.
     * @return list of all trades in the trade book.
     */
    List<Trade> getAllTrades();

    /**
     * Obtain the map of stock symbols and their respective lists of trade.
     * @return map of stock and their respective lists of trade.
     */
    Map<String, List<Trade>> getStockTradeListMap();

}
