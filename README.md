# tkit-quarkus-log

tkit quarkus logger extension

[![License](https://img.shields.io/badge/license-Apache--2.0-green?style=for-the-badge&logo=apache)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/org.tkit.quarkus/tkit-quarkus-log?logo=java&style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/org.tkit.quarkus/tkit-quarkus-log)

### Maven configuration

```xml
<dependency>
    <groupId>org.tkit.quarkus</groupId>
    <artifactId>tkit-quarkus-log</artifactId>
    <version>x.y.z-SNAPSHOT</version>
<dependency>
```
The interceptor for the logger will be activated automatically.

#### Custom log entry for the parameter

```java
@LoggerParam(classes = {AmqpMessage.class})
public static String logAmqpMessage(Object message) {
    AmqpMessage a = (AmqpMessage) message;
    return "AmqpMessage[" + a.getMessageId() + "," + a.getDeliveryCount() + "]";
}
```

The ```@LoggerParam``` annotation has this parameter:
 * classes - the parameter will be use for this classes.
 * assignableFrom - the parameter will be use for all assignable classes.
 * priority - the priority of this logger parameter.

To exclude the parameter from the log you can use the annotation ```@LoggerExclude```.
There is a parameter ```mask``` is not set the parameter name will be written in the log.
If you specified the value for the parameter mask it will be the value written in the log.

### Configuration

Only the defined packages in the configuration will be add to the interceptor (default: org.tkit)
For example:
```properties
tkit.logger.binding.includes=com.acme
tkit.logger.binding.includes=org.tkit
```

#### Method start and succeed log format

To configure the message format of the start, succeed, failed and future you can use this properties:

 * org.tkit.logger.result.void - text for the resulct void type. Default: ```void```
 * org.tkit.logger.start - message format for the method start log. Default: ```{0}({1}) started.```
 * org.tkit.logger.succeed - message format for the method succeed log. Default: ```{0}({1}):{2} [{3}s] succeed.```
 * org.tkit.logger.failed - message format for the failed method log. Default: ```{0}({1}):{2} [{3}s] failed.```
 * org.tkit.logger.futureStart - message format for the future return type method. Default: ```{0}({1}) future started.```
        
The logs of the rest endpoint could you configure with these variables:

 * org.tkit.logger.rs.start - rest endpoint start log. Default: ```{0} {1} [{2}] started.```
 * org.tkit.logger.rs.succeed - rest endpoint end log. Default: ```{0} {1} [{2}s] finished [{3}-{4},{5}].```
 
For the rest client you can configure these variables:
        
 * org.tkit.logger.rs.client.start - rest client start log. Default:  ```{0} {1} [{2}] started.```
 * org.tkit.logger.rs.client.succeed - rest client end log. Default:  ```{0} {1} finished in [{2}s] with [{3}-{4},{5}].```
        
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
