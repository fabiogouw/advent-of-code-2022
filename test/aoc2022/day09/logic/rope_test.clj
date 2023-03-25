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
    (are [rope direction result] (= (logic/move-rope rope direction) result)
                                 [{:x 0 :y 0} {:x 0 :y 0}] "L" [{:x -1 :y 0} {:x 0 :y 0}]
                                 [{:x 0 :y 0} {:x 0 :y 0}] "R" [{:x 1 :y 0} {:x 0 :y 0}]
                                 [{:x 0 :y 0} {:x 0 :y 0}] "U" [{:x 0 :y 1} {:x 0 :y 0}]
                                 [{:x 0 :y 0} {:x 0 :y 0}] "D" [{:x 0 :y -1} {:x 0 :y 0}]))
  (testing "horizontal moves"
    (are [rope direction result] (= (logic/move-rope rope direction) result)
                                 [{:x 1 :y 0} {:x 0 :y 0}] "R" [{:x 2 :y 0} {:x 1 :y 0}]
                                 [{:x -1 :y 0} {:x 0 :y 0}] "L" [{:x -2 :y 0} {:x -1 :y 0}]))
  (testing "vertical moves"
    (are [rope direction result] (= (logic/move-rope rope direction) result)
                                 [{:x 0 :y 1} {:x 0 :y 0}] "U" [{:x 0 :y 2} {:x 0 :y 1}]
                                 [{:x 0 :y -1} {:x 0 :y 0}] "D" [{:x 0 :y -2} {:x 0 :y -1}])))

(deftest follow
  (testing "knot must stay as it is close to the previous knot"
    (are [follower-knot updated-previous-knot result] (= (logic/follow-knot follower-knot updated-previous-knot) result)
                                                      {:x 0 :y 0} {:x 1 :y 1} {:x 0 :y 0}
                                                      {:x 0 :y 0} {:x 1 :y 0} {:x 0 :y 0}
                                                      {:x 0 :y 0} {:x 0 :y 0} {:x 0 :y 0}))
  (testing "knot must follow its previous knot"
    (are [follower-knot updated-previous-knot result] (= (logic/follow-knot follower-knot updated-previous-knot) result)
                                                      {:x 0 :y 0} {:x 0 :y 2}   {:x 0 :y 1}
                                                      {:x 0 :y 0} {:x 0 :y -2}  {:x 0 :y -1}
                                                      {:x 0 :y 0} {:x 2 :y 0}   {:x 1 :y 0}
                                                      {:x 0 :y 0} {:x -2 :y 0}  {:x -1 :y 0}))
  (testing "knot must follow its previous knot diagonally"
    (are [follower-knot updated-previous-knot result] (= (logic/follow-knot follower-knot updated-previous-knot) result)
                                                      {:x 0 :y 0} {:x 2 :y 1}   {:x 1 :y 1}
                                                      {:x 0 :y 0} {:x 1 :y 2}   {:x 1 :y 1}
                                                      {:x 0 :y 0} {:x -2 :y 1}  {:x -1 :y 1}
                                                      {:x 0 :y 0} {:x -1 :y 2}  {:x -1 :y 1}
                                                      {:x 0 :y 0} {:x 2 :y -1}  {:x 1 :y -1}
                                                      {:x 0 :y 0} {:x 1 :y -2}  {:x 1 :y -1}
                                                      {:x 0 :y 0} {:x -2 :y -1} {:x -1 :y -1}
                                                      {:x 0 :y 0} {:x -1 :y -2} {:x -1 :y -1})))

(deftest tail-position-count
  (testing "count all tail distinct positions"
    (let [rope  (take 2 (repeat {:x 0 :y 0}))
          moves (concat
                  (take 4 (repeat "R"))
                  (take 4 (repeat "U"))
                  (take 3 (repeat "L"))
                  (take 1 (repeat "D"))
                  (take 4 (repeat "R"))
                  (take 1 (repeat "D"))
                  (take 5 (repeat "L"))
                  (take 2 (repeat "R")))]
      (is (= (logic/tail-move-count rope moves) 13))))
  (testing "count all tail distinct positions with a longer rope"
    (let [rope  (take 10 (repeat {:x 0 :y 0}))
          moves (concat
                  (take 4 (repeat "R"))
                  (take 4 (repeat "U"))
                  (take 3 (repeat "L"))
                  (take 1 (repeat "D"))
                  (take 4 (repeat "R"))
                  (take 1 (repeat "D"))
                  (take 5 (repeat "L"))
                  (take 2 (repeat "R")))]
      (is (= (logic/tail-move-count rope moves) 1))))  
  (testing "count all tail distinct positions with a longer rope with a larger example"
    (let [rope  (take 10 (repeat {:x 0 :y 0}))
          moves (concat
                  (take 5 (repeat "R"))
                  (take 8 (repeat "U"))
                  (take 8 (repeat "L"))
                  (take 3 (repeat "D"))
                  (take 17 (repeat "R"))
                  (take 10 (repeat "D"))
                  (take 25 (repeat "L"))
                  (take 20 (repeat "U")))]
      (is (= (logic/tail-move-count rope moves) 36)))))