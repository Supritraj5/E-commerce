Shopping Cart Application
This is a Shopping Cart Application built using the MVVM architecture pattern in Android. The app features product listing, the ability to add products to a cart, and cart management (editing quantities and removing items). It uses Room Database for local storage and Retrofit for API calls. The app is fully implemented in Jetpack Compose.

Features
Product Listing: Displays a list of products fetched from a remote API.
Add to Cart: Add products to the cart from the product listing screen.
Cart Management: Edit the quantity of items or remove items from the cart.
Offline Persistence: Cart items are stored in the local Room database.
Architecture
The project follows MVVM Architecture with separation of concerns:

UI Layer:
Built using Jetpack Compose.
Organized into views for product listing and cart management.
ViewModel Layer:
Manages UI state.
Fetches data from repositories.
Interacts with the local database and remote API.
Repository Layer:
Handles data operations.
Serves as a single source of truth for data by combining Room and Retrofit.
Data Layer:
Local Storage: Room Database for storing cart items.
Remote Data: Retrofit for API communication.



Key Components
Database
Entities:
CartEntity: Represents items in the cart stored locally.
ProductEntity: Represents the product data fetched from the API.
DAO:
CartDAO: For managing cart data in the Room database.
ProductDAO: For managing product data (future use).
Repositories
ProductRepository:
Fetches product data from the API.
CartRepository:
Manages cart operations (add, update, and retrieve cart items).
ViewModels
HomeViewModel: Manages the state for the home screen.
ProductViewModel: Manages product listing and cart actions.
CartViewModel: Handles cart-specific operations like updating quantities or removing items.
UI Views
Home:
Home.kt: Composable for the home screen.
ProductListing.kt: Displays a list of products.
ProductItem.kt: Represents a single product item with an "Add to Cart" button.
Cart:
Cart.kt: Composable for the cart screen.
CartListing.kt: Displays cart items.
CartItem.kt: Represents a single item in the cart with edit and remove options.
Dependencies
Jetpack Compose: For UI.
Room: For local database.
Retrofit: For API calls.
Hilt: For dependency injection.







How to Run
Clone the repository:

bash
Copy code
git clone https://github.com/your-repo/shopping-cart.git
cd shopping-cart
Open the project in Android Studio.

Add your API endpoint in ApiService.kt.

Run the application.


