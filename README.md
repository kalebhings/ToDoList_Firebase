# Overview

I created this application in Android to learn how to connect a Cloud database to an Android application to make the app more practical so that the data can be accessed after the app is closed and on a different phone. I also wanted to learn how to utilize authentication because that is becoming more and more important in today's day to be safe. 

The app uses Firebase authentication for creating the logins and for signing in. It also utilizes Realtime database on Firebase to add the tasks from the to do list to the database and pull them when the app reopens so that the tasks are saved. It also has the ability to remove only the selected or clear all the tasks from the database and on the screen.

[Software Demo Video](https://www.youtube.com/watch?v=bDVOFFLGhy0&ab_channel=KalebHings)

# Cloud Database

I am using Firebase authentication and Firebase Realtime database. Realtime database isn't as advanced as Firebase firestore, another cloud database. However it works perfectly for what I needed since I didn't need the advanced query features. The database has 1 value and then the identifier to pull the task. 

# Development Environment

{Describe the tools that you used to develop the software}
* Android Studio
* Kotlin
* Google Firebase Authentication
* Google Firebase Realtime Database

# Useful Websites

* [Firebase Read and Write Documentation](https://firebase.google.com/docs/database/android/read-and-write#kotlin+ktx)
* [Firebase Get/Set Tutorial](https://www.youtube.com/watch?v=rg5WToMepJQ&ab_channel=Dr.ParagShukla)
* [Firebase Authentication Documentation](https://firebase.google.com/docs/auth/android/start)

# Future Work

* Link To Do list items to user that is signed in so that users can only view their own tasks
* Add a sign out button
* Add the ability to schedule when a to do list item needs to be done by, working with notifications when it hits that time in local time.