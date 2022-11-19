# Customer-Address

## Build Project
`mvn clean install`

## Run application
`mvn spring-boot:run`

## Application endpoints
`We have all the endpoints into the collection 'Resquest Volvo.postman_collection.json'.`

### Customer
* Find All Customers:&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;&nbsp; 'GET' - http://localhost:8080/api/customer/getAllCustomers
* Find Customer by Document ID: &emsp;                                'GET' - http://localhost:8080/api/customer/{documentId}
* Find All Customers By ZipCode: &emsp;&nbsp;                         'GET' - http://localhost:8080/api/customer/zipCode/{zipCode}
* Delete Customer By Id:&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;          'DELETE' - http://localhost:8080/api/customer/{customerId}
* Create Customer without address: &nbsp;                             'POST' - http://localhost:8080/api/customer/create
* Create Customer with address: &emsp;&nbsp;&nbsp;&nbsp;              'POST' - http://localhost:8080/api/customer/create/address/{addressId}
* Update Customer: &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;   'PUT' - http://localhost:8080/api/customer/{customerId}
* Associete Customer Address:&emsp;&emsp;&nbsp;&nbsp;&nbsp;           'PUT' - http://localhost:8080/api/customer/{customerId}/address/{addressId}

### Address
* Find All Addresses: &emsp;&nbsp;&nbsp;&nbsp;&nbsp; 'GET' - http://localhost:8080/api/address/getAllAddresses
* Find Address By id: &emsp;&emsp;                   'GET' - http://localhost:8080/api/address/{addressId}
* Delete Address By Id: &emsp;                       'DELELTE' - http://localhost:8080/api/address/{addressId}
* Create Address: &emsp;&emsp;&emsp;&nbsp;           'POST' - http://localhost:8080/api/address/create

`All the {xxxx} should be replaced with the respectives values.`
