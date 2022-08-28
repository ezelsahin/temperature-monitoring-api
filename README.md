## Temperature Monitoring API
An API for a temperature monitoring system. The main purpose of the API is to help measuring temperature values.These 
temperature values are average daily and hourly values of measured temperatures. And they are ready to be send to the 
client whenever client requests them.

This API gets measured temperature values from a client and saves these values to database. After that it checks the
database for calculating average daily temperature or average hourly temperature. If there are enough temperature values
for a calculation it calculates new average values and saves it to one of another two tables on database. For this 
calculation process API doesn't wait until average values to be wanted and calculates average values before they are 
wanted. With this feature it can response as fast as possible to any request because average temperatures are already 
ready to be send.

API is created with JAVA 11 and it adopts REST architecture. A created data.sql file contains sample values for testing
purposes. 

### Used Technologies
- JAVA 11
- Spring Boot
- REST
- Hibernate
- Maven
- JUnit, Mockito (Unit Testing)
- Lombok
- PostgreSQL

### How To Run
Clone this repo into your local:
```
git clone https://github.com/ezelsahin/temperature-monitoring-api.git
```

After that you should configure data resources in `application.properties`
- Download and install PostgreSQL
- Postgres default port : 5432
- Spring Boot default port : 8080
- Update Postgres ***user*** and ***password***
- Create a new database "*qardio_temperature_monitoring_api_db*" in PostgreSQL

Build with maven:

```
mvn clean install
```

Run the API:
```
mvn spring-boot:run
```

### Features
You can find some information about endpoints below.

BodyTemperatureController:

- (GET) /api/getHourly : Returns calculated hourly average temperature values from hourlyAverageTemperatureRepository
- (GET) /api/getDaily : Returns calculated daily average temperature values from dailyAverageTemperatureRepository
- (POST) /api/save : Saves given temperature value with date and time information to temperatureRepository
