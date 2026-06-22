<div align="center">
  <img src="Screenshots/banner.jpeg" width="100%" alt="NITA Campus Banner"/>
  
  # 🎓 NITA Campus
  
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  [![Android](https://img.shields.io/badge/Android-API_24+-34A853?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
  [![Firebase](https://img.shields.io/badge/Firebase-Realtime_DB-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com/)
  [![Architecture](https://img.shields.io/badge/Architecture-MVVM-purple?style=for-the-badge)](https://developer.android.com/topic/architecture)
  [![Gemini AI](https://img.shields.io/badge/AI-Gemini_API-4285F4?style=for-the-badge&logo=google&logoColor=white)](https://ai.google.dev/)
  [![Tests](https://img.shields.io/badge/Tests-Passing-success?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/)
  
  <p align="center">
    <b>A Modern Android Application for NIT Agartala Students</b><br>
    <i>Built with Kotlin • MVVM • Firebase • Gemini AI</i>
  </p>
</div>

---

## 📋 Table of Contents

- [🎯 Problem Statement](#-problem-statement)
- [💡 Solution Overview](#-solution-overview)
- [✨ Key Features](#-key-features)
- [🏗️ Technical Architecture](#️-technical-architecture)
- [🛠️ Tech Stack & Decisions](#️-tech-stack--decisions)
- [📸 Screenshots](#-screenshots)
- [💻 Installation & Setup](#-installation--setup)
- [🧪 Testing Strategy](#-testing-strategy)
- [🔒 Security Implementation](#-security-implementation)
- [⚡ Performance Optimizations](#-performance-optimizations)
- [📊 Skills Demonstrated](#-skills-demonstrated)
- [🚧 Challenges & Learnings](#-challenges--learnings)
- [🔮 Future Roadmap](#-future-roadmap)
- [🤝 Contributing](#-contributing)
- [👨‍💻 Developer](#-developer)



---

## 🎯 Problem Statement

Students at NIT Agartala faced **fragmented access** to academic resources across multiple platforms—department websites, shared drives, and physical notice boards. There was **no single, mobile-first solution** where students could:

- Access subject-wise study materials and previous year questions
- Get instant academic assistance
- Stay updated with campus information
- Navigate department-specific resources

**NITA Campus** solves this by consolidating all academic and campus resources into one intelligent, AI-powered Android application.

---

## 💡 Solution Overview

### What Makes This Different?

| Traditional Approach | NITA Campus |
|---------------------|-------------|
| Multiple websites & platforms | Single unified mobile app |
| Manual resource discovery | Categorized, searchable resources |
| No instant academic help | Integrated Gemini AI Assistant |
| Department info scattered | Centralized department hub |
| No offline access | Persistent login & session management |

### Impact
- **8 academic departments** covered with dedicated resources
- **AI-powered assistance** for instant academic queries
- **Zero-cost backend** using Firebase's free tier
- **Clean architecture** enabling easy feature additions

---

## ✨ Key Features

### 🔐 Robust Authentication System
| Feature | Implementation |
|---------|---------------|
| Username-based Registration | Firebase Realtime Database |
| Input Validation | Custom validators with real-time feedback |
| Duplicate Prevention | Server-side checks before account creation |
| Persistent Login | SharedPreferences with encrypted tokens |
| Session Management | Automatic session expiry handling |

### 🤖 AI-Powered Assistant
- Integrated **Google Gemini AI API** for academic guidance
- Context-aware responses for NITA-specific queries
- Subject matter assistance across engineering domains
- Real-time conversational interface

### 🏛️ Department Information Portal
- **9 departments** with detailed information
- Faculty listings and contact information
- Department-specific announcements
- Scholarship and academic updates

### 🛡️ Security First Approach
- Firebase Security Rules implementation
- Client-side input sanitization
- Password strength enforcement
- Network error graceful handling
- Crash prevention mechanisms

---

## 🏗️ Technical Architecture

### MVVM Architecture with Repository Pattern

```text
┌─────────────────────────────────────────────────────────────┐
│ PRESENTATION LAYER                                          │
│       ┌──────────┐   ┌──────────────┐   ┌─────────────┐     │
│       │ Activity │   │ Fragment     │   │ Adapter     │     │
│       └────┬─────┘   └──────┬───────┘   └───────┬─────┘     │
│            └─-──────────────┼───────────────────┘           │
│                             │                               │
│                   ViewBinding + LiveData                    │
├─────────────────────────────┼───────────────────────────────┤
│ DOMAIN LAYER                │                               │
│                  ┌──────────┴──────────┐                    │
│                  │      ViewModel      │                    │
│                  └──────────┬──────────┘                    │
│                             │                               │
├───────────────────────--─---┼───────────────────────────────┤
│ DATA LAYER                  │                               │
│                  ┌──────────┴──────────┐                    │
│                  │     Repository      │                    │
│                  └──────────┬──────────┘                    │
│                             │                               │
│ ┌───────────-────────┐  ┌──────────────┐  ┌───────────────┐ │
│ │ Firebase Database  │  │ SharedPref   │  │ Gemini AI API │ │
│ │      (Remote)      │  │   (Local)    │  │   (Remote)    │ │
│ └────────────────────┘  └──────────────┘  └───────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### Data Flow
1. **UI Layer** observes `LiveData` from ViewModel
2. **ViewModel** exposes data and handles business logic
3. **Repository** abstracts data sources (Firebase, SharedPreferences, Gemini API)
4. **Firebase Realtime Database** serves as the primary backend
5. **SharedPreferences** manages local session persistence

### Design Patterns Used
- ✅ **Repository Pattern** - Single source of truth
- ✅ **Observer Pattern** - LiveData for reactive UI
- ✅ **Singleton Pattern** - Firebase instances
- ✅ **Factory Pattern** - ViewModel creation

---

## 🛠️ Tech Stack & Decisions

| Technology | Purpose | Why This? |
|-----------|---------|-----------|
| **Kotlin** | Primary Language | Modern, null-safe, officially recommended by Google |
| **MVVM Architecture** | Design Pattern | Separation of concerns, testable, lifecycle-aware |
| **Firebase Realtime DB** | Backend | Real-time sync, offline support, free tier, no server management |
| **Gemini AI API** | AI Assistant | Latest Google AI, free tier available, context-aware responses |
| **SharedPreferences** | Local Storage | Lightweight session management, no database overhead |
| **LiveData** | Reactive Data | Lifecycle-aware, prevents memory leaks |
| **View Binding** | UI References | Type-safe, null-safe, compile-time verification |
| **Material Design 3** | UI Components | Modern, consistent, accessible |
| **RecyclerView** | List Rendering | Efficient memory usage for large datasets |
| **JUnit 5** | Unit Testing | Industry standard, extensive assertion library |

### Why Not SQLite/Room?
Firebase Realtime Database was chosen for its **real-time synchronization** capabilities. Academic resources can be updated by administrators and instantly reflected to all users without app updates.

### Why Not Retrofit/Volley?
Firebase SDK handles networking internally with built-in offline persistence, eliminating the need for additional HTTP libraries.

---

## 📸 Screenshots

<details open>
<summary><b>🔐 Authentication Flow</b></summary>
<br>
<p align="center">
  <img src="Screenshots/Login.png" width="250" alt="Login Screen"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="Screenshots/profile.png" width="250" alt="Profile Screen"/>
</p>
<p align="center"><i>Secure login with validation and persistent sessions</i></p>
</details>

<details open>
<summary><b>📊 Main Interface</b></summary>
<br>
<p align="center">
  <img src="Screenshots/Main.png" width="250" alt="Main Screen"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="Screenshots/main2.png" width="250" alt="Main Screen 2"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="Screenshots/dashboard.png" width="250" alt="Dashboard"/>
</p>
<p align="center"><i>Intuitive navigation with Navigation Drawer support</i></p>
</details>

<details open>
<summary><b>🤖 AI Assistant</b></summary>
<br>
<p align="center">
  <img src="Screenshots/AI.png" width="250" alt="AI Assistant"/>
</p>
<p align="center"><i>Gemini-powered academic assistant for instant help</i></p>
</details>

---

## 💻 Installation & Setup

### Prerequisites
- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK 17** or higher
- **Android SDK** API 24+
- **Gradle** 8.0+
- A **Firebase Project** with Realtime Database enabled
- **Gemini AI API Key** from Google AI Studio

### Step-by-Step Setup

```bash
# 1. Clone the repository
git clone https://github.com/pratikbhowal63-bot/NITA_Campus.git
cd NITA_Campus

# 2. Open in Android Studio
# File → Open → Select the project folder

# 3. Configure Firebase
# - Go to Firebase Console (https://console.firebase.google.com/)
# - Create a new project or use existing
# - Add Android app with package name: com.pratikbhowal.nitacampus
# - Download google-services.json
```
```text
app/
├── google-services.json
└── src/
    └── main/
        └── res/
            └── values/
```

## 🧪 Testing Strategy

### Unit Tests (JUnit)

| Test Category | Tests | Status |
|--------------|--------|--------|
| Phone Validation | Valid/invalid/edge cases | ✅ Passing |
| Username Validation | Format rules, length checks | ✅ Passing |
| Password Validation | Strength requirements, special characters | ✅ Passing |

### Running Tests

```bash
# Command line
./gradlew test

# Android Studio
# Right-click test directory → Run 'Tests' in 'com.pratikbhowal...'
```

### Test Coverage Goals

- Input validation (100% covered)
- ViewModel business logic (In Progress)
- Repository data operations (Planned)
- UI/Espresso tests (Planned)

## 🔒 Security Implementation

### Firebase Security Rules

```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "$uid === auth.uid",
        ".write": "$uid === auth.uid",
        "username": {
          ".validate": "newData.isString() && newData.val().length >= 3"
        }
      }
    },
    "resources": {
      ".read": "auth != null",
      ".write": "auth != null"
    }
  }
}
```

### Security Layers

1. **Firebase Auth Rules** - Server-side data protection
2. **Input Validation** - Client-side sanitization
3. **Password Policies** - Minimum 8 chars, special characters required
4. **Duplicate Prevention** - Unique username enforcement
5. **Network Security** - HTTPS enforced via Firebase SDK

---

## ⚡ Performance Optimizations

| Optimization | Implementation |
|-------------|---------------|
| **RecyclerView ViewHolder Pattern** | Efficient list rendering, reduced GC pressure |
| **ViewBinding** | No `findViewById` overhead, compile-time safety |
| **Lazy Loading** | Resources loaded on-demand, not all at startup |
| **Image Optimization** | Compressed assets, appropriate resolutions |
| **ProGuard/R8** | Code shrinking enabled for release builds |
| **Firebase Caching** | Offline data persistence (10MB disk cache) |

## 📊 Skills Demonstrated

### Technical Skills

| Category | Skills |
|-----------|---------|
| **Language** | Kotlin (Coroutines, Lambdas, Extension Functions) |
| **Architecture** | MVVM, Repository Pattern, Clean Architecture |
| **Android** | Activities, Fragments, Navigation Drawer, RecyclerView, ViewBinding |
| **Backend** | Firebase Realtime Database, Security Rules |
| **AI/ML** | Gemini AI API Integration, Prompt Engineering |
| **Testing** | JUnit, Unit Testing, Validation Testing |
| **UI/UX** | Material Design 3, Responsive Layout, Intuitive Navigation |
| **Tools** | Git, Android Studio, Gradle, Firebase Console |

### Soft Skills

- **Problem Identification** - Found real campus pain points
- **User-Centric Design** - Built for actual student needs
- **Independent Execution** - Complete project from idea to deployment
- **Documentation** - Comprehensive code comments and README

---

## 🚧 Challenges & Learnings

### Major Challenges Faced

### 1. Firebase Realtime Database Structure

**Challenge:** Designing a flat NoSQL structure for hierarchical academic data

**Solution:** Implemented denormalized data structure with department-based keys

**Learning:** NoSQL requires different thinking than SQL; query patterns drive structure

---

### 2. Persistent Login Security

**Challenge:** Maintaining user sessions without compromising security

**Solution:** Used SharedPreferences with encryption and session expiry checks

**Learning:** Convenience vs. security is always a tradeoff; document your decisions

---

### 3. Gemini AI Integration

**Challenge:** Making AI responses contextually relevant to NITA-specific queries

**Solution:** Crafted detailed system prompts with NITA context

**Learning:** Prompt engineering is crucial for domain-specific AI applications

---

### 4. Input Validation Edge Cases

**Challenge:** Handling all edge cases in registration forms

**Solution:** Wrote comprehensive unit tests covering 15+ validation scenarios

**Learning:** Unit tests catch bugs before users do; invest time in testing

## 🔮 Future Roadmap

### Short-term (Next 3 months)

- Push Notifications using Firebase Cloud Messaging (FCM)
- Dark Mode / Dynamic Theming
- Cloud Firestore Migration for better querying
- Enhanced AI with conversation history

### Mid-term (6 months)

- Offline-First Architecture with Room Database
- Attendance Tracking Module
- Timetable Management System
- Faculty-Student Communication Portal
- In-app PDF Viewer for Study Materials

### Long-term (1 year)

- Placement Preparation Module with Company-Specific Resources
- Analytics Dashboard for User Engagement
- Admin Panel for Content Management
- Multi-language Support (Hindi, Bengali, English)
- Google Play Store Publication

---

## 🤝 Contributing

Contributions are welcome! Here's how:

1. Fork the repository
2. Create a feature branch

```bash
git checkout -b feature/AmazingFeature
```

3. Commit changes

```bash
git commit -m "Add AmazingFeature"
```

4. Push to branch

```bash
git push origin feature/AmazingFeature
```

5. Open a Pull Request

### Code Guidelines

- Follow MVVM architecture strictly
- Write unit tests for new features
- Use meaningful commit messages
- Update documentation for significant changes

---

## 📄 License

This project is developed for academic purposes at NIT Agartala.

---

## 👨‍💻 Developer

<div align="center">

### Pratik Bhowal

B.Tech, Computer Science & Engineering  
National Institute of Technology Agartala

<br>

<a href="https://github.com/pratikbhowal63-bot">
  <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
</a>

</div>

---

# ⭐ Support

If this project helped you understand Android development, MVVM architecture, or Firebase integration, please consider:

- ⭐ Starring the repository
- 🔄 Sharing with fellow developers
- 📝 Providing feedback for improvements

<div align="center">

### Made with ❤️ for NIT Agartala

</div>
