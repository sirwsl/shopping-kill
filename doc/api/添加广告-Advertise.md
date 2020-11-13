###广告api

####添加一条广告
方式：POST

接口：/admin/addAdvertise/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|file|file|是|广告图片|
|targetUrl|String|是|图片地址|
|startTime|Date|否|默认当前时间|
|endTime|Date|否|默认7天结束|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```

####修改一条广告
方式：PUT

接口：/admin/updateAdvertise/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|id|
|file|file|否|广告图片|
|targetUrl|String|否|图片目标地址|
|startTime|Date|否|默认当前时间|
|endTime|Date|否|默认7天结束|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": "添加成功"
}
```


####删除一条广告
方式：DELETE

接口：/admin/delAdvertise/v1

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
    "data": "添加成功"
}
```


####前端获取广告进行展示
方式：GET

接口：/admin/getAdvertiseForView/v1

参数：


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 2,
            "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
            "targetUrl": "https:,https://"
        },
        {
            "id": 3,
            "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
            "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view"
        }
    ]
}
```


####前端获取广告进行展示
方式：GET

接口：/admin/getAdvertiseForView/v1

参数：


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 2,
            "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
            "targetUrl": "https:,https://"
        },
        {
            "id": 3,
            "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
            "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view"
        }
    ]
}
```


####获取所有广告
方式：GET

接口：/admin/getAdvertiseAll/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|page|Long|否|第几页|
|size|Long|否|每页几条|

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
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https:,https://",
                "startTime": "2020-11-09 23:45:45",
                "endTime": "2020-11-16 23:45:45",
                "creatTime": "2020-11-09 23:45:45",
                "updateTime": "2020-11-09 23:56:23",
                "delFlag": false
            },
            {
                "id": 3,
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view",
                "startTime": "2020-11-09 23:45:49",
                "endTime": "2020-11-16 23:45:49",
                "creatTime": "2020-11-09 23:45:49",
                "updateTime": "2020-11-09 23:45:49",
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



####获取未开始广告
方式：GET

接口：/admin/getAdvertiseBegin/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|page|Long|否|第几页|
|size|Long|否|每页几条|

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
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https:,https://",
                "startTime": "2020-11-09 23:45:45",
                "endTime": "2020-11-16 23:45:45",
                "creatTime": "2020-11-09 23:45:45",
                "updateTime": "2020-11-09 23:56:23",
                "delFlag": false
            },
            {
                "id": 3,
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view",
                "startTime": "2020-11-09 23:45:49",
                "endTime": "2020-11-16 23:45:49",
                "creatTime": "2020-11-09 23:45:49",
                "updateTime": "2020-11-09 23:45:49",
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
数据为空：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [],
        "total": 0,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 0
    }
}
```


####获取进行中广告
方式：GET

接口：/admin/getAdvertiseDoing/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|page|Long|否|第几页|
|size|Long|否|每页几条|

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
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https:,https://",
                "startTime": "2020-11-09 23:45:45",
                "endTime": "2020-11-16 23:45:45",
                "creatTime": "2020-11-09 23:45:45",
                "updateTime": "2020-11-09 23:56:23",
                "delFlag": false
            },
            {
                "id": 3,
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view",
                "startTime": "2020-11-09 23:45:49",
                "endTime": "2020-11-16 23:45:49",
                "creatTime": "2020-11-09 23:45:49",
                "updateTime": "2020-11-09 23:45:49",
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
数据为空：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [],
        "total": 0,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 0
    }
}
```

####获取已结束广告
方式：GET

接口：/admin/getAdvertiseOver/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|page|Long|否|第几页|
|size|Long|否|每页几条|

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
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https:,https://",
                "startTime": "2020-11-09 23:45:45",
                "endTime": "2020-11-16 23:45:45",
                "creatTime": "2020-11-09 23:45:45",
                "updateTime": "2020-11-09 23:56:23",
                "delFlag": false
            },
            {
                "id": 3,
                "imgUrl": "/img/sadah237hfas872fh384.jpg,/img/reisajf2903fjq0jfsd09fj0q9sjf082jcsd.jpg",
                "targetUrl": "https://www.www.top/view,https://www.wslhome.top/view",
                "startTime": "2020-11-09 23:45:49",
                "endTime": "2020-11-16 23:45:49",
                "creatTime": "2020-11-09 23:45:49",
                "updateTime": "2020-11-09 23:45:49",
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
数据为空：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [],
        "total": 0,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 0
    }
}
```







