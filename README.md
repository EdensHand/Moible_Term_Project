# Project Title

## Authors
- Mac Tully (wuj591)
- Alejandro White (ptk406)
  
## Project Overview

This project is a mobile application that integrates an Android app with Firebase for data storage and retrieval, while a Python backend is used for processing and additional functionality. The app allows users to interact with a specific feature, collect data, and store it in a Firebase Realtime Database. The backend handles complex tasks and provides APIs for the app to communicate with.

## Android App

### Workflow
The Android app follows a **news page** workflow where users can swipe through various news items, each with an image and title. Users swipe left or right to compare articles or images, providing feedback through their gestures.

### Swipe Gesture Implementation
The swipe gesture is implemented using the `GestureDetector` class in Android. This class allows detecting both simple swipes and gestures across the app's user interface. We added specific logic to handle left or right swipe actions, triggering events such as moving to the next or previous news article.

Steps to implement swipe gestures:
1. **Set up GestureDetector**: Create a custom `GestureDetector.OnGestureListener` to detect swipe events.
2. **Attach to UI component**: Attach this gesture listener to the `RecyclerView` or relevant UI components.
3. **Handle swipe actions**: Depending on the swipe direction (left or right), the app transitions to the next or previous item.

### Challenges
- **Handling swipe accuracy**: Ensuring the swipe gestures were responsive and consistent across different screen sizes posed a challenge, especially with varying sensitivities in different devices.
- **UI performance**: Ensuring smooth transitions between items while maintaining UI performance required optimizations.

#### How to Run:
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or device.
4. Ensure necessary permissions are granted (e.g., internet access for Firebase).

## Firebase Database

### Data Points Captured
For each swipe gesture, the app captures the following data points:
- **User ID**: Identifies the user performing the action.
- **Swipe Direction**: Indicates whether the user swiped left or right.
- **Timestamp**: Records the time when the swipe occurred.
- **Article/Item ID**: Tracks which article or image was swiped.

### Firebase Realtime Database Structure
The data is stored in Firebase Realtime Database with the following structure:
/username
/swipeID
/swipeDirection
/swipeDurration
/timestamp

## Python Backend

### Function
The Python backend serves as the processing engine for the app, handling complex data analysis, user behavior insights, and API interactions with both the app and Firebase. It also processes data from the Firebase Realtime Database and performs tasks such as:
- **Data Analysis**: Analyzing swipe behavior and patterns.
- **API Endpoints**: Exposing RESTful APIs for the Android app to interact with.
- **Firebase Interaction**: Retrieving and updating data in Firebase based on app interactions.

### How to Run:
1. Clone the repository.
2. Set up a Python environment and install the required dependencies using: pip install -r requirements.txt
3. Configure the Firebase credentials by downloading the `google-services.json` file and placing it in the backend directory.
4. Run the server using: python app.py
