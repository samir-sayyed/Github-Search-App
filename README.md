# GitHub Profile Search App

## Overview

This Android application allows users to search for GitHub profiles and view repositories associated with those profiles. The app is developed using Kotlin and follows best practices for network calls, data modeling, caching, and UI implementation.

## Features

1. **Fetch Repositories:**
    - Utilizes the GitHub API to fetch repositories for a given GitHub user.
    - Network calls are handled using Retrofit and OkHttp.

2. **Data Model:**
    - Defines a data class `GHRepo` with the following properties:
        - `id` (mapped to id)
        - `name` (mapped to name)
        - `repoURL` (mapped to html_url)

3. **NetworkService:**
    - Implements a `NetworkService` class for API requests.
    - Handles errors and success responses effectively.

4. **Caching:**
    - Utilizes a `Cacher` class to cache network responses.
    - Implements caching logic using Room database for local data storage.

5. **UI Implementation:**
    - Displays a list of repositories using RecyclerView.
    - Implements the ViewHolder pattern with a label for repository details.
    - Allows clicking on an item to open the repository URL in a WebView.
    - Implements local search functionality based on id or name.
    - Ensures proper cell reuse in RecyclerView.

6. **Additional Considerations:**
    - Follows good coding practices, clear naming conventions, and modularization.
    - Ensures graceful handling of network failures.
    - Prioritizes app performance in terms of RAM and CPU utilization.
    - Completeness of the app is a key focus.

## Technologies
1. **Kotlin:**
    - Developed using Kotlin.
    - Follows best practices for Kotlin development.
    - Uses coroutines for asynchronous programming.
    - Uses Kotlin extensions for concise code.
    - Uses Kotlin Android Extensions for view binding.
    - Uses Kotlin Gradle DSL for build management.
   
2. **Android Jetpack:**
    - Uses Android Jetpack components.
    - Follows best practices for Android development.
    - Uses Room for local data storage.
    - Uses LiveData for data observation.
    - Uses ViewModel for UI data handling.
    - Uses Data Binding for UI implementation.
    - Uses Navigation for fragment navigation.
    - Uses RecyclerView for displaying a list of repositories.
    - Uses WebView for displaying repository details.
    - Uses SearchView for local search functionality.
    - Uses Retrofit and OkHttp for network calls.
    - Uses Glide for image loading.
    - Uses Gradle for build management.
    - Uses Git for version control.
3. **GitHub API:**
    - Uses the GitHub API for fetching repositories.

## How to Use

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or device.


## Dependencies

- Retrofit
- OkHttp
- Room 

## Contributing

1. Fork the repository.
2. Create a new branch for your feature.
3. Make your changes and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Thanks to the GitHub API for providing the data used in this app.

