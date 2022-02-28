# Card Management API

We are about to build a REST API for a card management platform.
This API should allow us to do basic crud operations for cardholders and cards.
We should be able to insert money or create transactions on a given card.
Some features are already built, but in general we need the following additions in any order.

- Optional filtering to the get cards endpoint, so we can retrieve only active cards
- Optional filtering to the get cards endpoint, so we can retrieve cards with a balance >= X
- An endpoint to create a transaction which withdraws money from the credit card
    - Email the cardholder if the cards balance drops below X
- An endpoint to set cards as inactive (soft delete cards)
- An endpoint to delete a cardholder and all it’s cards and transactions
- Add a scheduled job to email cardholders whose card has expired today


## Current database structure

![Database ER diagram](images/db.png)


## Requirements

- Java 16 (Gradle will pick this up automatically)
- Docker/Docker Compose
- Postgres (Provided through `docker-compose.yml`)
- Mailhog (Provided through `docker-compose.yml`)

## Getting started
To start this API a Postgres database is required.
We can start the additional services through Docker Compose with below command:

```shell
docker-compose up
```

Starting the services through Docker Compose will setup the following:
- Mailhog
  - UI: http://localhost:9000
  - Smtp: `9001`
- Postgres: `9002`

To start up the application with Gradle:

```shell
./gradlew bootRun
```

### IntelliJ
There is supplied run configurations to the two above commands within the `.run` directory
