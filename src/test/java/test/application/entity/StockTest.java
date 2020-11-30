package test.application.entity;

import com.application.exceptions.StockException;
import com.application.entity.Stock;
import com.application.entity.enums.VotingType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for Common stock
 * @author aneesh
 */
public class StockTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void commonStockRecordsUpperSymbol(){
        Stock stock = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        assertEquals("Common stock does not record upper case symbol as expected.", "APPL", stock.getSymbol());
    }

    @Test
    public void commonStockRecordsLowerSymbol(){
        Stock stock = new Stock("appl", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        assertEquals("Common stock does not record lower case symbol as expected.", "APPL", stock.getSymbol());
    }

    @Test
    public void commonStockRecordsNumberSymbol(){
        Stock stock = new Stock("ap2l", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
        assertEquals("Common stock does not record lower case symbol as expected.", "AP2L", stock.getSymbol());
    }

    @Test
    public void commonStockSymbolTooLong(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("AAPLL", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void commonStockSymbolTooShort(){

        expectedException.expect(StockException.class);
        Stock stock = new Stock("", VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void commonStockSymbolNull(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock(null, VotingType.COMMON, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void commonVotingTypeNull(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void parValueNull(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", VotingType.PREFERRED, BigDecimal.valueOf(0), BigDecimal.valueOf(0), null);
    }

    @Test
    public void fixedDividendNull(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", VotingType.PREFERRED, BigDecimal.valueOf(0), null, BigDecimal.ONE);
    }

    @Test
    public void fixedDividendNegative(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", VotingType.PREFERRED, BigDecimal.valueOf(0), BigDecimal.valueOf(-0.1), BigDecimal.ONE);
    }



    @Test
    public void commonLastDividendLessThanZero(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", VotingType.COMMON, BigDecimal.valueOf(-10), BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void commonLastDividendNull(){
        expectedException.expect(StockException.class);
        Stock stock = new Stock("APPL", VotingType.COMMON, null, BigDecimal.valueOf(0), BigDecimal.valueOf(100));
    }

    @Test
    public void stockNotFoundInLookup(){
        expectedException.expect(StockException.class);
        Stock.getStockLookup().get("TEST");
    }
}
