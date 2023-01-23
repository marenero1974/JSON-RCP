# Customers
JSON-RPC applicazione:

L'applicazione è stata sviluppata con IDE: Intellij e java 8 ed è un microservizio sviluppato con 
spring boot.

impostare un settings.xml libero come ad esempio:

<?xml version="1.0" encoding="UTF-8"?>
<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd" xmlns="http://maven.apache.org/SETTINGS/1.1.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
</settings>


Per avviare l'applicazione che funge da server per richieste json-rpc, avviare la classe: RpcApplication

Una volta avviata l'applicazione aprire un terminale in windows e inserire il seguente comando:

curl -v -H "Content-Type: application/json"  -d "{\"id\":0, \"method\":\"GiveMeAdvice\", \"params\":[{\"topic\":\"love\", \"amount\":3}]}" http://localhost:8080/rpc/myservice

apparirà in console la response json-rpc

Si possono modificare in input topic e amount come desiderato













