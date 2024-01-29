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


