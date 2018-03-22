(ns cgol.games
  (:require [cgol.world :as world]))

(def coords-seq 
  (memoize (fn coords-seq [size]
     (for [x (range size) y (range size)] [x y])))) 

(defn neighbors [world x y] 
  (let [size (world/size world)]
    (for [ix (range (max 0 (dec x)) (min (+ 2 x) size))
          iy (range (max 0 (dec y)) (min (+ 2 y) size))
          :when (not= [x y] [ix iy])]
      (world/cell world ix iy))))  


(defn step-cgol [w]
  (reduce (fn step-cgol-cell-reddd [r [x y]]
            (let [alives-count (->> (neighbors w x y)
                                    (filter world/alive?)
                                    count)]
              (cond

                (and (not (world/alive? (world/cell w x y)))
                     (= alives-count 3))
                (world/make-alive r x y)

                (and (world/alive? (world/cell w x y))
                     (or (< alives-count 2)
                         (> alives-count 3)))
                (world/kill r x y)
                 
                true r)))
          w
          (coords-seq (world/size w)))) 

