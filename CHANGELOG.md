# 1.4.2

Add: LogFriendlyException interface to handle special exception logging, support for `quarkus.tkit.log.customdata.prefix` property, support for `quarkus.tkit.log.mdc.errorKey` property
Fix: clear data when request is finished

# 1.4.1

Fix: reduce the priority of the logging interceptor
Fix: make the ignorePattern config value optional

# 1.4.0

Feat: add support for ignore pattern configuration. Set property `tkit.log.ignore.pattern` to specify regex of excluded classes.
# 1.3.0

JSON Log: add support for message splitting, useful for Docker logs on many envs, as they have a 16KB limit on length.
To enable it set property: `quarkus.tkit.log.console.json.splitStacktracesAfter=10000`. This would force the json formatter to split any stacktraces into chunks, each with the same message header.
