(ns aoc2022.day10.logic.cycle-test
  (:require [clojure.test :refer :all]
            [aoc2022.day10.logic.cycle :as logic]
            [schema.core :as s]))

(s/set-fn-validation! true)

(deftest signal-strength
  (testing "calculate signal strength"
    (is (= (logic/signal-strength 1 [{:cycle 1 :value-to-add 0}
                                     {:cycle 2 :value-to-add 0}
                                     {:cycle 3 :value-to-add 3}
                                     {:cycle 4 :value-to-add 0}
                                     {:cycle 5 :value-to-add -5}]) [{:cycle 1 :x-value-at-start 1 :x-value-at-end 1 :signal-strength 1}
                                                                    {:cycle 2 :x-value-at-start 1 :x-value-at-end 1 :signal-strength 2}
                                                                    {:cycle 3 :x-value-at-start 1 :x-value-at-end 4 :signal-strength 3}
                                                                    {:cycle 4 :x-value-at-start 4 :x-value-at-end 4 :signal-strength 16}
                                                                    {:cycle 5 :x-value-at-start 4 :x-value-at-end -1 :signal-strength 20}]))))