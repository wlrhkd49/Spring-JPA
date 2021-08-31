# **Spring JPA**

## spring JPQL 사용

### 찾은 내용 중 상위 몇가지의 튜플만 가지고 오고 싶은 경우
```java
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findTop개수();

}
```
`findTop개수`를 통하여 원하는 개수 만큼의 Entity를 가져올 수 있다.

### 정렬을 한 이후 원하는 개수 만큼의 Entity를 가지고오고 싶은 경우
```java
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findTop개수ByOrderBy속성명Desc();

}
```

`findTop개수ByOrderBy속성명`을 통해 OrderBy뒤의 **속성명**으로 순서대로 정렬하고 찾은 엔티티 중 **개수**만큼의 엔티티를 가져올 수 있다.

그리고 **속성명**으로 정렬시 **내림차순**은 **Desc**를 붙이고 **오름차순**은 **Asc**를 붙이면 된다.
