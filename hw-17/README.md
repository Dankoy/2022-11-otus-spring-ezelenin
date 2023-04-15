### jasypt
Обязательно надо указать явно все параметры при шифровании

Шифрование файла с голыми паролями - `mvn jasypt:encrypt -Djasypt.plugin.path="file:./src/main/resources/application.yml" -Djasypt.encryptor.password=masterpassword`

Шифрование строки с помощью плагина - `mvn jasypt:encrypt-value -Djasypt.encry
ptor.password=masterpassword -Djasypt.encryptor.algorithm=PBEWithMD5AndDES -Djasypt.encryptor.ivGeneratorClassName=org.jasypt.iv.NoIvGenerator -Djasypt.plugin.value=mongopass`


### Билд бэкенд контейнера
`docker build -t evawe/library-backend:1 --build-arg maven_profile=no-frontend --build-arg JASYPT_PASSWORD=masterpassword .
`
### Запуск бэкенд контейнера 

`docker run --rm --name library-backend -e JASYPT_PASSWORD=masterpassword -e MONGODB_DATABASE=library -e MONGODB_PORT=27017 -e MONGODB_HOST=localhost evawe/library-backend:1`

### Зависимости для docker-compose
Для сокрытия паролей и прочих интересных переменных используется файл .env из корня проекта.

Пример содержимого файла:

`JASYPT_MASTER_PASSWORD=пароль
MONGO_CONTAINER_PASSWORD=пароль`