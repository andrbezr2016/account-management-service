## Account-Management-Service

Service for managing user accounts.

You can:

1. Create user account. POST api/account
2. Get user account by id. GET api/account/{id}
3. Get user accounts by filter. GET api/accounts

### Quick Guide

#### To run:

1. docker-compose up -d
2. run account-management-service

#### To change default sources:

Set SOURCES env property to change sources-values map.<br>
SOURCES env property format is:
`key1=value11,value12,...,value1M,key2=value21,value22,...,value2J,...,keyN=valueN1,valueN2,...,valueNK`.