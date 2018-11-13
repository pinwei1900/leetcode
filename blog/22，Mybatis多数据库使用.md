- 使用接口还是使用自定义的类

```java
public UserEntity getOne(Long id) {
    try (SqlSession session = sqlSessionFactory.openSession()) {
        return session.selectOne("com.neo.mapper.test1.User1Mapper.getOne", id);
    }
}
```

或者

```java
List<UserEntity> getAll();
```

官方推荐使用Mapper