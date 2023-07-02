(ns aoc2022.day10.adapter.cycle-text
  (:require [aoc2022.day10.model.cycle :as model]
            [schema.core :as s]
            [clojure.string :as str]))

(s/defn text->signal-stream [text-input :- s/Str] :- [model/Instruction]
  (let [lines (str/split-lines text-input)]
    (loop [lines        lines
           cycle        1
           instructions []]
      (if (empty? lines)
        instructions
        (let [line                            (first lines)
              [new-instructions next-cycle]   (cond
                                                (str/starts-with? line "noop") [[{:cycle cycle :value-to-add 0}] (+ cycle 1)]
                                                (str/starts-with? line "addx") (let [value-from-addx (read-string (str/replace line #"addx " ""))]
                                                                                 [[{:cycle cycle :value-to-add 0} {:cycle (+ cycle 1) :value-to-add value-from-addx}] (+ cycle 2)]))
              instructions                    (into instructions new-instructions)]
          (recur (rest lines) next-cycle instructions))))))
