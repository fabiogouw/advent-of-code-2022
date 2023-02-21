(ns aoc2022.day08.logic.forest-test
  (:require [clojure.test :refer :all]
            [aoc2022.day08.logic.forest :as logic]))

(def sample [ [3 0 3 7 3]
              [2 5 5 1 2]
              [6 5 3 3 2]
              [3 3 5 4 9]
              [3 5 3 9 0]])
(deftest all-visible-trees
  (testing "gets all the visible trees from the matrix"
    (is (= (logic/all-visible-trees sample) 21))))