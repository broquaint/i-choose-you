(ns i-choose-you.core
  (use [i-choose-you.get-game :only (get-game run-game)]
       [seesaw.core]
       [seesaw.font])
  (require [clojure.string :as str])
  (:gen-class))

(def f (frame :title "I Choose You!"
              :on-close :exit))

(defn display [content]
  (letfn [(show-content [f] (config! f :content content))]
    (-> f show-content pack! show!)))

(defn clicky-launch [e]
  (let [[game game-name] (get-game)]
    (do
      (invoke-now (run-game game))
      (display (label :text (str/join " " ["Running:" game-name]))))))

(def go-button
  (button :text "Launch game ..."
          :margin 5
          :font   (font :size 15 :style #{:bold})
          :listen [:action clicky-launch]))

(defn -main [& args]
  (display go-button))
