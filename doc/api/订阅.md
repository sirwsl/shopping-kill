## 订阅
涉及到mq，类有：SubscriptionHistoryService、ConsumerSubscription

### 发布订阅
方式：POST

地址：/admin/newsPush/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|title|String|是|标题|
|detail|String|是|内容|
|type|Integer|是|0-邮件 1-手机号|
PS：手机短信推送，标题，内容必须小于12字，内容为时间

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功"
}
```

### 获取订阅历史
方式：GET
地址：/admin/getAllSubscription/v1

参数：

|参数|类型|是否必须|说明|
|---|----|---|---|
|current|Integer|否|默认第一页|
|size|Integer|否|默认10条|

返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 4,
                "title": "而无法华为啊否和",
                "detail": "沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨",
                "type": 0,
                "adminId": 0,
                "realFlag": false,
                "creatTime": "2020-11-16 14:39:26",
                "updateTime": "2020-11-16 14:39:29",
                "delFlag": false
            },
            {
                "id": 3,
                "title": "而无法华为啊否和",
                "detail": "沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨",
                "type": 0,
                "adminId": 0,
                "realFlag": false,
                "creatTime": "2020-11-16 14:39:26",
                "updateTime": "2020-11-16 14:39:29",
                "delFlag": false
            }
        ],
        "total": 4,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```


### 按照时间获取订阅历史
方式：GET
地址：/admin/getAllSubscription/v1

参数：

|参数|类型|是否必须|说明|
|---|----|---|---|
|beginTime|LocalDateTime|是|2020-11-11 00:00:00|
|endTime|LocalDateTime|是|2020-11-11 00:00:00|
|current|Integer|否|默认第一页|
|size|Integer|否|默认10条|

返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 4,
                "title": "而无法华为啊否和",
                "detail": "沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨",
                "type": 0,
                "adminId": 0,
                "realFlag": false,
                "creatTime": "2020-11-16 14:39:26",
                "updateTime": "2020-11-16 14:39:29",
                "delFlag": false
            },
            {
                "id": 3,
                "title": "而无法华为啊否和",
                "detail": "沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨",
                "type": 0,
                "adminId": 0,
                "realFlag": false,
                "creatTime": "2020-11-16 14:39:26",
                "updateTime": "2020-11-16 14:39:29",
                "delFlag": false
            }
        ],
        "total": 4,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```

