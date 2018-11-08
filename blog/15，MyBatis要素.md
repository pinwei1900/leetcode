- SqlSessionFactoryBuilder（构造器）：根据配置信息xml生成session工厂

  构造成功之后直接丢弃

- SqlSessionFactory（工厂接口）

  全局唯一的保留一个工厂，便于管理

- SqlSession（会话）：执行对象

  每次需要执行的时候，需要由工厂生成一个session，这个session应该在任务完成之后被关闭

- Sql Mapper：由Java接口以及xml文件构成

  只在每次会话总存在，当直接结束之后会可以被回收了

 **MyBatis配置**

  properties：定义变量

  settings：用来设置Mybatis相关属性配置，和具体业务无关

  typeAliases：用来添加扫描自定义的对象类型，方便使用，可以通过包扫描

  typeHandlers：过程需要实现set和get方法，前者用来转换对象写入到数据库，后者用来读取数据库到对象

  objectFactory：用来在结果返回的时候构造对象的工厂方法

  plugins：暂不讨论

  environments：配置环境用来注册数据源，每一个数据源都有：1，数据库配置信息  2，数据库事务配置

  databaseIdProvider：数据库厂商标识号，如果在具体的sql语句的属性中配置了databaseId，那么如果有两个相同的sql语句，会执行当前启用的数据库对应的sql语句

  mappers：映射器



**映射器**

自动映射：如果返回的sql列名和JavaBean的属性一致，会自动回填这些字段

传递多个参数：1，可以使用传递Map对象；2，可以使用注解@Param；3，使用传递JavaBean

主键回填：在插入数据之后往往需要获取刚刚插入记录的主键，可以使用KeyProperty声明数据库主键，同时使用useGeneratedKeys告诉Mybatis是否使用数据库内置策略生成主键。

自定义主键插入：可以使用selectKey自定义主键生成规则，比如要求如果table没有记录就设置id=1，有记录就去最大值+2作为新的主键

sql元素：可以用来定义sql语句的组成部分，组成部分还可以存在参数

级联：

​	一对一级联使用association关联取出一对一的关系

​	一对多级联使用collection获取一对多级联

​	discriminator鉴别器级联：可以通过属性的不同返回不同的结果对象

> 注意：这里的级联操作，每增加一个级联都会多一次sql语句的操作，所以会引起N+1性能问题

级联性能N+1：每增加一个级联就会增加一次数据库的查询操作；Mybatis引入了延迟加载功能，一开始并不取出级联数据，只有在取数据的时候才会发送sql去取数据。配套的应该修改mybatis的settings配置中的全局参数。

另一种级联：在查询的时候将所有的数据一次性的查询出来，然后直接装配起来，这就避免了N+1操作。

缓存：默认情况下，mybatis开启一级缓存，也就是说，对于同一个session，调用两次sql方法，实际上只会执行一次查询。如果需要跨越session，使得缓存对所有session可用，就需要开启二级缓存，二级缓存是在SqlSessionFactory层面所共享的。如果需要实现自定义缓存，需要实现Mybatis为我们提供的Cache接口，然后配置到Cache上面（mapper文件中）



动态sql

if ，choose，when，otherwise，foreach，trim，set，test，bind