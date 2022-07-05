# UserBehaviorAnnotationDemo

## 通过自定义注解+aop实现

### 新建项目+导入依赖
初始化一个SpringBoot的新项目

导入相关依赖，包括：
web依赖和aop依赖
```xml
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

### 创建自定义注解
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
    /**
     * 接口名称
     */
    String value() default "";
    /**
     * 用户行为
     */
    HandEnum handle();
    /**
     * 是否将当前日志记录到数据库中
     */
    boolean save() default true;
```

### 定义注解中的枚举值
```java
public enum HandEnum {
    SAVE("SAVE","保存"),
    UPDATE("UPDATE","更新"),
    SELECT("SELECT","查询"),
    DELETE("DELETE","删除");

    private String name;
    private String desc;
    HandEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
```

### aop定义切面
```java
@Aspect
@Component
public class PrintLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintLogAspect.class);

    @Autowired
    HttpServletRequest request;

    @Around(value="@annotation(printLog)")
    public Object aroundMethod(ProceedingJoinPoint point, PrintLog printLog){

        try {
            //获取ip
            String ip = request.getRemoteAddr();
            LOGGER.info(String.format("请求ip=%s,请求接口=%s,用户行为=%s",ip,printLog.value(),printLog.handle().getDesc()));
            Object proceed = point.proceed();
            LOGGER.info(String.format("执行结果为=%s",proceed));
            return proceed;
        } catch (Throwable e) {
            LOGGER.error("发生异常，异常=" + e.getMessage());
        }
        //异常时修改返回结果为1222
        return "1222";
    }
```

### 定义controller
```java
@RestController
public class UserController {

    @RequestMapping("/user/info")
    @PrintLog(value = "/user/info",handle = HandEnum.SELECT)
    public String getUserInfo(String username){
        return "用户"+username+"登录成功";
    }
}
```

### 结果展示
![图片](https://user-images.githubusercontent.com/60306671/177241186-b10a30a8-8bf9-4beb-92f4-0fc2e27640c1.png)

![图片](https://user-images.githubusercontent.com/60306671/177241227-bf1de387-a92f-403d-b8e0-d589afef9b34.png)






