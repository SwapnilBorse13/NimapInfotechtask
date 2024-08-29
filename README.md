1. Project Overview
This mini-project is a Spring Boot application designed to perform CRUD operations on Category and Product entities. The project uses REST controllers to expose these operations, and JPA with Hibernate to manage persistence. The application also includes server-side pagination and implements a one-to-many relationship between Category and Product.

2. Technologies and Tools Used
Spring Boot: Framework used to build the application, providing a simplified configuration setup.
Spring Data JPA: Provides the data access layer to perform CRUD operations and handle persistence with relational databases.
Hibernate: ORM tool used in conjunction with JPA to manage database interactions.
MySQL: The relational database used to store Category and Product data.
Postman: Tool used to test the API endpoints.
Jackson: Library used for JSON processing, important for handling the serialization and deserialization of entities.
3. Database Structure
Category Table: Contains id (Primary Key) and name columns.
Product Table: Contains id (Primary Key), name, price, and category_id (Foreign Key referencing Category(id)).
This structure establishes a one-to-many relationship where one Category can have multiple Product entities.

4. Entities
Category Entity
Fields:
id: Primary key, auto-generated.
name: Name of the category.
products: A list of Product entities associated with this category.
Annotations:
@Entity: Marks this class as a JPA entity.
@Id, @GeneratedValue: Used for the primary key.
@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true): Defines a one-to-many relationship with Product.
@JsonManagedReference: Prevents circular references during JSON serialization.
Product Entity
Fields:
id: Primary key, auto-generated.
name: Name of the product.
price: Price of the product.
category: Reference to the Category it belongs to.
Annotations:
@Entity: Marks this class as a JPA entity.
@Id, @GeneratedValue: Used for the primary key.
@ManyToOne: Defines a many-to-one relationship with Category.
@JoinColumn(name = "category_id", nullable = false): Specifies the foreign key.
@JsonBackReference: Prevents circular references during JSON serialization.
5. Handling Circular References
Circular references between Category and Product can cause infinite loops when these entities are serialized into JSON, which happens when you return them in REST API responses.

To prevent this:

@JsonManagedReference is used on the products field in the Category entity. This marks it as the forward side of the reference.
@JsonBackReference is used on the category field in the Product entity. This marks it as the back side of the reference and prevents it from being serialized, thus avoiding the infinite loop.
6. Controllers
CategoryController
APIs:
GET /api/categories: Retrieves a paginated list of categories.
GET /api/categories/{id}: Retrieves a specific category by ID.
POST /api/categories: Creates a new category.
PUT /api/categories/{id}: Updates an existing category by ID.
DELETE /api/categories/{id}: Deletes a category by ID.
ProductController
APIs:
GET /api/products: Retrieves a paginated list of products.
GET /api/products/{id}: Retrieves a specific product by ID.
POST /api/products: Creates a new product.
PUT /api/products/{id}: Updates an existing product by ID.
DELETE /api/products/{id}: Deletes a product by ID.
7. Services and Repositories
The business logic is separated into service classes (CategoryService and ProductService), which interact with the repositories (CategoryRepository and ProductRepository). These repositories extend JpaRepository, providing standard CRUD operations without requiring boilerplate code.

8. Database Configuration
In the application.properties file, you’ve set up your database connection:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/nimap_db
spring.datasource.username=root
spring.datasource.password=swapnil
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
9. Testing with Postman
You can use Postman to test the API endpoints by making GET, POST, PUT, and DELETE requests. The JSON body in POST and PUT requests allows you to input data for categories and products.

10. Key Takeaways
RESTful API: The application adheres to REST principles, making it scalable and easy to interact with via HTTP.
Entity Relationships: Proper handling of entity relationships and serialization using annotations ensures efficient data handling.
Pagination: Server-side pagination is implemented to handle large datasets efficiently.
Error Handling: Custom exceptions like ResourceNotFoundException provide clear feedback when resources are not found, improving API usability.
11. Conclusion
This project showcases a solid understanding of Spring Boot, JPA, Hibernate, and RESTful web services. You’ve implemented key industry practices like separation of concerns (through service layers), handling of complex entity relationships, and robust API design. This knowledge and the ability to solve common issues, such as the infinite loop problem, are essential skills for any Spring Boot developer.
