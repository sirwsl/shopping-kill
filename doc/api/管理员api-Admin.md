### 管理员管理


#### 获取管理员列表
方式：GET

地址：/admin/getAdminList/v1

参数：null


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 37,
            "name": "ceshi123",
            "password": "12356789",
            "sex": "男",
            "idCard": "138543518343884464",
            "phone": "18314263373",
            "address": "测试家庭住址",
            "creatTime": "2020-11-09 11:58:46",
            "updateTime": "2020-11-09 12:42:12",
            "delFlag": false
        },
        {
            "id": 38,
            "name": "ceshi124",
            "password": "123",
            "sex": "男",
            "idCard": "138543518343884464",
            "phone": "18314263373",
            "address": "测试家庭住址",
            "creatTime": "2020-11-09 12:26:58",
            "updateTime": "2020-11-09 12:26:58",
            "delFlag": false
        }
    ]
}
```

#### 获取当前登录管理员
方式：GET

地址：/admin/getAdmin/v1

参数：null


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "id": 1,
        "name": "ceshi123",
        "password": "12356789",
        "sex": "男",
        "idCard": "138543518343884464",
        "phone": "18314263373",
        "address": "测试家庭住址",
        "creatTime": "2020-11-09 11:58:46",
        "updateTime": "2020-11-09 12:42:12",
        "delFlag": false
    }
}
```
error
```json
{
    "code": 5000,
    "msg": "error",
    "userMsg": "当前登录id异常"
}
```


#### 添加管理员
方式：POST

地址：/admin/addAdmin/v1

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
| name | String |是|账号| 
|password|String|是|密码|
|sex|enum|是|MAN or WOMAN|
|idCard|String|是|身份证号|
|phone|String|是|手机号|
|address|String|是|地址|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
error
```json
{
    "code": 5000,
    "msg": "org.springframework.dao.DuplicateKeyException",
    "userMsg": "服务器开小差了"
}
```

#### 修改管理员
方式：PUT

地址：/admin/updateAdmin/v1

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
|id|Long| 是 | 管理员id|
| name | String |是|账号| 
|password|String|是|密码|
|sex|enum|是|MAN or WOMAN|
|idCard|String|是|身份证号|
|phone|String|是|手机号|
|address|String|是|地址|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
error
```json
{
    "code": 5000,
    "msg": "org.springframework.dao.DuplicateKeyException",
    "userMsg": "服务器开小差了"
}
```



#### 删除管理员
方式：DELETE

地址：/admin/delAdmin/v1

参数：

|  参数  |  类型  |  是否必须  |  说明  |
|  ----  |  ----  |  ----  | ----  |
|id|Long| 是 | 管理员id|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```
false:
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

