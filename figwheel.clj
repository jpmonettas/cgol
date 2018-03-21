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
                :figwheel {:on-jsload "cgol.core/mount-root"}
                :compiler {:main "cgol.core"
                           :asset-path "js/out",
                           :optimizations :none
                           :preloads ['day8.re-frame-10x.preload 'devtools.preload]
                           :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true
                                             "goog.DEBUG" true}
                           :output-to "resources/public/js/cgol.js",
                           :output-dir "resources/public/js/out",
                           :source-map-timestamp true}}]})

;; Start a repl
(ra/cljs-repl)
