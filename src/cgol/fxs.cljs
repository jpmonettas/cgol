(ns cgol.fxs
  (:require [goog.Timer]
            [goog.events]
            [re-frame.core :as re-frame]))

(defonce running-timer nil)

(re-frame/reg-fx
 :start-running-timer
 (fn [_]
   (when running-timer (.dispose running-timer))
   (set! running-timer (goog.Timer. 10))
   (.start running-timer)
   (.listen goog.events running-timer goog.Timer/TICK #(re-frame/dispatch [:tick]))))

(re-frame/reg-fx
 :stop-running-timer
 (fn [_]
   (when running-timer
     (.stop running-timer))))
