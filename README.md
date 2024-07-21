# snowflake-id-jmeter-plugin
Jmeter plugin that adds a function to generate unique snowflake ids (64-bit integer). Based on https://github.com/phxql/snowflake-id implementation. 
## How to build and install
Build project using `./gradlew clean build`,
then copy jars from `build/lib` to `lib` and from `build/lib/ext` to `lib/ext` in jmeter directory respectively.

## Usage
### In Jmeter scenario
To generate snowflake id just call `__snowflakeId()`
### Uniqueness in distributed mode
To guarantee uniqueness of generated ids in [distributed mode](https://jmeter.apache.org/usermanual/remote-test.html) you must provide unique
property `snowflake_generator_id` for each slave node. For example, one has 4 slaves: slave0, slave1, slave2, slave3,
and on each slave node jmeter-server is started with the command:
```
jmeter-server -Dserver.rmi.localport=localport -Dserver_port=server_port -q generator.properties
```
then on slave0 `generator.properties` contains `snowflake_generator_id=0`,
on slave1 `generator.properties` contains `snowflake_generator_id=1` and so forth. \
If you do not provide `snowflake_generator_id`
then `snowflake_generator_id=0` will be used by default on each slave and there will be duplicates. \
`snowflake_generator_id` must be between 0 and 1023 because the plugin is using the default configuration
of snowflake-id library https://github.com/phxql/snowflake-id#default-settings.
### Use cases
It may be useful to generate unique 64-bit integers on the fly when one can't generate csv pool with
unique ids, split it between Jmeter slaves, and at the same time built-in functions [__UUID()](https://jmeter.apache.org/usermanual/functions.html#__UUID)
or [__Random(,)](https://jmeter.apache.org/usermanual/functions.html#__Random) are not appropriate.
### Links
Library that is used to generate snowflake ids: https://github.com/phxql/snowflake-id \
About snowflake ids: https://en.wikipedia.org/wiki/Snowflake_ID
### TODO
add tests
 