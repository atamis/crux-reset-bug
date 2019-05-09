(ns crux-reset-bug.demo
  (:require [integrant.core :as ig]
            [crux.api :as crux]
            )
  (:import [java.time Duration])
  )

#_
(crux-reset-bug.demo/check-crux)

(defmethod ig/init-key ::crux-test
  [_ system]
  (defn check-crux
    []
    (let [db (crux/db system)]
      (println "Found"
               (count (crux/q db '{:find [id]
                                   :where [[_ :crux.db/id id]]}))
               "records")
      (println "Attempting sync (5 minute timeout)")
      (crux/sync system (Duration/ofMinutes 5))
      )
    )
  )
