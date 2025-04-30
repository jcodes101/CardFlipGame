# 🃏 Card Flip Memory Game (JavaFX)

A multi-level memory matching card game developed using **JavaFX**, created for **COMP 167** by **Jadin Hutchinson**.

---

## 🎮 Game Overview

The **Card Flip Memory Game** challenges players to match pairs of cards by flipping them two at a time. It supports multiple difficulty levels and includes sound effects for matches and mismatches. The game keeps track of the player's turn count and provides a game-over alert when all pairs are matched.

---

## 🧩 Game Features

- 🧠 **Memory Matching Gameplay** – Flip cards to find and match pairs.
- 🔢 **Multiple Levels**:
  - Level 1: 2x3 grid
  - Level 2: 2x4 grid
  - Level 3: 4x4 grid
  - Level 4: 4x6 grid
  - Level 5: 6x6 grid
  - Level 6: 8x8 grid
- 🔁 **New Game Button** – Instantly start a new game.
- 🔊 **Audio Feedback** – Sound effects play on matches and mismatches.
- 🕐 **Turn Tracking** – Displays number of turns taken.
- 🚪 **Exit Button** – Easily close the game.
- ✅ **Game Over Alert** – Pops up when all cards are successfully matched.

---

## 🛠️ Tech Stack

- **Java**
- **JavaFX**
- **FXML** (optional)
- **MediaPlayer/AudioClip** (for sound effects)

---

## 📂 Project Structure

```plaintext
src/
├── GamePane.java        # Main game logic and layout
├── Card.java            # Represents a single card (flip/match logic)
├── CardGridPane.java    # Handles the grid layout of cards
├── match.mp3            # Sound for a successful match
├── nomatch.mp3          # Sound for an unsuccessful match
└── assets/              # (Optional) Directory for card images

🚀 How to Run
Clone the repository:

bash
Copy
Edit
git clone https://github.com/yourusername/CardFlipMemoryGame.git
Open the project in your preferred Java IDE (such as IntelliJ IDEA or Eclipse).

Make sure JavaFX is properly configured in your environment.

Run the GamePane class to start the game.

🔈 Notes
Ensure the sound files (match.mp3 and nomatch.mp3) are correctly located and properly loaded into the classpath.

Card images should be placed in an accessible resource folder if used.
