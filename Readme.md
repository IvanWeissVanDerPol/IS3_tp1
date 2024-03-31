# Project Structure and Documentation

Welcome to the comprehensive documentation for the backend component of our application. This document aims to provide developers, project managers, and stakeholders with a clear understanding of the architectural design, directory structure, and the functionalities of each component within the backend system.

## Directory Structure

### In-Depth Look at the  `cors` Directory
The `cors` directory is an integral part of the backend application, focusing on handling Cross-Origin Resource Sharing (CORS) configurations. CORS is a security feature that allows or restricts web applications running on one origin from requesting resources or making calls to APIs hosted on a different origin.

#### Purpose of CORS

- **Security**: CORS helps in securing web applications by ensuring that only allowed domains can make web requests to your server.
- **Control**: Provides a mechanism to specify which domains, HTTP methods, and headers can interact with your application, thus offering fine-grained control over cross-origin requests.

#### Components

##### `CorsFilter.java`

- **Overview**: The `CorsFilter.java` file contains the implementation of a filter that intercepts incoming HTTP requests to your application. It applies CORS policies to these requests before they reach your application's endpoints.

- **Key Responsibilities**:
  - **Pre-flight Requests Handling**: Manages pre-flight requests by validating the `Origin` header, HTTP methods, and headers of the request against the configured CORS policies.
  - **Setting Response Headers**: Adds CORS-related headers to the HTTP response, such as `Access-Control-Allow-Origin`, `Access-Control-Allow-Methods`, and `Access-Control-Allow-Headers`, among others. These headers inform the client about the permissions of the cross-origin requests.
  - **Configuration Flexibility**: Allows the configuration of various CORS policies, such as enabling requests from specific origins, configuring the allowed methods (GET, POST, PUT, DELETE, etc.), and setting the allowed headers. This can often be customized via application properties or annotations.

#### How It Works

1. **Interception**: The `CorsFilter` acts as a servlet filter, intercepting all incoming HTTP requests to the application server.
2. **Policy Check**: It checks the incoming request's origin against the list of allowed origins configured in the application. If the request's origin is not allowed, the filter can block the request by not setting the appropriate CORS headers or by sending an HTTP error response.
3. **Setting Headers**: If the request is from an allowed origin, the filter adds the CORS headers to the response, indicating which origins, methods, and headers are allowed.
4. **Continuation or Rejection**: Based on the CORS headers present in the response, the client's browser decides whether to proceed with the request or reject it due to CORS policy violations.

#### Benefits

- **Enhanced Security**: By specifying which domains are allowed to access your application, you reduce the risk of cross-site scripting attacks and data breaches.
- **Cross-Domain Resource Sharing**: Enables the consumption of your API across different domains, which is essential for modern web applications that rely on APIs for data and functionality.



### In-Depth Look at the  `dao` Directory
The `dao` (Data Access Object) directory plays a pivotal role in the application's architecture, serving as the layer responsible for all interactions with the database. This layer abstracts the complexity involved in performing CRUD (Create, Read, Update, Delete) operations and ensures the business logic layer remains separate from the persistence layer.

#### Purpose and Benefits

- **Abstraction**: DAOs provide a clear abstraction of data access mechanisms, allowing for easy modifications to the database access logic without affecting the business layer.
- **Decoupling**: Helps in decoupling the application's core business logic from the specifics of the persistence technology (e.g., JDBC, Hibernate).
- **Reusability and Maintainability**: Promotes code reusability and maintainability by centralizing data access in one layer.

#### Components Explained

##### `Customer`
- `CustomerDAO.java`
  - **Interface**: Defines the contract for customer-related data operations, such as retrieving customer information, adding a new customer, updating existing customer data, and deleting a customer.
  - **Benefits**: Ensures consistency in data access logic across the application and facilitates the implementation of various data storage mechanisms.
- `CustomerDAOImpl.java`
  - **Implementation**: Provides the concrete implementation of the `CustomerDAO` interface, utilizing a specific data access technology (e.g., JDBC, JPA) to interact with the database.
  - **Functionality**: Includes detailed logic for executing SQL queries or using an ORM framework to perform operations on the customer data within the database.

##### `PointAllocationRule`
- `PointAllocationRuleDAO.java`
  - **Interface**: Specifies methods for accessing and managing point allocation rules, which define how points are allocated to customers based on certain actions.
- `PointAllocationRuleDAOImpl.java`
  - **Implementation**: Implements the data access logic for point allocation rules, handling the persistence, retrieval, update, and deletion of these rules in the database.

##### `PointExpiration`
- `PointExpirationDAO.java`
  - **Interface**: Outlines operations related to point expiration policies, such as fetching, updating, and removing expiration rules.
- `PointExpirationDAOImpl.java`
  - **Implementation**: Contains logic for managing point expiration rules in the database, ensuring that points expire in accordance with the defined policies.

##### `Points`
- `PointsDetailDAO.java` & `PointsDetailDAOImpl.java`
  - **Purpose**: Focuses on the detailed transactions of points, handling operations related to individual points transactions, such as accumulation and usage.
- `PointsHeaderDAO.java` & `PointsHeaderDAOImpl.java`
  - **Purpose**: Manages the aggregate data of points transactions, like total points earned or spent by a customer, serving as a summary view of points activities.

##### `PointUseConcept`
- `PointUseConceptDAO.java` & `PointUseConceptDAOImpl.java`
  - **Functionality**: Deals with the concepts under which points can be used, outlining the operations for managing these concepts in the database.

##### `PointWallet`
- `PointWalletDAO.java` & `PointWalletDAOImpl.java`
  - **Role**: Manages the point wallet operations, where a wallet represents a customer's balance of points, including adding to or deducting points from the wallet.
### In-Depth Look at the `ejb` Directory

The `ejb` (Enterprise Java Beans) directory is a cornerstone of the application's business layer, encapsulating the business logic in a way that promotes reusability, scalability, and transaction management. EJBs facilitate the development of distributed, transactional, secure, and portable applications aimed at the enterprise level.

#### Purpose and Advantages

- **Transaction Management**: EJBs provide built-in support for managing transactions, ensuring data integrity and consistency across operations.
- **Security**: Leverages container-managed security for robust access control and authentication.
- **Simplification of Development**: Reduces the complexity of enterprise application development by handling many aspects of application behavior automatically, including lifecycle management, concurrency, and more.

#### Components Explained

##### `CustomerServiceEJB.java`
- **Role**: Focuses on customer-related operations, encompassing all the business logic for managing customer information such as registration, updating profiles, and deletion.
- **Functionality**: Interfaces with `CustomerDAO` to persist customer information, ensuring business rules are enforced (e.g., validation of customer details).

##### `PointAllocationRuleService.java`
- **Purpose**: Manages the business logic for point allocation rules, defining how points are awarded or deducted based on specific customer actions.
- **Details**: Interacts with `PointAllocationRuleDAO` to retrieve and update the rules in the database, applying business validations to ensure rule integrity.

##### `PointExpirationService.java`
- **Functionality**: Handles the logic for point expiration, determining when points should expire and executing the expiration process.
- **Implementation**: Works closely with `PointExpirationDAO` to manage expiration rules and perform batch operations for expiring points.

##### `PointsService.java`
- **Overview**: Acts as the core service for managing points within the system, covering the accumulation, deduction, and querying of points.
- **Operations**: This service orchestrates complex transactions involving multiple DAOs (e.g., `PointsDetailDAO`, `PointsHeaderDAO`), ensuring atomicity and consistency.

##### `PointUseConceptService.java`
- **Role**: Deals with the concepts under which points can be used, such as redeeming points for products or services.
- **Logic**: Manages business rules around the usage of points, ensuring that points are used in accordance with predefined concepts and policies.

##### `PointWalletService.java`
- **Purpose**: Focuses on the management of point wallets, where each wallet represents a collection of points owned by a customer.
- **Functionality**: Responsible for operations such as adding points to a wallet, deducting points, and querying the wallet balance, interfacing with `PointWalletDAO` for persistence.


### In-Depth Look at the `exception` Directory

The `exception` directory plays a crucial role in the application's error handling and management strategy, housing custom exceptions that are tailored to specific failure scenarios within the application. Custom exceptions enable more granular error handling and provide clear, actionable information about issues that arise during execution.

#### Purpose of Custom Exceptions

- **Enhanced Clarity**: Custom exceptions give developers and users a clearer understanding of what went wrong, as opposed to generic exceptions that might be ambiguous or too broad.
- **Improved Error Handling**: They allow for more precise catch blocks in try-catch structures, enabling specific responses to different error conditions.
- **Consistency**: Standardizing error responses across the application, making it easier to document and troubleshoot errors.

#### `DatabaseException.java`

##### Role and Functionality

- **Overview**: `DatabaseException.java` is designed to encapsulate errors related to database operations, acting as a wrapper for exceptions thrown by the persistence layer. This can include SQL exceptions, connection failures, transaction issues, and more.
- **Usage**: This exce

### In-Depth Look at the `model` Directory

The `model` directory is fundamental to the application's structure, containing Java classes that define the data model. These classes represent the entities within the application, capturing both the data and the relationships between different data entities. By providing a clear representation of the application's domain, the model classes play a crucial role in both the persistence layer and throughout the application's business logic.

#### Purpose and Importance

- **Data Representation**: Model classes encapsulate the application's data, defining what information is stored and how it's organized.
- **Data Validation**: They can enforce data integrity and validation rules, ensuring that only valid data is stored in the application.
- **Abstraction**: Serve as an abstraction layer over the database, allowing the rest of the application to interact with the data without needing to know about the database schema directly.

#### Components Explained

##### `Customer.java`
- **Overview**: Represents a customer entity, including all relevant information about a customer, such as name, contact details, and account information.
- **Functionality**: Often includes methods for validating customer data (e.g., email format) and may include business logic related to customers.

##### `PointAllocationRule.java`
- **Purpose**: Defines the rules for allocating points to customers based on certain actions or purchases.
- **Details**: Includes properties that describe the rule, such as the number of points awarded and the conditions under which points are allocated.

##### `PointExpiration.java`
- **Functionality**: Models the logic for point expiration, detailing how and when points expire. This might include expiration periods and conditions that trigger expiration.

##### `PointsDetail.java`
- **Role**: Details individual point transactions, capturing information about the earning or spending of points, including the amount, date, and reason for the transaction.
- **Usage**: Critical for tracking the flow of points in and out of a customer's account.

##### `PointsHeader.java`
- **Overview**: Provides summary information about points transactions, potentially including total points earned or spent over a period.
- **Functionality**: Acts as a header or summary record for groups of `PointsDetail` records.

##### `PointUseConcept.java`
- **Purpose**: Represents the concept under which points can be used, such as redeeming points for goods, services, or discounts.
- **Details**: Includes information about the rules and conditions associated with using points under this concept.

##### `PointWallet.java`
- **Functionality**: Models a wallet or account where points are stored, tracking the total points available to a customer.
- **Role**: Essential for managing and querying a customer's points balance, as well as for implementing logic to add or subtract points.

### Detailed Overview of the `rest` Directory

The `rest` directory is essential for defining the RESTful API endpoints of the application, serving as the interface through which external clients interact with the application. This layer is responsible for handling HTTP requests, mapping them to business logic operations, and returning appropriate responses.

#### Purpose and Functionality

- **API Exposure**: Facilitates the exposure of the application's functionality over HTTP, allowing for easy integration with other services and applications.
- **Data Transformation**: Converts data between the HTTP protocol and the application's internal representations, often involving JSON or XML for data exchange.
- **Request Routing**: Directs incoming requests to the correct handler based on the URL and HTTP method.

#### Components Explained

##### `ApplicationConfig.java`
- **Role**: Serves as the central point for configuring the RESTful services, including registering resource classes and custom providers or filters.
- **Details**: May involve setting up URL patterns for API endpoints, configuring CORS policies, and enabling features such as JSON parsing.

##### `CustomerResource.java`
- **Purpose**: Provides API endpoints related to customer operations, such as creating new customers, retrieving customer details, updating customer information, and deleting customers.
- **Functionality**: Interacts with `CustomerServiceEJB` or similar business services to perform the necessary operations and constructs HTTP responses to return to the client.

##### `PointAllocationRuleResource.java`
- **Role**: Manages the API endpoints for point allocation rules, including adding new rules, fetching existing rules, updating rules, and deleting rules.
- **Implementation**: Works closely with `PointAllocationRuleService` to access and manipulate the point allocation rules data.

##### `PointExpirationResource.java`
- **Functionality**: Handles endpoints for operations related to point expiration, such as defining expiration policies and executing batch expiration processes.
- **Details**: Leverages `PointExpirationService` to perform business logic associated with point expiration and communicates results back to clients.

##### `PointsUsageResource.java`
- **Purpose**: Offers endpoints for querying and managing the usage of points, allowing clients to understand how points can be spent or redeemed.
- **Operations**: Interacts with services like `PointsService` and `PointUseConceptService` to provide comprehensive functionality around points usage.

##### `PointUseConceptResource.java`
- **Role**: Provides endpoints for managing point use concepts, defining how points can be used within the application (e.g., for services, products, discounts).
- **Implementation**: Utilizes `PointUseConceptService` to manipulate and retrieve data related to point use concepts.

##### `PointWalletResource.java`
- **Functionality**: Exposes API endpoints for operations on point wallets, including viewing a customer's point balance, adding points, and deducting points.
- **Details**: Coordinates with `PointWalletService` to ensure accurate and secure management of point wallets.
