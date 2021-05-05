Provide a list of violations in the current implementation

* Bug could not create new product record to database.
> [POST] http://localhost:8080/products/new
* Missing some spring annotations configuration
* Clean code:
    1. Structure naming package
    2. Missing service package, service class
    3. Equal and hashCode method is kind of boiler plate code
    4. HTTP_METHOD used only POST for CRUD action, need to use [POST, PUT, DELETE, GET] to have meaningful
    5. Refactor flow of code for clearer 
    > Controller class -> Service class -> Repository class
    6. Remove unused code in controller 
    > ProductController((ProductRepository productRepository) {...}
    > Getter & Setter setProductRepository(ProductRepository productRepository)){}, getProductRepository(ProductRepository productRepository){}
    7. Exception handling. Both Success or Failure is always return HTTP Status 200. Need to refactor, and custom exception
* Missing Testing => Adding BDD testing use Cucumber (5 Scenarios)
* Missing Versioning API => Adding Versioning through content negotiation
* Missing API documentation => Adding swagger-ui
* Missing profile for production (dev environment, prod environment) => Added dev, test, prod environment
* Missing dockerfile build, docker-compose => Added Dockerfile and docker-compose.yml to containerize