# 1.3.0

JSON Log: add support for message splitting, useful for Docker logs on many envs, as they have a 16KB limit on length.
To enable it set property: `quarkus.tkit.log.console.json.splitStacktracesAfter=10000`. This would force the json formatter to split any stacktraces into chunks, each with the same message header.