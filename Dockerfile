# Use the Playwright Java Docker image as the base image
FROM mcr.microsoft.com/playwright/java:v1.42.0-jammy

LABEL maintainer="nlitvinov"

# Set the working directory inside the container
#/usr/src/app is a directory path within the Docker container, not on your host machine.
#It's a convention used to organize files and execute commands within Docker containers.
WORKDIR /usr/src/app

# This command will copy all files and directories from the current directory (.) on your local machine
#into the /usr/src/app directory within the Docker image.
COPY . /usr/src/app

# Run your automation tests using JUnit
CMD ["mvn", "test"]

## Expose the port your application runs on
#EXPOSE 8080

# Define the command to run your application
#ENTRYPOINT ["mvn test"]