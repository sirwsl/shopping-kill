### SKU库存管理
#### 获取全部SKU列表
方式：GET

路径：/admin/getSkuAll/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|否|id模糊查询|
|name|String|否|物品名称模糊查询|
|current|Long|否|当前默认第一页|
|size|Long|否|当前默认10|

放回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "records": [
            {
                "id": 2,
                "goodsId": 1,
                "goodsName": "测试手机",
                "attribute": "128G 4G内存 白色",
                "imgUrl": "upload/imigsdg/img.jpeg",
                "realPrice": 1256.00,
                "costPrice": 1200.00,
                "sellPrice": 1695.00,
                "num": 1000,
                "warnNum": 20,
                "expPrice": 10.00
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

#### 获取指定Id的sku
方式：GET

路径：/admin/getSkuVoById/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|SkuId|

放回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": {
        "id": 1,
        "goodsId": 1,
        "goodsName": "手机",
        "attribute": "128G 6G内存 红色",
        "imgUrl": "upload/imigsdg/img.jpeg",
        "realPrice": 1256.00,
        "costPrice": 1200.00,
        "sellPrice": 1695.00,
        "num": 10000,
        "warnNum": 20,
        "expPrice": 10.00
    }
}
```


#### 根据id更新指定SKU
方式:POST

路径：/updateSkuInfo/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|skuId|
|goodsId|Long|是|goodsId|
|goodsName|String|是|物品名称|
|attribute|String|是|sku属性|
|imgUrl|String|二选一|图片连接|
|img|MultipartFile|二选一|图片地址|
|realPrice|BigDecimal|是|真实价格|
|costPrice|BigDecimal|是|成本价格|
|sellPrice|BigDecimal|是|出售价格|
|num|Integer|是|库存|
|warnNum|Intger|是|报警量|
|expPrice|BigDecimal|是|邮费|

放回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```


#### 根据id删除SKU
方式：DELETE

路径：/admin/delSkuById/v1?id=1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|SkuId|

放回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": true
}
```