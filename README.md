# SettlementSystem
Instructions fed to the X company needs to processed and report has to be generated.
Ths system tries to fullfill the above requests.

### Requirements
* Java 8: Any compliant JVM should work.
  * [Java 8 JDK from Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  * [Java 8 JDK from IBM (AIX, Linux, z/OS, IBM i)](http://www.ibm.com/developerworks/java/jdk/)
* [Maven](https://maven.apache.org/install.html)
Java 8 streams, filter, map and lambda expressions have been used to process the instructions and generate reports for sorting and aggreagting.
Java 8 has defined a separate Enum for handling days of the week named – DayOfWeek (java.time.DayOfWeek).
It is recommended to use the Enum constants in code rather than their equivalent int values to enhance code readability and clarity.

### Create a report that shows
 Amount in USD settled incoming everyday
 Amount in USD settled outgoing everyday
 Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
amount for a buy instruction, then foo is rank 1 for outgoing

### Tests

Apart from unit tests, the application can also incorporate integration test with karate framework
Due to time constraints it has been skipped.
Junit test is available in /src/test/java/com/jpmg/forex/settlementsystem/SettlementSystemApplicationTests.java

Assumptions
-----------
For currencies AED / SAR (Mid East couties) Work week > Sun to Thursday.
                                            Week end holdays = Fri and Saturday

For All other currencies Work week = Mon to Friday
                         Week end holdays = Saturday and Sunday



Updating settlement dates if falls on non working days
-------------------------------------------------------
if currency is AED / SAR and settlement falls on Friday, then setlement date should be incremented by 2 days
                             settlement falls on saturday, then setlement date should be incremented by 1 day


For currencies other than AED / SAR and settlement falls on Saturaday, then setlement date should be incremented by 2 days
										settlement falls on sunday, then setlement date should be incremented by 1 day

There are many instances when the business requirements specify implementation logic based on day of the week(Monday, Tuesday…) for a given date. 
Java 8 has defined a separate Enum for handling days of the week named – DayOfWeek (java.time.DayOfWeek).

java.time.DayOfWeek is an Enum which defines 7 constants representing the seven days of the week – MONDAY(int value=1), TUESDAY(2), WEDNESDAY(3)… till SUNDAY(7).
It is recommended to use the Enum constants in code rather than their equivalent int values to enhance code readability and clarity.


Functionality
-------------
Fetch the day of the settlement date, from the instruction.
Get the currency of the instruction.
Alter the settlement date in necessary

Normal System.out.println is used to print the logs
In real scenario Log4j can be used to deug and trace.

Exceptions handling is minismised to concentrate more on the business logic, since time is very restricted.

Junit using powermockito is used to inject mocks.
Test to ensure that settlement data is modified , when settlement date is instructed on a holiday for the currency.
Test to ensure the Ranks are generated based on the USD transascted.
Day wise settlement is also tested for the given instructions.

Code is packaged with services, model, utils, test and interface for the service.
Class simulateForex has a main method to run and feed data to system.
Test class ForexHandlerImplTest runs a junit test with below mentioned sample instructions.

Java 8 streams, filter, map and lambda expressions have been used to process the instructions and generate reports for sorting and aggreagting.

