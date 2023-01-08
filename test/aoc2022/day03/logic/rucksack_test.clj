(ns aoc2022.day03.logic.rucksack-test
  (:require [clojure.test :refer :all]
            [aoc2022.day03.logic.rucksack :as logic]))

(deftest priority
  (testing "converts all priorities given the numbers"
    (is (= [16 38 42 22 20 19] (map logic/priority [\p \L \P \v \t \s])))))

(deftest repeated
  (testing "find repeated item type in map"
    (is (= \a (first (logic/repeated {:first-compartment [\a \b] :second-compartment [\c \a]})))))
  (testing "find repeated item type comparing two sequences"
    (is (= [\b \c] (logic/repeated [\a \b \c] [\b \c \d])))))

(deftest sum
  (testing "sums the priorities of the item types that are repeated"
    (is (= 157 (logic/sum-repeated-priority [{:first-compartment (seq (char-array "vJrwpWtwJgWr"))     :second-compartment (seq (char-array "hcsFMMfFFhFp"))}
                                             {:first-compartment (seq (char-array "jqHRNqRjqzjGDLGL")) :second-compartment (seq (char-array "rsFMfFZSrLrFZsSL"))}
                                             {:first-compartment (seq (char-array "PmmdzqPrV"))        :second-compartment (seq (char-array "vPwwTWBwg"))}
                                             {:first-compartment (seq (char-array "wMqvLMZHhHMvwLH"))  :second-compartment (seq (char-array "jbvcjnnSBnvTQFn"))}
                                             {:first-compartment (seq (char-array "ttgJtRGJ"))         :second-compartment (seq (char-array "QctTZtZT"))}
                                             {:first-compartment (seq (char-array "CrZsJsPPZsGz"))     :second-compartment (seq (char-array "wwsLwLmpwMDw"))}])))))

(deftest badges-summed
  (testing "sums the values of the badges"
    (is (= 70 (logic/badges-summed [{:first-compartment (seq (char-array "vJrwpWtwJgWr"))     :second-compartment (seq (char-array "hcsFMMfFFhFp"))}
                                    {:first-compartment (seq (char-array "jqHRNqRjqzjGDLGL")) :second-compartment (seq (char-array "rsFMfFZSrLrFZsSL"))}
                                    {:first-compartment (seq (char-array "PmmdzqPrV"))        :second-compartment (seq (char-array "vPwwTWBwg"))}
                                    {:first-compartment (seq (char-array "wMqvLMZHhHMvwLH"))  :second-compartment (seq (char-array "jbvcjnnSBnvTQFn"))}
                                    {:first-compartment (seq (char-array "ttgJtRGJ"))         :second-compartment (seq (char-array "QctTZtZT"))}
                                    {:first-compartment (seq (char-array "CrZsJsPPZsGz"))     :second-compartment (seq (char-array "wwsLwLmpwMDw"))}])))))