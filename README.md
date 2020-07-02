# tkit-quarkus-log

1000kit Quarkus logger extension

[![License](https://img.shields.io/badge/license-Apache--2.0-green?style=for-the-badge&logo=apache)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/org.tkit.quarkus/tkit-quarkus-log-parent?logo=java&style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/org.tkit.quarkus/tkit-quarkus-log-parent)

> The version 1.0.0+ contains new package structure. For an old version please use 
> branch [0.9](https://gitlab.com/1000kit/libs/quarkus/tkit-quarkus-log/-/tree/0.9).

### Maven configuration

 * tkit-quarkus-log-cdi - core log library for Quarkus ARC `cdi` implementation.
 * tkit-quarkus-log-rs - log interceptor for `jaxrs` rest service endpoint.
 * tkit-quarkus-log-json - advanced `JSON` formatter for the log.
   
```xml
<dependency>
    <groupId>org.tkit.quarkus</groupId>
    <artifactId>tkit-quarkus-log-cdi</artifactId>
    <version>1.0.0</version>
<dependency>
<dependency>
    <groupId>org.tkit.quarkus</groupId>
    <artifactId>tkit-quarkus-log-rs</artifactId>
    <version>1.0.0</version>
<dependency>
<dependency>
    <groupId>org.tkit.quarkus</groupId>
    <artifactId>tkit-quarkus-log-json</artifactId>
    <version>1.0.0</version>
<dependency>
```
The interceptor for the logger will be activated automatically.

#### Custom log entry for the parameter

```java
@LogParam(classes = {AmqpMessage.class})
public static String logAmqpMessage(Object message) {
    AmqpMessage a = (AmqpMessage) message;
    return "AmqpMessage[" + a.getMessageId() + "," + a.getDeliveryCount() + "]";
}
```

The ```@LogParam``` annotation has this parameter:
 * classes - the parameter will be use for these classes.
 * assignableFrom - the parameter will be use for all assignable classes.
 * priority - the priority of this logger parameter.

To exclude the parameter from the log you can use the annotation ```@LogExclude```.
There is a parameter ```mask``` is not set the parameter name will be written in the log.
If you specified the value for the parameter mask it will be the value written in the log.

### Configuration

Only the defined packages in the configuration will be add to the interceptor (default: `org.tkit`)
For example:
```properties
tkit.log.packages=org.tkit
tkit.log.packages=com.acme
```

#### Method start and succeed log format

To configure the message format of the start, succeed, failed and future you can use these properties:
 * tkit.log.result.void - text for the result void type. Default: ```void```
 * tkit.log.start - message format for the method start log. Default: ```{0}({1}) started.```
 * tkit.log.succeed - message format for the method succeed log. Default: ```{0}({1}):{2} [{3}s] succeed.```
 * tkit.log.failed - message format for the failed method log. Default: ```{0}({1}):{2} [{3}s] failed.```
 * tkit.log.futureStart - message format for the future return type method. Default: ```{0}({1}) future started.```
 
#### REST service configuration

The logs of the rest endpoint could you configure with these variables:
 * tkit.log.rs.start - rest endpoint start log. Default: ```{0} {1} [{2}] started.```
 * tkit.log.rs.succeed - rest endpoint end log. Default: ```{0} {1} [{2}s] finished [{3}-{4},{5}].```
 * tkit.log.rs.mdc - log rest endpoint information to the log as MDC keys.
 * tkit.log.rs.disable - disable rest endpoint log.
 * tkit.log.rs.header - comma separated list of `header_key=log_mdc_key`
  
#### REST client configuration 

For the rest client you can configure these variables:
 * tkit.log.rs.client.start - rest client start log. Default:  ```{0} {1} [{2}] started.```
 * tkit.log.rs.client.succeed - rest client end log. Default:  ```{0} {1} finished in [{2}s] with [{3}-{4},{5}].```
 * tkit.log.rs.client.mdc - log rest client information to the log as MDC keys.
 * tkit.log.rs.client.disable - disable rest client log.
 * tkit.log.rs.client.header - comma separated list of `header_key=log_mdc_key`
         
#### JSON configuration

JSON formatter configuration:
 * quarkus.tkit.log.console.json - enable or disable the JSON formatter.
 * quarkus.tkit.log.console.json.keys.mdc - comma separated list of mapping `mdc_key=root_key`.
 * quarkus.tkit.log.console.json.keys.group - comma separated list of MDC group prefix and root key`mdc_prefix=root_key`. Group all MDC keys with a prefix.
 * quarkus.tkit.log.console.json.keys.ignore - ignore key.
 * quarkus.tkit.log.console.json.keys.type - comma separated list of key and type `key=long`. Types are `int`,`long` and `double`.
         
#### Configuration of external libraries

To configure external libraries you can use this configuration pattern for method level
<class>.<method>/tkit-log/trace=true|false
<class>.<method>/tkit-log/log=true|false

or for the class level:
<class>/tkit-log/trace=true|false
<class>/tkit-log/log=true|false

For example:
org.tkit.test.Service.start/tkit-log/log=false
org.tkit.test.Service/tkit-log/log=false

The ```trace``` attribute will printout the stacktrace of the exception in the interceptor.

## Release

### Create a release

```bash
mvn semver-release:release-create
```

### Create a patch branch
```bash
mvn semver-release:patch-create -DpatchVersion=x.x.0
