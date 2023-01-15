(ns aoc2022.day06.logic.comm-device-test
  (:require [clojure.test :refer :all]
            [aoc2022.day06.logic.comm-device :as logic]))

(deftest signal
  (testing "finding the start of the datastream"
    (testing "example 1"
      (is (= (logic/start-of-packet "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7)))
    (testing "example 2"
      (is (= (logic/start-of-packet "bvwbjplbgvbhsrlpgdmjqwftvncz") 5)))
    (testing "example 3"
      (is (= (logic/start-of-packet "nppdvjthqldpwncqszvftbrmjlhg") 6)))
    (testing "example 4"
      (is (= (logic/start-of-packet "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10)))
    (testing "example 5"
      (is (= (logic/start-of-packet "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))
    (testing "empty example"
      (is (= (logic/start-of-packet "") -1)))
    (testing "no start example"
      (is (= (logic/start-of-packet "xxxx") -1)))))
