# TKMTUBE

 TKMTube is a social media platform designed for centralized storage of photos and videos of all events that occur at TKM College of Engineering. This project is built using Spring Boot and follows a microservices architecture.

## Features

- Upload, store, and share photos and videos.
- User authentication and authorization using Jason Web Token.
- Event-based organization of media content.
- User profiles and social interactions (likes, comments).
- Scalable microservices architecture.


## Technologies Used

- **Spring Boot**: For building the microservices.
- **Spring Cloud**: For service discovery, configuration management, and other cloud-native patterns.
- **Spring Security and JWT**: For authentication and authorization.
- **MySQL**: For relational data storage.
- **Google CLoud**: For storing media metadata.
- **Docker**: For containerizing the microservices.
- **Kubernetes**: For orchestrating the deployment of the microservices.



### Architecture and microservices 

### BLOG-SERVICE 
#### http://localhost:8080 
- `/blogs/`
- `/blogs/likes/{id}`
- `/blogs/add `
- `/blogs/{id}`
- `/blogs/delete/{id}`

### LIKE-SERVICE 
#### http://localhost:9090 
- `/likes/`
- `/likes/getLikes` 

### MEDIA-SERVICE 
#### http://localhost:5000 
- `/media/{username}/fetch`
- `/media/{username}/upload`


### PROFILE-SERVICE 
#### http://localhost:8989 
- `/profile/upload`
- `/profile/fetch`


### GALLERY-SERVICE 
#### http://localhost:9005 
- `/gallery/upload`
- `/gallery/`

### AUTH-SERVICE
#### http://localhost:9898
- `auth/register`
- `auth/login`

