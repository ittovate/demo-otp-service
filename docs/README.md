# ⚫ Introduction
- This is an OTP (One Time Password) service built using Spring Boot.
- It leverages Twilio for sending OTPs via SMS and Redis for storing OTPs with an expiration time.
- This service is designed to be simple, and easy to configure.

# 🔴 Prerequisites
- Java 21.0.3
- Apache Maven 3.9.8
- Spring Boot 3.3.1
- Redis Account (along with a created database).
- Twilio Account (along with your verified number).

# ⚡ Running
1. Clone the repository `git clone https://github.com/MohanadKh03/otp-service.git`.
2. Change directory to it `cd otp-service`.
3. [Configure Environment Variables](#-setting-up-environment-variables).
4. Build the Project `mvn clean install`.
5. Run the project `mvn spring-boot:run`.
6. [Try it out!](usage.md)

# ⚙ Setting Up Environment Variables
1. Duplicate `src/main/resources/keys.env` file and rename the copy to `.env`.
2. Fill fields with values from your Twilio account and Redis account:
   - `TWILIO_API_KEY` is "Accound SID" under "Account Info" section in https://console.twilio.com/.
   - `TWILIO_API_TOKEN` is "Auth Token" under "Account Info" section in https://console.twilio.com/.
   - `TWILIO_SENDER_NUMBER` is "My Twilio phone number" under "Account Info" section in https://console.twilio.com/.
   - `TWILIO_VERIFIED_NUMBER` is your verified number used in your Twilio account.
   - `REDIS_HOST` & `REDIS_PORT` are "Public endpoint" under "General" section (in the database settings).
   - `REDIS_PASSWORD` is under "Security" section (in the database settings).
