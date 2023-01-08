(ns aoc2022.day04.logic.section-assignment_test
  (:require [clojure.test :refer :all]
            [aoc2022.day04.logic.section-assignment :as logic]))

(deftest fully-overlaps-count
  (testing "count how many section assignment pair fully overlaps them"
    (is (= (logic/fully-overlaps-count [{:first-elf {:start 2 :end 4} :second-elf {:start 6 :end 8}}
                                        {:first-elf {:start 2 :end 3} :second-elf {:start 4 :end 5}}
                                        {:first-elf {:start 5 :end 7} :second-elf {:start 7 :end 9}}
                                        {:first-elf {:start 2 :end 8} :second-elf {:start 3 :end 7}}
                                        {:first-elf {:start 6 :end 6} :second-elf {:start 4 :end 6}}
                                        {:first-elf {:start 2 :end 6} :second-elf {:start 4 :end 8}}]) 2))))

(deftest fully-overlaps?
  (testing "totally apart"
    (is (= (logic/fully-overlaps? {:first-elf {:start 2 :end 4} :second-elf {:start 6 :end 8}}) false)))
  (testing "fully contains"
    (is (= (logic/fully-overlaps? {:first-elf {:start 2 :end 8} :second-elf {:start 4 :end 6}}) true)))
  (testing "same range overlaps"
    (is (= (logic/fully-overlaps? {:first-elf {:start 2 :end 8} :second-elf {:start 2 :end 8}}) true)))
  (testing "contains only the start"
    (is (= (logic/fully-overlaps? {:first-elf {:start 2 :end 4} :second-elf {:start 3 :end 5}}) false)))
  (testing "contains only the end"
    (is (= (logic/fully-overlaps? {:first-elf {:start 2 :end 4} :second-elf {:start 1 :end 3}}) false))))