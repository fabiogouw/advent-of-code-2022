(ns aoc2022.day09.logic.rope-test
  (:require [clojure.test :refer :all]
            [aoc2022.day09.logic.rope :as logic]))

(deftest farther?
  (testing "head close to tail"
    (are [head tail result] (= (logic/farther? head tail) result)
                            {:x 1 :y 1} {:x 1 :y 1} false
                            {:x 1 :y 1} {:x 2 :y 2} false))
  (testing "head far from tail"
    (are [head tail result] (= (logic/farther? head tail) result )
                            {:x -10 :y 1} {:x -2 :y 1} true
                            {:x 1 :y 1} {:x 3 :y 2} true)))

(deftest move
  (testing "starting move (head and tail in the same position)"
    (are [rope direction result] (= (logic/move rope direction) result)
                                 {:head {:x 0 :y 0} :tail {:x 0 :y 0}} "L" {:head {:x -1 :y 0} :tail {:x 0 :y 0}}
                                 {:head {:x 0 :y 0} :tail {:x 0 :y 0}} "R" {:head {:x 1 :y 0} :tail {:x 0 :y 0}}
                                 {:head {:x 0 :y 0} :tail {:x 0 :y 0}} "U" {:head {:x 0 :y 1} :tail {:x 0 :y 0}}
                                 {:head {:x 0 :y 0} :tail {:x 0 :y 0}} "D" {:head {:x 0 :y -1} :tail {:x 0 :y 0}}))
  (testing "horizontal moves"
    (are [rope direction result] (= (logic/move rope direction) result)
                                 {:head {:x 1 :y 0} :tail {:x 0 :y 0}} "R" {:head {:x 2 :y 0} :tail {:x 1 :y 0}}
                                 {:head {:x -1 :y 0} :tail {:x 0 :y 0}} "L" {:head {:x -2 :y 0} :tail {:x -1 :y 0}}))
  (testing "vertical moves"
    (are [rope direction result] (= (logic/move rope direction) result)
                                 {:head {:x 0 :y 1} :tail {:x 0 :y 0}} "U" {:head {:x 0 :y 2} :tail {:x 0 :y 1}}
                                 {:head {:x 0 :y -1} :tail {:x 0 :y 0}} "D" {:head {:x 0 :y -2} :tail {:x 0 :y -1}})))

(deftest tail-position-count
  (testing "count all tail distinct positions"
    (let [moves (concat
                  (take 4 (repeat "R"))
                  (take 4 (repeat "U"))
                  (take 3 (repeat "L"))
                  (take 1 (repeat "D"))
                  (take 4 (repeat "R"))
                  (take 1 (repeat "D"))
                  (take 5 (repeat "L"))
                  (take 2 (repeat "R")))]
      (is (= (logic/tail-move-count {:head {:x 0 :y 1} :tail {:x 0 :y 0}} moves) 13)))))