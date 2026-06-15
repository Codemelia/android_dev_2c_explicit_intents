# Android Workshop: Explicit Intents

This project demonstrates how to navigate between Activities and pass data using **Explicit Intents** in Android.

## Key Features
- **Data Exchange:** Uses `registerForActivityResult` to launch an activity and receive data back (getting a quote from `WriteActivity`).
- **Intent Extras:** Passes data from `MainActivity` to `ShowActivity` using `intent.putExtra()`.
- **Activity Lifecycle:** Manages result codes (`RESULT_OK` vs `RESULT_CANCELED`) to handle user actions properly.
- **Custom Styling:** Implements a consistent UI using `styles.xml` and `AppCompat` themes.

## Activities
1. **MainActivity:** The entry point with buttons to "Write" or "Show" a quote.
2. **WriteActivity:** Allows the user to enter a quote and returns it to the main screen.
3. **ShowActivity:** Displays the current quote stored in the application.

## The Back Button & Result Codes
- **Default Behavior:** Pressing the system Back button or the Up button in the toolbar finishes the current activity and returns `RESULT_CANCELED` by default.
- **Handling Interruption:** If an activity is launched via `ActivityResultLauncher`, your callback must distinguish between `RESULT_OK` (user clicked 'Save' or 'OK') and `RESULT_CANCELED` (user backed out) to avoid showing error messages or processing empty data incorrectly.
- **Avoid Overlap:** Only use `launcher.launch()` when you actually expect a result back. For simple one-way navigation (like moving to a "Show" screen), use `startActivity()` so the launcher callback isn't triggered when the user returns.

## Workshop Tips & Lessons Learned

### Kotlin Syntax & Logic
- **The Colon Matters:** In Kotlin property declarations, a colon is required before the type: `var launcher: ActivityResultLauncher<Intent>? = null`. Omitting it causes the "Property getter or setter expected" error.
- **Getting Text vs. Getting Objects:** Always use `editText.text.toString()` to get user input. Calling `.toString()` directly on the `EditText` object returns its memory address (e.g., `androidx.appcompat...`), not the text inside.
- **Null vs. Empty:** When retrieving data, check `isNullOrEmpty()`. A variable can be non-null but still empty (`""`), which might lead to "empty" screens if not handled.

### UI & Styling
- **Units:** Always use `dp` (Density-independent Pixels) instead of `px`. This ensures padding and margins look consistent on screens with different pixel densities.
- **ConstraintLayout Percentages:** Attributes like `layout_constraintHeight_percent` require a float between `0.0` and `1.0` (e.g., `0.5` for 50%).
- **Style Item Names:** In `styles.xml`, do **not** include the `app:` or `android:` prefix in the `name` attribute of an `<item>`. The system handles the namespace resolution.
- **Material vs. AppCompat:** Material 3 themes automatically round button corners. To get square buttons in `AppCompat` without using Material Components, you must create a custom `<shape>` drawable and apply it to `android:background`.
