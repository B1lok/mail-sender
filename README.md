## Running
To run application with docker compose you need:
1. Create .env file and put there your connection parameters to the email server. File should look like this
```
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=yourEmailAdress@
SPRING_MAIL_PASSWORD=your generated password
```
2. To start the application just use the command
`docker-compose up --build`

## Adjustments to the previous task
### Here I implemented functionality to send message into MB(kafka) after new entity is added
[New commit to second task](https://github.com/B1lok/music-manager-api/commit/ccfd3d2bc7501416eda2e44fffc128eec6deae4e)
