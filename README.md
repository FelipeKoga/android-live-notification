# Android Live Notification

## Description
A test application for Android that demonstrates the implementation of live notifications using Firebase Cloud Messaging (FCM).

## Setup

### Prerequisites
- Firebase account.
- Android Studio installed.
- Node.js installed.

### Configuration
1. **Firebase Setup**
   - Create a new project in your Firebase Console.
   - Add an Android app to your Firebase project and follow the setup instructions.

2. **Project Integration**
   - Clone the `android-live-notification` repository.
   - Open the cloned project in Android Studio.

3. **Add Firebase Configuration File**
   - Download the `google-services.json` file from your Firebase project.
   - Place this file in the `app/` directory of your Android Studio project.

4. **Generate and Place Service Account Key**
   - In the Firebase Console, navigate to your project's settings.
   - Go to the 'Service Accounts' tab.
   - Click on 'Generate New Private Key', then download and save the `serviceAccountKey.json` file.
   - Place this key in the `fcm-script` folder within your project directory.

5. **Install Node Dependencies**
   - Navigate to the `fcm-script` folder in your project directory.
   - Run `npm install` to install the required Node.js dependencies.

6. **Execute Push Notification Script**
   - Run the script with `node index.js` to send the push notification.

7. **Build and Run**
   - Build the project in Android Studio.
   - Run the app on an emulator or physical device.
