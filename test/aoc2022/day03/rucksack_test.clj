(ns aoc2022.day03.rucksack_test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [aoc2022.day03.adapters.rucksack-text :as adapter]
            [aoc2022.day03.logic.rucksack :as logic]))

(deftest challenge
  (testing "Part 2 - What is the sum of the priorities of those item types?"
    (let [input (slurp (io/resource "input-day03.txt"))]
      (is (= (->> input
                  (adapter/input->rucksacks)
                  (logic/sum-repeated-priority)) 7872)))))