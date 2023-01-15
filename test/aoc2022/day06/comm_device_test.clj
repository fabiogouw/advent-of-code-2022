(ns aoc2022.day06.comm-device-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day06.logic.comm-device :as logic]))

(deftest challenge
  (testing "Part 1 - How many characters need to be processed before the first start-of-packet marker is detected?"
    (let [input (slurp (io/resource "input-day06.txt"))]
      (is (= (->> input
                  (logic/start-of-packet)) 1042))))
  (testing "Part 2 - How many characters need to be processed before the first start-of-message marker is detected?"
    (let [input (slurp (io/resource "input-day06.txt"))]
      (is (= (->> input
                  (logic/start-of-message)) 2980))))  )