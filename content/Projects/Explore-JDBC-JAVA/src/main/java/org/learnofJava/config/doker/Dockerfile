# Utiliza a imagem oficial do MySQL da Docker Hub
FROM mysql:8.0

# Define variáveis de ambiente para a configuração do MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=db_JDBC
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=root

# Exponha a porta 3306 para acessar o MySQL fora do contêiner
EXPOSE 3306

# Adicione a configuração para permitir a criação de triggers sem o privilégio SUPER
## ASSIM CONSIGO CRIAR TRIGEGR SEM IMPECILIO
CMD ["mysqld", "--log-bin-trust-function-creators=1"]
