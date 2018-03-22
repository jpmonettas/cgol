(ns cgol.world)


(defprotocol WorldP
  (make-alive [_ x y])
  (kill [_ x y])
  (cell [_ x y])
  (size [_]))

(defn alive? [x]
  (= x 'x))

(extend-protocol WorldP
  cljs.core/PersistentVector
 
  (make-alive [world x y]
    (assoc-in world [y x] 'x))
  
  (kill [world x y]
    (assoc-in world [y x] '_))
  
  (cell [world x y]
    (get-in world [y x]))
  
  (size [world]
    (count world)))

(defn create-genesis-vector-world [size]
  (into [] (repeat size (into [] (repeat size '_)))))




