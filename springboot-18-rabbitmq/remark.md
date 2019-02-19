>####rabbitmq的使用

1.rabbitmq功能

  - 队列
  - 发布订阅
  - rpc远程调用
  
2.rabbitmq的消费类型不是根据队列地址绑定 根据exchange交换机类型决定
  
  - direct 根据route key来匹配队列名称放入消息(可能存在多个队列 route key相同 导致放入同样的信息)
  - topic  根据表达式放入
  - fanout 放入到该交换机的所有队列中(相当于发布/订阅模式)
  - header 
  
3.rabbitmq发送/消费数据
   
  - coverAndSend如果是指向了fanout类型的交换机 似乎不需要指定队列名
  - 每个队列在创建时会默认新增一个direct的归属交换机,
  `@RabbitListener(queues = "test-topic-2")`  即可
  