# Customer-Address

## Build Project
`mvn clean install`

## Run application
`mvn spring-boot:run`

## Application endpoints
`We have all the endpoints into the collection 'Resquest Volvo.postman_collection.json'.`

### Customer
* Find All Customers:&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/customer/getAllCustomers
* Find Customer by Document ID: &nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/customer/{documentId}
* Find All Customers By ZipCode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'GET' - http://localhost:8080/api/customer/zipCode/{zipCode}
* Delete Customer By Id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'DELETE' - http://localhost:8080/api/customer/{customerId}
* Create Customer without address: &nbsp;'POST' - http://localhost:8080/api/customer/create
* Create Customer with address: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'POST' - http://localhost:8080/api/customer/create/address/{addressId}
* Update Customer: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'PUT' - http://localhost:8080/api/customer/{customerId}
* Associete Customer Address:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 'PUT' - http://localhost:8080/api/customer/{customerId}/address/{addressId}

### Address
* Find All Addresses: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/address/getAllAddresses
* Find Address By id: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'GET' - http://localhost:8080/api/address/{addressId}
* Delete Address By Id: &nbsp;&nbsp;&nbsp;'DELELTE' - http://localhost:8080/api/address/{addressId}
* Create Address: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'POST' - http://localhost:8080/api/address/create

`All the {xxxx} should be replaced with the respectives values.`
