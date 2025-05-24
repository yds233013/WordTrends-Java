WordTrends-Java

Overview

WordTrends-Java is a browser-based data analysis tool designed to explore the historical usage of words in English texts, leveraging the Google Ngram dataset and WordNet lexical database. The backend is implemented in Java, with the frontend provided in HTML and JavaScript. The application supports interactive visualizations, text-based historical queries, and semantic graph exploration of word meanings and relationships.

Features

Project 2A: NGrams Viewer

Implements a back-end Java system that analyzes word frequencies over time using the Google Ngram dataset.

Constructs two main classes:

TimeSeries: Extends TreeMap<Integer, Double> to represent data per year.

NGramMap: Parses input files and supports queries like countHistory(), totalCountHistory(), and weightHistory().

Allows users to:

Enter words and view frequency plots across years (via browser interface).

See raw frequency data in JSON format using HistoryTextHandler.

View interactive frequency graphs with HistoryHandler.

Utilizes a local HTTP server to handle frontend interaction via Java handlers.

Project 2B: WordNet Explorer

Extends the application to explore semantic relationships using the WordNet dataset.

Implements a directed graph to model hyponym relationships.

Adds a new feature for exploring hyponyms of one or more input words:

Single and multi-word queries.

Optional parameter k for top-k most frequent results within a year range.

Constructs core logic in HyponymsHandler.java with custom supporting classes like Graph, WordNet, etc.

Offers accurate intersection of hyponyms across multiple words and filters based on historical usage frequency (integrated with NGramMap).

Folder Structure

WordTrends-Java
├── proj2a
│   ├── src
│   ├── static
│   ├── tests
│   └── data
│       ├── ngrams
│       └── wordnet
├── proj2b
│   ├── src
│   ├── static
│   ├── tests
│   └── data
│       ├── ngrams
│       └── wordnet

Technologies Used

Java (JDK 17+)

HTML / JavaScript (frontend interface)

Jetty Web Server (local backend routing)

TreeMap, Set, List, Graphs (custom-built)

Base64 image encoding for frontend visualization

Setup Instructions

Clone Repository

git clone https://github.com/yds233013/WordTrends-Java.git
cd WordTrends-Java

Download Dataset Files
Download and unzip:

Project 2A dataset (NGrams)

Project 2B dataset (WordNet)
Place each data/ folder inside the respective proj2a and proj2b directories.

Run Main Class

Open Main.java inside either proj2a or proj2b depending on task.

Run the file.

Open ngordnet_2a.html or ngordnet.html in your browser.

Testing

JUnit tests are provided in tests/ for both projects.

You can also manually verify output by interacting with the web interface.

Authors

Developed as part of an academic data structures and software engineering curriculum.

License

This project is for academic and educational use. Do not upload the data files to GitHub. Respect .gitignore configurations.

Acknowledgements

Google NGrams Dataset

Princeton WordNet

Adapted from UC Berkeley course assignments.

