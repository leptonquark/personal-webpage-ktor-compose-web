package ui

enum class WindowClass {
    Compact, Medium, Expanded;

    companion object {
        @Suppress("MagicNumber")
        operator fun invoke() = when (getWindowWidth()) {
            in 0..<600 -> Compact
            in 600..840 -> Medium
            else -> Expanded
        }
    }
}

