(ns aoc2022.day10.adapter.cycle-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day10.adapter.cycle-text :as adapter]
            [schema.core :as s]))

(s/set-fn-validation! true)
(def text-input "noop
addx 3
addx -5")
(deftest conversion
  (testing "can convert string to a list of all movements"
    (is (= (adapter/text->signal-stream text-input) [ {:cycle 1 :value-to-add 0}
                                                      {:cycle 2 :value-to-add 0}
                                                      {:cycle 3 :value-to-add 3}
                                                      {:cycle 4 :value-to-add 0}
                                                      {:cycle 5 :value-to-add -5}]))))