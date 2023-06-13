# Spring Boot Issues

This repository was created to host Spring Boot examples that replicate issues found in the code. Should be used as an example repo for Spring Boot GitHub issues

Current issue:

To reproduce just use the following steps:

- Step 1: Start both projects the Spring Boot Server and the Spring Cloud Gateway

- Step 2: Run the below curl command

    ```
    curl --location 'localhost:8081' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.XoAyZgxbfuEoRILrvWkdeikGRdF0OzmuPG8nDtMcFnQ'
    ```

- Step 3: Inspect the logs of the Spring Boot app
  - You should see a log statement like the following: 
  
  
  `2023-06-13 16:57:42.439 DEBUG [,,] 36927 --- [ctor-http-nio-2] com.example.demo.HelloController         : request-id from the reactor context is: request-id/1
    `

- Step 4: Run the below curl command

    ```
      curl --location 'localhost:8081' \
    --header 'request-id: wrong_request_id' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaWF0IjoxNTE2MjM5MDIyfQ.XoAyZgxbfuEoRILrvWkdeikGRdF0OzmuPG8nDtMcFnQ'
    ```

- Step 5: Inspect the logs of the Spring Boot app
  - You should see a log statement like the following:
  

  `2023-06-13 17:03:05.290 DEBUG [,,] 36927 --- [ctor-http-nio-2] com.example.demo.HelloController         : request-id from the reactor context is: wrong_request_id
  `

- Step 6: Repeat the step 3 using the curl command that does not include the specific request-id header. The request-id in the logs does not change and stays stuck with the value `wrong_request_id`

