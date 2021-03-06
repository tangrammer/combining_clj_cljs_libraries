(defproject myproject "0.1.0-SNAPSHOT"
  :description "Combining Clojure and ClojureScript Libraries"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [speclj "2.9.4"]]

  :plugins [[speclj "2.9.4"]]


  :profiles {
             :dev {
                   }

             :clj {
                    :source-paths ["src/clj"]
                    :test-paths ["spec/clj"]
                   }

             :cljs {:dependencies [[org.clojure/clojurescript "0.0-2014"]
                                   [org.clojure/tools.reader "0.7.10"]
                                   [lein-cljsbuild "1.0.2"]
                                   [speclj "2.9.4"]]
                    :plugins [[lein-cljsbuild "1.0.2"]
                              [speclj "2.9.4"]]

                    :src-paths ["src/cljs"]

                    :cljsbuild ~(let [run-specs ["bin/speclj" "target/tests.js"]]
                                  {:builds
                                   {:dev {:source-paths ["src/cljs"  "spec/cljs"]
                                          :compiler {:output-to "target/tests.js"
                                                     :pretty-print true}
                                          :notify-command run-specs
                                          }}
                                   :test-commands {"test" run-specs}})

                    }
             }
  )
