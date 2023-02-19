(ns aoc2022.day07.filesystem-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day07.adapter.filesystem-text :as adapter]
            [aoc2022.day07.logic.filesystem :as logic]))

(deftest challenge
  (testing "Part 1 - What is the sum of the total sizes of those directories?"
    (let [input (slurp (io/resource "input-day07.txt"))]
      (is (= (-> input
                 adapter/text->filesystem-structure
                 (logic/total-size-at-most 100000)) 2104783))))
  (testing "Part 2 - What is the total size of the smallest directory we need to delete to have enough available space for update?"
    (let [input (slurp (io/resource "input-day07.txt"))]
      (is (= (-> input
                 adapter/text->filesystem-structure
                 (logic/smallest-directory-size-enough-for-update 70000000 30000000)) 5883165)))))