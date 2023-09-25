U can replace ___localhost___ to ___tonydomain.ddns.net___

# Account

Link: http://localhost:8080/api/accounts/signup

Or: http://localhost:8080/giftapp/api/accounts/signup

## POST:

### Request

Body:

```json
{
	"username": "catuser",
	"password": "123meow"
}
```

### Response

Status code:

+ 400 (Bad Request): Invalid input or username was exist
+ 201 (Created): Successfully

---

Link: http://localhost:8080/api/accounts/signin

Or: http://localhost:8080/giftapp/api/accounts/signin

## POST

### Request

Body:

```json
{
	"username": "catuser",
	"password": "123meow",
    	"accountType": 1
}
```

accountType

+ 1: normal user
+ 0: admin user

### Response

Status code:

+ 400 (Bad Request): Invalid input
+ 200 (OK): Successfully

# Nhóm 15
# Mô tả phiên bản 1.1

# API: AccountInfos
link: http://localhost:8080/api/accountinfos
## POST
<!---
     * 
     * POST
     * Lưu accountInfo vào database
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
     * Use for add acountinfos into database
     * NOTE: "phonenum" don't have zero in beginning 
     * 
-->
link: http://localhost:8080/api/accountinfos
### Request
```
{
    "uname" : "thanhtest2",
    "disname" : "thanhtest2",
    "uaddress" : "daylaaddresss",
    "phonenum" : 919910266,
    "email" : "mailjcungdc"
}
```

### Response
#### Successes
Status: 201
Lưu thành công accountInforService

#### Failure
Status: 404

## PUT
<!---
     * PUT
     * update accountInfo
     * @param uname
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
     * Use for update accountinfos
     * NOTE: "phonenum" don't have zero in beginning 
-->
link: http://localhost:8080/api/accountinfos/thanhtest1
### Request
```
{
    "disname" : "thanhtest3",
    "uaddress" : "daylaaddresss",
    "phonenum" : 919910266,
    "email" : "mailjcungdc"
}
```

### Response
#### Successes
Status:200
AccountInfo đã được update

#### Failure
Status:404

## GET
<!---
    * Lấy tất cả AccountInfos
    * GET
    * Note: API for get all accountinfos
-->
### Request
link: http://localhost:8080/api/accountinfos
### Response
#### Successes
```
[
    {
        "uname": "thanhtest1",
        "disname": "thanhtest",
        "uaddress": "daylaaddress",
        "phonenum": 919910266,
        "email" : "mailjcungdc"
    }
]
```
#### Failure
Status:404

## GET
<!---
    * Lấy một accountInfo
    * GET
    * Note: Use this API for get one accountinfo by using uname
-->
link: http://localhost:8080/api/accountinfos/thanhtest1

### Request

### Response
#### Successes
Status:200
```
{
    "uname": "thanhtest2",
    "disname": "thanhtest2",
    "uaddress": "daylaaddresss",
    "phonenum": 919910266
}
```
#### Failure
Status: 404

## DELETE
<!---
    * Xóa một accountInfo
    * DELETE
    * Using for admin to delete by using uname
-->

link: http://localhost:8080/api/accountinfos/thanhtest1
### Request

### Response
#### Successes:
Status:200
Xóa accountInfo thành công
#### Failure:
Status:404




# API: GOrderAddOnUserRest
<!---
    * Use this API for managing order&addon
-->
link: http://localhost:8080/api/gorderaddon

## POST
<!---
    * POST
    * thêm gorderaddon vào database
    * Note: this method incomplete, you just can add each order & addon
-->
### Request
```
{

    "gorderId": 2,
    "addonId": 2,
    "content":"day la lan thu dau hai"
    
}
```

### Response
#### Successes
Status: 201
Lưu thành công gOrderAddOn

#### Failure
Status: 404

## PUT
<!---
    * UPDATE
    * chỉnh sửa gorderaddon
    * Use can update gorderaddon by using cid of this gOrderAddOns table
    * Note: 2 in below link is cid of gOrderAddOns table
    * gOderId can get by using 2 API below
-->
link: http://localhost:8080/api/gorderaddon/2
### Request
```
{
    "gorderId": 15,
    "addonId": 3,
    "content":"Muon sua thanh addon thu ba"
}
```
### Response
#### Successes
Status: 200
GOrderAddOn update thành công

#### Failure
Status: 400

## GET
<!---
    * GET
    * Lấy tất cả gOrderAddOn
    * Get all gorderaddon in database
    * 
-->
link: http://localhost:8080/api/gorderaddon
### Request

### Response
#### Successes
```
[
    {
        "cid": 1,
        "gorderId": null,
        "addonId": 2,
        "content": "sua cai content khac",
        "aname": null,
        "adescription": null,
        "price": null
    },
    {
        "cid": 2,
        "gorderId": null,
        "addonId": 2,
        "content": "sua cai content khac",
        "aname": null,
        "adescription": null,
        "price": null
    },
    {
        "cid": 3,
        "gorderId": null,
        "addonId": 2,
        "content": "sua cai content khac",
        "aname": null,
        "adescription": null,
        "price": null
    },
    {
        "cid": 5,
        "gorderId": 2,
        "addonId": 2,
        "content": "day la lan thu dau hai",
        "aname": null,
        "adescription": null,
        "price": null
    }
]
```
#### Failure
Status: 404

## GET

<!---
    * GET
    * Lấy một gOrderAddOn
    * Get gorderaddon by using gorderid
    * Note: 15 in below link is gorderid
    *  
-->
link: http://localhost:8080/api/gorderaddon/15
### Request

### Response
#### Successes
Status:200
```
[
    {
        "cid": 17,
        "gorderId": 15,
        "addonId": 1,
        "content": "cái nơ màu xanh",
        "aname": "test1",
        "adescription": "dung de test",
        "price": 10000
    },
    {
        "cid": 18,
        "gorderId": 15,
        "addonId": 5,
        "content": "Muon sua thanh addon thu ba",
        "aname": "fakeAddOn1",
        "adescription": "Đây là addOnFake",
        "price": 30000
    }
]
```
#### Failure
Status: 404

## DELETE
<!---
    * DELETE
    * Xóa một gOrderAddOn
    * You can delete gOrderAddOn by using cid of it
    * Note: 2 in below link is cid of gOrderAddOn id
-->

link: http://localhost:8080/api/gorderaddon/2
### Request

### Response
#### Successes
Status: 200
Ğã xóa thành công addon

#### Failure
Status: 404



# Mô tả phiên bản 2.0

# API: Order

## POST
<!---
    * Using this API for add one order into database
    * You can add so many addon and get content with it
    * Note: it send a mail so you can wait a minute
    * Note: you can choose your date you want us to delivery
-->
link: http://localhost:8080/api/user/order

### Request
```
{
    "uname" : "thanhtest1",
    "giftpackId": 1,
    "receiveAddress" : "123/dada/tp.HCM",
    "phoneNumber" : 919910266,
    "dateArrive" : "23-05-2021",
    "greetingCardContent" : "Happy birthday",
    "addonId": [1,2],
    "content" : ["cái nơ màu xanh", "cái thiệp màu hồng"]
}
```

### Response

#### Success

Status: 200
Đã tạo đơn hàng thành công
Location: /api/user/order/id/16 
#### Failure

Status: 404

## GET
<!---
    * Using this API to getting order by orderid
    * Use can get orderid by watch location in header or it send with a mail for you
    * Note: 16 is orderid
-->
link: http://localhost:8080/api/user/order/id/16

#### Request

#### Response
```
{
    "cid": 16,
    "uname": "thanhtest1",
    "giftpackId": 1,
    "receiveAddress": "123/dada/tp.HCM",
    "phoneNumber": 919910266,
    "dateOrder": "17-05-2021",
    "dateArrive": "23-05-2021",
    "greetingCardContent": "Happy birthday",
    "addonId": [
        1,
        2
    ],
    "content": [
        "cái nơ màu xanh",
        "cái thiệp màu hồng"
    ],
    "status": "Đang xử lý",
    "cost": 140000
}
```

## GET

<!---
    * Using API to getting order by using uname
    * this return a list of order
    * 
-->
link: http://localhost:8080/api/user/order/uname/thanhtest1

### Request

### Response

#### Success

Status: 200
```
[
    {
        "cid": 1,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": [
            1
        ],
        "content": [
            "cái nơ màu xanh"
        ],
        "status": "Đang chờ xử lý",
        "cost": 110000
    },
    {
        "cid": 4,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": null,
        "content": null,
        "status": "Đang chờ xử lý",
        "cost": 100000
    },
    {
        "cid": 12,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": [
            1,
            2
        ],
        "content": [
            "cái nơ màu xanh",
            "cai thiep mau hong"
        ],
        "status": "Đang chờ xử lý",
        "cost": 140000
    },
    {
        "cid": 13,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": [
            2
        ],
        "content": [
            "cai thiep mau hong"
        ],
        "status": "Đang chờ xử lý",
        "cost": 130000
    },
    {
        "cid": 14,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": [
            1,
            2
        ],
        "content": [
            "cái nơ màu xanh",
            "cai thiep mau hong"
        ],
        "status": "Đang chờ xử lý",
        "cost": 140000
    }
]
```

#### Failure
Status: 404

## GET
<!---
    * Get all orders
    * Using API to get all orders from database
    * this return list of orderUsermodel
    * 
-->

link: http://localhost:8080/api/user/order

### Request

### Response
Status: 200
```
[
    {
        "cid": 1,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": [
            1
        ],
        "content": [
            "cái nơ màu xanh"
        ],
        "status": "Đang chờ xử lý",
        "cost": 110000
    },
    {
        "cid": 4,
        "uname": "thanhtest1",
        "giftpackId": 1,
        "receiveAddress": "123/dada/tp.HCM",
        "phoneNumber": 919910266,
        "dateOrder": "17-05-2021",
        "dateArrive": "23-05-2021",
        "greetingCardContent": "Happy birthday",
        "addonId": null,
        "content": null,
        "status": "Đang chờ xử lý",
        "cost": 100000
    }
]
```
### API OrdeProcess

## GET
<!--
Get all OrderProcess from database
-->
link:http://localhost:8080/api/delivery
## Request:
## Response:
Status.200
```
[
    {
        "gorderId": 5,
        "pstate": "Canceled",
        "pdelivered": 1,
        "pcanceled": 1
    },
    {
        "gorderId": 8,
        "pstate": "done",
        "pdelivered": 1,
        "pcanceled": 0
    },
    {
        "gorderId": 10,
        "pstate": "cancel",
        "pdelivered": 0,
        "pcanceled": 1
    }
]
```
## Fail:
 Status.400
## GET
<!--
Get all OrderProcess deliveried
-->
link:http://localhost:8080/api/delivery/Listdeliveried
## Request
## Response
Status.200
```
[
    {
        "gorderId": 5,
        "pstate": "Canceled",
        "pdelivered": 1,
        "pcanceled": 1
    },
    {
        "gorderId": 8,
        "pstate": "done",
        "pdelivered": 1,
        "pcanceled": 0
    }
]
```
## Fail:
 Status.400

## POST
<!--
Add orderProcess.
You can set state of order such as waiting, done.
Addition,you can set pdeliveried and pcanceled by 0. 
-->
link:http://localhost:8080/api/delivery/addOrderProcess
## Request
```
    {
        "gorderId": 5,
        "pstate": "process",
        "pdelivered": 0,
        "pcanceled": 0
    }
```
## Response
Status.200
Add success

## PUT
<!--
Update orderProcess.
You can change state of order such as waiting, done.
Addition,you can set pdeliveried and pcanceled by 0 or 1.
Mention:end of link is orderProcess id. 
-->
link:http://localhost:8080/api/delivery/update/5
## Request
```
    {
        "gorderId": 5,
        "pstate": "done",
        "pdelivered": 1,
        "pcanceled": 0
    }
```
## Response
Status.200
Update success


## DELETE
<!--
Delete using orderID in database. 
Change number in last link to chose orderProcess to delete.
-->
link:http://localhost:8080/api/delivery/delete/5
## Request

## Response
Status.200
Delete success


## API GiftPackComments

## GET
<!--
Get all list Comment in database comments.
-->
link:http://localhost:8080/comments/listComments
## Request

# Response
## Success
Status.200
```
[
    {
        "cid": 4,
        "giftpackId": 2,
        "uname": "catuser",
        "content": "Fail"
    }
]
```
## Fail:
 Status.400

## GET
<!--
Get all list Comment by Uname.
Last link you need to change uname to find.
-->
link:http://localhost:8080/comments/getCommentsByUname/catuser
## Request

# Response
## Success
Status.200
```
[
    {
        "cid": 4,
        "giftpackId": 2,
        "uname": "catuser",
        "content": "Fail"
    }
]
```
## Fail:
 Status.400
## GET
<!--
Get Comment by CommentID.
Note:you change number commentID in last link
 -->
 link: http://localhost:8080/comments/getCommentsBygiftID/1
 ## Request

 ## Response
 ## Success
 Status.200
```
 {
    "cid": 10,
    "giftpackId": 1,
    "uname": "catuser",
    "content": "Failed"
}
```
## Fail:
 Status.400
## POST
<!--
Insert comment.
 -->
 ## Request
```
 {
    "cid": 10,
    "giftpackId": 1,
    "uname": "catuser",
    "content": "Failed"
}
```
## Response
## Success
Add Comment sucess
## Fail:
 Status.400
## PUT
<!--
Update comment.
 -->
 ## Request
```
     {
        "giftpackId": 2,
        "uname": "catuser",
        "content": "Fail"
    }
```
 ## Response
 Status.200
 Update success
## Fail:
 Status.400
 Update unsuccess

## DELETE
<!--
Delete comment by commentID.
Change last number in link to chose comment to delete.
 -->

 link:http://localhost:8080/comments/delete/10
 ## Request
 ## Response

 ## Sucess:
 Status.200
 Delete success
 ## Fail:
 Status.400
Delete unsuccess


## POST
link:http://localhost:8080/comments/resolveComment
## Request:
```
{
    "email":"18110092@student.hcmute.edu.vn",
    "content":"Gift very good",
    "commentResolve":"Thanks you for buy Gift",
    "giftpackId":2

}
```
## Response

## Success
Status.200
Mail sent.
## Fail
Mail unsent

# Mô tả phiên bản 3.0

# API: Items

Link: http://localhost:8080/api/items

## GET

### Response

Status code: 200

```json
[
  {
    "cid": 1,
    "itemName": "keo",
    "itemDescription": "2 bich keo",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 250,
    "activeState": 1
  },
  {
    "cid": 2,
    "itemName": "keo",
    "itemDescription": "2 bich keo",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 200,
    "activeState": 1
  }
]
```

## GET

Link: http://localhost:8080/api/items/1

> Get an item with id (this link for id = 1)

### Response

Status code: 200

```json
{
  "cid": 1,
  "itemName": "keo",
  "itemDescription": "2 bich keo",
  "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
  "price": 250,
  "activeState": 1
}
```

## POST

> Create new item

### Request

```json
{
  "itemName": "gau bong",
  "itemDescription": "1 co gau bong mau tin",
  "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
  "price": 450,
  "activeState": 1
}
```

### Response

Status code: 201

```text
Created new item.
```

## PUT

Link: http://localhost:8080/api/items/1

> Update an item with id (this link for item has id = 1)

### Request

```json
{
  "itemName": "gau bong",
  "itemDescription": "Con gau bong mau tim to dung",
  "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
  "price": 350,
  "activeState": 1
}
```

### Response

Status code: 200

```text
Update item.
```

## GET

Link: http://localhost:8080/api/items/noitemtypes

> Get a number of item's types

### Response

Status code: 200

```json
{
  "numberofitemtypes": 3
}
```

# API: giftpack

Link: http://localhost:8080/api/giftpacks

## GET

> Get all giftpack

### Response

Status code: 200

```json
[
  {
    "cid": 1,
    "gname": "qua valentine",
    "gdescription": "sicola va hoa hong",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 570,
    "averageStar": 0,
    "activeState": 1
  },
  {
    "cid": 10,
    "gname": "qua sinh nhat",
    "gdescription": "banh kem",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 270,
    "averageStar": 0,
    "activeState": 1
  }
]
```

## GET

Link:http://localhost:8080/api/giftpacks/1

> Get a giftpack has id = 1

### Response

Status code: 200

```json
{
  "cid": 1,
  "gname": "qua valentine",
  "gdescription": "sicola va hoa hong",
  "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
  "price": 570,
  "averageStar": 0,
  "activeState": 1
}
```



## POST

> Create new giftpack

### Request

```json
{
    "gname": "qua xin loi",
    "gdescription": "hoa cuc",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 130,
    "averageStar": 0,
    "activeState": 1
}
```

### Response

Status code: 201

```text
Created new giftpack
```

## PUT

Link: http://localhost:8080/api/giftpacks/10

> Update an existed gitfpack has id = 10

### Request

```json
{
    "gname": "qua chia tay",
    "gdescription": "hoa hong trang",
    "thumbnailimg": "https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201810040211",
    "price": 1300,
    "averageStar": 0,
    "activeState": 1
}
```

### Response

Status code: 200

```text
Updated giftpack
```

## GET

Link: http://localhost:8080/api/giftpacks/nogiftpacktypes

> Get the number of giftpack's types

### Response

```json
{
  "numberofgiftpacktypes": 3
}
```

# API: Addon

Link: http://localhost:8080/api/addons

## GET

> Get all of addon

### Response

Status code: 200

```json
[
  {
    "cid": 1,
    "aname": "loi chuc",
    "adescription": "mot loi chuc den nhung ai ...",
    "price": 25
  },
  {
    "cid": 2,
    "aname": "phat nhac",
    "adescription": "O noi dau do nhin ve qua khu cua hai ta lieu em co tiec?",
    "price": 25
  },
  {
    "cid": 3,
    "aname": "choi nhac",
    "adescription": "O noi dau do nhin ve qua khu cua hai ta lieu em co tiec?",
    "price": 25
  }
]
```

## GET

Link: http://localhost:8080/api/addons/1

> Get an addon has id = 1

### Response

Status code: 200

```json
{
  "cid": 1,
  "aname": "loi chuc",
  "adescription": "mot loi chuc den nhung ai ...",
  "price": 25
}
```

## POST

> Create new addon

### Request

```json
{
  "aname": "dance",
  "adescription": "999 doa hoa hong",
  "price": 99
}
```

### Response

Status code: 201

```text
Created new addon
```

## PUT

Link: http://localhost:8080/api/addons/1

> Update addon has id = 1

### Request

```json
{
  "aname": "dance and nu.de",
  "adescription": "999 doa hoa hong, thoi doi",
  "price": 199
}
```

### Response

Status code: 200

```text
Updated addon!
```

## GET

Link: http://localhost:8080/api/addons/noaddontypes

> Get the number of addon's types

### Response

Status code: 200

```json
{
  "noaddontypes": 4
}
```

# API : GiftpackItems

<!---
    One giftpack has many items so this API can manage them.
-->
link: http://localhost:8080/api/giftpackitems
## GET
> Get all giftpackItems

Link : http://localhost:8080/api/giftpackitems

### Request

### Response
```
[
    {
        "cid": 3,
        "giftpackId": 1,
        "itemId": 2
    }
]
```

## GET

> Get giftpacksItem by id=1
link: http://localhost:8080/api/giftpackitems/1
### Request

### Response
```
[
    {
        "cid": 3,
        "giftpackId": 1,
        "itemId": 2
    }
]
```

## POST

> Add one giftpack has many items

### Request
```
{
    "giftpackid": 1,
    "itemid" : [1, 2]
}
```

### Response

Success: 201

## PUT

> Update by id in database of giftpackItems

link: http://localhost:8080/api/giftpackitems/2
### Request
```
{
    "giftpackId": 1,
    "itemId": 2
}
```

### Response

Success: 200

## DELETE

> Delete by id giftpack of giftpackItems

link: http://localhost:8080/api/giftpackitems/2
### Request

### Response

Success: 200
