(defproject file_generator "0.1.0-SNAPSHOT"
  :description "persist contents from a http service to a fixed length file"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [clj-http "1.1.2"]
                 [org.clojure/data.json "0.2.6"]
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [clj-time "0.10.0"]
                 ]
  :ring {:handler file-generator.handler/app}
  :plugins [[lein-ring "0.8.13"] 
            [lein-exec "0.3.5"]
            ]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [lein-cloverage "1.0.6"]]
                        

         }

   }
  :aliases {"generate-file" ["exec" "-ep" "(use 'file-generator.handler) (generate-file)"]}


  )

