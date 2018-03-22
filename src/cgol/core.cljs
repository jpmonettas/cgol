(ns cgol.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cgol.events]
            [cgol.subs]
            [cgol.fxs]
            [cgol.views :as views]
            [goog.events]
            [goog.events.EventType]))

(defn dev-setup []
  (enable-console-print!)
  (println "dev mode"))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  
  (.listen goog.events js/document goog.events.EventType/WHEEL
           (fn [e] (let [down? (-> e .-event_ .-deltaY pos?)]
                     (re-frame/dispatch (if down? [:zoom-out] [:zoom-in])))))
  
  (dev-setup)
  (mount-root))

