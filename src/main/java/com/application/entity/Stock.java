package com.application.entity;

import com.application.entity.enums.VotingType;
import com.application.exceptions.InputValidator;
import com.application.service.stockmarketFunction.StockLookup;
import com.application.service.stockmarketFunction.impl.StockLookupImpl;

import java.math.BigDecimal;

/**
 * Stock entity
 * @author aneesh
 */
public final class Stock {

    private final String symbol;
    private final VotingType type;
    private final BigDecimal parValue;
    private final BigDecimal lastDividend;
    private final BigDecimal fixedDividend;
    private static StockLookup stockLookup = new StockLookupImpl();
    /**
     * Stock is constructed by a symbol, type, last dividend, fixed dividend and par value
     * @param symbol stock symbol
     * @param type stock voting type
     * @param lastDividend stock last dividend
     * @param fixedDividend stock fixed dividend
     * @param parValue stock par value
     */
    public Stock(String symbol,VotingType type, BigDecimal lastDividend, BigDecimal fixedDividend,BigDecimal parValue) {

            InputValidator.validateStockParameters(symbol, type, lastDividend, fixedDividend, parValue);
            this.symbol = symbol.toUpperCase();
            this.type = type;
            this.parValue = parValue;
            this.lastDividend = lastDividend;
            this.fixedDividend = fixedDividend;
            addStock(this);
    }

    /**
     * Return lookup map of all stocks recorded.
     * @return StockLookup instance.
     */
    public static StockLookup getStockLookup() {
        return stockLookup;
    }

    private static void addStock(Stock stock){
        stockLookup.add(stock);
    }

    public String getSymbol() {
        return symbol;
    }

    public VotingType getType() {
        return type;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public BigDecimal getFixedDividend() {
        return fixedDividend;
    }
    


    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", type=" + type +
                ", parValue=" + parValue +
                ", lastDividend=" + lastDividend +
                ", fixedDividend=" + fixedDividend +
                '}';
    }
}
