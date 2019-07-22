[![Build Status](https://travis-ci.org/diegocmsantos/java-cicd.svg?branch=master)](https://travis-ci.org/diegocmsantos/java-cicd)

# Deploying Spring Boot in Azure VM

This is a simple project to see how to deploy a Spring Boot project in
Azure Virtual Machine using maven.

## Web Application
This web application exposes a single endpoint that shows the host machine 
name and its IP address.

```
GET /hello

Hello 87da7dce8b2f/172.16.0.4
```

## Azure CLI

```
$ az login
```

```
$ az ad sp create-for-rbac --name "app-name" --password "password"
```

You should receive this as response:
```
{
    "appId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
    "displayName": "app-name",
    "name": "http://app-name",
    "password": "password",
    "tenant": "tttttttt-tttt-tttt-tttt-tttttttttttt"
}
```

### Maven Settings.xml Configuration
Now we configure Azure service principal authentication settings in our 
Maven settings.xml, with the help of the following section, under <servers>:
```
<server>
    <id>azure-auth</id>
    <configuration>
        <client>aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa</client>
        <tenant>tttttttt-tttt-tttt-tttt-tttttttttttt</tenant>
        <key>password</key>
        <environment>AZURE</environment>
    </configuration>
</server>
```

## Maven Configuration

In pom.xml file add this plugin configuration to plugins section.
```
<plugin>
    <groupId>com.microsoft.azure</groupId>
    <artifactId>azure-webapp-maven-plugin</artifactId>
    <version>1.7.0</version>
    <configuration>
        <authentication>
            <serverId>azure-auth</serverId>
        </authentication>
        <resourceGroup>basic</resourceGroup>
        <appName>java-cicd-k8s</appName>
        <region>westus</region>
    </configuration>
</plugin>
```
Change 'resourceGroup' and 'appName' to your values.

## Deploying
For deploying run the following command:
```
$ mvn clean package azure-webapp:deploy
```
You should see the below messages:
```
[INFO] Start deploying to Web App java-cicd-k8s...
[INFO] Authenticate with ServerId: azure-auth
[INFO] [Correlation ID: cccccccc-cccc-cccc-cccc-cccccccccccc] \
Instance discovery was successful
[INFO] Target Web App doesn't exist. Creating a new one...
[INFO] Creating App Service Plan 'ServicePlanssssssss-bbbb-0000'...
[INFO] Successfully created App Service Plan.
[INFO] Successfully created Web App.
[INFO] Starting to deploy the war file...
[INFO] Successfully deployed Web App at \
https://java-cicd-k8s.azurewebsites.net
```
