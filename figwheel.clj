(require '[clojure.tools.nrepl.server :as server]
         '[figwheel-sidecar.repl-api :as ra]
         '[cider.nrepl :refer [cider-nrepl-handler]])

;; Start nrepl server with cider and piggieback
(server/start-server
 :handler cider-nrepl-handler 
 :port 7888)

;; Start figwheel
(ra/start-figwheel!
 {:figwheel-options {:css-dirs ["resources/public/css"]}
  :build-ids ["dev"]
  :all-builds [{:id "dev", 
                :source-paths ["src"],
                :figwheel true
                :compiler {:main "cgol.core"
                           :asset-path "js/out",
                           :optimizations :none
                           :output-to "resources/public/js/cogl.js",
                           :output-dir "resources/public/js/out",
                           :source-map-timestamp true}}]})

;; Start a repl
(ra/cljs-repl)
