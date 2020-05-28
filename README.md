Sample Retail Product Springboot Application.

Database used - cassandra.

DDL statements -

CREATE KEYSPACE my_retail WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }


CREATE TABLE price(
                 productId text PRIMARY KEY,
                 price text,
                 currency text
                 );
