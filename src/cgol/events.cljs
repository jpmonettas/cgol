(ns cgol.events 
    (:require [re-frame.core :as re-frame :refer [debug after]]
              [cgol.db :as db]
              [clojure.spec.alpha :as s]
              [cgol.world :as world]
              [cgol.games :as games]))

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

(re-frame/reg-event-db
 :zoom-in
 [validate-spec-mw]
 (fn  [db _]
   (update db :zoom #(+ % 10))))

(re-frame/reg-event-db
 :zoom-out
 [validate-spec-mw]
 (fn  [db _]
   (if (< 10 (:zoom db))
     (update db :zoom #(- % 10))
     db)))

(re-frame/reg-event-db
 :cell-click
 [validate-spec-mw]
 (fn  [db [_ x y]]
   (update db :world (fn [w]
                       (if (world/alive? (world/cell w x y))
                         (world/kill w x y)
                         (world/make-alive w x y))))))

(re-frame/reg-event-db
 :tick
 []
 (fn [db _]
   (update db :world games/step-cgol)))

(re-frame/reg-event-fx
 :run
 []
 (fn [{:keys [db]} _]
   {:db (assoc db :running? true)
    :start-running-timer nil}))

(re-frame/reg-event-fx
 :stop
 []
 (fn [{:keys [db]} _]
   {:db (assoc db :running? false)
    :stop-running-timer nil}))
 
(comment

  (re-frame/dispatch [:tick])
  )
