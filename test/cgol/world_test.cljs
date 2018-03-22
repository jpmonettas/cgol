(ns cgol.world-test
  (:require [cgol.world :as world]
            [cljs.test :refer-macros [deftest is testing run-tests]]))
 
(deftest alive-test
  (let [world '[[x _ _]
                [x _ _]
                [_ _ _]]]
    (is (world/alive? (world/cell world 0 0)))
    (is (world/alive? (world/cell world 0 1)))
    (is (not (world/alive? (world/cell world 0 2))))))
