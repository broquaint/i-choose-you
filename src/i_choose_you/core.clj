(ns i-choose-you.core
  (require [fs.core          :as fs]
           [clj-commons-exec :as exec]))

;; Hackery for fs/Java fail
(defn path-join [& args] (clojure.string/join "/" args))
(defn path-join-dir [d] (map #(path-join d %) (fs/list-dir d)))

;; Random non-game exe patterns, + Pirate Kart, Steam & Desura
(defn skip-exclusions [f]
  (not-any? #(re-find % f)
            [#"(Desura|Steam|PIRATE)" #"(?i:config|setup|unins|launch|setting|viewer|performance)"]))

(defn get-game-executables [games]
    (filter skip-exclusions
            (flatten
             (for [d (filter fs/directory? (path-join-dir games))]
               (filter #(re-find #"exe$" %) (path-join-dir d))))))

(defn run-game [exe-path]
  @(exec/sh [exe-path] ; ["cmd" "/C" "start" exe-path]
            {:dir (fs/parent exe-path)}))

(defn -main [& args]
  (do
   (let [game (rand-nth (get-game-executables "D:/Games"))]
     (println "About to start " game)
     (Thread/sleep 10)
     (run-game game)
     (println "Fun time is ovah!"))))

; @(exec/sh ["cmd" "/C" "start" "D:/games/yyyyyy/yyyyyy.exe"] {:dir "d:/games/yyyyyy"})