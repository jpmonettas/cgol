(ns cgol.db
  (:require [clojure.spec.alpha :as s]
            [cgol.world :as world]))

(s/def ::db any?)

(def app-db {:world (world/create-genesis-vector-world 50)
             :zoom 50
             :running? false})
