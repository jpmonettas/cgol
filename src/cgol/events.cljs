(ns cgol.events 
    (:require [re-frame.core :as re-frame :refer [debug after]]
              [cgol.db :as db]
              [clojure.spec.alpha :as s]))

(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (.log js/console "Spec check failed: " explain-data)
      (throw (ex-info (str "Spec check failed: " explain-data) explain-data)))))

(def validate-spec-mw
  (if goog.DEBUG
    (after (partial check-and-throw ::db/db))
    []))

(re-frame/reg-event-db
 :initialize-db
 [validate-spec-mw debug]
 (fn  [_ _]
   db/app-db))

(re-frame/reg-event-db
 :increment-counter
 [validate-spec-mw]
 (fn  [db _]
   (update db :counter inc)))
