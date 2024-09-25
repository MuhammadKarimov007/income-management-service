# Income Management Service
## This is a web service developed in Java to manage income and expenses efficiently.
This project is a web-based application that helps its users to monitor their incomes and expenses. The application takes into consideration that users will enter many of their 
sensitive data to actually use the service. So, security in mind, the project uses Spring Security to ensure the safety of all the users' data.

## Docker image install
1. Create a folder in your local machine. You can run the application from this folder once you pull the Docker image.
2. Download the docker-compose.yml of the application. You can download it [here](https://github.com/MuhammadKarimov007/income-management-service/blob/secureBranch/docker-compose.yml).
3. Pull the docker image using this command. Note that you need to specify the version of the image. Otherwise, you may get manifest unknown error message.
    ```
    docker pull shermukhammad843/final-project:v1.0
   ```
4. Once the pull is complete, run the following command.
    ```
   docker-compose up
   ```
5. Once the application is (hopefully) up and running, you can use the application from [localhost:8080](http://localhost:8080/) port. Please make sure that port 8080 is free, otherwise you may run into some exceptions.

## Features:
- Sign in users given their email and password
- Sign up new users and save them to the DB
- Overview of all incomes
- Overview of all expenses
- Register new income
- Register new expense
- Calculate all incomes for a given period
- Calculate all expenses for a given period
- Calculate balance for a given period

You can read the API documentation of the application [here](https://www.postman.com/descent-module-saganist-36810950/workspace/income-management-project/overview).