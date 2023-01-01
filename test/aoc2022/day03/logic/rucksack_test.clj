(ns aoc2022.day03.logic.rucksack-test
  (:require [clojure.test :refer :all]
            [aoc2022.day03.logic.rucksack :as logic]))

(deftest priority
  (testing "converts all priorities given the numbers"
    (is (= [16 38 42 22 20 19] (map logic/priority [\p \L \P \v \t \s])))))

(deftest repeated
  (testing "find repeated item type"
    (is (= \a (logic/repeated {:first-compartment [\a \b] :second-compartment [\c \a]})))))

(deftest sum
  (testing "sums the priorities of the item types that are repeated"
    (is (= 157 (logic/sum-repeated-priority [{:first-compartment [\v \J \r \w \p \W \t \w \J \g \W \r]              :second-compartment [\h \c \s \F \M \M \f \F \F \h \F \p]}
                                             {:first-compartment [\j \q \H \R \N \q \R \j \q \z \j \G \D \L \G \L]  :second-compartment [\r \s \F \M \f \F \Z \S \r \L \r \F \Z \s \S \L]}
                                             {:first-compartment [\P \m \m \d \z \q \P \r \V]                       :second-compartment [\v \P \w \w \T \W \B \w \g]}
                                             {:first-compartment [\w \M \q \v \L \M \Z \H \h \H \M \v \w \L \H]     :second-compartment [\j \b \v \c \j \n \n \S \B \n \v \T \Q \F \n]}
                                             {:first-compartment [\t \t \g \J \t \R \G \J]                          :second-compartment [\Q \c \t \T \Z \t \Z \T]}
                                             {:first-compartment [\C \r \Z \s \J \s \P \P \Z \s \G \z]              :second-compartment [\w \w \s \L \w \L \m \p \w \M \D \w]}])))))