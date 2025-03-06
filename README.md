# Touchalytics

## Authors
- Mac Tully (wuj591)
- Alejandro White (ptk406)

---

## Project Overview

This project is a mobile application that integrates an Android app with Firebase for data storage, while a Python backend is used to access the stored data in the Firebase database. The app allows users to log in with a username and record directional swipes, then store the user's swiping data in a Firebase Realtime Database. The backend handles admin retrieval of the swipe data by user and prints out the requested data in readable format to the console.

---

## Android App

### Workflow
The app follows a simple login-to-gesture workflow:
1. The user enters a username in the **LoginActivity**.
2. Upon successful login, the app proceeds to the **MainActivity**, where users can swipe on the screen.
3. The swipe gestures are tracked and sent to Firebase for storage.

### Swipe Gesture Implementation
In the `MainActivity`, swipe gestures are detected using `OnTouchListener`. When the user swipes on the screen:
- **Swipe Direction**: The direction of the swipe (up, down, left, right) is calculated based on the difference between the initial touch coordinates and the final coordinates.
- **Swipe Duration**: The time taken to complete the swipe is recorded by calculating the difference between the start and end times of the swipe.

#### Steps to Implement Swipe Gestures:
1. **Track Touch Events**: The `onTouch` method is implemented to capture `ACTION_DOWN`, `ACTION_MOVE`, and `ACTION_UP` events.
2. **Calculate Direction**: The swipe direction is determined by comparing the horizontal (`x`) and vertical (`y`) movement.
3. **Send Data to Firebase**: After a swipe is completed, the swipe data (direction, duration, timestamp) is stored in Firebase under the user's username.

### Challenges
- **Finding an Appropriate Data Structure**: `ArrayList` works well to store swipe data on-device, but is not compatible with Firebase. Once data is ready to send, `ArrayList` is converted to `hashmap` for proper Firebase data storage.
- **Firebase Connectivity**: Implementing appropriate and compatible libraries, versions, and methods to successfully and reliably connect to the Firebase took significant trial and error. Generative AI assisted in the troubleshooting of `sendSwipeDataToFirebase`. 

#### How to Run:
1. Clone the repository.  **OR**  Unzip `touchalytics-android-proj-ptk406-wuj591.zip` into Android Studio environment.
2. Open the project in **Android Studio**.
3. Build and run the app on an Android emulator or physical device running at least Android 15.0 (API 35).

---

## Firebase Database

### Data Points Captured
For each swipe gesture the app captures, Firebase stores the data as:
- **User ID**: Identifies the user performing the action.
  - **Swipe Direction**: Indicates whether the user swiped Up, Down, Left, or Right.
  - **Swipe Duration**: Time in milliseconds from when the finger pressed down on the screen until it was released.
  - **Timestamp**: Records the time when the swipe occurred.

---

## Python Backend

### Function
The Python backend is used to retrieve and display swipe data stored in Firebase. It allows an admin to enter a username and fetch all recorded swipes associated with that user, sorting them in descending order based on their timestamp.

### How to Run:
1. Ensure you have Python installed (**version 3.6 or later**).
2. Install the required dependencies by running: `pip install firebase-admin`.
3. Place the Firebase service account JSON file `touchalytics-27705-firebase-adminsdk-fbsvc-faa52519b1.json` in the same directory as the Python script.
4. Run the script: `python3 backend.py`.
