# Java Software Development — Athlete Analysis & Cinema Booking System

A two-part Java project demonstrating core software development principles including **array-based data processing**, **OOP class design**, **CLI interaction**, **input validation**, and **file I/O**. Built entirely in Java SE with no external libraries.

---

## Overview

This repository contains two independent Java programs:

| Program | Package | Entry Point | Description |
|---|---|---|---|
| **Athlete Analysis** | `questionOne` | `AthleteAnalysis.java` | CLI tool to load, analyse, and report on athlete datasets |
| **Cinema Booking System** | `questionTwo` | `BookingSoft.java` | Interactive OOP-based seat booking system for a multi-venue cinema |

---

## Project Structure

```
f28pa-assignment/
├── data.txt                          # Sample athlete dataset (20 entries, CSV format)
└── src/
    ├── questionOne/
    │   └── AthleteAnalysis.java      # Athlete data loader, analyser & reporter
    └── questionTwo/
        ├── BookingSoft.java          # Main entry point — cinema booking CLI
        ├── Show.java                 # Aggregates Film + Venue; manages seat booking
        ├── Venue.java                # Models a cinema hall; stores 2D Ticket array
        ├── Film.java                 # Stores film title and session time
        └── Ticket.java               # Represents an individual seat (row, col, occupied)
```

---

## Part 1 — Athlete Analysis (`questionOne`)

### What it does

A **CLI data analysis tool** that accepts athlete data either via **manual keyboard input** or by **reading from a CSV text file** (`data.txt`). It then computes and prints a full statistical report.

### Data Format (`data.txt`)

```
20,,,
Maria,F,26,160,55,Badminton,Bronze
James,M,18,165,65,Badminton,Gold
...
```

- Line 1: number of athlete entries
- Lines 2+: `Name, Gender, Age, Height(cm), Weight(kg), Sport, Medal`

### Features

- **Dual input modes** — manual entry (with full validation) or automatic file loading
- **Formatted table output** — dynamically adjusts column widths based on the longest data values in each field
- **Gender ratio** calculation (Male : Female)
- **Mean & Standard Deviation** — computed separately for male and female athletes across age, height, and weight; overloaded methods handle both `int[]` and `double[]` inputs
- **Oldest & youngest athletes** per gender — supports ties
- **Unique sports count** — custom deduplication algorithm without using Collections
- **Medal breakdown** — total medals per gender across all sports, and broken down per sport

### Key Design Decisions

- **Array-only approach** — all data stored in 7 parallel primitive/String arrays; no `ArrayList` or `HashMap` used
- **Overloaded `mean()` and `standardD()`** — one version for `int[]` data (age), another for `double[]` data (height, weight), allowing reuse with type safety
- **Robust input validation** — every user input is wrapped in `try-catch` for `InputMismatchException`, with looping prompts until valid input is received
- **Dynamic table formatting** — uses `String.format()` with computed field widths to produce cleanly aligned output regardless of data length

### Sample Output

```
The details of the athletes are as follows:

|Name  |Gender|Age|Height|Weight|Sport     |Medal |
|      |      |   |      |      |          |      |
|Maria |F     |26 |160.00|55.00 |Badminton |Bronze|
|James |M     |18 |165.00|65.00 |Badminton |Gold  |
...

The gender ratio of the athletes (Male : Female) is 1.2
The mean and standard deviation of the age of male athletes are 22.5 and 2.3 respectively
...
There are 4 unique sports available in the data
There are 4 gold medal(s), 3 silver medal(s), 3 bronze medal(s) obtained by male athletes in the data in all sports
...
```

---

## Part 2 — Cinema Booking System (`questionTwo`)

### What it does

A fully interactive **CLI-based cinema seat booking system** for 6 shows across 4 venue types. Users can browse available shows, select a movie, pick a seat from a visual hall layout, and book tickets — with real-time occupancy tracking.

### Class Design

```
BookingSoft (main)
    └── Show[]
          ├── Film          (title + session time)
          └── Venue         (hall layout + seat state)
                └── Ticket[][]  (row letter, seat number, isOccupied)
```

| Class | Responsibility |
|---|---|
| `Film` | Stores film title and maps session integer (1/2/3) to readable time (Afternoon/Evening/Night) |
| `Ticket` | Represents one seat — stores `rowLetter` (char), `seatNo` (int), `isOccupied` (boolean) |
| `Venue` | Manages a 2D `Ticket[][]` grid; hall dimensions determined by venue ID direction suffix (N/S/E/W) |
| `Show` | Composes `Film` and `Venue`; exposes seat booking, availability checks, and hall display methods |
| `BookingSoft` | Main driver — runs the interactive booking loop |

### Venue Layout

Hall size is determined by the directional suffix in the venue ID:

| Suffix | Rows × Cols | Total Seats |
|---|---|---|
| `E` (East) | 7 × 7 | 49 |
| `W` (West) | 5 × 7 | 35 |
| `N` (North) | 7 × 5 | 35 |
| `S` (South) | 9 × 9 | 81 |

### Shows Available

| Film | Venue ID | Session |
|---|---|---|
| SING | 1N | Afternoon (1pm) |
| THE GRINCH | 2W | Evening (5pm) |
| BOSS BABY | 3E | Night (9pm) |
| YES DAY | 3S | Night (9pm) |
| THE KARATE KID | 1E | Afternoon (1pm) |
| THE SEA BEAST | 2N | Evening (5pm) |

### Features

- **Live show listing** with movie name, screening time, and location
- **Real-time availability banner** — warns users before they select a fully booked show
- **Visual seat map** — shows the hall layout; occupied seats marked as `[ XX ]`
- **Seat selection with validation** — validates row letter (A to max row) and seat number (1 to max col), with friendly error messages
- **Booking confirmation** — confirms seat on success; allows continued booking of the same show
- **Auto-exit** — terminates gracefully when all 6 venues are fully booked

### Sample Seat Map Display

```
[ A1 ]  [ A2 ]  [ A3 ]  [ A4 ]  [ A5 ]
[ B1 ]  [ B2 ]  [ XX ]  [ B4 ]  [ B5 ]
[ C1 ]  [ C2 ]  [ C3 ]  [ C4 ]  [ C5 ]
...
```

---

## How to Run

### Prerequisites
- Java SE 17+
- Any Java IDE (Eclipse, IntelliJ) or CLI

### Using CLI

**Part 1 — Athlete Analysis:**
```bash
cd src
javac questionOne/AthleteAnalysis.java
java questionOne.AthleteAnalysis
# When prompted, enter any number other than 1 to load from data.txt
# (ensure data.txt is in the working directory)
```

**Part 2 — Cinema Booking System:**
```bash
cd src
javac questionTwo/*.java
java questionTwo.BookingSoft
```

### Using Eclipse
1. Import the project as an existing Java project.
2. For Part 1, run `AthleteAnalysis.java` as a Java Application (ensure `data.txt` is in the project root).
3. For Part 2, run `BookingSoft.java` as a Java Application.

---

## Technologies Used

- **Java SE 17**
- `java.util.Scanner` — console and file input
- `java.io.File` / `java.io.FileNotFoundException` — CSV file reading
- `java.util.InputMismatchException` — robust user input handling
- `String.format()` — dynamic console table formatting
- Object-Oriented Programming — encapsulation, composition, method overloading

---

## License

MIT
