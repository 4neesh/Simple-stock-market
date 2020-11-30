package test.application.service;

import com.application.entity.Trade;
import com.application.entity.enums.TradeType;
import com.application.exceptions.PriceException;
import com.application.exceptions.StockException;
import com.application.service.stockCalculation.impl.DividendYieldImpl;
import com.application.entity.Stock;
import com.application.entity.enums.VotingType;
import com.application.service.stockCalculation.PriceEarnings;
import com.application.service.stockCalculation.VolWeightedStockPrice;
import com.application.service.stockmarketFunction.TradeBook;
import com.application.service.stockmarketFunction.impl.TradeBookImpl;
import com.application.service.stockmarketFunction.TradeRecorder;
import com.application.service.stockmarketFunction.impl.TradeRecorderImpl;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class StockCalculationTest {

    private static Stock tea;
    private static Stock pop;
    private static Stock gin;
    private static BigDecimal positivePrice;
    private static BigDecimal negativePrice;
    private static TradeBook tradeBook;
    private static TradeRecorder tradeRecorder;
    @BeforeClass
    public static void setup(){

        positivePrice = new BigDecimal("10");
        negativePrice = new BigDecimal("-10");
        tea = new Stock("TEA", VotingType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(  100));
        pop = new Stock("POP",VotingType.COMMON, BigDecimal.valueOf(8), BigDecimal.ZERO, BigDecimal.valueOf(100));
        gin = new Stock("GIN",VotingType.PREFERRED, BigDecimal.valueOf(8), new BigDecimal("2"),BigDecimal.valueOf(100));
        tradeBook = new TradeBookImpl();
        tradeRecorder = new TradeRecorderImpl(tradeBook);

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void dividendYieldAsExpected(){
        BigDecimal expected = new BigDecimal("0.80");
        assertEquals("Dividend yield of common does not return as expected for positive price and last dividend.", expected,  new DividendYieldImpl().calculate(pop, positivePrice));

    }

    @Test
    public void dividendYieldForNullPrice(){

        expectedException.expect(PriceException.class);
        new DividendYieldImpl().calculate(pop, null);
    }

    @Test
    public void dividendYieldForNegativePrice(){

        expectedException.expect(PriceException.class);
        new DividendYieldImpl().calculate(pop, BigDecimal.valueOf(-1));
    }

    @Test
    public void dividendYieldForNullStock(){

        expectedException.expect(StockException.class);
        expectedException.expectMessage("Stock cannot be null.");
        new DividendYieldImpl().calculate(null, positivePrice);
    }

    @Test
    public void dividendYieldForZeroLastDividendCommon(){
        BigDecimal expected = new BigDecimal("0.00");
        assertEquals("Zero value for last dividend does not return 0 dividend yield.", expected, new DividendYieldImpl().calculate(tea, positivePrice));
    }

    @Test
    public void dividendYieldForZeroPrice(){
        expectedException.expect(ArithmeticException.class);
        new DividendYieldImpl().calculate(pop, BigDecimal.ZERO);
    }

    @Test
    public void dividendYieldForPositivePricePreferred(){

        BigDecimal expected = new BigDecimal("0.20");
        assertEquals("Dividend yield of preferred does not return as expected for positive price, fixed dividend and par.",
                expected,
                new DividendYieldImpl().calculate(gin, positivePrice));
    }

    @Test
    public void dividendYieldForNegativePricePreferred(){
        expectedException.expect(PriceException.class);
        new DividendYieldImpl().calculate(gin, BigDecimal.valueOf(-0.1));
    }

    @Test
    public void priceEarningsAsExpected(){
        DividendYieldImpl dividendYieldImpl = new DividendYieldImpl();
        PriceEarnings priceEarnings = new PriceEarnings(dividendYieldImpl);
        BigDecimal expected = BigDecimal.valueOf(12.50).setScale(2,RoundingMode.HALF_UP);
        assertEquals("Price earnings is not calculated as expected for positive price.", expected, priceEarnings.calculate(pop, BigDecimal.TEN));
    }

    @Test
    public void priceEarningsNullStock(){
        expectedException.expect(StockException.class);
        new DividendYieldImpl().calculate(null, BigDecimal.ZERO);
    }

    @Test
    public void priceEarningsNegativePrice(){
        expectedException.expect(IllegalArgumentException.class);
        new DividendYieldImpl().calculate(pop, BigDecimal.valueOf(-1));
    }

    @Test
    public void volumeWeightedPriceAsExpected(){
        VolWeightedStockPrice volWeightedPrice = new VolWeightedStockPrice(tradeBook);
        Trade trade1 = new Trade(gin, 5,TradeType.BUY_ORDER, BigDecimal.valueOf(6), tradeRecorder);
        Trade trade2 = new Trade(gin, 5,TradeType.BUY_ORDER, BigDecimal.valueOf(5), tradeRecorder);
        BigDecimal expected = BigDecimal.valueOf(5.50).setScale(2, RoundingMode.HALF_UP);
        assertEquals("Price earnings is not calculated as expected for positive price.", expected, volWeightedPrice.calculate(gin, 15));

    }

    @Test
    public void volumeWeightedPriceNullStock(){
        VolWeightedStockPrice volWeightedPrice = new VolWeightedStockPrice(tradeBook);
        expectedException.expect(StockException.class);
        volWeightedPrice.calculate(null, 10);
    }

    @Test
    public void volumeWeightedPriceNegativeMinutes(){
        VolWeightedStockPrice volWeightedPrice = new VolWeightedStockPrice(tradeBook);
        expectedException.expect(IllegalArgumentException.class);
        volWeightedPrice.calculate(pop, -1);
    }

}
