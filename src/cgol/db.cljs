(ns cgol.db
  (:require [clojure.spec.alpha :as s]))

(s/def ::counter int?)
(s/def ::db (s/keys :req-un [::counter]))

(def app-db {:counter 0})
