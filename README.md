# Customer-Address

## Build Project
`mvn clean install`

## Run application
`mvn spring-boot:run`

## Application endpoints
`We have all the endpoints into the collection 'Resquest Volvo.postman_collection.json'`

### Customer
* Find All Customers:&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/customer/getAllCustomers
* Find Customer by Document ID: &nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/customer/000000
* Find All Customers By ZipCode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'GET' - http://localhost:8080/api/customer/zipCode/000000
* Delete Customer By Id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'DELETE' - http://localhost:8080/api/customer/0
* Create Customer without address: &nbsp;'POST' - http://localhost:8080/api/customer/create
* Create Customer with address: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'POST' - http://localhost:8080/api/customer/create/address/0
* Update Customer: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'PUT' - http://localhost:8080/api/customer/0
* Associete Customer Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'PUT' - http://localhost:8080/api/customer/0/address/0

### Address
* Find All Addresses: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/address/getAllAddresses
* Find Address By id: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/address/0
* Delete Address By Id: &nbsp;&nbsp;&nbsp;'DELELTE' - http://localhost:8080/api/address/0
* Create Address: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'POST' - http://localhost:8080/api/address/create
