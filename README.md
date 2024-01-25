Gym Spring Boot Application

How to run
  * Configure java 17 version 
  * Developed using PostgresSQL relational database. Application includes 4 databases (springboot, springbootdev, springbootprod, springbootlocal).
  * Good to have Docker inorder to run images of prometheus.
  * OpanAPI documentations of rest api endpoints are in this link http://localhost:{properPortOfProfile}/swagger-ui/index.html#/ 

Architecture of Application

Domain (Domain is a package which includes 5 entites of our model)
  * User
  * Trainee
  * Trainer
  * TrainingType
  * Training
    
DTO (DTO is a package which includes our Request and Response dto inorder to hide our actual models)
Request
  * ActivateProfileRequest
  * ChangeLoginRequest
  * LoginRequest
  * TraineeRegistrationRequest
  * TraineeTrainingsListRequest
  * TrainerRegistrationRequest
  * TrainerTrainingListRequest
  * TrainingDTORequest
  * UpdateTraineeRequest
  * UpdateTrainerRequest
Response
  * ActiveTrainerResponse
  * RegistrationResponse
  * TraineeListResponse
  * TraineeProfileResponse
  * TraineeTrainingsListResponse
  * TrainerListResponse
  * TrainerProfileResponse
  * TrainerTrainingsListResponse
  * UpdateTraineeResponse
  * UpdateTrainerResponse

Repository (Repository is layer which stands between Database and Busnees logic layer. Implemented with Spring Data JPA in order to make our life easy using one of the interface like JpaRepository<T,L> which provide an extra abstract layer compare to jdbc. I mean I used couple of prepared statements rather than writing queries manually.)
  * UserRepository
  * TraineeRepository
  * TrainingRepository
  * TrainingTypeRepository
  * TrainerRepository

Service (Service layer which is localed between DAO and Controller. The main purpose of this layer is not hide complaxity and busines logic of the request from client side. I mean Controller is for only handling request and return responce toward REST APIs. However, service classes is only for busines logic part)
  * UserService
  * TraineeService
  * TrainerService
  * TrainingService
  * TrainingTypeService

Controller (Controller layer is for handling request from client side and return proper Response account condition of the result it provides proper status code)
  * UserController
  * TraineeController
  * TrainerController
  * TrainingController
  * TrainingTypeController

Required Task for this model

* Convert existing application to be Spring boot Application
* Enable Actuator
    a) Implement a few custom health indicators.
    b) Implement a few custom metrics using Prometheus.
* Implement support for different environments (local, dev, stg, prod). Use Spring profiles.
* Cover code with unit tests. Code should contain proper logging.
* Pay attention that each environment - different db properties.
* All functions except Create Trainer/Trainee profile. Should be executed only after Trainee/Trainer authentication (on this step should be checked
username and password matching).
* The written code adheres to clean code standards, development best practices such as SOLID, KISS, and DRY, utilizes design patterns, and maintains engineering excellence
* The code includes comprehensive logging, conforming to the best practices of logging; it includes various log levels, is sufficiently detailed, and does not contain sensitive data
* The code is covered by unit tests, achieving a line coverage of at least 80%. Additionally, these unit tests adhere to the FIRST principles
* Criteria specific to the task account for the remaining
            Database connections for each environment are implemented using profiles.
            At least two health check indicators have been implemented.
            At least two metrics are available in Prometheus.

Implementation of provided tasks

* I cloned existing project from last module(GymRestAPI) and converted from spring core into spring boot application by removing manually configurations and adding
  @SpringBootApplication annotation as entry class to my application which basicly consists of 3 annotations(@EnableAutoConfigure, @ComponenScans, and   
  @Configuration).
* Enabled actuator by including couple of dependencies into build.gradle. Actually there are a lot of endpoints of actuator I only enable couple of them (health,   
  beand, info, and metrics). Also, I used prometheus devtool for monitoring my application.
* Implemented spring profile in resources directory. Basicly there are 4 profile local, prod, dev, and stg. Also, every profile consists of their own configuration 
  details in their properties file and different db connections.
* JUnit test cases implemented for domain, dto, and service classes. However, Controller classes implemented with integration test using MockMvc defined sample 
  prepared data by inserting data into tables in data.sql file
* All functionalities executed only after checking its authentication expect Post requests for creating profiles of trainee or trainer(but in every single request 
  consists of only username but there is no password so I cannot check authentication as required in documentation, therefore I just checked it for existence of 
  user. If it does exists, it executes requested function, otherwise return bad request with preper log message).
* Development of application follows all required principles expect KISS(Keep it simple stupid) principle, bacause I implemented weird dto logic with unnessecary 
  lombok annotation rather than just using record class type which was introduces in java 14.
* The LoggingAspect class logs method executions, durations, return values, and exceptions in Spring components like controllers and services.
* Implemented unit test and integration test cases for service and controller layers reaching over 90% of class coverage, 90% of method coverage, and 75% line 
  coverage. Using JUnit5 and Mockito frameworks.
* Configuration of databse connection are specified in spring profile properties file.
* Two health indicators are implemented beans and info.
* Two metrics are implemented by Prometheus for monitoring our project. Docker engine would be needed in order to build images of prometheus using docker run -- 
  image.
* Implemented OpenAPI documentation for rest api endpoints. However, I personally did not liked features of Swagger. I suppose Postman is much much more better.
