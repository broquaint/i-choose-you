(defproject i-choose-you "0.3.0"
  :description "Play a random game"
  :url "https://github.com/broquaint/i-choose-you"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [fs "1.3.2"]
                 [seesaw "1.4.4"]]
  :main i-choose-you.core
  :aot [i-choose-you.core])
