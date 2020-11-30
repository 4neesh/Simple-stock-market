package com.application.entity;

import com.application.entity.enums.TradeType;
import com.application.exceptions.InputValidator;
import com.application.service.stockmarketFunction.TradeRecorder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Trade entity
 * @author aneesh
 */
public final class Trade implements Comparable<Trade>{

    private final Date timeStamp;
    private final int quantity;
    private final TradeType action;
    private final BigDecimal price;
    private final Stock stock;

    /**
     *
     * @param stock the stock to be traded.
     * @param quantity the quantity of stock traded.
     * @param action the side of the trade to be actioned.
     * @param price the price of the stock to be traded.
     */
    public Trade( Stock stock, int quantity,  TradeType action,  BigDecimal price, TradeRecorder tradeRecorder) {
        super();
        InputValidator.validateTradeQuantityActionPrice(stock, quantity, action, price);
        this.stock = stock;
        this.timeStamp = new Date();
        this.quantity = quantity;
        this.action = action;
        this.price = price;
        tradeRecorder.recordTrade(this);
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public int compareTo(Trade trade) {
        return trade.getTimeStamp().compareTo(this.timeStamp);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "timeStamp=" + timeStamp +
                ", quantity=" + quantity +
                ", action=" + action +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
