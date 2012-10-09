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
  (let [[game game-name] (get-game)
        go   (select f [:#go])
        info (select f [:#info])]
    (do
      (run-game game)
      (config! go   :text "Launch another game?")
      (config! info :text (str/join " " ["Running:" game-name]))
      (-> f pack! show!))))

(def go-button
  (button :id :go
          :text "Launch game ..."
          :margin 5
          :font   (font :size 15 :style #{:bold})
          :listen [:action clicky-launch]))

(def info-label
  (label :id :info
         :text "Awaiting launch ..."
         :h-text-position :center
         :v-text-position :center))

(defn -main [& args]
  (native!)
  (display (grid-panel :items [go-button info-label]
                       :vgap 0
                       :rows 2)))
