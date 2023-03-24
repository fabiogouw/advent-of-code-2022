(ns aoc2022.day09.adapter.rope-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day09.adapter.rope-text :as adapter]))

(def text-input "R 2
R 4
L 1
D 1")
(deftest conversion
  (testing "can convert string to a list of all movements"
    (is (= (adapter/text->rope text-input) '("R" "R" "R" "R" "R" "R" "L" "D"))))
  (testing "can convert string to a list of all movements"
    (is (= (adapter/text->rope "R 10") '("R" "R" "R" "R" "R" "R" "R" "R" "R" "R")))))