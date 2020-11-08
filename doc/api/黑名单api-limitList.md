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

###三、移除黑名单
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


