(ns cgol.subs 
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :world
 (fn [db]
   (:world db)))

(re-frame/reg-sub
 :zoom
 (fn [db]
   (:zoom db)))

(re-frame/reg-sub
 :running?
 (fn [db]
   (:running? db)))
