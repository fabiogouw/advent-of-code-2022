(ns aoc2022.day08.forest-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day08.adapter.forest-text :as adapter]
            [aoc2022.day08.logic.forest :as logic]))

(deftest challenge
  (testing "Part 1 - how many trees are visible from outside the grid?"
    (let [input (slurp (io/resource "input-day08.txt"))]
      (is (= (-> input
                 adapter/text->forest
                 logic/all-visible-trees) 1662)))))