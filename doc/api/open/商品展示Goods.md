### 商品展示api

#### 获取推荐商品
方式：GET

接口：/api/getRecommendedGoods/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|size|Integer|否|获取条数|


返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 7,
            "name": "123",
            "maxPrice": 99.00,
            "imgUrl": "http://static.wslhome.top/goods/eaa76633-baeb-4382-9c7f-4ca6d54d6018.jpg"
        },
        {
            "id": 2,
            "name": "手机",
            "maxPrice": 8766.00,
            "imgUrl": "http://static.wslhome.top/advertise/timg.jpg"
        },
        {
            "id": 5,
            "name": "test1",
            "maxPrice": 1695.00,
            "imgUrl": "http://static.wslhome.top/advertise/elec1.jpg"
        },
        {
            "id": 6,
            "name": "电灯泡",
            "maxPrice": 8766.00,
            "imgUrl": "http://static.wslhome.top/goods/067c984c-163a-4f52-86aa-38603f6a0641.jpg"
        }
    ]
}
```


#### 获取商品展示接口
方式：GET

接口：/api/getAllGoods/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|name|String|否|商品名称|
|current|Long|否|页数 1|


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
                "name": "手机",
                "minPrice": 8766.00,
                "imgUrl": "http://static.wslhome.top/advertise/timg.jpg",
                "number": 3,
                "skuImgUrl": [
                    "http://static.wslhome.top/advertise/login-background.jpg?x-oss-process=image/resize,m_fill,h_200,w_200",
                    "http://static.wslhome.top/advertise/p1.jpg?x-oss-process=image/resize,m_fill,h_200,w_200",
                    "http://static.wslhome.top/advertise/vip_shop4.png?x-oss-process=image/resize,m_fill,h_200,w_200"
                ]
            },
            {
                "id": 3,
                "name": "充电宝",
                "minPrice": 99.00,
                "imgUrl": "http://static.wslhome.top/advertise/commend1.jpg",
                "number": 2,
                "skuImgUrl": [
                    "http://static.wslhome.top/advertise/vip_shop3.png?x-oss-process=image/resize,m_fill,h_200,w_200",
                    "http://static.wslhome.top/advertise/vip_shop2.png?x-oss-process=image/resize,m_fill,h_200,w_200"
                ]
            }
        ],
        "total": 6,
        "size": 20,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```


#### 获取即将开始的活动（未来5小时）
方式：GET

接口：/api/getKillGoods/v1

参数：无

返回值:
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [
        {
            "id": 1,
            "name": "手机",
            "minPrice": 88.00,
            "maxPrice": 88.00,
            "imgUrl": "http://static.wslhome.top/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg",
            "startTime": "19:00:00"
        },
        {
            "id": 1,
            "name": "手机",
            "minPrice": 38.00,
            "maxPrice": 38.00,
            "imgUrl": "http://static.wslhome.top/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg",
            "startTime": "20:00:00"
        }
    ]
}
```


#### 获取正在进行的活动
方式：GET

接口：/api/getActivityDoing/v1

参数：无

返回值:
```json

{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": [{
            "id": 1,
            "name": "手机",
            "minPrice": 38.00,
            "maxPrice": 38.00,
            "imgUrl": "http://static.wslhome.top/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg",
            "startTime": "2020-12-25 20:00:00",
            "endTime": "2020-12-31 15:00:00",
            "sum": 88
        },
        {
            "id": 1,
            "name": "手机",
            "minPrice": 38.00,
            "maxPrice": 38.00,
            "imgUrl": "http://static.wslhome.top/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg",
            "startTime": "2020-12-27 16:00:00",
            "endTime": "2020-12-28 08:00:00",
            "sum": 87
        }
    ]
}
```
