(defproject i-choose-you "0.2.0"
  :description "Play a random game"
  :url "https://github.com/broquaint/i-choose-you"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [fs "1.3.2" :exclusions [[org.clojure/clojure]]]
                 [seesaw "1.4.2" :exclusions [[org.clojure/clojure]]]]
  :main i-choose-you.core
  :aot [i-choose-you.core])
