(ns cgol.subs 
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :counter
 (fn [db]
   (:counter db)))
