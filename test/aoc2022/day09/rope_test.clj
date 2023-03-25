(ns aoc2022.day09.rope-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day09.adapter.rope-text :as adapter]
            [aoc2022.day09.logic.rope :as logic]))

(deftest challenge
  (testing "Part 1 - How many positions does the tail of the rope visit at least once?"
    (let [input (slurp (io/resource "input-day09.txt"))]
      (is (= (-> input
                 adapter/text->rope
                 ((partial logic/tail-move-count [{:x 0 :y 0} {:x 0 :y 0}]))) 5907)))))