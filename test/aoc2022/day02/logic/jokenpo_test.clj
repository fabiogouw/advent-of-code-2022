(ns aoc2022.day02.logic.jokenpo-test
  (:require [clojure.test :refer :all]
            [aoc2022.day02.logic.jokenpo :as logic]))

(deftest logic
  (testing "sum all moves considering move types"
    (let [input [{:opponent :rock     :you :paper}
                 {:opponent :paper    :you :rock}
                 {:opponent :scissors :you :scissors}]]
     (is (= (logic/moves-summed input) 15))))
  (testing "sum all moves considering move result"
    (let [input [{:opponent :rock     :you :draw}
                 {:opponent :paper    :you :lose}
                 {:opponent :scissors :you :win}]]
      (is (= (logic/result-summed input) 12)))))
