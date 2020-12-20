##订阅者

###获取订阅者 短信
方式：GET

地址：/admin/getSubscriberBySms/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|current|Integer|否|默认第一页|
|size|Integer|否|默认10|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "number": "18888888888",
                "type": 1,
                "status": 0,
                "delFlag": false
            }
        ],
        "total": 1,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```

###获取订阅者 短信
方式：GET

地址：/admin/getSubscriberByEmail/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|current|Integer|否|默认第一页|
|size|Integer|否|默认10|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "number": "sir@163.com",
                "type": 1,
                "status": 0,
                "delFlag": false
            }
        ],
        "total": 1,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```

### 添加订阅者
方式：POST

地址：/admin/addSubscriber/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|number|String|是|订阅号|
|type|Integer|是|类型 1-手机号 0-邮箱|


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

### 删除订阅者
方式：delete

地址：/admin/admin/delSubscriber/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|number|String|是|订阅号|


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```




