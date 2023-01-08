(ns aoc2022.day03.rucksack_test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [aoc2022.day03.adapters.rucksack-text :as adapter]
            [aoc2022.day03.logic.rucksack :as logic]))

(deftest challenge
  (testing "Part 1 - Sums the priorities of the repeated items in the compartments of each rucksack"
    (let [input (slurp (io/resource "input-day03.txt"))]
      (is (= (->> input
                  (adapter/input->rucksacks)
                  (logic/sum-repeated-priority)) 7872))))
  (testing "Part 2 - Sums the priorities of the badges for each group (the repeated item that appears in the groups of three rucksacks"
    (let [input (slurp (io/resource "input-day03.txt"))]
      (is (= (->> input
                  (adapter/input->rucksacks)
                  (logic/badges-summed)) 2497)))))