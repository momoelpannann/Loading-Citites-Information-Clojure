;; menu.clj
(ns menu
  (:require [clojure.string :as str]
            [db]))

(defn showMenu []
  ;; Display the main menu and prompt the user for an option.
  (println "\n\n*** City Information Menu ***")
  (println "-----------------------------\n")
  (println "1. List cities")
  (println "2. Display City Information")
  (println "3. List Provinces")
  (println "4. Display Province Information")
  (println "5. Exit")
  (do 
    (print "\nEnter an option? ") 
    (flush) 
    (read-line)))

(defn option1 []
  ;; Submenu for listing cities
  (println "*** List Cities Submenu ***")
  (println "1. List all cities")
  (println "2. List all cities for a given province, ordered by size and name")
  (println "3. List all cities for a given province, ordered by population density")
  (println "4. Back to main menu")
  (print "Enter an option: ")
  (flush)
  (let [sub-option (read-line)]
    (cond
      (= sub-option "1") (doseq [city (db/get-cities db/cities-db)]
                           (println (:name city)))
      (= sub-option "2") (do
                           (print "Enter province name: ")
                           (flush)
                           (let [province (read-line)]
                             (doseq [city (db/get-cities-by-province db/cities-db province)]
                               (println (str (:name city) " " (:size city) " " (:population city))))))
      (= sub-option "3") (do
                           (print "Enter province name: ")
                           (flush)
                           (let [province (read-line)]
                             (doseq [city (db/get-cities-by-density db/cities-db province)]
                               (println (str (:name city) " " (:size city) " " (double (/ (:population city) (:area city))))))))
      (= sub-option "4") (println "Returning to main menu")
      :else (println "Invalid option"))))

(defn option2 []
  ;; Display information for a specific city.
  (print "\nPlease enter the city name => ") 
  (flush)
  (let [city-name (read-line)]
    (if-let [city (db/get-city-info db/cities-db city-name)]
      (println city)
      (println "City not found."))))

(defn option3 []
  ;; List all provinces with the total number of cities.
  (println "List all provinces with total number of cities")
  (doseq [[province count] (db/get-provinces db/cities-db)]
    (println (str province " " count)))
  (let [total-cities (reduce + (map second (db/get-provinces db/cities-db)))
        total-provinces (count (db/get-provinces db/cities-db))]
    (println (str "Total: " total-provinces " provinces, " total-cities " cities on file."))))

(defn option4 []
  ;; List all provinces with the total population.
  (println "List all provinces with total population")
  (doseq [[province population] (db/get-provinces-population db/cities-db)]
    (println (str province " " population))))

(defn processOption [option]
  ;; Call the relevant function based on the user's menu selection.
  (cond
    (= option "1") (option1)
    (= option "2") (option2)
    (= option "3") (option3)
    (= option "4") (option4)
    :else (println "Invalid Option, please try again")))

(defn menu []
  ;; Main menu loop for user interaction.
  (let [option (str/trim (showMenu))]
    (if (= option "5")
      (println "\nGood Bye\n")
      (do 
        (processOption option)
        (recur)))))

(menu)