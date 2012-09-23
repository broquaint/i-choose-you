(ns i-choose-you.core
  (use [i-choose-you.get-game :only (get-game run-game)])
  (:gen-class))

(defn -main [& args]
  (let [[game game-name] (get-game)]
    (do
      (println "Launching -" game-name)
      (Thread/sleep 3000)
      (run-game game)
      (println "Fun time is ovah!"))))
