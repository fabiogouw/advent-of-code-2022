(ns aoc2022.day08.adapter.forest-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day08.adapter.forest-text :as adapter]))

(def input "30373
25512
65332
33549
35390")
(deftest conversion-in
  (testing "can convert text representation to a bunch of pairs of section assignments"
    (is (= (adapter/text->forest input) [[3 0 3 7 3]
                                         [2 5 5 1 2]
                                         [6 5 3 3 2]
                                         [3 3 5 4 9]
                                         [3 5 3 9 0]]))))