package com.application.exceptions;

import com.application.entity.Stock;
import com.application.entity.enums.TradeType;
import com.application.entity.enums.VotingType;

import java.math.BigDecimal;

/**
 * Service used to validate input arguments to the stock, trade and calculations
 * @author aneesh
 */
public class InputValidator {

    /**
     * Validate the stock and price arguments are valid for a dividend.
     * @param stock the stock to be calculated upon.
     * @param price the price that is calculated with the stock.
     */
    public static void validateStockAndPrice(Stock stock, BigDecimal price) {
        if(price == null){
            throw new PriceException("Price for dividend cannot be null.");
        }
        if(stock == null){
            throw new StockException("Stock cannot be null.");
        }
        if(price.doubleValue() < 0){
            throw new PriceException("Price for dividend must be greater than 0.");
        }
        if(Stock.getStockLookup().get(stock.getSymbol()).equals(null)){
            throw new StockException("Stock is not found in the market.");
        }
    }

    /**
     * Validate the input parameters when creating an instance of a Stock
     * @param symbol Stock symbol.
     * @param type Stock voting rights.
     * @param lastDividend Stock last dividend payment.
     * @param fixedDividend Stock fixed dividend as a percentage.
     * @param parValue Stock par value.
     */
    public static void validateStockParameters(String symbol, VotingType type, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
        if(symbol == null ||symbol.length() <1 || symbol.length() > 4 ){
            throw new StockException("Stock symbol must be between 1 and 4 characters.");
        }
        if(type == null){
            throw new StockException("Stock voting type cannot be null.");
        }
        if(lastDividend == null || lastDividend.doubleValue() < 0 ){
            throw new StockException("Stock last dividend cannot be less than 0.");
        }
        if(fixedDividend == null || fixedDividend.doubleValue() < 0){
            throw new StockException("Stock fixed dividend cannot be less than 0.");

        }
        if(parValue == null){
            throw new StockException("Stock par value cannot be null.");
        }


    }

    /**
     * Validate the trade quantity, trade type and price of a Trade when created.
     *
     * @param stock stock to be traded.
     * @param quantity quantity of a stock to be traded.
     * @param action type of stock action made.
     * @param price price of a trade when made.
     */
    public static void validateTradeQuantityActionPrice(Stock stock, int quantity, TradeType action, BigDecimal price) {
            if(action == null){
                throw new TradeException("Trade type cannot be null.");
            }
            if(quantity < 1){
                throw new TradeException("Trade quantity must be greater than zero.");
            }
            if(price.doubleValue() < 0){
                throw new TradeException("Trade price must be greater than zero.");
            }
            if(stock == null){
                throw new TradeException("Traded stock must not be null.");
            }
            if(Stock.getStockLookup().get(stock.getSymbol()).equals(null)){
                throw new TradeException("Stock is not found in the market.");
            }

    }

    /**
     * Validate the stock and the minutes proposed for retrieval of trades for the stock.
     * @param stock the stock to searched upon within the trade book.
     * @param minutes the backdated minutes from present to search the trade book for.
     */
    public static void validateStockAndMinutes(Stock stock, int minutes) {
        if(stock == null){
            throw new StockException("Stock cannot be null.");
        }
        if(minutes < 0){
            throw new IllegalArgumentException("Minutes must be greater than zero.");
        }
    }


}
