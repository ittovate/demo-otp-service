# OTP Service
This is an OTP (One Time Password) service built using Spring Boot. It leverages Twilio for sending OTPs via SMS and Redis for storing OTPs with an expiration time. This service is designed to be simple, and easy to configure.


# Prerequisites
- Java 21
- Spring Boot 3.3.1
- Maven (for building the project)
- Twilio Account (for sending SMS)
- Redis (either local, on the cloud, or via Docker)


# Environment Variables
- You must set up the following environment variables in a file named `.env` inside the `src/main/resources` directory.
- Create a `.env file` in the same directory and fill it with values.
- There is an example file called `keys.env` located in the src/main/resources directory. This file contains all the necessary environment variables required for the OTP Service. Below is the content of the keys.env file:
```
# The following values can be obtained from your Twilio account
TWILIO_API_KEY=
TWILIO_API_TOKEN=
TWILIO_SENDER_NUMBER=
TWILIO_VERIFIED_NUMBER=

# The following values can be obtained from your Redis DB
REDIS_HOST=
REDIS_PORT=
REDIS_PASSWORD=
```
There is also a configurable expiry date for the message which you can modify inside `application.yaml` called `spring.data.redis.expiry-date` the default is 5 but you can change it.


# Getting Started
1. Clone the Repository
   - ```git clone https://github.com/yourusername/otp-service.git```
   - ```cd otp-service```
2. Configure Environment Variables
   - Create the ```.env``` file in ```src/main/resources``` and fill in the required environment variables as explained above.
3. Build the Project
   - ```mvn clean install```
   - This should make a new ```target``` directory which has all the ```.class``` (build) files

4. Run the project
   - ```mvn spring-boot:run```
   - This runs the web server at the default tomcat port 8080
# [How To Use](docs/usage.md)
# Contribution
## Code Quality And Style
- Use Checkstyle IntelliJ plugin (use [sun_checks_custom.xml](https://github.com/MohanadKh03/otp-service/blob/checkstyle/sun-config/config/checkstyle/sun_checks_custom.xml) as the configuration).
- Use SonarLint IntelliJ plugin.
## Pre-commit Hooks
### Installation
1. Install [Python](https://www.python.org/downloads/).
2. Install [pre-commit](https://pre-commit.com/): `pip install pre-commit`.
3. Install hooks: `pre-commit install --config ./config/.pre-commit-config.yaml`.
4. Test hooks without committing: `pre-commit run --config ./config/.pre-commit-config.yaml --all-files`.
### Hooks
- pre-commit hooks:
  - trailing-whitespace.
  - end-of-file-fixer.
  - check-yaml.
- Local hooks:
  - maven-checkstyle.
