package test.application.service;

import com.application.entity.Stock;
import com.application.entity.Trade;
import com.application.entity.enums.TradeType;
import com.application.entity.enums.VotingType;

import com.application.service.stockmarketFunction.TradeBook;
import com.application.service.stockmarketFunction.impl.TradeBookImpl;
import com.application.service.stockmarketFunction.TradeRecorder;
import com.application.service.stockmarketFunction.impl.TradeRecorderImpl;
import com.application.service.stockmarketFunction.TradeBookGeometricMean;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for Stock formulae
 * @author aneesh
 */
public class TradeBookGeometricMeanTest {
    private static Stock tea;
    private static Stock pop;
    private static Stock gin;
    private static TradeBook tradeBook;

    @Before
    public void setup(){

        tea = new Stock("TEA", VotingType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(  100));
        pop = new Stock("POP",VotingType.COMMON, BigDecimal.valueOf(8), BigDecimal.ZERO, BigDecimal.valueOf(100));
        gin = new Stock("GIN",VotingType.PREFERRED, BigDecimal.valueOf(8), new BigDecimal("2"),BigDecimal.valueOf(100));

    }

    @Test
    public void geometricMeanAsExpected(){
        tradeBook = new TradeBookImpl();
        TradeRecorder tradeRecorder = new TradeRecorderImpl(tradeBook);
        Trade tradeOne = new Trade(tea, 10, TradeType.BUY_ORDER, BigDecimal.valueOf(11), tradeRecorder);
        Trade tradeTwo = new Trade(pop, 5, TradeType.BUY_ORDER, BigDecimal.valueOf(7.88), tradeRecorder);
        Trade tradeThree = new Trade(gin, 7, TradeType.SELL_ORDER, BigDecimal.valueOf(3.99), tradeRecorder);
        Trade tradeFour = new Trade(gin, 7, TradeType.BUY_ORDER, BigDecimal.valueOf(7), tradeRecorder);

        TradeBookGeometricMean tradeBookGeometricMean = new TradeBookGeometricMean(tradeBook);
        BigDecimal expected = new BigDecimal("7.01");
        assertEquals("Geometric mean is not calculated as expected for all trades.",expected, tradeBookGeometricMean.calculateGeometricMean());
    }


}
