;; db.clj
;; This file contains functions for loading and processing city data from the "cities.txt" file.
;; It provides functions to parse city data, retrieve cities, get city information, 
;; and summarize province data by the number of cities and total population.

(ns db
  (:require [clojure.string :as str]))

;; Function to parse a line from the cities.txt file into a city map.
(defn parse-city [line]
  (let [[name province size population area] (str/split line #"\|")]
    {:name name
     :province province
     :size size
     :population (read-string population)
     :area (read-string area)}))

;; Function to load and parse the city data from a file.
(defn load-data [filename]
  (let [lines (str/split-lines (slurp filename))]
    (map parse-city lines)))

;; Function to return a list of cities sorted by name.
(defn get-cities [cities-db]
  (sort-by :name cities-db))

;; Function to return a list of cities for a given province, sorted by size and name.
(defn get-cities-by-province [cities-db province]
  (sort-by (juxt :size :name)
           (filter #(= (:province %) province) cities-db)))

;; Function to return a list of cities for a given province, sorted by population density.
;; Population density is calculated as population divided by area.
(defn get-cities-by-density [cities-db province]
  (sort-by #(double (/ (:population %) (:area %)))
           (filter #(= (:province %) province) cities-db)))

;; Function to return information for a specific city by name.
(defn get-city-info [cities-db city-name]
  (first (filter #(= (:name %) city-name) cities-db)))

;; Function to return a list of provinces with the total number of cities, sorted by the number of cities.
(defn get-provinces [cities-db]
  (->> cities-db
       (group-by :province)
       (map (fn [[k v]] [k (count v)]))
       (sort-by second >)))

;; Function to return a list of provinces with the total population, sorted by province name.
(defn get-provinces-population [cities-db]
  (->> cities-db
       (group-by :province)
       (map (fn [[k v]] [k (reduce + (map :population v))]))
       (sort-by first)))
