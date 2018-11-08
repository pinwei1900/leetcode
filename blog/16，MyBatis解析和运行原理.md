#### 构建SqlFactory

读取配置文件组装Configuration，再通过Configuration构建SqlSessionFactory

**反射技术**

通过类的名称通过Class.forName(xxx).newInstance()获取新的实例，通过xxx.getClass(yyy).getMethod()获取该对象的具体方法，最后调用yyy.invoke()执行方法。

**JDK动态代理**

是通过JDK的java.lang.reflect.*包提供支持的，编写步骤：1，编写服务类和接口 2，编写代理类

Proxy.newProxyInstance(classLoader,target.getclass().getInterfaces(),this)返回对象的接口对象，使用这样的方法实现动态绑定最大的缺点是需要实现接口

**CGLIB动态代理**

通过直接调用原生类生成代理类，在调用代理类的方法过程中，实现此类的方法实现动态代理

```Java
	@Override
	public Object intercept(Object obj, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
		Object ret = null;
		String name = method.getName();
		if (name.equals("getRandomInt")) {
			ret = methodProxy.invokeSuper(obj, params); 
			System.out.println("Print random int: " + ret);
		} else if (name.equals("printClassInfo")) { 
			System.out.println("Class: " + obj.getClass());
		}
		BaseGen baseGen = (BaseGen) obj;
		baseGen.calledMethods.add(name); 
		return ret;
	}
```

**映射器的内部组成**

一个Mapper最重要的部分：1，MappedStatement，每一个映射的节点（增删改查）2，SqlSource，根据参数和规则组装Sql的接口 3，BoundSql，建立sql和参数地方，分别包含parameterObject（参数本身），parameterMapping（关于参数本身的属性，名称，typehandler等信息），sql（书写在mapper中的一条sql语句）



#### SqlSession运行过程

映射器的XML文件的命名空间对应的是接口的全路径，根据全路径和方法明就可以绑定具体的接口，通过动态代理让接口可以使用，然后采用命令模式， 使用SqlSession接口的方法使得它能够执行查询。

Mapper的执行过程是通过Executor（执行器，调度接下来的对象），StatementHandler（执行操作），ParameterHandler（参数处理），ResultHandler（返回结果处理）来完成数据的操作和结果返回的

Executor存在三种执行器，分别是simple（默认），reuse（重用预处理），batch（批量专用），Mybatis会根据配置类型确定是三种执行器的哪一种，创建成功之后会加载所有插件

Mybatis根据Configuration创建StatementHandler，对Sql编译并对参数进行初始化，然后执行查询，ResultHandler再组装查询结果返回给调用者完成最后的查询。



#### 插件

要在Mybatis中使用插件，就必须实现接口Interceptor，有3个方法：

​	intercept：插件的核心方法

​	plugin：给被拦截的对象生成一个代理对象

​	setProperties：允许在plugin元素中配置所需要的参数

插件在Configuration中添加，在执行过程中被调用，它是使用的责任链模式，在添加过程中，每添加一次plugin都会生成一个代理对象。

- 确定需要拦截的对象：Executor，StatementHandler，ParameterHandler，ResultSetHandler，都是可以拦截的对象，但是需要了解此插件需要用到的地方，应该具体拦截的对象
- 确定拦截的方法和参数：对于每一个对象来说都有多个可以拦截的方法，所有需要对此有所确定
- 实现拦截方法，直接根据需求实现Interceptor接口

虽然插件可以帮助开发者实现功能，但是还是尽量的不要使用插件，因为会修改Mybatis的实现底层，很可能出现错误，编写插件也非常考验开发者对Mybatis底层实现的理解，一定要谨慎操作。



#### 分表

如果需要通过年份分表，可以在传入的时候传递年份，然后使用Sql组合起来，但是具有比较危险的地方，如果传递了一个不存在的年份，则会导致sql报错，当然也可以通过日期进行分表。

#### 分页

Mybatis具有分页功能，RowBound类，但是它会在一条Sql中查询出所有的结果，然后从第几条到第几条取出数据返回，如果有很多数据，毫无疑问容易造成内存溢出。

插件实现：从对象中获取当前需要执行的Sql，将其改写为分页的Sql，然后回填到对象中，改写时会添加两个分页参数，页码和页大小，从而实现改写Sql实现分页的功能。

#### 上传文件

在上传文件到服务器中的时候，一般会在数据库中保存文件的地址，一定要先上传完成之后再写数据库，也不要在事务中上传文件，否则可能会出现数据库连接的性能问题。











