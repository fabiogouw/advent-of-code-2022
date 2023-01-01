(ns aoc2022.day02.jokenpo-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [aoc2022.day02.logic.jokenpo :as logic]
            [aoc2022.day02.adapters.jokenpo-text :as adapter]))

(deftest challenge
  (testing "Part 1 - What would your total score be if everything goes exactly according to your strategy guide?"
    (let [input (slurp (io/resource "input-day02.txt"))]
      (is (= (->> input
                  (adapter/text->map-move)
                  (logic/moves-summed)) 8392))))
  (testing "Part 2 - What would your total score be if everything goes exactly according to your strategy guide"
    (let [input (slurp (io/resource "input-day02.txt"))]
      (is (= (->> input
                  (adapter/text->map-result)
                  (logic/result-summed)) 10116)))))