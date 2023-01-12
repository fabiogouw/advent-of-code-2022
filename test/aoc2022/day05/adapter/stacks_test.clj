(ns aoc2022.day05.adapter.stacks-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day05.adapter.stacks-text :as adapter]))

(def input (slurp (io/resource "input-day05-sample.txt")))
(deftest conversion-in
  (testing "can convert the text representation to the map"
    (is (= (adapter/text->stacks input) {:stacks    {1 [\Z \N]
                                                     2 [\M \C \D]
                                                     3 [\P]}
                                         :commands  [{:movements 1 :from 2 :to 1}
                                                     {:movements 3 :from 1 :to 3}
                                                     {:movements 2 :from 2 :to 1}
                                                     {:movements 1 :from 1 :to 2}]}))))
