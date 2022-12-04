(ns aoc2022.day01.logic.calorie-test
  (:require [clojure.test :refer :all]
            [aoc2022.day01.logic.calorie :as logic]))

(deftest logic
  (testing "find most calories"
    (let [input [{:elf 1 :food [100 200 300]}
                 {:elf 2 :food [100]}]]
     (is (= (logic/most-calories input) 600)))))
