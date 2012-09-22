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

(defn get-games []
  (concat
   (path-join-dir "C:/Users/Dan/Game Shortcuts")
   (get-game-executables "D:/Games")))

(defn run-game [game-path]
  @(exec/sh
    ["cmd" "/C" (fs/base-name game-path)]
    {:dir (fs/parent game-path)}))

(defn -main [& args]
  (let [game (rand-nth (get-games))
        game-name (clojure.string/replace (str (fs/base-name game)) #"[.]\w+$" "")]
    (do
      (println "Launching -" game-name)
      (Thread/sleep 3000)
      (run-game game)
      (println "Fun time is ovah!"))))
