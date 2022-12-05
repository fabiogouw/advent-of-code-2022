(ns aoc2022.day02.logic.jokenpo-test
  (:require [clojure.test :refer :all]
            [aoc2022.day02.logic.jokenpo :as logic]))

(deftest logic
  (testing "sum all moves"
    (let [input [{:opponent :rock     :you :paper}
                 {:opponent :paper    :you :rock}
                 {:opponent :scissors :you :scissors}]]
     (is (= (logic/moves-summed input) 15)))))
