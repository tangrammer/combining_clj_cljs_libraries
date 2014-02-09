(ns myproject.shared-file-spec
  (#+clj :require #+cljs :require-macros
         [speclj.core :refer [describe it should=]]
         [myproject.macros :as macros])
  (:require [speclj.core]
            [myproject.shared-file :as shared-file]
            ))

(describe "sample cljx file"

  (it "uses cljx files to generate tested code in clj an cljx"
      (should= 12 (shared-file/multiply 3 4)))

  (it "finds the absolute value of -100"
      (should= 1 (shared-file/abs-diff -101 100)))

  (it "finds absolute value using macro"
      (should= 2 (macros/abs -2)))
)