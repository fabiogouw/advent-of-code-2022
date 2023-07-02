(ns aoc2022.day10.cycle-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [aoc2022.day10.adapter.cycle-text :as adapter]
            [aoc2022.day10.logic.cycle :as logic]))

(deftest challenge
  (testing "Part 1 - What is the sum of these six signal strengths? (example)"
    (let [input (slurp (io/resource "input-day10-sample.txt"))]
      (is (= (-> input
                 adapter/text->signal-stream
                 ((partial logic/signal-strength 1))
                 logic/signal-strength-summed) 13140))))
  (testing "Part 1 - What is the sum of these six signal strengths?"
    (let [input (slurp (io/resource "input-day10.txt"))]
      (is (= (-> input
                 adapter/text->signal-stream
                 ((partial logic/signal-strength 1))
                 logic/signal-strength-summed) 14540))))
  (testing "Part 2 - What eight capital letters appear on your CRT? (example)"
    (let [input (slurp (io/resource "input-day10-sample.txt"))
          crt   (slurp (io/resource "input-day10-crt-sample.txt"))]
      (is (= (-> input
                 adapter/text->signal-stream
                 ((partial logic/signal-strength 1))
                 adapter/position->text) crt))))
  (testing "Part 2 - What eight capital letters appear on your CRT? (example)"
    (let [input (slurp (io/resource "input-day10.txt"))
          crt   (slurp (io/resource "input-day10-crt-answer.txt"))]
      (is (= (-> input
                 adapter/text->signal-stream
                 ((partial logic/signal-strength 1))
                 adapter/position->text) crt)))))