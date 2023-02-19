(ns aoc2022.day07.logic.filesystem-test
  (:require [clojure.test :refer :all]
            [aoc2022.day07.logic.filesystem :as logic]))

(def sample {:name "/" :items [{:name "a" :items [ {:name "e"      :items [{:name "i" :size 584}]}
                                                  {:name "f"      :size 29116}
                                                  {:name "g"      :size 2557}
                                                  {:name "h.lst"  :size 62596}]}
                               {:name "b.txt" :size 14848514}
                               {:name "c.dat" :size 8504156}
                               {:name "d" :items [{:name "j"     :size 4060174}
                                                  {:name "d.log" :size 8033020}
                                                  {:name "d.ext" :size 5626152}
                                                  {:name "k"     :size 7214296}]}]})
(deftest total-size
  (testing "get the total size of a filesystem"
    (is (= (logic/total-size-filesystem sample) 48381165))))

(deftest total-size-at-most
  (testing "get the total size of a filesystem with directories that do not pass 100000 in size"
    (is (= (logic/total-size-at-most sample 100000) 95437))))

(deftest smallest-directory-size-enough-for-update
  (testing "get the total size of a filesystem with directories that do not pass 100000 in size"
    (is (= (logic/smallest-directory-size-enough-for-update sample 70000000 30000000) 24933642))))