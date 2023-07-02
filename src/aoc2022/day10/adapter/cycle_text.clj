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

(defn- overlaps? [crt-position x-value]
  (let [crt-position (mod crt-position 40)]
    (and (>= x-value (- crt-position 1))
         (<= x-value (+ crt-position 1)))))

(defn- crt-stream->screen [crt-stream]
  (->> crt-stream
       (partition-all 40)
       (map (partial apply str))
       (map #(str % "\n"))
       str/join
       ((comp str/join drop-last))                          ; remove last \n
       ))
(s/defn position->text [cycles :- [model/Cycle]]
  (loop [cycles       cycles
         crt-position 0
         crt          ""]
    (if (empty? cycles)
      (crt-stream->screen crt)
      (let [x-value     (:x-value-at-start (first cycles))
            pixel-drawn (if (overlaps? crt-position x-value) "#" ".")
            crt         (str crt pixel-drawn)]
        (recur (rest cycles) (+ crt-position 1) crt)))))
