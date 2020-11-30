package com.application.service.stockmarketFunction;

import com.application.entity.Trade;

/**
 * TradeRecorder interface to record { @code Trade}s into a trade book.
 * @author aneesh
 */
public interface TradeRecorder {

    /**
     * Record trade { @code trade} into a Trade book.
     * @param trade trade to be recorded.
     */
    void recordTrade(Trade trade);
}
