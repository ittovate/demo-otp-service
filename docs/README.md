# ⚫ Introduction
- This is an OTP (One Time Password) service built using Spring Boot.
- It leverages Twilio for sending OTPs via SMS and Redis for storing OTPs with an expiration time.
- This service is designed to be simple, and easy to configure.

### Key Features:
- **Twilio Integration**: Send OTPs via SMS to users.
- **Redis Cache**: Securely store OTPs with a predefined expiration time.
- **Swagger UI**: Interactive API documentation for easy testing and interaction.

### Visual Representation
- The following video demonstrates how to interact with the application:
  - **Left window**: Using Swagger UI to send and verify OTP.
  - **Right window**: [PhoneLink](https://www.microsoft.com/en-us/windows/sync-across-your-devices?r=1) app to show recieved OTP on phone.

<div align="center">
  <video src="https://github.com/user-attachments/assets/467165ec-c961-463b-8971-fe86522e6dd4"></video>
</div>

# 🔴 Prerequisites
- **Java 21.0.3**: Required to build and run the Spring Boot applications.
- **Apache Maven 3.9.8**: Used for dependency management and building the project.
- **Spring Boot 3.3.1**: Framework for building the Kafka producer and consumer demos.
- Redis Account (along with a created database).
- Twilio Account (along with your verified number).

# ⚡ Running the Project
1. **Clone the repository**: 
    ```bash
    git clone https://github.com/ittovate/otp-service.git
    ```
   
2. **Navigate to the project directory**:
    ```bash
    cd demo-otp-service
    ```
   
3. **Configure environment variables**:
    1. Duplicate `src/main/resources/keys.env` file and rename the copy to `.env`.
    2. Fill fields with values from your Twilio account and Redis account:
       - `TWILIO_API_KEY` is "Accound SID" under "Account Info" section in https://console.twilio.com/.
       - `TWILIO_API_TOKEN` is "Auth Token" under "Account Info" section in https://console.twilio.com/.
       - `TWILIO_SENDER_NUMBER` is "My Twilio phone number" under "Account Info" section in https://console.twilio.com/.
       - `TWILIO_VERIFIED_NUMBER` is your verified number used in your Twilio account.
       - `REDIS_HOST` & `REDIS_PORT` are "Public endpoint" under "General" section (in the database settings).
       - `REDIS_PASSWORD` is under "Security" section (in the database settings).
       - 
4. **Build and run the project**: 
      ```bash
      mvn clean install spring-boot:run 
      ```

5. [**Try it out!**](http://localhost:8080/swagger-ui/index.html)
  
6. **Stop the project**: After testing, stop both demos by pressing `CTRL + C` in each terminal.
