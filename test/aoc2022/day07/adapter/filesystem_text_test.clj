(ns aoc2022.day07.adapter.filesystem-text_test
  (:require [clojure.test :refer :all]
            [aoc2022.day07.adapter.filesystem-text :as adapter]))

(def input "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(deftest conversion-in
  (testing "mounting file system structure"
    (is (= (adapter/text->filesystem-structure input) {:name "/" :items [{:name "a" :items [ {:name "e"      :items [{:name "i" :size 584}]}
                                                                                             {:name "f"      :size 29116}
                                                                                             {:name "g"      :size 2557}
                                                                                             {:name "h.lst"  :size 62596}]}
                                                                         {:name "b.txt" :size 14848514}
                                                                         {:name "c.dat" :size 8504156}
                                                                         {:name "d" :items [{:name "j"     :size 4060174}
                                                                                            {:name "d.log" :size 8033020}
                                                                                            {:name "d.ext" :size 5626152}
                                                                                            {:name "k"     :size 7214296}]}]}))))