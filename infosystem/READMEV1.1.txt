

#API: AccountInfos
link: http://localhost:8080/api/accountinfos
/**
     * 
     * POST
     * Lưu accountInfo vào database
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
*/
link: http://localhost:8080/api/accountinfos
### Request
```
{
    "uname" : "thanhtest2",
    "disname" : "thanhtest2",
    "uaddress" : "daylaaddresss",
    "phonenum" : 919910266
}
```

###Response
#Successes
Status: 201
Lưu thành công accountInforService

#Failure
Status: 404

/**
     * PUT
     * update accountInfo
     * @param uname
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
*/
link: http://localhost:8080/api/accountinfos/thanhtest1
###Request
```
{
    "disname" : "thanhtest3",
    "uaddress" : "daylaaddresss",
    "phonenum" : 919910266
}
```

###Response
#Successes
Status:200
AccountInfo đã được update

#Failure
Status:404
/**
    Lấy tất cả AccountInfos
    GET
*/
###Request
link: http://localhost:8080/api/accountinfos
###Response
#Successes
[
    {
        "uname": "thanhtest1",
        "disname": "thanhtest",
        "uaddress": "daylaaddress",
        "phonenum": 919910266
    }
]
#Failure
Status:404

/**
    Lấy một accountInfo
    GET
*/
link: http://localhost:8080/api/accountinfos/thanhtest1

###Request

###Response
#Successes
Status:200
{
    "uname": "thanhtest2",
    "disname": "thanhtest2",
    "uaddress": "daylaaddresss",
    "phonenum": 919910266
}
#Failure
Status: 404

/**
    Xóa một accountInfo
    DELETE
*/

link: http://localhost:8080/api/accountinfos/thanhtest1
###Request

###Response
#Successes:
Status:200
Xóa accountInfo thành công
#Failure:
Status:404




#API: GOrderAddOnUserRest
link: http://localhost:8080/api/gorderaddon
/**
    POST
    thêm gorderaddon vào database
*/
###Request
```
{

    "gorderId": 2,
    "addonId": 2,
    "content":"day la lan thu dau hai"
    
}
```

###Response
#Successes
Status: 201
Lưu thành công gOrderAddOn

#Failure
Status: 404

/**
    UPDATE
    chỉnh sửa gorderaddon
*/
link: http://localhost:8080/api/gorderaddon/2
###Request
{
    "addonId": 3,
    "content":"Muon sua thanh addon thu ba"
}
###Response
#Successes
Status: 200
GOrderAddOn update thành công

#Failure
Status: 400

/**
    GET
    Lấy tất cả gOrderAddOn
*/
link: http://localhost:8080/api/gorderaddon
###Request

###Response
#Successes
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

#Failure
Status: 404

/**
    GET
    Lấy một gOrderAddOn
*/
link: http://localhost:8080/api/gorderaddon/2
###Request

###Response
#Successes
Status:200
{
    "cid": 5,
    "gorderId": 2,
    "addonId": 3,
    "content": "Muon sua thanh addon thu ba",
    "aname": "test1",
    "adescription": "dung de test",
    "price": 10000
}
#Failure
Status: 404

/**
    DELETE
    Xóa một gOrderAddOn
*/

link: http://localhost:8080/api/gorderaddon/2
###Request

###Response
#Successes
Status: 200
Đã xóa thành công addon

#Failure
Status: 404