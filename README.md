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

This now should be enough to to start documenting the application. Using the Swagger annotations, we can document
our RESTFul endpoints:

```java
@GetMapping("")
    @ApiOperation(value = "Get list of Employees in the System ", response = Iterable.class, tags = "employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    List<Employee> all() {
        return employeeManager.getAll();
    }
```

When we run the application, we can navigate to http://localhost:8080/swagger-ui.html and see how these are converted into
Swagger docs:

![Swagger](swagger.png?raw=true "Swagger")

As an exercise, go through the other end-points and annotate the functions. You can also trigger the endpoints via the
Swagger page if you want to test them further.