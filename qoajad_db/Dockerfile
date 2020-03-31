# Derived from official mysql image (our base image)
FROM mysql:5.7
# Add a database with a given name
ENV MYSQL_DATABASE qoajad_users
# Sets the root password
ENV MYSQL_ROOT_PASSWORD a351F90B485180?
# Create an extra user for development
ENV MYSQL_USER qoajad
ENV MYSQL_PASSWORD qoajad
# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
#COPY ./sql-scripts/ /docker-entrypoint-initdb.d

