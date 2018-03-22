(ns cgol.games-test
  (:require [cgol.games :as games]
            [cgol.world :as world]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest neighbors-test
  (let [world [[11 12 13 14]
               [21 22 23 24]
               [31 32 33 34]
               [41 42 43 44]]]
    (is (= (sort (games/neighbors world 0 0))
           (sort '(22 12 21))))
    (is (= (sort (games/neighbors world 3 3))
           (sort '(34 43 33))))
    (is (= (sort (games/neighbors world 1 1))
           (sort '(33 23 13 32 12 31 21 11))))))


(deftest step-cgol-test

  (testing "cell resurrection"
    (let [world '[[_ _ _ _]
                  [_ _ x _]
                  [_ x x _]
                  [_ _ _ _]]]
      (is (= (games/step-cgol world)
             '[[_ _ _ _]
               [_ x x _]
               [_ x x _]
               [_ _ _ _]]))))

  (testing "cell death"
    (let [world '[[_ _ _ _]
                  [_ x x _]
                  [_ _ _ _]
                  [_ _ _ _]]]
      (is (= (games/step-cgol world)
             '[[_ _ _ _]
               [_ _ _ _]
               [_ _ _ _]
               [_ _ _ _]]))))

  (testing "death and resurrection"
    (let [world '[[_ _ _ _]
                  [x x x _]
                  [_ _ _ _]
                  [_ _ _ _]]]
      (is (= (games/step-cgol world)
             '[[_ x _ _]
               [_ x _ _]
               [_ x _ _]
               [_ _ _ _]])))))
