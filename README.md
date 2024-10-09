Pokedex App

Welcome to the Pokedex App, an Android application that allows users to explore a comprehensive list of Pokémon. View detailed information about each Pokémon, including abilities, stats, and evolution chains. Experience smooth animations and transitions as you navigate through the app.

Table of Contents

	•	Features
	•	Screenshots
	•	Architecture
	•	Tech Stack
	•	Setup and Installation
	•	Usage
	•	Future Enhancements
	•	Contributing
	•	License
	•	Acknowledgments

Features

	•	Pokémon List: Browse a list of all available Pokémon fetched from the PokeAPI.
	•	Pokémon Details: View detailed information about each Pokémon, including:
	•	Abilities: Learn about the unique abilities of each Pokémon.
	•	Stats: Check out the base stats like HP, Attack, Defense, etc.
	•	Evolutions: Explore the evolution chain of the Pokémon.
	•	Favorites: Mark your favorite Pokémon and access them easily.
	•	Offline Access: Data is cached locally using Room Database for offline access.
	•	Smooth Animations: Enjoy shared transitions and animations when navigating between screens.
	•	Image Loading: Images are efficiently loaded using the Coil library.

ScreenShots

<img src="https://github.com/user-attachments/assets/22fdae66-83d7-46be-95e8-ead63cf68686" width="300" />
<img src="https://github.com/user-attachments/assets/468092c8-51f1-4221-b185-8940e750b696" width="300" />
<img src="https://github.com/user-attachments/assets/efc53569-77fc-4900-86eb-e7b5153775b0" width="300" />
<img src="https://github.com/user-attachments/assets/57144e08-080b-42ec-a34a-52a2227f9027" width="300" />
<img src="https://github.com/user-attachments/assets/81d8c6ee-c4b8-4c04-b2b4-a9f920f92f66" width="300" />

Architecture

The app is built following Clean Architecture principles, ensuring a separation of concerns and promoting scalability and testability.

	•	Presentation Layer: Contains UI components built with Jetpack Compose.
	•	Domain Layer: Includes business logic and use cases.
	•	Data Layer: Handles data operations, including network requests and local database interactions.

Tech Stack

	•	Kotlin: Programming language used for Android development.
	•	Jetpack Compose: Modern toolkit for building native UI.
	•	Ktor: HTTP client used for making network calls to the PokeAPI.
	•	Hilt: Dependency Injection framework for managing dependencies.
	•	Room Database: Provides local data persistence and caching.
	•	Coil: Image loading library optimized for Android.
	•	Shared Transitions: Implements smooth animations between composable destinations.
	•	Coroutines: For asynchronous programming.
	•	StateFlow: Manages and observes state changes.

 Usage

	•	Browse Pokémon
	•	Launch the app to see a list of Pokémon.
	•	Scroll through the list to find your favorite Pokémon.
	•	View Pokémon Details
	•	Tap on a Pokémon to view detailed information.
	•	Navigate through tabs to see About, Base Stats, and Evolution.
	•	Favorite a Pokémon
	•	Tap the heart icon to add a Pokémon to your favorites.
	•	Access your favorite Pokémon from the favorites section.
	•	Offline Access
	•	Previously viewed Pokémon details are available offline.
	•	Favorites are stored locally for quick access.

Future Enhancements

	•	Search Functionality: Implement a search bar to find Pokémon by name.
	•	Filtering and Sorting: Add options to filter Pokémon by type and sort by stats.
	•	User Accounts: Allow users to create accounts and sync favorites across devices.
