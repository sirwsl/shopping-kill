### 日志接口
### 获取日志
方法：GET

地址：/admin/getAllLoggerByType/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|page|Long|否|第几页|
|num|Long|否|每页条数|
|type|Integer|是|角色：管理员-1 用户-0|

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
                "detail": "添加管理员[className:com.wsl.shoppingkill.serviceImpl.AdminServiceImpl]",
                "manId": 1,
                "name": "ceshi123",
                "type": 1,
                "grade": 3,
                "ip": "0:0:0:0:0:0:0:1",
                "creatTime": "2020-11-09 11:58:46"
            },
            {
                "id": 6,
                "detail": "删除管理员[className:AdminServiceImpl]->[serviceImpladmin]",
                "manId": 1,
                "name": "ceshi123",
                "type": 1,
                "grade": 3,
                "ip": "0:0:0:0:0:0:0:1",
                "creatTime": "2020-11-09 12:50:55"
            }
        ],
        "total": 6,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}

```

