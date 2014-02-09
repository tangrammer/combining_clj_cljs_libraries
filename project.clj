(defproject myproject "0.1.0-SNAPSHOT"
  :description "Combining Clojure and ClojureScript Libraries"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [speclj "2.9.4"]
                 [com.keminglabs/cljx "0.3.1"]]

  :plugins [[speclj "2.9.4"]]

  :aliases {  "clj-test" ["with-profile","clj","spec"]
              "clj-clean-test" ["do" "clean," "clj-test"]

              "cljs-test" ["do" "cljx," "with-profile" "cljs" "cljsbuild" "test"]
              "cljs-clean-test" ["do" "clean," "cljs-test"]

              "all-tests" ["do" "clean," "clj-test," "cljs-test"]
              "combined-tests" ["do" "clean," "with-profile" "combined" "spec," "with-profile" "combined" "cljsbuild" "test"]

              "install" ["do" "clean," "with-profile" "combined" "install"]
            }


  :profiles {
             :combined {
                    :dependencies [[org.clojure/math.numeric-tower "0.0.4"]
                                   [org.clojure/clojurescript "0.0-2014"] ;necessary or current version of speclj
                                   [org.clojure/tools.reader "0.7.10"] ;necessary or current version of speclj
                                   [lein-cljsbuild "1.0.2"]]

                    :source-paths ["src/clj", "target/generated/src/clj"]
                    :resource-paths ["src/cljs", "target/generated/src/cljs"]
                    :test-paths ["spec/clj", "target/generated/spec/clj"]

                    :cljsbuild ~(let [run-specs ["bin/speclj" "target/tests.js"]]
                                  {:builds
                                   {:dev {:source-paths ["src/cljs" 
                                                         "spec/cljs"
                                                         "target/generated/src/cljs"
                                                         "target/generated/spec/cljs"] 
                                          :compiler {:output-to "target/tests.js"
                                                     :pretty-print true}
                                          }}
                                   :test-commands {"test" run-specs}})
                   }

             :clj {
                    :dependencies [[org.clojure/math.numeric-tower "0.0.4"]]
                    :source-paths ["src/clj", "target/generated/src/clj"]
                    :test-paths ["spec/clj", "target/generated/spec/clj"]
                   }

             :cljs {
                    :dependencies [ [org.clojure/clojurescript "0.0-2014"] ;necessary or current version of speclj
                                   [org.clojure/tools.reader "0.7.10"] ;necessary or current version of speclj
                                   [lein-cljsbuild "1.0.2"] ]
                    :plugins [[lein-cljsbuild "1.0.2"]]

                    :source-paths ["src/clj"]

                    :cljsbuild ~(let [run-specs ["bin/speclj" "target/tests.js"]]
                                  {:builds
                                   {:dev {:source-paths ["src/cljs" 
                                                         "spec/cljs"
                                                         "target/generated/src/cljs"
                                                         "target/generated/spec/cljs"] 
                                          :compiler {:output-to "target/tests.js"
                                                     :pretty-print true}
                                          }}
                                   :test-commands {"test" run-specs}})

                    }
             }

  :hooks [cljx.hooks]
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/generated/src/clj"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/src/cljs"
                   :rules :cljs}
                  {:source-paths ["spec/cljx"]
                   :output-path "target/generated/spec/clj"
                   :rules :clj}
                  {:source-paths ["spec/cljx"]
                   :output-path "target/generated/spec/cljs"
                   :rules :cljs}]}
  )
