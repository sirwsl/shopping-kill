### 顾客管理
#### 获取所有顾客
方式：GET

地址：/admin/getUserAll/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|current|Integer|否|当前页，默认第一页|
|size|Integer|否|每页大小默认10|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 1,
                "name": "sir",
                "password": "asf",
                "nickName": "测试昵称",
                "img": "img/ser/img",
                "sex": "0",
                "phone": "18888888888",
                "email": "sir@163.com",
                "idCard": "53292312303023421916",
                "realName": "sirwsl",
                "weChat": "59awf",
                "apply": "48asd",
                "creatTime": "2020-11-17 09:31:14",
                "updateTime": "2020-11-17 10:37:23",
                "delFlag": false
            },
            {
                "id": 2,
                "name": "测试姓名",
                "password": "123456",
                "nickName": "18453",
                "img": "img/ser/img",
                "sex": "0",
                "phone": "18888888888",
                "email": "sir@163.com",
                "idCard": "53292312303023421916",
                "realName": "sirwsl",
                "weChat": "59f",
                "apply": "48asd",
                "creatTime": "2020-11-17 09:31:14",
                "updateTime": "2020-11-17 09:31:17",
                "delFlag": false
            }
        ],
        "total": 10,
        "size": 2,
        "current": 1,
        "searchCount": true,
        "pages": 5
    }
}
```
#### 更新某个用户基本信息
方式：PUT

地址：/admin/updateUserInfo/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|用户id|
|name|String|是|用户名|
|phone|String|是|用户手机号|
|password|String|是|用户密码|
|nickName|String|否|用户昵称|
|img|file|否|用户头像|
|email|String|否|用户邮箱|
|idCard|String|否|用户身份证号|
|realName|String|否|用户真实姓名|
|weChat|String|否|微信|
|apply|String|否|支付宝|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

#### 删除某个用户
方式：DELETE

地址：/admin/delUser/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|用户id|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

#### 将用户加入黑名单
方式：POST

地址：/addBlackListById/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|用户id|
|beginTime|LocalDateTime|是|开始时间|
|endTime|LocalDateTime|是|结束时间|

返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
