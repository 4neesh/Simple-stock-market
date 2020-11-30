package test.application.entity;

import com.application.exceptions.TradeException;
import com.application.entity.Stock;
import com.application.entity.enums.VotingType;
import com.application.entity.Trade;
import com.application.entity.enums.TradeType;
import com.application.service.stockmarketFunction.TradeBook;
import com.application.service.stockmarketFunction.impl.TradeBookImpl;
import com.application.service.stockmarketFunction.TradeRecorder;
import com.application.service.stockmarketFunction.impl.TradeRecorderImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for creating Trades
 * @author aneesh
 */
public class TradeTest {


    private static Stock stockA;
    private static TradeRecorder tradeRecorder;
    private static TradeBook tradeBook;

    @Before
    public void setup(){
        stockA = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        tradeBook = new TradeBookImpl();
        tradeRecorder = new TradeRecorderImpl(tradeBook);
    }
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void TradeIsCreatedAsExpected(){
        Trade trade = new Trade(stockA,10,TradeType.BUY_ORDER,BigDecimal.ONE, tradeRecorder);
        assertEquals("Trade is not created in trade book as expected.", 1, tradeBook.getAllTrades().size());
    }
    @Test
    public void stockCannotBeNull(){

        expectedException.expect(TradeException.class);
        Trade trade = new Trade(null,10,TradeType.BUY_ORDER,BigDecimal.ONE, tradeRecorder);
    }

    @Test
    public void tradeTypeCannotBeNull(){

        expectedException.expect(TradeException.class);
        Trade trade = new Trade(stockA,10,null,BigDecimal.ONE, tradeRecorder);
    }

    @Test
    public void tradeQuantityMustBeGreaterThanZero(){

        expectedException.expect(TradeException.class);
        Trade trade = new Trade(stockA,-1, TradeType.BUY_ORDER,BigDecimal.ONE, tradeRecorder);
    }

    @Test
    public void tradePriceMustBeGreaterThanZero(){

        expectedException.expect(TradeException.class);
        Trade trade = new Trade(stockA,1, TradeType.BUY_ORDER,BigDecimal.valueOf(-0.1), tradeRecorder);
    }


}
