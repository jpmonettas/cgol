(ns cgol.views
  (:require [re-frame.core :as re-frame]
            [cgol.world :as world]))

(defn world [w zoom]
  [:div.world {:style {:zoom (str zoom "%")}}
   (for [y (range (world/size w))]
     [:div.row {:key y} 
      (for [x (range (world/size w))]
        [:div.cell {:key x
                    :class (if (world/alive? (world/cell w x y)) "alive" "dead")
                    :on-click #(re-frame/dispatch [:cell-click x y])}])])])

(defn main-panel []
  (let [w @(re-frame/subscribe [:world])
        zoom @(re-frame/subscribe [:zoom])
        running? @(re-frame/subscribe [:running?])]
    [:div
     [:div
      (if running?
        [:button {:on-click #(re-frame/dispatch [:stop])} "Stop"]
        [:button {:on-click #(re-frame/dispatch [:run])} "Run"])
      [:button {:on-click #(re-frame/dispatch [:tick])} "Step"]]
     [world w zoom]]))
