
# DEPRECATED

# bitlog-btlg-cmp

## Local development

### Dependencies

* Kafka broker\
  You can easily start one using [bitnami-docker-kafka](https://github.com/bitnami/bitnami-docker-kafka)

### run build

```bash
mvn clean package
```

### run application

```bash
mvn spring-boot:run -Plocal
```

The `local` profile configures human-readable log output and provides some configuration for your local environment.

`spring.security.user.name: admin`\
`spring.security.user.password: admin`\
`management.server.port: 18080`

### Endpoints

#### Swagger UI / OpenAPI specification

port: 8080\
authentication: basic authentication - user configured in application-local.yaml \
urls:
- [swagger-ui](http://localhost:8080/bitlog-btlg-cmp/swagger-ui/index.html)
- [openapi-specs](http://localhost:8080/bitlog-btlg-cmp/openapi/openapi.yaml)

#### Spring Boot Actuator

port: 18080\
authentication: none - the actuator port is not exposed via ingress\
urls:
- [mappings](http://localhost:18080/mappings)
- [prometheus](http://localhost:18080/prometheus)
- [health](http://localhost:18080/health)
- [readiness](http://localhost:18080/health/readiness)
- [liveness](http://localhost:18080/health/liveness)
- [info](http://localhost:18080/info)

## kubernetes

As the actuator is not exposed via ingress on kubernetes, kubernetes port forwarding can be used to access the endpoint urls:
```
kubectl port-forward <podname> 18080:8081
```
By doing so, you will be able to access the endpoints using the same urls as documented for [local endpoints actuator](#actuator) above.

### Helm Chart

#### Referencing values inside values

[More about that here](charts/${CHARTNAME}/_helpersReadme.md)
