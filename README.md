# api2

A simple grails project, containing CRUDs for citys, employees and salary readjustment manipulation.

## Cidade

**GET** - `/cidade?id=$id`

**GET** - `/cidade/list`

**GET** - `/cidade/get?id=$id`

**POST** - `/cidade/save`

**PUT** - `/cidade/update?id=$id`

**DELETE** - `/cidade/delete?id=$id`

### Index

#### Request

- **Endpoint:** _/cidade_
- **Method:** _GET_
- **URL Query Params:** _id_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nome": "Sapiranga"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### List

#### Request

- **Endpoint:** _/cidade/list_
- **Method:** _GET_
- **URL Query Params:** _none_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "nome": "Sapiranga"
    },
    {
      "id": 2,
      "nome": "Porto Alegre"
    },
    {
      "id": 3,
      "nome": "Canoas"
    }
  ]
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Get

#### Request

- **Endpoint:** _/cidade/get_
- **Method:** _GET_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nome": "Sapiranga"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Save

#### Request

- **Endpoint:** _/cidade/save_
- **Method:** _POST_
- **URL Query Params:** _none_
- **Body:** _json_
```json
{
  "nome": ""
}
```

#### Response

_201 Created:_
```json
{
  "success": true,
  "data": {
    "id": 4,
    "nome": "Picada Café"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Update

#### Request

- **Endpoint:** _/cidade/update_
- **Method:** _PUT_
- **URL Query Params:** _id_
- **Body:** _json_

```json
{
    "nome": ""
}
```

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```

### Delete

#### Request

- **Endpoint:** _/cidade/delete_
- **Method:** _DELETE_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```

## Funcionario

**GET** - `/funcionario?id=$id`

**GET** - `/funcionario/list`

**GET** - `/funcionario/get?id=$id`

**POST** - `/funcionario/save`

**PUT** - `/funcionario/update?id=$id`

**DELETE** - `/funcionario/delete?id=$id`

### Index

#### Request

- **Endpoint:** _/funcionario_
- **Method:** _GET_
- **URL Query Params:** _id_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "cidade": {
      "id": 2
    },
    "nome": "Joey Ramone"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### List

#### Request

- **Endpoint:** _/funcionario/list_
- **Method:** _GET_
- **URL Query Params:** _none_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "cidade": {
        "id": 2
      },
      "nome": "Joey Ramone"
    },
    {
      "id": 2,
      "cidade": {
        "id": 3
      },
      "nome": "Scott Pilgrim"
    }
  ]
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Get

#### Request

- **Endpoint:** _/funcionario/get_
- **Method:** _GET_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "cidade": {
      "id": 2
    },
    "nome": "Joey Ramone"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Save

#### Request

- **Endpoint:** _/funcionario/save_
- **Method:** _POST_
- **URL Query Params:** _none_
- **Body:** _json_
```json
{
  "nome": "",
  "cidadeId:": 0
}
```

#### Response

_201 Created:_
```json
{
  "success": true,
  "data": {
    "id": 3,
    "cidade": {
      "id": 2
    },
    "nome": "Johnny Rotten"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Update

#### Request

- **Endpoint:** _/funcionario/update_
- **Method:** _PUT_
- **URL Query Params:** _id_
- **Body:** _json_

```json
{
    "nome": "",
    "cidadeId": 0
} 
```

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```

### Delete

#### Request

- **Endpoint:** _/funcionario/delete_
- **Method:** _DELETE_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```

## ReajusteSalario

**GET** - `/reajusteSalario?id=$id`

**GET** - `/reajusteSalario/list`

**GET** - `/reajusteSalario/get?id=$id`

**POST** - `/reajusteSalario/save`

**PUT** - `/reajusteSalario/update?id=$id`

**DELETE** - `/reajusteSalario/delete?id=$id`

### Index

#### Request

- **Endpoint:** _/reajusteSalario_
- **Method:** _GET_
- **URL Query Params:** _id_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "funcionarioId": 1,
    "dataReajuste": "19/08/2023",
    "valorSalario": 3058.85
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### List

#### Request

- **Endpoint:** _/reajusteSalario/list_
- **Method:** _GET_
- **URL Query Params:** _none_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "funcionarioId": 1,
      "dataReajuste": "19/08/2023",
      "valorSalario": 3058.85
    },
    {
      "id": 2,
      "funcionarioId": 2,
      "dataReajuste": "15/07/2023",
      "valorSalario": 2080.2
    }
  ]
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Get

#### Request

- **Endpoint:** _/reajusteSalario/get_
- **Method:** _GET_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_200 OK:_
```json
{
  "success": true,
  "data": {
    "id": 1,
    "funcionarioId": 1,
    "dataReajuste": "19/08/2023",
    "valorSalario": 3058.85
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Save

#### Request

- **Endpoint:** _/reajusteSalario/save_
- **Method:** _POST_
- **URL Query Params:** _none_
- **Body:** _json_
```json
{
  "funcionarioId": 0,
  "dataReajuste": "dd/MM/yyyy",
  "valorSalario": 0.0
}
```

#### Response

_201 Created:_
```json
{
  "success": true,
  "data": {
    "id": 3,
    "cidade": {
      "id": 2
    },
    "nome": "Johnny Rotten"
  }
}
```

---

_400 Bad Request:_
```json
{
  "success": false,
  "message": "error.message"
}
```

### Update

#### Request

- **Endpoint:** _/reajusteSalario/update_
- **Method:** _PUT_
- **URL Query Params:** _id_
- **Body:** _json_

```json
{
  "valorSalario": 0.0,
  "dataReajuste": "dd/MM/yyyy",
  "funcionarioId": 0
}
```

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```

### Delete

#### Request

- **Endpoint:** _/reajusteSalario/delete_
- **Method:** _DELETE_
- **URL Query Params:** _id_
- **Body:** _none_

#### Response

_204 No Content_

---

_400 Bad Request:_

```json
{
  "success": false,
  "message": "error.message"
}
```
