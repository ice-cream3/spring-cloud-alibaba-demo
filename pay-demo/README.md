### 简单的restTemplate调用事例
>定义TemplateConfig配置类,在PayController中注入,调用order-demo的服务方法:

``
restTemplate.getForObject("http://127.0.0.1:8081/order/get", String.class);
``
![fire smile](../fire_smile.gif)