package com.yunjy.jianyou.page.fragment.order;

/**
 * Created by zt on 2017/10/20.
 */

public enum OrderStatusEnum {
    all(1,"全部"),
    receive(2,"已取货"),
    unreceive(3,"待取货"),
    evaluate(4,"待评价"),
    afterSale(5,"退款/售后");


    public int orderStatus;
    public  String describe;

    OrderStatusEnum(int orderStatus, String describe) {
        this.orderStatus = orderStatus;
        this.describe = describe;
    }

    public  static String getDescribe(int orderStatus){

        OrderStatusEnum[] values = values();

        for (int x = 0; x< values.length ;x ++){

            if(values[x].orderStatus == orderStatus){
                return values[x].describe;
            };

        }
        return "未知";
    }

}
