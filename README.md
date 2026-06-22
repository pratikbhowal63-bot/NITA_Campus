<img src="Screenshots/banner.jpeg" width="100%"/>

# 🎓 NITA Campus

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-34A853?style=for-the-badge&logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge)

A modern Android application developed for students of **National Institute of Technology Agartala (NIT Agartala)**.

NITA Campus serves as a centralized platform where students can access academic resources, explore department information, communicate with peers, and utilize AI-powered assistance—all within a single application.

---

## 🚀 Features

### 🔐 Authentication
- Username-based Sign Up and Sign In
- Firebase Realtime Database integration
- Persistent Login using SharedPreferences
- User account management
- Session management

### 🛡️ Security & Reliability
- Persistent Login (Auto Login)
- Input Validation
- Username Validation
- Password Strength Validation
- Phone Number Validation
- Network Error Handling
- Duplicate Username Prevention
- Firebase Security Rules
- Error Handling & Crash Prevention

### 📊 Student Dashboard
- Modern and intuitive user interface
- Quick access to academic and campus resources
- Easy navigation across modules
- Navigation Drawer support

### 🤖 AI Assistant
- Integrated AI-powered assistant
- Academic guidance and assistance
- Student support features

### 📚 Academic Resources
- Subject-wise study materials
- Notes and learning resources
- Previous Year Questions (PYQs)
- Department-specific information

### 🏛️ Department Information
- Computer Science & Engineering
- Electronics & Communication Engineering
- Electrical Engineering
- Mechanical Engineering
- Civil Engineering
- Chemical Engineering
- Production Engineering
- Instrumentation Engineering
- Basic Science Departments

### 🎯 Campus Information
- Student activities
- Faculty information
- Scholarships
- Campus facilities
- Academic updates

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|--------|
| Kotlin | Android Development |
| Android SDK | Core Application Framework |
| Firebase Realtime Database | Backend & Data Storage |
| Gemini AI API | AI Assistant |
| SharedPreferences | Session Management |
| LiveData | Reactive UI Updates |
| View Binding | UI Binding |
| Material Design Components | Modern UI |
| RecyclerView | Dynamic Lists |
| MVVM Architecture | Project Structure |

---

## 🏗️ Architecture

The project follows the **MVVM (Model-View-ViewModel)** architecture pattern.

```text
UI Layer
    ↓
ViewModel
    ↓
Repository
    ↓
Firebase Realtime Database
```

### Components
- ViewModel
- Repository Pattern
- LiveData
- Firebase Realtime Database
- SharedPreferences
- View Binding

---

## 🧪 Testing

Implemented JUnit tests for:

- Phone Number Validation
- Username Validation
- Password Validation

All validation tests are passing successfully.

---

## 📸 Screenshots

<p align="center">
  <img src="Screenshots/Login.png" width="220"/>
  <img src="Screenshots/Main.png" width="220"/>
  <img src="Screenshots/main2.png" width="220"/>
</p>

<p align="center">
  <img src="Screenshots/dashboard.png" width="220"/>
  <img src="Screenshots/profile.png" width="220"/>
  <img src="Screenshots/AI.png" width="220"/>
</p>

---

## 💡 Skills Demonstrated

- Android Application Development
- Kotlin Programming
- Firebase Integration
- MVVM Architecture
- Repository Pattern
- Authentication Systems
- Realtime Database Management
- API Integration
- UI/UX Design
- RecyclerView Implementation
- Navigation Drawer Implementation
- Unit Testing
- Problem Solving & Debugging

---

## 🔮 Future Improvements

- Push Notifications (FCM)
- Dark Mode Support
- Attendance Tracking
- Timetable Management
- Placement Preparation Module
- Faculty-Student Communication Portal
- Offline Support using Room Database
- Cloud Firestore Migration
- Enhanced Analytics Dashboard

---

## ⚙️ Installation

### 1. Clone the Repository

```bash
git clone https://github.com/pratikbhowal63-bot/NITA_Campus.git
```

### 2. Open in Android Studio

Open the project using the latest version of Android Studio.

### 3. Add Firebase Configuration

Place your Firebase configuration file in:

```text
app/google-services.json
```

### 4. Sync Gradle

Allow Android Studio to download all dependencies.

### 5. Build & Run

Run the application on an Android device or emulator.

---

## 👨‍💻 Developer

**Pratik Bhowal**  
B.Tech, Computer Science & Engineering  
National Institute of Technology Agartala

---

## ⭐ Support

If you found this project useful, consider giving it a star on GitHub.
