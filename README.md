# Medical History
Project for subject Large-Scale Software Development.


# Setting up the MySQL environment

In order to execute the SQL script, you must execute the following command while on the qoajad_db folder

`docker-compose up`

Once the environment has been set, you can connect through the following commands

```
docker ps
docker exec -it mh-users-mysql /bin/bash
mysql -u qoajad -p
mysql> use qoajad_users
```

You must replace the above fields with the respective container, user and database names that you have set up.
