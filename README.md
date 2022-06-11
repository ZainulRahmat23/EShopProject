
# EShopProject


[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/676906385eea201b077b?action=collection%2Fimport)

Eshop.bak is database for this Application
Please read this documentation before running application.


# Shop

Web application for a store

<!--- If we have only one group/collection, then no need for the "ungrouped" heading -->


## Variables

| Key | Value | Type |
| --- | ------|-------------|
| host | http://localhost:8181 | string |



## Endpoints

* [Product](#product)
    1. [find-all (Permit all)](#1-find-all-permit-all)
    1. [find by id (Permit all)](#2-find-by-id-permit-all)
    1. [new products (Admin)](#3-new-products-admin)
    1. [update product (Admin)](#4-update-product-admin)
    1. [delete (Admin)](#5-delete-admin)
* [Account](#account)
    1. [find all Account (Admin Only)](#1-find-all-account-admin-only)
    1. [acount profile (Authenticated))](#2-acount-profile-authenticated)
    1. [register (permit All)](#3-register-permit-all)
    1. [update account (Authenticated)](#4-update-account-authenticated)
    1. [Customer Orders(Authenticated))](#5-customer-ordersauthenticated)
    1. [customerHistory(Authenticated))](#6-customerhistoryauthenticated)
    1. [delete](#7-delete)
* [ShipVia](#shipvia)
    1. [get all(Permit All)](#1-get-allpermit-all)
    1. [get by id(permit all)](#2-get-by-idpermit-all)
    1. [insert (admin)](#3-insert-admin)
    1. [update(admin)](#4-updateadmin)
* [Category](#category)
    1. [getAll](#1-getall)
    1. [getById](#2-getbyid)
    1. [Insert](#3-insert)
    1. [update](#4-update)
* [order](#order)
    1. [find (Admin)](#1-find-admin)
    1. [order (Authenticated)](#2-order-authenticated)
    1. [confirm-order (Authenticated)](#3-confirm-order-authenticated)

--------



## Product

Request dalam folder ini untuk menampilkan, menambah, mengubah dan menghapus product



### 1. find-all (Permit all)


dapat diakses oleh semua


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/product/all
```



### 2. find by id (Permit all)


StartFragment

dapat diakses oleh semua

EndFragment


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/product/getById/6
```



### 3. new products (Admin)


Hanya dapat diakses admin


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{host}}/product/insert
```



***Body:***

```js        
{
    "product": "food 6",
    "category": 1,
    "price": 15000.0,
    "stock":50
}
```



### 4. update product (Admin)


Hanya dapat diakses admin


***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{host}}/product/update/6
```



***Body:***

```js        
{
    "product": "food 6",
    "category": 1,
    "price": 12000.0,
    "stock":50
}
```



### 5. delete (Admin)


hanya dapat diakses admin


***Endpoint:***

```bash
Method: DELETE
Type: 
URL: {{host}}/product/delete/6
```



## Account

Untuk melakukan register, menampilkan profile, daftar order dan riwayat order



### 1. find all Account (Admin Only)


Request ini untuk menampilkan Semua Akun yang terdaftar di database, request ini hanya bisa dijalankan oleh admin


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/account/all
```



### 2. acount profile (Authenticated))


Request ini untuk menampilkan profile dari user yang mengakses halaman, Hanya bisa diakses oleh User yang sudah terdaftar


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/account/profile
```



### 3. register (permit All)


untuk mendaftarkan akun, dapat diakses oleh siapa saja


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{host}}/account/register
```



***Body:***

```js        
{
    "username" : "zainulAdmin",
    "password" : "admin",
    "role"     : "Admin",
    "firstName": "zainul",
    "lastName" : "Admin",
    "birthDate": "11/11/2011",
    "gender"   : "M"
}
```



### 4. update account (Authenticated)


Request ini untuk melakukan update akun pengguna, hanya dapat diakses oleh Pengguna yang sudah terdaftar


***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{host}}/account/update
```



***Body:***

```js        
{
    "username" : "zainulAdmin",
    "password" : "admin",
    "role"     : "Admin",
    "firstName": "zainul",
    "lastName" : "Admins",
    "birthDate": "11/11/2011",
    "gender"   : "M"
}
```



### 5. Customer Orders(Authenticated))


Request ini untuk menampilkan pesanan Pengguna yang masih dalam tahapan proses, dapat diakses akun yang sudah terdaftar


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/account/order
```



### 6. customerHistory(Authenticated))


Reques ini untuk menampilkan semua pesanan pengguna yang sudah tidak di proses dan atau pesanan yang gagal, dapat diakses akun yang sudah terdaftar


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/account/history
```



### 7. delete


menghapus akun, hanya dapat diakses admin


***Endpoint:***

```bash
Method: DELETE
Type: 
URL: {{host}}/account/delete
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| id | 7 |  |



## ShipVia

Requeest dalam folder ini adalah untuk menampilkan, menambahkan, mengubah dan menghapus sipper



### 1. get all(Permit All)



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/shipper/getAll
```



### 2. get by id(permit all)



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/shipper/getById
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| id | 1 |  |



### 3. insert (admin)



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{host}}/shipper/insert
```



***Body:***

```js        
{
    "companyName" : "Shipper 2",
    "cost" : 10000.0
}
```



### 4. update(admin)



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{host}}/shipper/update
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| id | 1 |  |



***Body:***

```js        
{
    "companyName" : "Shipper pertama",
    "cost" : 15000.0
}
```



## Category



### 1. getAll



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/category/getAll
```



### 2. getById



***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/category/getById/1
```



### 3. Insert



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{host}}/category/insert
```



***Body:***

```js        
{
    "category" : "drink",
    "description" : "All kind of drink"
}
```



### 4. update



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{host}}/category/update
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| id | 2 |  |



***Body:***

```js        
{
    "category" : "Drink",
    "description" : "All kind of Drink"
}
```



## order

Request dalam folder ini unutk menampilkan order, melakukan order, dan konfirmasi order



### 1. find (Admin)


Menampilkan semua order tanpa terkecuali


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{host}}/order/all
```



### 2. order (Authenticated)


Melakukan order yang baru, dapat diakses oleh semua user yang terdaftar


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{host}}/order/new-order
```



***Body:***

```js        
{
    "shipViaId" : 1,
    "products" : [2],
    "quantities": [6]
}
```



### 3. confirm-order (Authenticated)


melakukan konfirmasi order, dapat diakses oleh semua user yang terdaftar


***Endpoint:***

```bash
Method: PUT
Type: 
URL: {{host}}/order/confirm-order
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| id | 1 |  |



---
[Back to top](#shop)


