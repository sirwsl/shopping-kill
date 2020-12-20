### 售后
#### 获取售后服务列表
方式：GET 

地址：/admin/getAfterSalesAll/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|否|模糊匹配，售后编号|
|orderId|String|否|模糊匹配，订单编号|
|type|Integer|是|类型|
|result|Boolean|否|是否已经处理，1-已处理 0-未处理|
|current|Long|否|但前页 默认 1|
|size|Long|否|每页个数 默认10|

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
                "orderId": "23124234125",
                "detail": "申请换货啦啦啦啦啦啦啦啦",
                "type": 1,
                "result": false,
                "userId": 1,
                "userName": "sirwsl",
                "userPhone": "18888888888",
                "userNickName": "测试昵称",
                "skuId": 2,
                "skuDetail": "128G 4G内存 白色",
                "num": 1,
                "payPrice": 100.00,
                "goodsId": 1,
                "goodsName": "手机",
                "skuList": [
                    {
                        "id": 1,
                        "attribute": "128G 6G内存 红色",
                        "num": 1000
                    },
                    {
                        "id": 2,
                        "attribute": "128G 4G内存 白色",
                        "num": 1000
                    },
                    {
                        "id": 3,
                        "attribute": "128G 6G内存 黑色色",
                        "num": 658
                    }
                ]
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

#### 售后服务处理
方式：PUT

地址：/admin/refundGoodsAndMoney/v1

参数：

|参数|类型|是否必须|说明|
|---|---|---|---|
|id|Long|是|售后工单编号|
|oldSkuId|String|是|原有SkuID|
|resultDetail|String|是|处理结果|
|result|Boolean|是|是否已经处理|
|type|Integer|是|3-退货退钱 2-换货 1-仅退钱|
|skuId|String|换货必须|换货的SkuId|

返回值：
```json
{
    "code": 0,
    "msg": "SUCCESS",
    "userMsg": "操作成功",
    "data": "售后问题处理成功"
}
```
error:
```json
{
    "code": 5000,
    "msg": "error",
    "userMsg": "售后工单处理失败,请重新查看是否已经处理或内容是否符合规范"
}
```