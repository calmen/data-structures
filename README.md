# Data Structures Project - Athens University of Economics and Business

##  Overview
This repository contains three projects focused on **Data Structures**, designed for coursework at **Athens University of Economics and Business**. It demonstrates the implementation of **stacks** and **queues**, applied in practical scenarios.

## 🏗 Projects
### 1️⃣ Stack & Queue Implementations (`StringStackImpl.java`, `IntQueueImpl.java`)
- Implemented two **Abstract Data Types (ADTs)**:
  - `StringStackImpl` (a **stack** for `String` elements)
  - `IntQueueImpl` (a **FIFO queue** for `int` elements)
- **Used:** **Singly Linked Lists**
- **Operations run in O(1) time**, including `push()`, `pop()`, `enqueue()`, `dequeue()`, and `size()`.
- **Exception handling:** Throws `NoSuchElementException` for empty structure operations.

### 2️⃣ HTML Tag Matching (`TagMatching.java`)
- A program to **validate nested HTML tags** using the **stack implementation**.
- Reads an **HTML file** and checks if all `<tags>` correctly match their closing `</tags>`.
- **Usage:**
  
  java TagMatching path/to/html_file.html
