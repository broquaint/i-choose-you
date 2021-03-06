(ns i-choose-you.get-game
  (:use [clojure.java.shell :only (sh)])
  (:require [fs.core          :as fs]
            [clojure.string   :as str]
            [clojure.edn      :as edn]))

;; Hackery for fs/Java fail
(defn path-join     [& args] (str/join "/" args))
(defn path-join-dir [d]      (map #(path-join d %) (fs/list-dir d)))

;; Random non-game exe patterns + Pirate Kart, Steam & Desura
(defn skip-exclusions [f]
  (not-any? #(re-find % f)
            [#"(Desura|Steam|PIRATE)" #"(?i:config|setup|unins|launch|setting|viewer|performance)"]))

(defn get-game-executables [games]
    (filter skip-exclusions
            (flatten
             (for [d (filter fs/directory? (path-join-dir games))]
               (filter #(re-find #"exe$" %) (path-join-dir d))))))

(defn get-config [key]
  (let [config (edn/read-string (slurp (str fs/*cwd* "/config.edn")))]
    (config key)))

(defn get-games []
  (concat
   (mapcat path-join-dir (get-config :shortcuts))
   (mapcat get-game-executables (get-config :directories))))

(defn run-game [game-path callback]
  (sh
    "cmd" "/C" (fs/base-name game-path)
    :dir (fs/parent game-path))
  (callback))

(defn get-game []
  (let [game (rand-nth (get-games))]
    [game (clojure.string/replace (str (fs/base-name game)) #"[.]\w+$" "")]))
