# Example project for Spring Boot + Apache Kafka + Docker-compose solution

Some functions to managing a kafka 

Create topic
docker exec broker kafka-topics --bootstrap-server broker:9092 --create --topic topic-1

Sending message to the topic-1
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server broker:9092 --topic topic-1

Reading message
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092 --topic topic-1 --from-beginning

Delete topic
docker exec --interactive --tty broker kafka-topics --delete --topic topic-1