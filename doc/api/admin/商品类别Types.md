### 商品类别
#### 添加商品类别
方式：POST

地址：/admin/addTypes/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|name|String|是|名称|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

#### 根据id删除商品类别
方式：DELETE

地址：admin/delTypes/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|id|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

#### 更新某个商品类别
方式：PUT

地址：/admin/updateTypes/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Integer|是||
|name|String|是|更改后名称|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

#### 获取全部商品类别
方式：GET

地址：/admin/getTypesAll/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|current|Long|否|默认1|
|size|Long|否|默认10|

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
                "name": "456789",
                "creatTime": "2020-11-18 17:13:50",
                "updateTime": "2020-11-18 17:35:38",
                "delFlag": false
            },
            {
                "id": 3,
                "name": "测试分类电脑2",
                "creatTime": "2020-11-18 17:13:54",
                "updateTime": "2020-11-18 17:13:54",
                "delFlag": false
            },
            {
                "id": 4,
                "name": "测试分类电脑6",
                "creatTime": "2020-11-18 17:13:58",
                "updateTime": "2020-11-18 17:13:58",
                "delFlag": false
            },
            {
                "id": 5,
                "name": "123",
                "creatTime": "2020-11-18 17:22:42",
                "updateTime": "2020-11-18 17:22:42",
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



#### 根据name,id模糊查找商品类别
方式：GET

地址：/admin/getTypesByName/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|name|String|是|模糊查找name|
|current|Long|否|默认1|
|size|Long|否|默认10|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 3,
                "name": "测试分类电脑2",
                "creatTime": "2020-11-18 17:13:54",
                "updateTime": "2020-11-18 17:13:54",
                "delFlag": false
            },
            {
                "id": 4,
                "name": "测试分类电脑6",
                "creatTime": "2020-11-18 17:13:58",
                "updateTime": "2020-11-18 17:13:58",
                "delFlag": false
            }
        ],
        "total": 2,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```
