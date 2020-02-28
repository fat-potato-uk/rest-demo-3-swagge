### Challenge 11

As all good software engineers know, documentation is the most fun part of development.
For this exercise, we are going to add swagger to our "Restful" service we wrote in challenges
2-7.

If you have not completed all these, then checkout [Challenge 7](https://github.com/fat-potato-uk/rest-demo-2f.git).

There are a few ways we can use Swagger. For this example we will be using the Swagger
native annotations. There will be a separate tutorial covering [Enunciate](https://github.com/stoicflame/enunciate) 
use.


First, we will need to include the Swagger libraries: 

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```


Next, define the configuration class:

```java
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

This now should be enough to 
