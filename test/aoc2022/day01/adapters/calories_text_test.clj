(ns aoc2022.day01.adapters.calories-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day01.adapters.calories-text :as adapter]))

(def input-value "100\n200\n300\n\n100")
(deftest conversion-in
  (testing "can convert input file content to map internal representation"
      (is (= (adapter/text->map input-value) [{:elf-id 1 :food [100 200 300]}
                                              {:elf-id 2 :food [100]}]))))
