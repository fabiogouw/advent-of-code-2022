(ns aoc2022.day01.calories-counting-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [aoc2022.day01.logic.calorie :as logic]
            [aoc2022.day01.adapters.calories-text :as adapter]))

(deftest challenge
  (testing "Part 1- How many total Calories is that Elf carrying?"
    (let [input (slurp (io/resource "input-day01.txt"))]
      (is (= (->> input
                  (adapter/text->map)
                  (logic/most-calories)) 70509))))
  (testing "Part 2- How many Calories are those Elves carrying in total?"
    (let [input (slurp (io/resource "input-day01.txt"))]
      (is (= (->> input
                  (adapter/text->map)
                  (logic/top-3-most-calories)) 208567))))  )