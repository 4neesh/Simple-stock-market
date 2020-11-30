package test.application.service;

import com.application.entity.Stock;
import com.application.entity.Trade;
import com.application.entity.enums.TradeType;
import com.application.entity.enums.VotingType;
import com.application.service.stockmarketFunction.impl.TradeBookImpl;
import com.application.service.stockmarketFunction.TradeRecorder;
import com.application.service.stockmarketFunction.impl.TradeRecorderImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for TradeBookImpl and TradeRecorderImpl classes
 * @author aneesh
 */

public class TradeBookImplRecorderImplTest {

    private static TradeBookImpl tradeBook;
    private static TradeRecorder tradeRecorder;
    @Before
    public void setup(){
        tradeBook = new TradeBookImpl();
        tradeRecorder = new TradeRecorderImpl(tradeBook);
    }

    @Test
    public void newTradeBookHasZeroEntries(){
        assertEquals("New TradeBookImpl does not exist as an empty trade book.",0, tradeBook.getAllTrades().size());
    }

    @Test
    public void tradeBookRecordsSingleTrade(){

        Stock stockA1 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA1 = new Trade(stockA1,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);

        assertEquals("Single trade recorded into TradeBookImpl is not reflected in all trades.", 1, tradeBook.getAllTrades().size());
        assertEquals("Single trade recorded into TradeBookImpl is not reflected in trades since last 15 minutes.", 1, tradeBook.getTradesSinceMinutes(tradeA1.getStock(), 15).size());
    }

    @Test
    public void tradeBookRecordsMultipleTradesOfSameStock(){
        Stock stockA1 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA1 = new Trade(stockA1,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA2 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA2 = new Trade(stockA2,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);

        assertEquals("Multiple trades recorded of same stock into TradeBookImpl is not reflected in all trades.", 2, tradeBook.getAllTrades().size());
        assertEquals("Multiple trades recorded of same stock into TradeBookImpl is not reflected in trades since last 15 minutes.", 2, tradeBook.getTradesSinceMinutes(tradeA2.getStock(), 15).size());
    }

    @Test
    public void tradeBookRecordsTradesOfDifferentStock(){
        Stock stockA1 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA1 = new Trade(stockA1,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA2 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA2 = new Trade(stockA2,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA3 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA3 = new Trade(stockA3,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);

        assertEquals("Trade recorded of different stock into TradeBookImpl is not reflected in all trades.", 3, tradeBook.getAllTrades().size());
        assertEquals("Trade recorded of different stock into TradeBookImpl is not reflected in trades since last 15 minutes.", 1, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).size());
    }

    @Test
    public void tradeBookRecordsMultipleTradesOfDifferentStock(){
        Stock stockA1 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA1 = new Trade(stockA1,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA2 = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA2 = new Trade(stockA2,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA3 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA3 = new Trade(stockA3,11, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA4 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA4 = new Trade(stockA4,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);

        assertEquals("Multiple trades recorded of different stock into TradeBookImpl is not reflected in all trades.", 4, tradeBook.getAllTrades().size());
        assertEquals("Multiple trades recorded of different stock into TradeBookImpl is not reflected in trades since last 15 minutes.", 2, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).size());
    }

    @Test
    public void tradeBookRecordsMultipleTradesOfSameStockInFifteenMinutesInOrder(){
        Stock stockA3 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA3 = new Trade(stockA3,11, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA4 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA4 = new Trade(stockA4,10, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA5 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA5 = new Trade(stockA5,9, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);
        Stock stockA6 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Trade tradeA6 = new Trade(stockA6,8, TradeType.BUY_ORDER, BigDecimal.ONE, tradeRecorder);

        assertEquals("Trader order of multiple stock into TradeBookImpl is not reflected in-order trades since last 15 minutes.", tradeA3, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).get(0));
        assertEquals("Trade recorded of different stock into TradeBookImpl is not reflected in-order trades since last 15 minutes.", tradeA4, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).get(1));
        assertEquals("Trade recorded of different stock into TradeBookImpl is not reflected in-order trades since last 15 minutes.", tradeA5, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).get(2));
        assertEquals("Trade recorded of different stock into TradeBookImpl is not reflected in-order trades since last 15 minutes.", tradeA6, tradeBook.getTradesSinceMinutes(tradeA3.getStock(), 15).get(3));
    }

    @Test
    public void tradeBookRecordsMultipleTradesOfSameStockInZeroMinutesInOrder(){
        Stock stockA3 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Stock stockA4 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Stock stockA5 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        Stock stockA6 = new Stock("IBM", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));


        assertEquals("Trader order of multiple stock into TradeBookImpl is not reflected in-order trades since last 15 minutes.",
                0,
                tradeBook.getTradesSinceMinutes(stockA3, 0).size());
  }


}
