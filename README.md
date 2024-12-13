## Account-Management-Service

Service for managing user accounts.

You can:

1. Create user account.
2. Get user account by id.
3. Get user accounts by filter.

### Quick Guide

#### To run:

1. docker-compose up -d
2. set POSTGRES_PATH as jdbc:postgresql://localhost:5555/accounts-db
3. run account-management-service

#### To change default sources:

Set SOURCES env property to change sources-values map.<br>
SOURCES map format is
`key1=value11,value12,...,value1M,key2=value21,value22,...,value2J,...,keyN=valueN1,valueN2,...,valueNK`.