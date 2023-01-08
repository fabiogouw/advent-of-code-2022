(ns aoc2022.day04.adapter.section-assignment_test
  (:require [clojure.test :refer :all]
            [aoc2022.day04.adapter.section-assignment :as adapter]))

(def input-file "2-4,6-8
2-3,4-5")
(deftest conversion-in
  (testing "can convert text representation to a bunch of pairs of section assignments"
    (is (= (adapter/text->section-assignments input-file) [{:first-elf {:start 2 :end 4} :second-elf {:start 6 :end 8}}
                                                           {:first-elf {:start 2 :end 3} :second-elf {:start 4 :end 5}}]))))
