 (ns config.migrate-config)

(defn current-db-version []
  (sql/with-connection DB
    (sql/with-query-results res 
      ["select version from schema_migrations"]
      (or (:version (last res)) 0))))

(defn update-db-version [version]
  (sql/with-connection DB
    (sql/insert-values :schema_migrations [:version] [version])))

 (defn migrate-config []
   { :directory "/src/migrations"
     :current-version current-db-version
     :update-version update-db-version })
