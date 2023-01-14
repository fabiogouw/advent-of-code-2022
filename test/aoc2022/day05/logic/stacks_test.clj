(ns aoc2022.day05.logic.stacks-test
  (:require [clojure.test :refer :all]
            [aoc2022.day05.logic.stacks :as logic]))

(deftest final-stack
  (testing "should return the final state os the movements over the stacks"
    (is (= (logic/final-stack {:stacks    {1 [\Z \N]
                                           2 [\M \C \D]
                                           3 [\P]}
                               :commands  [{:movements 1 :from 2 :to 1}
                                           {:movements 3 :from 1 :to 3}
                                           {:movements 2 :from 2 :to 1}
                                           {:movements 1 :from 1 :to 2}]}), "CMZ"))
    ))
