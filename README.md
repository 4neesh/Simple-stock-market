#Super Simple Stock Market
* The super simple stock market is an application that can create stocks, record trades and report 
statistics upon different stocks and trades placed.<br>
* J-unit is an added dependency used for unit testing.
* All functionality from the specification are as expected.

##Main application
* Run from <code>Main.class</code> or command line. A demo of the stocks created, trades placed and calculations performed will run.
* To run from command line, navigate to Target directory and run "java -jar SimpleStockMarket.jar"

##Design choices
* Dependency injection is preferred to the singleton pattern for the <code>TradeBookImpl</code> due to difficulties with unit testing.
* BigDecimal is used to ensure accuracy of prices at the expense of application performance. All values are to 2 decimal places.
* Custom exceptions are defined to validate the input parameters of stocks and trades.
* SOLID principles have been applied throughout the design. Single responsibility is used for each class, open closed is used to support 
new behaviour of the application and dependency inversion is used to loosely couple the relationships between the Stocks, Trades, and 
StockMarket.
* Immutable class design is used for Stock and Trade as I believe they should not change once created. This meant the Trade is placed when created 
and the timestamp of it is therefore set once created. If the Trade creation and placement were at separate times, the Trade class could not be immutable.

##Testing
* Unit testing has been carried out with J-unit. Testing includes validation of entities, functionality of the stock market, 
and calculations regarding individual stocks and stock markets.
