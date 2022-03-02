# jumia-code-challenge
Jumia's Code Challenge

## Disclaimer
The implementation provided here has no intent to be complete/best solution(hard coded endpoints/ports, cross origin settings, low code coverage in unit tests).

The solution here it's just for you to "get the point" about how the application could be structured(front end was not taken into account).

## Description
The easiest way to get the application up and running is by using docker compose:
```
docker-compose up
```
The above command will provision two services:

__backend__: http://localhost:8080

__frontend__: http://localhost:8000

## Frontend
The frontend can be accessed over ```http://localhost:8000``` and it provides a really basic implementation to visualize data from the rest api.

## REST API
The backend service provides two endpoints:
```
/customers - Retrieves the registered customer's phones
/countries - Retrieves the list of supported country names
```

### /customers
The customers endpoint returns a list of registered customer's phones and can be filtered by two attributes:
| Attribute | Description                                                                      | Required |
| --------- | -------------------------------------------------------------------------------  | -------- |
| countries | A set of countries to be filtered                                                | false    |
| valid     | A boolean flag indicating the state of the phone: true = valid / false = invalid | false    |

#### Examples
Listing all the customer's phones:
```
curl http://localhost:8080/customers
```
Result:
```
[
  {
    "number": "(212) 6007989253",
    "country": "Morocco",
    "dialCode": "212",
    "valid": false
  }, 
  {
    "number": "(212) 698054317",
    "country": "Morocco",
    "dialCode": "212",
    "valid": true
  },
  ...
]
```
Filtering by country:
```
curl http://localhost:8080/customers?countries=Cameroon,Ethiopia
```
Result:
```
[
  {
    "number": "(212) 698054317",
    "country": "Morocco",
    "dialCode": "212",
    "valid": true
  },
  {
    "number": "(237) 699209115",
    "country": "Cameroon",
    "dialCode": "237",
    "valid": true
  }
  ...
]
```
Filtering by phone state:
```
curl http://localhost:8080/customers?valid=true
```
Result:
```
[
  {
    "number": "(212) 698054317",
    "country": "Morocco",
    "dialCode": "212",
    "valid": true
  },
  {
    "number": "(237) 699209115",
    "country": "Cameroon",
    "dialCode": "237",
    "valid": true
  }
  ...
]
```
Filtering by country and phone state:
```
curl http://localhost:8080/customers?countries=Cameroon,Morocco&valid=true
```
Result:
```
[
  {
    "number": "(212) 698054317",
    "country": "Morocco",
    "dialCode": "212",
    "valid": true
  },
  {
    "number": "(237) 699209115",
    "country": "Cameroon",
    "dialCode": "237",
    "valid": true
  }
  ...
]
```