(ns aoc2022.day02.adapters.jokenpo-text-test
  (:require [clojure.test :refer :all]
            [aoc2022.day02.adapters.jokenpo-text :as adapter]))

(def input-value "A Y\nB X\nC Z")
(deftest conversion-in
  (testing "can convert input file content to map internal representation"
      (is (= (adapter/text->map input-value) [{:opponent :rock     :you :paper}
                                              {:opponent :paper    :you :rock}
                                              {:opponent :scissors :you :scissors}]))))
