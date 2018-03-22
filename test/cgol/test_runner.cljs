(ns cgol.test-runner
  (:require [cgol.games-test]
            [cgol.world-test]
            [cljs.test :refer-macros [run-tests]]))

(defn run-all-tests []
 (run-tests 'cgol.games-test 'cgol.world-test))


