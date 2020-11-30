package com.application.service.stockCalculation.impl;

import com.application.exceptions.InputValidator;
import com.application.entity.Stock;
import com.application.entity.enums.VotingType;
import com.application.service.stockCalculation.DividendYield;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Service to calculate the dividend yield of a Stock for preferred and common voting types.
 * @author aneesh
 */
public class DividendYieldImpl implements DividendYield {


    public BigDecimal calculate(Stock stock, BigDecimal price){

        InputValidator.validateStockAndPrice(stock, price);

        if(stock.getType().equals(VotingType.COMMON)){
            return commonTypeDividend(stock, price);
        }
        else{
            return preferredTypeDividend(stock, price);
        }
    }

    public String calculateToString(Stock stock, BigDecimal price){
        return "Dividend Yield for {Stock:" + stock.getSymbol() + " Price:" + price+"}: " + calculate(stock, price);
    }

    private static BigDecimal preferredTypeDividend(Stock stock, BigDecimal price) {
        MathContext mc = new MathContext(4, RoundingMode.CEILING);
        BigDecimal fixedDivByPar = stock.getFixedDividend().multiply(stock.getParValue()).divide(BigDecimal.valueOf(100));
        BigDecimal result = fixedDivByPar.divide(price,2,RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP);
        return result;
    }

    private static BigDecimal commonTypeDividend(Stock stock, BigDecimal price){

        MathContext mc = new MathContext(4, RoundingMode.CEILING);
        BigDecimal result = stock.getLastDividend().divide(price,2,RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP);
        return result;
    }

}
