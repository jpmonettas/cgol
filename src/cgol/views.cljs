(ns cgol.views
    (:require [re-frame.core :as re-frame]))

(defn main-panel []
  [:div 
   [:span (str "Great!" @(re-frame/subscribe [:counter]))]
   [:button {:on-click #(re-frame/dispatch [:increment-counter])} "Increment"]])
