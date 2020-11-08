##黑名单管理
###一、添加用户手机号
方式：POST

地址：
**/admin/addBlackListByPhone/v1**

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| number | String |是|手机号| 
|startTime|date|是|开始时间|
|endTime|date|是|结束时间|

返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
失败：
```json
{
    "code": 5000,
    "msg": "error",
    "userMsg": "数据已经存在"
}
```



###二、添加黑名单IP
方式：POST

地址：
**/admin/addBlackListByIp/v1**

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| number | String |是|IP地址| 
|startTime|date|是|开始时间|
|endTime|date|是|结束时间|

返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

###三-0、移除黑名单
方式：delete

地址：
**/admin/delBlackListById/v1**

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| id | Long |是|id| 


返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

###三-1、批量移除黑名单
方式：delete

地址：
**/admin/delBlackListByIds/v1**

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| ids | Integer[] |是|id列表| 


返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```


###四、修改黑名单
方式：put

地址：
**/updateBlackListById/v1**

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| id | Long |是|所要修改ID| 
| number | String |是|手机号| 
|startTime|date|是|开始时间|
|endTime|date|是|结束时间|


返回值：

```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
### 五、获取黑名单(IP or Phone)
方法：GET

地址-IP：/admin/getBlackListForIp/v1

地址-Phone：/admin/getBlackListForPhone/v1


参数：

|  参数  |  类型  |  是否必须  |  说明  |
| ---- | ---- | ---- | ---- |
|page|Integer|否|第几页|
|num|Integer|否|每页数量 default = 10|
返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 2,
                "type": 2,
                "number": "127.0.0.1",
                "status": 0,
                "startTime": "2021-11-01 00:00:00",
                "endTime": "2021-11-02 00:00:00",
                "creatTime": "2020-11-08 11:39:14",
                "updateTime": "2020-11-08 12:01:31",
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

### 六、根据number进行模糊查询(IP or Phone)
方法：GET

地址-Phone：/admin/selectBlackListByPhone/v1

地址-Ip：/admin/selectBlackListByIp/v1


参数：

|  参数  |  类型  |  是否必须  |  说明  |
| ---- | ---- | ---- | ---- |
|num|String|是|IP or Phone|
返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 2,
            "type": 2,
            "number": "127.0.0.1",
            "status": 0,
            "startTime": "2021-11-01 00:00:00",
            "endTime": "2021-11-02 00:00:00",
            "creatTime": "2020-11-08 11:39:14",
            "updateTime": "2020-11-08 12:01:31",
            "delFlag": false
        },
        {
            "id": 3,
            "type": 2,
            "number": "123123123",
            "status": 0,
            "startTime": "2020-11-08 17:10:52",
            "endTime": "2020-11-08 17:10:58",
            "delFlag": false
        }
    ]
}
```

error:
```json
{
    "code": 5000,
    "msg": "error",
    "userMsg": "IP不能为空"
}
```


