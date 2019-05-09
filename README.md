# crux-reset-bug

## Repro steps

1. Ensure Kafka is running on localhost:9092 (or adjust
   `crux-reset-bug.demo/src/config.edn` to point at a live Kafka instance.)
2. `cd crux-reset-bug.demo/`
3. `clj -A:dev`
4. `(dev)`
5. `(go)` Start the system, which is just crux and
   `crux-reset-bug.demo/crux-test`, which registers a function var that checks
   the crux system it's passed.
6. `(crux-reset-bug.demo/check-crux)` This lists the number of entities found in
   crux, and attempts to sync the crux system with a 5 minute timeout.
   
## Example Output

```
Clojure 1.10.0
user=> (dev)
[Edge] Loading Clojure code, please wait...
[Edge] Enter (go) to start the dev system
#object[clojure.lang.Namespace 0x408980aa "dev"]
dev=> (go)
16:28:10.068 WARN  crux.kv.memdb [crux.bootstrap.cluster-node.main-thread]
Using fsync on MemKv has no effect.
[Edge] Now make code changes, then enter (reset) here
:initiated
dev=> (crux-reset-bug.demo/check-crux)
Found 0 records
Attempting sync (5 minute timeout)
Execution error (NullPointerException) at crux.tx/await-no-consumer-lag$fn (tx.clj:406).
null
dev=>

```
