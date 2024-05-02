# Detailed Servlet Documentation for WebAppProject
**Authored by Edward Riley**

This document provides detailed descriptions of each servlet in the `WebAppProject`, including their specific functionalities, supported endpoints, HTTP methods, and parameters.

## Servlet Overview

Each servlet manages different data aspects and functionalities within the web application, such as user management, assignments, courses, questions, and authentication.

### 1. `AnswersServlet`
- **Endpoint**: `/api/answers/*`
- **Supported Methods**: `POST`
- **Description**: This servlet only supports the creation of answers. It does not provide functionalities for retrieving answers.
- **Parameters**:
  - `answerText`: Text of the answer.
  - `questionId`: ID of the question the answer belongs to.
  - `correctAnswer`: Boolean indicating whether the answer is correct.

### 2. `AssignmentsServlet`
- **Endpoints**:
  - `/api/assignments/`: Retrieves all assignments.
  - `/api/assignments/{assignmentId}`: Retrieves a specific assignment by ID.
  - `/api/assignments/{assignmentId}/questions`: Retrieves all questions for a specific assignment.
- **Supported Methods**: `GET`, `POST`
- **Description**: Manages assignment data, including retrieval of assignments and related questions, and creation of new assignments.
- **Parameters for GET**:
  - `onlyActive`: Boolean to filter only active assignments.
  - `includeAnswers`: Boolean to include answers in the question retrieval.
  - `indicateCorrectAnswers`: Boolean to include correctness indication for each answer.
- **Parameters for POST**:
  - `assignmentName`, `assignmentDescription`, `courseId`, `studentId`, `dueDate`, `openDate`: Details required to create a new assignment.

### 3. `CoursesServlet`
- **Endpoints**:
  - `/api/courses/`: Retrieves all courses.
  - `/api/courses/{courseId}`: Retrieves a specific course by ID.
  - `/api/courses/{courseId}/assignments`: Retrieves all assignments for a specific course.
  - `/api/courses/teacher/{teacherId}`: Retrieves all courses taught by a specific teacher.
  - `/api/courses/student/{studentId}`: Retrieves all courses taken by a specific student.
- **Supported Methods**: `GET`, `POST`
- **Description**: Manages course data, including retrieval and creation of courses.
- **Parameters for GET**:
  - `onlyActive`: Boolean to filter only active assignments within the courses.
- **Parameters for POST**:
  - `courseNumber`, `courseName`, `description`, `teacherId`: Details required to create a new course.

### 4. `LoginServlet`
- **Endpoint**: `/api/login`
- **Supported Methods**: `POST`
- **Description**: Handles user authentication by verifying login credentials.
- **Parameters**:
  - `username`, `password`: Credentials required for user authentication.

### 5. `QuestionsServlet`
- **Endpoints**:
  - `/api/questions/`: Retrieves all questions.
  - `/api/questions/{questionId}`: Retrieves a specific question by ID.
- **Supported Methods**: `GET`, `POST`
- **Description**: Manages question data, including retrieval and creation of questions.
- **Parameters for GET**:
  - `includeAnswers`, `indicateCorrectAnswers`: Booleans to control the inclusion of answers and their correctness status in the retrieval.

### 6. `SignupServlet`
- **Endpoint**: `/api/signup`
- **Supported Methods**: `POST`
- **Description**: Handles user registration.
- **Parameters**:
  - `firstName`, `lastName`, `username`, `password`, `email`, `role`: Details required to create a new user account.

### 7. `UsersServlet`
- **Endpoints**:
  - `/api/users/`: Retrieves all users.
  - `/api/users/{userId}`: Retrieves a specific user by ID.
  - `/api/users/{userId}/assignments`: Retrieves all assignments for a specific user.
- **Supported Methods**: `GET`
- **Description**: Manages user data, including retrieval of user details and their assignments.
- **Parameters for GET**:
  - `onlyActive`: Boolean to filter only active assignments when retrieving user assignments.

## Error Handling

Each servlet is equipped to handle exceptions and return an XML-formatted error message, typically including a `<Status>` and `<Message>` tag to describe the nature of the error.

## Usage Instructions

To use these servlets effectively:
- Ensure requests are directed to the correct endpoint.
- Provide all required parameters in your requests, formatted as expected.
- Handle XML responses and error messages appropriately in your client application.

## Dependencies

Ensure your project configuration includes the following:
- `jakarta.servlet.http.*`
- `java.sql.*`
- `javax.xml.parsers.*`

## Conclusion

These servlets form the backbone of the `WebAppProject`, enabling robust data management and user interaction within the application. Proper understanding and usage of these servlets are crucial for maintaining the application's functionality.
