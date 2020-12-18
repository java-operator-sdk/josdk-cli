{{name}} 
=======
{{description}}

## How to run the project locally

run the following command
```
gradle run
``` 

## How to deploy the operator

```
docker build -t {{image}} .
docker push {{image}}
kubectl apply -f deployment/
```

Your operator will be up and running on K8s cluster you are connected to very soon! 
