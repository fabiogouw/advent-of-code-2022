(ns aoc2022.day05.stacks-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day05.adapter.stacks-text :as adapter]
            [aoc2022.day05.logic.stacks :as logic]))

(deftest challenge
  (testing "Part 1 - After the rearrangement procedure completes, what crate ends up on top of each stack?"
    (let [input (slurp (io/resource "input-day05.txt"))]
      (is (= (->> input
                  (adapter/text->stacks)
                  (logic/final-stack)) "SPFMVDTZT")))))
