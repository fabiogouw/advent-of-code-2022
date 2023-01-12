(ns aoc2022.day04.section-assignment-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day04.adapter.section-assignment-text :as adapter]
            [aoc2022.day04.logic.section-assignment :as logic]))

(deftest challenge
  (testing "Part 1 - Sums how many times a section is fully overlapped"
    (let [input (slurp (io/resource "input-day04.txt"))]
      (is (= (->> input
                  (adapter/text->section-assignments)
                  (logic/fully-overlaps-count)) 500))))
  (testing "Part 2 - Sums how many times a section is overlapped (fully or partially)"
    (let [input (slurp (io/resource "input-day04.txt"))]
      (is (= (->> input
                  (adapter/text->section-assignments)
                  (logic/overlaps-count)) 815)))))