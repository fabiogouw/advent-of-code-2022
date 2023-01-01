(ns aoc2022.day03.adapters.rucksack-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day03.adapters.rucksack-text :as adapter]))

(def input-line "ttgJtRGJQctTZtZT")

(def input-file "aa
bb")
(deftest conversion-in
  (testing "can convert a line of content to map internal rucksack representation"
    (is (= (adapter/line->rucksack input-line) {:first-compartment [\t \t \g \J \t \R \G \J]
                                                :second-compartment [\Q \c \t \T \Z \t \Z \T]})))
  (testing "can convert a bunch of lines into an seq of rucksacks"
    (is (= (adapter/input->rucksacks input-file) [{:first-compartment [\a] :second-compartment [\a]}
                                                  {:first-compartment [\b] :second-compartment [\b]}]))))