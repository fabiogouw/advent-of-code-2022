(ns aoc2022.day01.logic.calorie-test
  (:require [clojure.test :refer :all]
            [aoc2022.day01.logic.calorie :as logic]))

(deftest logic
  (testing "find most calories"
    (let [input [{:elf 1 :food [100 200 300]}
                 {:elf 2 :food [100]}]]
     (is (= (logic/most-calories input) 600))))
  (testing "find sum of top 3 most carrying calories elves"
    (let [input [{:elf 1 :food [100 200]}
                 {:elf 2 :food [80 20]}
                 {:elf 3 :food [90 3]}
                 {:elf 4 :food [1000 1500 500]}
                 {:elf 5 :food [10]}]]
      (is (= (logic/top-3-most-calories input) 3400))))  )
