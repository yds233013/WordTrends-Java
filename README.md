# WordTrends-Java

A browser-based Java application for analyzing historical word usage and exploring lexical relationships using NGram and WordNet datasets.

## Overview

This project is divided into two parts:

- **Project 2A: NGrams Viewer** – Implements backend logic for visualizing word frequency trends over time using Google NGram data.
- **Project 2B: WordNet Explorer** – Enhances functionality by incorporating WordNet synsets and hyponyms, allowing for semantic exploration of language.

---

## 🔍 Project 2A: NGrams Viewer

### Features

- Parses NGram datasets and maps word usage to time series.
- Returns historical frequency of words as JSON-formatted text or a base64-encoded chart image.
- Backend logic integrates with a provided JavaScript/HTML frontend.
- Implements:
  - `TimeSeries`: TreeMap-backed year → frequency container.
  - `NGramMap`: Maps words to their historical usage and supports both raw counts and normalized weights.
  - `HistoryTextHandler`: Returns textual history for word usage.
  - `HistoryHandler`: Generates and encodes time-series charts.

### File Structure

proj2a/
├── data/ # Contains ngrams and total word counts (excluded via .gitignore)
├── src/ngrams/ # Java backend implementation
├── static/ # Frontend UI files (ngordnet_2a.html)
├── tests/ # JUnit tests


### Usage

1. Run `Main.java` to start the backend server.
2. Open `static/ngordnet_2a.html` in a browser.
3. Input words and view their usage over time or as a chart.

---

## 🧠 Project 2B: WordNet Explorer

### Features

- Reads synset and hyponym files to build a directed graph of semantic relationships.
- Implements:
  - A custom `Graph` structure for traversal.
  - `WordNet` classes to query synset relationships.
  - `HyponymsHandler`: Displays hyponyms for input words.
  - Supports multi-word queries and popularity filtering (`k` parameter).

### Advanced Functionality

- Returns all hyponyms for a word or set of words.
- When `k > 0`, filters results based on frequency in a given time range.
- Integrates NGramMap to compute popularity-based intersections.

### File Structure

proj2b/
├── data/ # Contains synsets and hyponyms (excluded via .gitignore)
├── src/wordnet/ # WordNet classes and handlers
├── static/ # Frontend UI files (ngordnet.html)
├── tests/ # JUnit tests and test harness


---

## 🧪 Testing

- JUnit-based test coverage for all major components.
- Use `AutograderBuddy.java` to wire handlers for autograding.

---

## 🧰 Requirements

- Java 17+
- IntelliJ IDE recommended
- Git, Terminal access for setup

---

## ⚠️ Notes

- Data folders (`/data`) are excluded from GitHub for size reasons. Download and unzip separately.
- Do not modify `.gitignore` files.
- Avoid global static variables (e.g. static `NGramMap`).

---

## 💡 Acknowledgements

- WordNet data adapted from Alina Ene and Kevin Wayne’s assignments at Princeton.
- Project scaffold and datasets provided as part of university coursework.

---


